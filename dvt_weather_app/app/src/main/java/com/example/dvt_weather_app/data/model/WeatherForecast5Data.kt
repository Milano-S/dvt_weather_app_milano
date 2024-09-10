package com.example.dvt_weather_app.data.model

data class WeatherForecast5Data(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<WeatherData>,
    val message: Int
)