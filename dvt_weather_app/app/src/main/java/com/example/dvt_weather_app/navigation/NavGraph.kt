package com.example.dvt_weather_app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.dvt_weather_app.presentation.screens.SplashScreen
import com.example.dvt_weather_app.presentation.screens.WeatherScreen
import com.example.dvt_weather_app.presentation.viewmodels.WeatherViewModel

@Composable
fun NavGraph(navController: NavHostController, weatherViewModel: WeatherViewModel) {

    NavHost(navController = navController, startDestination = Screen.SplashPage.route) {

        //Splash Screen
        composable(Screen.SplashPage.route) {
            SplashScreen(navController = navController, weatherVM = weatherViewModel)
        }

        //Weather Screen
        composable(Screen.WeatherPage.route) {
            WeatherScreen(weatherVM = weatherViewModel)
        }

    }

}