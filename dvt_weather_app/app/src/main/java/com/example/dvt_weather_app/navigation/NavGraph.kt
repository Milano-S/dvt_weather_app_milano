package com.example.dvt_weather_app.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.dvt_weather_app.presentation.screens.SplashScreen
import com.example.dvt_weather_app.presentation.screens.WeatherScreen
import com.example.dvt_weather_app.presentation.viewmodels.LocationViewModel
import com.example.dvt_weather_app.presentation.viewmodels.WeatherViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph(
    navController: NavHostController,
    weatherViewModel: WeatherViewModel,
    locationViewModel: LocationViewModel
) {

    NavHost(navController = navController, startDestination = Screen.SplashPage.route) {

        //Splash Screen
        composable(Screen.SplashPage.route) {
            SplashScreen(navController = navController, weatherVM = weatherViewModel, locationViewModel = locationViewModel)
        }

        //Weather Screen
        composable(Screen.WeatherPage.route) {
            WeatherScreen(weatherVM = weatherViewModel, locationVM = locationViewModel)
        }

    }

}