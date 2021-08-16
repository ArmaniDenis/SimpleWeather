package com.example.simpleweather.data.provider


import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.widget.Toast
import com.example.simpleweather.R
import com.example.simpleweather.data.db.CurrentWeatherResponse
import com.example.simpleweather.data.network.ConnectivityInterceptor
import com.example.simpleweather.data.network.OpenWeatherApiService
import com.example.simpleweather.data.network.ProvideHttpOkClientImpl
import com.example.simpleweather.data.provider.location.GooglePlayServicesAvailable
import com.example.simpleweather.data.provider.location.LocationProviderImpl
import com.example.simpleweather.internal.Constants
import com.example.simpleweather.internal.NoConnectivityException
import com.example.simpleweather.ui.fragments.WeatherViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CurrentWeatherImpl(
    private var apiService: OpenWeatherApiService,
    var realm: Realm,
    var weatherModel: WeatherViewModel,
    var locationProviderImpl: LocationProviderImpl,
    var googlePlayServicesAvailable: GooglePlayServicesAvailable
) : CurrentWeather {

    private var disposable: Disposable? = null

    private fun loadWeather(lon: Double, lat: Double): Observable<CurrentWeatherResponse> {
        return apiService.getCurrentWeather(longitude = lon, latitude = lat)
    }

    @SuppressLint("CheckResult")
    fun initWeather(
        context: Context
    ) {
        checkPermissions(
            context
        )
    }

    @SuppressLint("CheckResult")
    fun getWeather(
        context: Context,
        latitude: Double,
        longitude: Double

    ) {
        loadWeather(longitude, latitude)
            .flatMap { items ->
                Realm.getDefaultInstance().executeTransaction {
                    it.deleteAll()
                    it.insertOrUpdate(items)
                }
                Observable.just(items)
            }
            .doOnError {
                it.printStackTrace()
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                val data = realm.where(CurrentWeatherResponse::class.java).findAll()
                weatherModel._weather.value = realm.copyFromRealm(data).first()
            }, { error ->
                if (error is NoConnectivityException) {
                    Toast.makeText(context, R.string.no_connect_text, Toast.LENGTH_LONG).show()
                } else {
                    error.printStackTrace()
                }
            })
    }

    private fun checkPermissions(
        context: Context
    ) {
        googlePlayServicesAvailable.isAvailableShowDialog(context as Activity).let {
            when (it) {
                is GooglePlayServicesAvailable.Result.True -> getLocation(
                    context
                )
                is GooglePlayServicesAvailable.Result.False -> context.finish()
                is GooglePlayServicesAvailable.Result.FalseDialog -> {
                    it.dialog.apply { setOnDismissListener { context.finish() } }.show()
                }
            }
        }
    }

    private fun getLocation(
        context: Context
    ) {
        disposable = locationProviderImpl
            .getLocation()
            .doOnError {
                it.printStackTrace()
            }
            .subscribe({
                getWeather(context, it.latitude, it.longitude)
            }, {
                it is SecurityException
                Toast.makeText(context, context.getString(R.string.need_geoloc), Toast.LENGTH_SHORT)
                    .show()
                it.printStackTrace()
            })


    }

    //unsafe-safe Client for testing on virtual device
    companion object {
        operator fun invoke(connectivityInterceptor: ConnectivityInterceptor): OpenWeatherApiService {
            return Retrofit.Builder()
                .client(ProvideHttpOkClientImpl(connectivityInterceptor).getSafeOkHttpClient())
                .baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(OpenWeatherApiService::class.java)
        }
    }
}
