package com.example.simpleweather.data.db.entity


import com.example.simpleweather.data.db.CurrentWeatherResponse
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.annotations.LinkingObjects

open class WindResponse : RealmObject () {
    @SerializedName("deg")
    var deg: Int = 0

    @SerializedName("gust")
    var gust: Double = 0.0

    @SerializedName("speed")
    var speed: Double = 0.0

    @LinkingObjects("windResponse")
    val owners: RealmResults<CurrentWeatherResponse>? = null
}