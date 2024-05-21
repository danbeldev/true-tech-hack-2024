package ru.mtc.live.common

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationManager
import com.yandex.mapkit.geometry.Point

object Utils {

    @SuppressLint("MissingPermission")
    internal fun getGPS(
        context: Context
    ): Point {
        val lm = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val providers = lm.getProviders(true)
        var l: Location? = null
        for (i in providers.indices.reversed()) {
            l = lm.getLastKnownLocation(providers[i])
            if (l != null) break
        }
        l?.let {
            return Point(l.latitude, l.longitude)
        }
        return Point(0.1,0.1)
    }
}