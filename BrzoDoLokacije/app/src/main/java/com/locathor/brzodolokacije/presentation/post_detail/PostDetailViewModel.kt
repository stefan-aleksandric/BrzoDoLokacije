package com.locathor.brzodolokacije.presentation.post_detail

import android.app.Application
import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class PostDetailViewModel @Inject constructor(): ViewModel() {
    var state by mutableStateOf(PostDetailState())



    fun getCompleteAddress(context: Context, latitude: Double, longitude: Double): String {
        val geocoder: Geocoder = Geocoder(context)
        var addresses: List<Address>? = null
        var addressText = ""

        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1)
        }catch(ex: Exception){
            ex.printStackTrace()
        }

        val address: Address? = addresses?.get(0)
        addressText = address?.locality ?: address?.countryName ?: ""
        return addressText
    }
}