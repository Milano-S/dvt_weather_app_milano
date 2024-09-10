package com.example.dvt_weather_app.data.model

data class WeatherResponse(
    val response: WeatherForecast5Data?,
    val error: Throwable?
)