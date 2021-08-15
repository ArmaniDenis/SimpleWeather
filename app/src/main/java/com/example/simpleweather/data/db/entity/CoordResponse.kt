package com.example.simpleweather.data.db.entity


import com.example.simpleweather.data.db.CurrentWeatherResponse
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.annotations.LinkingObjects

open class CoordResponse : RealmObject() {
    @SerializedName("lat")
    var lat: Double = 0.0

    @SerializedName("lon")
    var lon: Double = 0.0

    @LinkingObjects("coordResponse")
    val owners: RealmResults<CurrentWeatherResponse>? = null
}