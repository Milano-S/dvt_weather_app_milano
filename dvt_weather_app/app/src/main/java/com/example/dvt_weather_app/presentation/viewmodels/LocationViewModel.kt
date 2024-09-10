package com.example.dvt_weather_app.presentation.viewmodels

import android.app.Application
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.core.app.ActivityCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.launch


private const val TAG = "LocationViewModel"
class LocationViewModel(application: Application) : AndroidViewModel(application) {

    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(application)

    var location by mutableStateOf<Location?>(null)
        private set

    init {
        getLastLocation()
    }

    private fun getLastLocation() {
        viewModelScope.launch {
            if (ActivityCompat.checkSelfPermission(getApplication(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getApplication(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
            ) {
                return@launch
            }

            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    location?.let {
                        Log.i(TAG, "Location : ${it.latitude}, ${it.longitude}")
                        this@LocationViewModel.location = it
                    }
                }
        }
    }
}
