package com.example.dvt_weather_app.ui.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.dvt_weather_app.R

class Font {
    companion object {

        val poppinsFontFamily = FontFamily(
            Font(R.font.poppins_regular, FontWeight.Normal),
            Font(R.font.poppins_black, FontWeight.Black)
        )

    }
}