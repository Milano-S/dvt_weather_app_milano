package com.example.dvt_weather_app.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.dvt_weather_app.navigation.NavGraph
import com.example.dvt_weather_app.presentation.viewmodels.LocationViewModel
import com.example.dvt_weather_app.presentation.viewmodels.WeatherViewModel
import com.example.dvt_weather_app.ui.theme.Dvt_weather_appTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val weatherViewModel = ViewModelProvider(this)[WeatherViewModel::class.java]
        val locationViewModel = ViewModelProvider(this)[LocationViewModel::class.java]

        enableEdgeToEdge()
        setContent {
            Dvt_weather_appTheme {
                val navController = rememberNavController()
                NavGraph(
                    navController = navController,
                    weatherViewModel = weatherViewModel,
                    locationViewModel = locationViewModel
                )
            }
        }
    }
}
