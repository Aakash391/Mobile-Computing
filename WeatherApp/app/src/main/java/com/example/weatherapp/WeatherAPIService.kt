package com.example.weatherapp

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.Date

interface WeatherAPIService {
//    @GET("")
//    suspend fun fetchTemperatureForDate(@Query("key") key: String): Response<Float>
    @GET("VisualCrossingWebServices/rest/services/timeline/{location}/{date}")
    suspend fun getWeatherData(
    @Path("location") location: String,
    @Path("date") date: String,
    @Query("key") apiKey: String,
    @Query("include") include: String,
    @Query("elements") elements: String
    ): Response<WeatherData>

}
