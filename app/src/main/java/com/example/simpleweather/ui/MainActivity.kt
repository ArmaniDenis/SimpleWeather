package com.example.simpleweather.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.simpleweather.R
import com.example.simpleweather.data.db.CurrentWeatherResponse
import com.example.simpleweather.data.network.ConnectivityInterceptorImpl
import com.example.simpleweather.data.provider.CurrentWeatherProvider
import com.example.simpleweather.data.provider.location.GooglePlayServicesAvailable
import com.example.simpleweather.data.provider.location.LocationProviderImpl
import com.example.simpleweather.databinding.ActivityMainBinding
import com.example.simpleweather.ui.fragments.WeatherViewModel
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val weatherModel: WeatherViewModel by viewModels()
    lateinit var realm: Realm
    internal lateinit var googlePlayServicesAvailable: GooglePlayServicesAvailable
    private lateinit var locationProviderImpl: LocationProviderImpl
    private var disposable: Disposable? = null

    @SuppressLint("CheckResult", "ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        realm = Realm.getDefaultInstance()

        googlePlayServicesAvailable = GooglePlayServicesAvailable(this)


        weatherModel._weather.observe(this, {

            actionBar?.title = it.name
            supportActionBar?.title = it.name
        })
        locationProviderImpl = LocationProviderImpl(this)

        val data = realm.where(CurrentWeatherResponse::class.java).findAll()
        if (data.isNullOrEmpty()) {
            provideInitWeather()
        } else {
            weatherModel._weather.value = realm.copyFromRealm(data).first()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        locationProviderImpl.onActivityResult(requestCode, resultCode)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.refresh -> {
                disposable?.dispose()
                provideInitWeather()
                setTimer()
            }
        }
        return true
    }

    private fun provideInitWeather() {
        CurrentWeatherProvider().provideCurrentWeather(
            ConnectivityInterceptorImpl(this),
            realm, weatherModel, locationProviderImpl, googlePlayServicesAvailable
        ).initWeather(this)
    }

    private fun setTimer() {
        disposable = Observable.interval(10, TimeUnit.MINUTES)
            .timeInterval()
            .observeOn(Schedulers.io())
            .subscribe {
                provideInitWeather()
            }
    }


    override fun onResume() {
        super.onResume()
        setTimer()
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
        disposable?.dispose()
    }



}




