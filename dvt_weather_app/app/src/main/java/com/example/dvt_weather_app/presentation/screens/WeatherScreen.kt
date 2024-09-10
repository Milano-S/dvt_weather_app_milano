import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dvt_weather_app.R
import com.example.dvt_weather_app.presentation.viewmodels.WeatherViewModel
import com.example.dvt_weather_app.ui.theme.Font.Companion.poppinsFontFamily
import com.example.dvt_weather_app.ui.theme.GradientColor1

@Composable
fun WeatherScreen(weatherVM: WeatherViewModel) {

    Column(
        modifier = Modifier
            .background(GradientColor1)
            .fillMaxSize()
            .padding(0.dp)
    ) {
        AppHeader()
        Spacer(modifier = Modifier.height(16.dp))

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
            modifier = Modifier.size(50.dp).padding(end = 15.dp),
            painter = painterResource(id = R.drawable.storm),
            contentDescription = null,
        )
    }
}
