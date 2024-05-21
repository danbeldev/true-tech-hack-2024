package ru.mtc.live.common.geocalc

import kotlin.math.*


object EarthCalc {
    const val EARTH_RADIUS: Double = 6356752.314245 // radius at the poles, meters

    fun distanceInKilometers(
        standLatitude: Double?,
        standLongitude: Double,
        foreLatitude: Double?,
        foreLongitude: Double
    ): Double {
        val distanceMeters = distance(standLatitude, standLongitude, foreLatitude, foreLongitude)
        return distanceMeters / 1000
    }

    fun distance(
        standLatitude: Double?,
        standLongitude: Double,
        foreLatitude: Double?,
        foreLongitude: Double
    ): Double {
        val Δλ = Math.toRadians(abs(foreLongitude - standLongitude))
        val φ1 = Math.toRadians(standLatitude!!)
        val φ2 = Math.toRadians(foreLatitude!!)

        //spherical law of cosines
        val sphereCos = (sin(φ1) * sin(φ2)) + (cos(φ1) * cos(φ2) * cos(Δλ))
        val c = acos(max(min(sphereCos, 1.0), -1.0))

        return EARTH_RADIUS * c
    }
}