package com.example.dvt_weather_app.presentation.screens

import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dvt_weather_app.R
import com.example.dvt_weather_app.navigation.Screen
import com.example.dvt_weather_app.presentation.viewmodels.WeatherViewModel
import com.example.dvt_weather_app.ui.theme.GradientColor1
import com.example.dvt_weather_app.ui.theme.GradientColor2
import kotlinx.coroutines.launch


@Composable
fun SplashScreen(
    navController: NavController,
    weatherVM: WeatherViewModel
) {

    val scope = rememberCoroutineScope()
    val currentContext = LocalContext.current
    val scale = remember { Animatable(0f) }
    val alpha = remember { Animatable(0f) }

    LaunchedEffect(key1 = true) {

        weatherVM.getWeatherForecast(
            context = currentContext,
            lat = 43.34,
            lon = 10.99,
            apiKey = currentContext.getString(R.string.api_key),
        )

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
