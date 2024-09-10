package com.example.dvt_weather_app.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dvt_weather_app.R
import com.example.dvt_weather_app.presentation.viewmodels.WeatherViewModel

private const val TAG = "WeatherPage"
@Composable
fun WeatherScreen(weatherVM: WeatherViewModel) {
    Column(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize()
    ) {
        AppHeader()

    }
}

@Composable
private fun AppHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Gray)
            .padding(14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "5 Day Forecast",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = com.example.dvt_weather_app.ui.theme.Font.poppinsFontFamily
        )
        Image(
            modifier = Modifier.size(50.dp),
            painter = painterResource(id = R.drawable.storm),
            contentDescription = null,
        )
    }
}

/*@Preview
@Composable
private fun PreviewWeatherScreen() {
    WeatherScreen()
}*/

