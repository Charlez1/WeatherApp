package com.example.weatherapp.utils

import com.example.weatherapp.App
import com.example.weatherapp.R
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    private val apiDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    fun formatDateToWeekday(dateString: String): String {
        val date = apiDateFormat.parse(dateString) ?: return ""
        val calendar = Calendar.getInstance()
        val currentDate = calendar.time

        return if (isSameDay(date, currentDate)) {
            App.resourses.getString(R.string.today_text)
        } else {
            val weekdayFormat = SimpleDateFormat("EEE", Locale.ENGLISH)
            weekdayFormat.format(date).uppercase(Locale.getDefault())
        }
    }

    fun formatDateToDayOfMonth(dateString: String): String {
        val date = apiDateFormat.parse(dateString) ?: return ""
        val dayOfMonthFormat = SimpleDateFormat("dd", Locale.getDefault())
        return dayOfMonthFormat.format(date)
    }

    private fun isSameDay(date1: Date, date2: Date): Boolean {
        val calendar1 = Calendar.getInstance()
        calendar1.time = date1

        val calendar2 = Calendar.getInstance()
        calendar2.time = date2

        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR) &&
                calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH) &&
                calendar1.get(Calendar.DAY_OF_MONTH) == calendar2.get(Calendar.DAY_OF_MONTH)
    }

}