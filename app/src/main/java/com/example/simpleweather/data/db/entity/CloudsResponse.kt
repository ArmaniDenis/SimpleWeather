package com.example.simpleweather.data.db.entity


import com.example.simpleweather.data.db.CurrentWeatherResponse
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.annotations.LinkingObjects

open class CloudsResponse : RealmObject() {
    @SerializedName("all")
    var all: Int = 0

    @LinkingObjects("cloudsResponse")
    val owners: RealmResults<CurrentWeatherResponse>? = null
}