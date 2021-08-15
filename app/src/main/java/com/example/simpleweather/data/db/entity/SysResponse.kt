package com.example.simpleweather.data.db.entity


import com.example.simpleweather.data.db.CurrentWeatherResponse
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.annotations.LinkingObjects

open class SysResponse : RealmObject() {
    @SerializedName("country")
    var country: String? = null

    @SerializedName("sunrise")
    var sunrise: Int = 0

    @SerializedName("sunset")
    var sunset: Int = 0

    @LinkingObjects("sysResponse")
    val owners: RealmResults<CurrentWeatherResponse>? = null
}