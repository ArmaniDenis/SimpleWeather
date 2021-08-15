package com.example.simpleweather.data.db.entity


import com.example.simpleweather.data.db.CurrentWeatherResponse
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.annotations.LinkingObjects

open class WeatherResponse : RealmObject() {
    @SerializedName("description")
    var description: String? = null

    @SerializedName("icon")
    var icon: String? = null

    @SerializedName("id")
    var id: Int = 0

    @SerializedName("main")
    var main: String? = null

    @LinkingObjects("weatherResponse")
    val owners: RealmResults<CurrentWeatherResponse>?= null
}