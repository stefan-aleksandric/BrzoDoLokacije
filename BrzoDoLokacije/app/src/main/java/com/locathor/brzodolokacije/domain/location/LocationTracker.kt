package com.locathor.brzodolokacije.domain.location

import android.location.Location
import com.google.android.gms.location.LocationAvailability

interface LocationTracker {
   suspend fun getLocation(): Location?
}