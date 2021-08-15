package com.example.simpleweather.data.provider

import com.example.simpleweather.data.network.ConnectivityInterceptor
import com.example.simpleweather.data.provider.location.GooglePlayServicesAvailable
import com.example.simpleweather.data.provider.location.LocationProviderImpl
import com.example.simpleweather.ui.fragments.WeatherViewModel
import io.realm.Realm


class CurrentWeatherProvider {
    fun provideCurrentWeather(connectivityInterceptor: ConnectivityInterceptor, realm: Realm,
                               weatherModel: WeatherViewModel,
                               locationProviderImpl: LocationProviderImpl,
                               googlePlayServicesAvailable: GooglePlayServicesAvailable
    ): CurrentWeatherImpl{
        return CurrentWeatherImpl(CurrentWeatherImpl.invoke(connectivityInterceptor), realm,
         weatherModel,
         locationProviderImpl,
         googlePlayServicesAvailable)
    }
}