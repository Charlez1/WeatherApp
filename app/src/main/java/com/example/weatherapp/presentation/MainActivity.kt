package com.example.weatherapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()
    private lateinit var adapter: WeatherForecastListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(root) }

        setupViews()
        setupObservers()
        viewModel.getCurrentWeather(binding.cityEditText.text.toString())
    }

    private fun setupViews() {
        adapter = WeatherForecastListAdapter()
        binding.weatherRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.weatherRecyclerView.adapter = adapter

        binding.cityEditText.setOnEditorActionListener { _, actionId, _ -> onCityEditDone(actionId) }
    }

    private fun setupObservers() {
        viewModel.weatherForecastList.observe(this) { weatherForecast ->
            adapter.weatherForecastList = weatherForecast
        }

        viewModel.loadWeatherInProgress.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.showErrorToast.observe(this) { errorMessage ->
            showSnackBar(errorMessage)
        }
    }

    private fun onCityEditDone(actionId: Int): Boolean {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            val currentCityText = binding.cityEditText.text.toString()
            if (currentCityText.isNotEmpty()) {
                viewModel.getCurrentWeather(currentCityText)
            }
            return true
        }
        return false
    }

    private fun showSnackBar(message: Int) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }
}