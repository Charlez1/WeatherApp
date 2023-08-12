package com.example.weatherapp.domain.usecases

import com.example.weatherapp.domain.entity.WeatherDayInfo
import com.example.weatherapp.domain.repository.WeatherRepository
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository
) {

    suspend operator fun invoke(location: String, days: Int): List<WeatherDayInfo> {
        return repository.getWeatherInfo(location, days)
    }

}