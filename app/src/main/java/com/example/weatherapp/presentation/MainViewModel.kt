package com.example.weatherapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.utils.BackendException
import com.example.weatherapp.utils.ConnectionException
import com.example.weatherapp.R
import com.example.weatherapp.domain.entity.WeatherDayInfo
import com.example.weatherapp.domain.usecases.GetWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase
): ViewModel() {

    private val _weatherForecastList = MutableLiveData<List<WeatherDayInfo>> ()
    val weatherForecastList: LiveData<List<WeatherDayInfo>> = _weatherForecastList

    private val _loadWeatherInProgress = MutableLiveData<Boolean> (false)
    val loadWeatherInProgress: LiveData<Boolean> = _loadWeatherInProgress

    private val _showErrorToast = MutableLiveData<Int>()
    val showErrorToast: LiveData<Int> = _showErrorToast

    fun getCurrentWeather(location: String) = viewModelScope.launch {
        setProgressVisibility(true)
        try {
            _weatherForecastList.value = getWeatherUseCase(location, DAYS)
        } catch (e: ConnectionException) {
            showErrorToast(R.string.connection_error)
        } catch (e: BackendException) {
            showErrorToast(R.string.backend_error)
        } catch (e: Exception) {
            showErrorToast(R.string.other_error)
        } finally {
            setProgressVisibility(false)
        }
    }

    private fun setProgressVisibility(isInProgress: Boolean) {
        _loadWeatherInProgress.value = isInProgress
    }

    private fun showErrorToast(errorMessageRes: Int) {
        _showErrorToast.value = errorMessageRes
    }


    companion object {
        const val DAYS = 5
    }

}