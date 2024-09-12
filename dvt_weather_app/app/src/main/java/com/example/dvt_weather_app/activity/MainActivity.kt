package com.example.dvt_weather_app.activity

import android.os.Bundle
import android.view.View
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

        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)

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
