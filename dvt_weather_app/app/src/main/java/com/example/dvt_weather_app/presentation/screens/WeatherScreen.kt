package com.example.dvt_weather_app.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
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
import com.example.dvt_weather_app.ui.theme.CardEndGradient
import com.example.dvt_weather_app.ui.theme.Font.Companion.poppinsFontFamily


private const val TAG = "WeatherScreen"

@Composable
fun WeatherScreen(weatherVM: WeatherViewModel) {

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

            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.End)
                    .padding(top = 10.dp, end = 11.dp)
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(
                                Color.Gray,
                                CardEndGradient
                            )
                        ), RoundedCornerShape(8.dp)
                    ),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(3.dp)
                ) {
                    Icon(
                        Icons.Default.LocationOn,
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        modifier = Modifier.padding(end = 3.dp),
                        text = if (weatherVM.weatherResponseVM.response == null) "Location" else weatherVM.weatherResponseVM.response?.city?.name.toString(),
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                }
            }


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
