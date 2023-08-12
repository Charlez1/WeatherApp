package com.example.weatherapp.data.mapper

import com.example.weatherapp.domain.entity.WeatherDayInfo
import com.example.weatherapp.data.network.WeatherForecastResponse
import javax.inject.Inject

class WeatherDataMapper @Inject constructor() {

    fun mapWeatherForecastResponseToWeatherDaysInfo(weatherForecastResponse: WeatherForecastResponse): List<WeatherDayInfo> {
        val weatherDaysInfo = mutableListOf<WeatherDayInfo>()

        val forecastDays = weatherForecastResponse.forecast.forecastDays
        for (forecastDay in forecastDays) {
            val dayInfo = forecastDay.dayInfo
            val condition = dayInfo.weatherCondition

            val weatherDayInfo = WeatherDayInfo(
                date = forecastDay.forecastDate,
                description = condition.description,
                iconUrl = condition.iconUrl,
                temperature = dayInfo.averageTemperatureCelsius,
                maxWindSpeed = dayInfo.maxWindSpeedKph.toInt(),
                humidity = dayInfo.averageHumidity.toInt()
            )

            weatherDaysInfo.add(weatherDayInfo)
        }

        return weatherDaysInfo
    }
}