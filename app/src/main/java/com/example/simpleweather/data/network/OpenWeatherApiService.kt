package com.example.simpleweather.data.network

import com.example.simpleweather.data.db.CurrentWeatherResponse
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "521ae36ff604f77df1817e2e79168a81"
const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

interface OpenWeatherApiService {

    @GET("weather")
    fun getCurrentWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") measurementUnit: String = "metric",
        @Query("lang") languageCode: String = "ru",
        @Query("appid") apiKey: String = API_KEY
    ): Observable<CurrentWeatherResponse>
}