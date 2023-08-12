package com.example.weatherapp.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.databinding.ItemWeatherInfoBinding
import com.example.weatherapp.domain.entity.WeatherDayInfo
import com.example.weatherapp.utils.DateUtils

class WeatherForecastListAdapter : RecyclerView.Adapter<WeatherForecastListAdapter.WeatherForecastListViewHolder>() {

    var weatherForecastList: List<WeatherDayInfo> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherForecastListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemWeatherInfoBinding.inflate(inflater, parent, false)

        return WeatherForecastListViewHolder(binding)
    }

    override fun getItemCount(): Int = weatherForecastList.size

    override fun onBindViewHolder(holder: WeatherForecastListViewHolder, position: Int) {
        val weatherDayInfo = weatherForecastList[position]
        holder.bind(weatherDayInfo)
    }

    inner class WeatherForecastListViewHolder(
        private val binding: ItemWeatherInfoBinding
    ): RecyclerView.ViewHolder(binding.root) {

        private val glide = Glide.with(itemView)

        fun bind(weatherDayInfo: WeatherDayInfo) {
            with(binding) {
                dayOfWeek.text = DateUtils.formatDateToWeekday(weatherDayInfo.date)
                dayOfMonth.text = DateUtils.formatDateToDayOfMonth(weatherDayInfo.date)
                description.text = weatherDayInfo.description
                temperature.text = "${weatherDayInfo.temperature} $TEMPERATURE_FORMAT"
                humidity.text = "${weatherDayInfo.humidity} $HUMIDITY_FORMAT"
                windSpeed.text = "${weatherDayInfo.maxWindSpeed} $WIND_SPEED_FORMAT"
                glide.load(HTTPS + weatherDayInfo.iconUrl).into(icon)
            }
        }
    }

    companion object {
        const val HTTPS = "https:"
        const val TEMPERATURE_FORMAT = "°C"
        const val HUMIDITY_FORMAT = "%"
        const val WIND_SPEED_FORMAT = "kph"
    }
}