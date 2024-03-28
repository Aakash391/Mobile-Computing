package com.example.weatherapp

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response

suspend fun fetchTemperature(date : String) : Response<WeatherData>{

    var response = RetrofitInstance.api.getWeatherData(
        location = "London,UK",
        date = date,
        apiKey = "QQU8YA2R6VVVWH4JVKFPKDB6R",
        include = "days",
        elements = "tempmax,tempmin,temp"
    )

    return response
}