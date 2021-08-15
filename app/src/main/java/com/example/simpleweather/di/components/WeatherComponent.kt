package com.example.simpleweather.di.components

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDexApplication
import com.example.simpleweather.di.modules.WeatherModule
import com.example.simpleweather.ui.MainActivity
import dagger.Component
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Singleton


class WeatherComponent : MultiDexApplication() {
    //    lateinit var appComponent: AppComponent
//
//    @Component(modules = [WeatherModule::class])
//    interface AppComponent {
//        fun inject(activity: MainActivity)
//    }
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val config = RealmConfiguration.Builder().build()
        Realm.setDefaultConfiguration(config)
        //appComponent = DaggerWeatherComponent_AppComponent.builder().build()

    }

    /*val Context.appComponent: AppComponent
        get() = when (this) {
            is WeatherComponent -> appComponent
            else -> applicationContext.appComponent
        }*/

}