package com.example.dvt_weather_app.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dvt_weather_app.R
import com.example.dvt_weather_app.presentation.components.WeatherCard
import com.example.dvt_weather_app.presentation.viewmodels.WeatherViewModel
import com.example.dvt_weather_app.ui.theme.Font.Companion.poppinsFontFamily


private const val TAG = "com.example.dvt_weather_app.presentation.screens.WeatherScreen"

@Composable
fun WeatherScreen(weatherVM: WeatherViewModel) {

    val currentContext = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(
                id = when (weatherVM.backGround) {
                    "Rain" -> R.drawable.rainy
                    "Clear" -> R.drawable.sunny
                    "Clouds" -> R.drawable.cloudy
                    else -> R.drawable.blank
                }
            ),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            AppHeader()

            weatherVM.weatherResponseVM.response?.list?.let { weatherList ->
                weatherVM.getUniqueDaysWeatherData(weatherList).forEach { weatherData ->
                    WeatherCard(
                        weatherData = weatherData,
                        weatherVM = weatherVM,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(horizontal = 10.dp, vertical = 15.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun AppHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color.Gray)
            .padding(top = 30.dp, bottom = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.padding(start = 15.dp),
            text = "5 Day Forecast",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = poppinsFontFamily
        )
        Image(
            modifier = Modifier
                .size(50.dp)
                .padding(end = 15.dp),
            painter = painterResource(id = R.drawable.storm),
            contentDescription = null,
        )
    }
}
