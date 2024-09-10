package com.example.dvt_weather_app.data.api

import com.example.dvt_weather_app.data.model.WeatherForecast5Data
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("data/2.5/forecast")
    fun getWeatherForecast(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String
    ): Call<WeatherForecast5Data>
}
