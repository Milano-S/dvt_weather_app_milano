package com.example.dvt_weather_app.presentation.screens

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import com.example.dvt_weather_app.R
import com.example.dvt_weather_app.navigation.Screen
import com.example.dvt_weather_app.presentation.viewmodels.LocationViewModel
import com.example.dvt_weather_app.presentation.viewmodels.WeatherViewModel
import com.example.dvt_weather_app.ui.theme.GradientColor1
import com.example.dvt_weather_app.ui.theme.GradientColor2
import kotlinx.coroutines.launch

private const val TAG = "SplashScreen"

@Composable
fun SplashScreen(
    navController: NavController,
    weatherVM: WeatherViewModel,
    locationViewModel: LocationViewModel
) {
    val scope = rememberCoroutineScope()
    val currentContext = LocalContext.current
    val scale = remember { Animatable(0f) }
    val alpha = remember { Animatable(0f) }

    var permissionsGranted by remember { mutableStateOf(false) }

    val permissionLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            permissionsGranted =
                permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true && permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true
            if (permissionsGranted) {
                locationViewModel.getLastLocation()
            }
        }

    LaunchedEffect(Unit) {
        initTracker(currentContext) { granted ->
            if (!granted) {
                permissionLauncher.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )
            } else {
                permissionsGranted = true
                locationViewModel.getLastLocation()
            }
        }
    }

    val locationState by remember { derivedStateOf { locationViewModel.location } }

    LaunchedEffect(key1 = locationState) {

        locationState?.let {
            val lat = it.latitude
            val long = it.longitude
            Log.i(TAG, "$lat , $long")
        }

        // Get Weather Forecast
        /*weatherVM.getWeatherForecast(
            context = currentContext,
            lat = lat,
            lon = long,
            apiKey = currentContext.getString(R.string.api_key),
        )
        Log.i(TAG, "Weather Data : ${weatherVM.weatherResponseVM.response?.message}")*/

        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            )
        )
        alpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            )
        )
        scope.launch {
            navController.navigate(Screen.WeatherPage.route)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        GradientColor1,
                        GradientColor2
                    )
                )
            )
    ) {
        Image(
            painter = painterResource(id = R.drawable.storm),
            contentDescription = "Logo",
            modifier = Modifier
                .scale(scale.value)
                .alpha(alpha.value)
                .align(Alignment.Center)
        )
    }
}

fun initTracker(context: Context, onPermissionsResult: (Boolean) -> Unit) {
    val hasFineLocation = ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    val hasCoarseLocation = ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
    if (!hasFineLocation || !hasCoarseLocation) {
        ActivityCompat.requestPermissions(
            (context as Activity),
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ),
            1337
        )
    } else {
        onPermissionsResult(true)
    }
}
