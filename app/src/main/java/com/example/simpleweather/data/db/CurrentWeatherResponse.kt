package com.example.simpleweather.data.db


import com.example.simpleweather.data.db.entity.*
import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class CurrentWeatherResponse : RealmObject() {

    @PrimaryKey
    @SerializedName("id")
    var id: Int = 0

    @SerializedName("base")
    var base: String? = null

    @SerializedName("clouds")
    var cloudsResponse: CloudsResponse? = null

    @SerializedName("cod")
    var cod: Int = 0

    @SerializedName("coord")
    var coordResponse: CoordResponse? = null

    @SerializedName("dt")
    var dt: Int = 0

    @SerializedName("main")
    var mainResponse: MainResponse? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("sys")
    var sysResponse: SysResponse? = null

    @SerializedName("timezone")
    var timezone: Int = 0

    @SerializedName("visibility")
    var visibility: Int = 0

    @SerializedName("weather")
    var weatherResponse: RealmList<WeatherResponse>? = RealmList()

    @SerializedName("wind")
    var windResponse: WindResponse? = null
}