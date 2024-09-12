package com.example.dvt_weather_app.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapView(lat: Double, lng: Double) {
    val position = LatLng(lat, lng)
    val cameraPositionState = rememberCameraPositionState {
        CameraPosition.fromLatLngZoom(position, 15f)
    }
    val markerState = remember { MarkerState(position = position) }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            state = markerState,
            title = "Marker",
            snippet = "Lat: $lat, Lng: $lng"
        )
    }

    LaunchedEffect(Unit) {
        cameraPositionState.animate(CameraUpdateFactory.newLatLngZoom(position, 15f))
    }

}
