package com.example.simpleweather.ui.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simpleweather.data.db.CurrentWeatherResponse

class WeatherViewModel : ViewModel() {
    val _weather: MutableLiveData<CurrentWeatherResponse> by lazy {
        MutableLiveData<CurrentWeatherResponse>()
    }
}