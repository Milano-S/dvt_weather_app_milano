package com.example.dvt_weather_app.navigation

sealed class Screen (
    val route : String
){
    data object SplashPage : Screen("splash_screen")
    data object WeatherPage : Screen("weather_screen")
}