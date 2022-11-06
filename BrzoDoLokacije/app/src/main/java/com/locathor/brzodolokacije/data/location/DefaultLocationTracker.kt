package com.locathor.brzodolokacije.data.location
import android.Manifest
import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.util.Log
import androidx.annotation.RequiresPermission
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationAvailability
import com.google.android.gms.location.Priority
import com.locathor.brzodolokacije.domain.location.LocationTracker
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

class DefaultLocationTracker @Inject constructor(
    private val locationClient: FusedLocationProviderClient,
    private val application: Application
) : LocationTracker {

    @SuppressLint("MissingPermission")
    suspend fun getLastLocation(): Location? {

        if(!checkPermissions() || !checkGpsAvailability() )
            return null

         //if want to pass specific error message wrap Location in ResourceProvider

        //pass specific Location in suspending coroutine
        return suspendCancellableCoroutine { cont ->
            locationClient.lastLocation.apply {
                if(isComplete) {
                    if(isSuccessful) {
                        cont.resume(result)
                        } else {
                        cont.resume(null)
                        }
                    return@suspendCancellableCoroutine
                }
                 //transform callback into coroutine
                addOnSuccessListener {
                    cont.resume(it)
                }
                addOnFailureListener{
                    cont.resume(null)
                }
                addOnCanceledListener {
                    cont.cancel()
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    suspend fun getLocationAvailability(): LocationAvailability? {

        if(!checkPermissions() || !checkGpsAvailability())
            return null

        //if want to pass specific error message wrap Location in ResourceProvider

        //pass specific Location in suspending coroutine

        return suspendCancellableCoroutine { cont ->
            locationClient.locationAvailability.apply {
                if(isComplete) {
                    if(isSuccessful) {
                        cont.resume(result)
                    } else {
                        cont.resume(null)
                    }
                    return@suspendCancellableCoroutine
                }
                //transform callback into coroutine
                addOnSuccessListener {
                    cont.resume(it)
                }
                addOnFailureListener{
                    cont.resume(null)
                }
                addOnCanceledListener {
                    cont.cancel()
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    suspend fun getCurrentLocation(): Location?
    {
        if(!checkPermissions() || !checkGpsAvailability())
            return null

        //if want to pass specific error message wrap Location in ResourceProvider

        //pass specific Location in suspending coroutine
        return suspendCancellableCoroutine { cont ->
            locationClient.getCurrentLocation(
                Priority.PRIORITY_HIGH_ACCURACY,
                null
            ).apply {
                if(isComplete) {
                    if(isSuccessful) {
                        cont.resume(result)
                    } else {
                        cont.resume(null)
                    }
                    return@suspendCancellableCoroutine
                }
                //transform callback into coroutine
                addOnSuccessListener {
                    cont.resume(it)
                }
                addOnFailureListener{
                    cont.resume(null)
                }
                addOnCanceledListener {
                    cont.cancel()
                }
            }
        }
    }

    override suspend fun getLocation(): Location? {
       return when(getLocationAvailability()!!.isLocationAvailable &&   getLastLocation()!!.time + 1000*30 > System.currentTimeMillis()){
            true -> getLastLocation()
            false -> getCurrentLocation()
       }
    }

    private fun checkPermissions(): Boolean {
        val hasAccessFineLocationPermission = ContextCompat.checkSelfPermission(
            application,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        val hasAccessCoarseLocationPermission = ContextCompat.checkSelfPermission(
            application,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        if(!hasAccessFineLocationPermission || !hasAccessCoarseLocationPermission)
            return false

        return true
    }

    private fun checkGpsAvailability(): Boolean {
        val locationManager = application.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

        if( !isGpsEnabled )
            return false

        return true
    }


}