package com.example.simpleweather.data.network

import com.example.simpleweather.data.db.CurrentWeatherResponse
import com.example.simpleweather.internal.Constants
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query



interface OpenWeatherApiService {

    @GET("weather")
    fun getCurrentWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") measurementUnit: String = "metric",
        @Query("lang") languageCode: String = "ru",
        @Query("appid") apiKey: String = Constants.API_KEY
    ): Observable<CurrentWeatherResponse>
}