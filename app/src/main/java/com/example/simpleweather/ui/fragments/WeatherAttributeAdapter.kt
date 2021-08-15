package com.example.simpleweather.ui.fragments

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.simpleweather.R

class WeatherAttributeAdapter : RecyclerView.Adapter<WeatherAttributeViewAdapter>() {
    var weatherAttrList = ArrayList<WeatherAttribute>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherAttributeViewAdapter {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.weather_attribute, parent, false)
        return WeatherAttributeViewAdapter(view)
    }

    override fun onBindViewHolder(holder: WeatherAttributeViewAdapter, position: Int) {
        holder.create(weatherAttrList[position])
    }

    override fun getItemCount(): Int {
        return weatherAttrList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addWeatherAttr(weatherAttr: WeatherAttribute) {
        weatherAttrList.add(weatherAttr)
        notifyDataSetChanged()
    }

    fun clearWeatherAttr() {
        weatherAttrList.clear()
    }

}