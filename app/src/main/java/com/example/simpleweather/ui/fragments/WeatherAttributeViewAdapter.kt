package com.example.simpleweather.ui.fragments


import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.simpleweather.databinding.WeatherAttributeBinding

class WeatherAttributeViewAdapter(item: View) : RecyclerView.ViewHolder(item) {
    private val binding = WeatherAttributeBinding.bind(item)
    fun create(weatherAttribute: WeatherAttribute) = with(binding) {
        weatherAttName.editableText.append(weatherAttribute.text)
        weatherInpLay.setHint(weatherAttribute.hint)
    }
}
