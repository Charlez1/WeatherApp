package com.example.weatherapp.data.network

import com.google.gson.annotations.SerializedName

data class WeatherForecastResponse(
    @SerializedName("location") val location: LocationInfo,
    @SerializedName("forecast") val forecast: ForecastInfo
)

data class LocationInfo(
    @SerializedName("name") val cityName: String
)

data class ForecastInfo(
    @SerializedName("forecastday") val forecastDays: List<ForecastDayInfo>
)

data class ForecastDayInfo(
    @SerializedName("date") val forecastDate: String,
    @SerializedName("day") val dayInfo: DailyInfo
)

data class DailyInfo(
    @SerializedName("avgtemp_c") val averageTemperatureCelsius: Double,
    @SerializedName("maxwind_kph") val maxWindSpeedKph: Double,
    @SerializedName("avghumidity") val averageHumidity: Double,
    @SerializedName("condition") val weatherCondition: WeatherCondition
)

data class WeatherCondition(
    @SerializedName("text") val description: String,
    @SerializedName("icon") val iconUrl: String
)