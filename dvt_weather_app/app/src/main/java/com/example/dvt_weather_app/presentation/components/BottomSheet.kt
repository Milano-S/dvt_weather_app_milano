package com.example.dvt_weather_app.presentation.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dvt_weather_app.data.model.WeatherData
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BottomSheet(weatherData: WeatherData, modifier: Modifier = Modifier) {

    val date = Instant.ofEpochSecond(weatherData.dt.toLong())
        .atZone(ZoneId.systemDefault())
        .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = "Weather for : $date", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(8.dp))

        WeatherDetailItem(
            value = "Temperature: ${weatherData.main.temp}°C"
        )
        WeatherDetailItem(
            value = "Feels Like: ${weatherData.main.feels_like}°C"
        )
        WeatherDetailItem(
            value = "Min Temperature: ${weatherData.main.temp_min}°C"
        )
        WeatherDetailItem(
            value = "Max Temperature: ${weatherData.main.temp_max}°C"
        )
        WeatherDetailItem(
            value = "Pressure: ${weatherData.main.pressure} hPa"
        )
        WeatherDetailItem(
            value = "Ground Level: ${weatherData.main.grnd_level} hPa"
        )
        WeatherDetailItem(
            value = "Humidity: ${weatherData.main.humidity} %"
        )
        WeatherDetailItem(
            value = "Visibility: ${weatherData.visibility / 1000} km"
        )
    }
}

@Composable
private fun WeatherDetailItem(value: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Info,
            contentDescription = null,
            tint = Color.Black,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = value,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }
}
