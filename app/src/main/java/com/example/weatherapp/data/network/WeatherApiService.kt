package com.example.weatherapp.data.network

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET("v1/forecast.json")
    suspend fun getWeatherForecast(
        @Query("q") location: String,
        @Query("days") days: Int,
        @Query("key") apiKey: String
    ): WeatherForecastResponse

}