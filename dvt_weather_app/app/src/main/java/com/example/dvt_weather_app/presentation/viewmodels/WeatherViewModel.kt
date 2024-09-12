package com.example.dvt_weather_app.presentation.viewmodels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.dvt_weather_app.data.api.WeatherApi
import com.example.dvt_weather_app.data.model.WeatherData
import com.example.dvt_weather_app.data.model.WeatherForecast5Data
import com.example.dvt_weather_app.data.model.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.Locale

private const val TAG = "WeatherViewModel"

class WeatherViewModel : ViewModel() {

    private val _weatherResponseVM: MutableState<WeatherResponse> = mutableStateOf(WeatherResponse(null, null))
    val weatherResponseVM: WeatherResponse get() = _weatherResponseVM.value
    fun getWeatherForecast(
        lat: Double,
        lon: Double,
        apiKey: String,
    ) {
        val url = "https://api.openweathermap.org/"

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)

        val retrofitData = retrofitBuilder.getWeatherForecast(lat, lon, apiKey)

        retrofitData.enqueue(object : Callback<WeatherForecast5Data> {
            override fun onResponse(
                call: Call<WeatherForecast5Data>,
                response: Response<WeatherForecast5Data>
            ) {
                val responseData = response.body()
                if (responseData != null) {
                    _weatherResponseVM.value = WeatherResponse(responseData, null)
                    setBackGround(responseData.list[0].weather[0].main)
                    Log.i(TAG, "onResponse: $responseData")
                }
            }

            override fun onFailure(call: Call<WeatherForecast5Data>, t: Throwable) {
                _weatherResponseVM.value = WeatherResponse(null, t)
                Log.i(TAG, "Error: ${t.message.toString()}")
            }
        })
    }

    private val _backGround: MutableState<String> = mutableStateOf("")
    val backGround: String get() = _backGround.value
    fun setBackGround(condition: String) {
        _backGround.value = condition
        Log.i(TAG, "Background : $condition")
    }

    fun getUniqueDaysWeatherData(weatherList: List<WeatherData>): List<WeatherData> {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val uniqueDates = mutableSetOf<String>()
        val uniqueDaysWeatherData = mutableListOf<WeatherData>()

        for (weatherData in weatherList) {
            val date = dateFormat.format(SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse(weatherData.dt_txt))
            if (uniqueDates.add(date)) {
                uniqueDaysWeatherData.add(weatherData)
                if (uniqueDaysWeatherData.size == 5) break
            }
        }

        return uniqueDaysWeatherData
    }


    fun getCurrentDayName(dtTxt: String): String {
        val inputDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val outputDateFormat = SimpleDateFormat("EEEE", Locale.getDefault())
        val date = inputDateFormat.parse(dtTxt)
        return outputDateFormat.format(date)
    }

}

