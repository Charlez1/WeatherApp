package com.example.weatherapp.domain.entity

data class WeatherDayInfo(
    val date: String,
    val description: String,
    val iconUrl: String,
    val temperature: Double,
    val maxWindSpeed: Int,
    val humidity: Int
)