package com.example.dvt_weather_app.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalProvider
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dvt_weather_app.R
import com.example.dvt_weather_app.data.model.Clouds
import com.example.dvt_weather_app.data.model.Main
import com.example.dvt_weather_app.data.model.Rain
import com.example.dvt_weather_app.data.model.Sys
import com.example.dvt_weather_app.data.model.Weather
import com.example.dvt_weather_app.data.model.WeatherData
import com.example.dvt_weather_app.data.model.Wind
import com.example.dvt_weather_app.presentation.viewmodels.WeatherViewModel
import com.example.dvt_weather_app.ui.theme.Font.Companion.poppinsFontFamily

@Composable
fun WeatherCard(weatherData: WeatherData?, weatherVM: WeatherViewModel) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(Color.White, RoundedCornerShape(16.dp)),
        onClick = { /*TODO*/ }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 7.dp)
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = if (weatherData != null) weatherVM.getCurrentDayName(weatherData.dt.toLong()) else "Day",
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(8.dp))
                if (weatherData != null) {
                    Image(
                        painter = painterResource(
                            id = when (weatherData.weather[0].description) {
                                "light rain" -> R.drawable.property_1_20_rain_light
                                "clear sky" -> R.drawable.property_1_01_sun_light
                                "broken clouds" -> R.drawable.property_1_05_partial_cloudy_light
                                "overcast clouds" -> R.drawable.property_1_11_mostly_cloudy_light
                                "moderate rain" -> R.drawable.property_1_20_rain_light
                                "heavy intensity rain" -> R.drawable.property_1_18_heavy_rain_light
                                "scattered clouds" -> R.drawable.property_1_07_mostly_cloud_light
                                else -> R.drawable.property_1_01_sun_light
                            }

                        ),
                        contentDescription = null,
                        modifier = Modifier.size(60.dp)
                    )
                }
            }
            Text(
                text = if (weatherData != null) (weatherData.main.temp.toString() + "°") else "0°",
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 36.sp,
                color = Color.Black,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 8.dp, bottom = 0.dp)
            )
        }
    }


}

@Preview
@Composable
private fun PreviewWeatherCard() {
    val sampleWeatherData = WeatherData(
        clouds = Clouds(all = 75),
        dt = 1625241600,
        dt_txt = "2024-09-10 18:00:00",
        main = Main(
            0.0,
            10,
            10,
            10,
            10,
            20.0,
            4.0,
            0.0,
            0.0,
        ),
        pop = 0.8,
        rain = Rain(`3h` = 0.67),
        sys = Sys(pod = "n"),
        visibility = 10000,
        weather = listOf(
            Weather(
                description = "light rain",
                icon = "10n",
                id = 500,
                main = "Rain"
            )
        ),
        wind = Wind(
            speed = 1.12,
            deg = 191,
            gust = 0.43
        )
    )
    WeatherCard(sampleWeatherData, WeatherViewModel())
}