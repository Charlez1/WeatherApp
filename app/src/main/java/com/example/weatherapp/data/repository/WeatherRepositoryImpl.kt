package com.example.weatherapp.data.repository

import com.example.weatherapp.utils.BackendException
import com.example.weatherapp.utils.ConnectionException
import com.example.weatherapp.data.mapper.WeatherDataMapper
import com.example.weatherapp.data.network.ApiConstants
import com.example.weatherapp.data.network.WeatherApiService
import com.example.weatherapp.domain.entity.WeatherDayInfo
import com.example.weatherapp.domain.repository.WeatherRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepositoryImpl @Inject constructor(
    private val weatherApiService: WeatherApiService,
    private val mapper: WeatherDataMapper
): WeatherRepository {

    override suspend fun getWeatherInfo(location: String, days: Int): List<WeatherDayInfo> {
        return try {
            val response = weatherApiService.getWeatherForecast(
                location = location,
                days = days,
                apiKey = ApiConstants.API_KEY
            )
            mapper.mapWeatherForecastResponseToWeatherDaysInfo(response)
        } catch (e: IOException) {
            // Handling I/O errors (e.g., no internet connection)
            throw ConnectionException(e)
        } catch (e: HttpException) {
            // Handling HTTP error (error codes, response data, etc.)
            throw BackendException(e)
        } catch (e: Exception) {
            // Handling other exceptions
            throw e
        }
    }

}