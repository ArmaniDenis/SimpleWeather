package com.example.simpleweather.ui.fragments

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.simpleweather.R
import com.example.simpleweather.databinding.WeatherFragmentBinding
import com.example.simpleweather.data.db.CurrentWeatherResponse
import com.example.simpleweather.internal.GlideApp
import io.realm.Realm
import kotlin.math.roundToInt

class WeatherFragment : Fragment() {

    companion object {
        fun newInstance() = WeatherFragment()
    }

    private val weatherModel: WeatherViewModel by activityViewModels()
    private lateinit var viewModel: WeatherViewModel
    private lateinit var binding: WeatherFragmentBinding
    lateinit var realm: Realm
    private val adapter = WeatherAttributeAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = WeatherFragmentBinding.inflate(inflater)
        return binding.root
    }

    @SuppressLint("SetTextI18n", "ResourceAsColor", "CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)

        weatherModel._weather.observe(viewLifecycleOwner, {
            adapter.clearWeatherAttr()
            GlideApp.with(this)
                .load("https://openweathermap.org/img/w/${it.weatherResponse?.single()?.icon}.png")
                .override(150, 150)
                .into(binding.weatherIcon)
            binding.apply {
                weatherAttrRcView.suppressLayout(true)
                windDeg.rotation = it.windResponse?.deg?.toFloat()!! + 180 % 360
                clouds.text = it.weatherResponse?.single()?.description
                temp.text = it.mainResponse?.temp?.roundToInt().toString()
                init(it)
            }
        })

    }

    private fun init(currWeathResp: CurrentWeatherResponse) {
        binding.apply {
            weatherAttrRcView.layoutManager = GridLayoutManager(this@WeatherFragment.context, 2)
            weatherAttrRcView.adapter = adapter
            adapter.addWeatherAttr(
                WeatherAttribute(
                    R.string.feels_like,
                    currWeathResp.mainResponse?.feelsLike?.roundToInt().toString()
                            + resources.getString(R.string.metric_prefix)
                )
            )
            adapter.addWeatherAttr(
                WeatherAttribute(
                    R.string.wind_speed,
                    currWeathResp.windResponse?.speed?.toString()
                            + " " + getString(R.string.wind_metric_prefix)
                )
            )
            adapter.addWeatherAttr(
                WeatherAttribute(
                    R.string.pressure,
                    currWeathResp.mainResponse?.pressure?.toDouble()?.div(1.333)?.roundToInt().toString() + "mmHg"
                )
            )
            adapter.addWeatherAttr(
                WeatherAttribute(
                    R.string.humidity,
                    currWeathResp.mainResponse?.humidity?.toString() + "%"
                )
            )

        }
    }


    override fun onDestroy() {
        super.onDestroy()
    }

}