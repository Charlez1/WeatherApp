package com.example.weatherapp.domain.repository

import com.example.weatherapp.domain.entity.WeatherDayInfo

interface WeatherRepository {

    /**
     * Retrieves weather data for the given location and days.
     *
     * @throws ConnectionException When an I/O error occurs, such as no internet connection.
     * @throws ParseBackendResponseException When there's an error parsing the backend response.
     * @throws BackendException When an HTTP-related error occurs, including error codes.
     */
    suspend fun getWeatherInfo(location: String, days: Int) : List<WeatherDayInfo>

}