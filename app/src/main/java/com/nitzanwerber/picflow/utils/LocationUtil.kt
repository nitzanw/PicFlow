package com.nitzanwerber.picflow.utils

import android.content.Context
import android.location.Location
import androidx.lifecycle.MutableLiveData
import com.nitzanwerber.picflow.R
import java.text.DateFormat
import java.util.*


const val KEY_REQUESTING_LOCATION_UPDATES = "requesting_locaction_updates"
const val LAST_KNOWN_LOCATION = "last_known_location"
const val DISTANCE_IN_METERS_BETWEEN_PHOTO_UPDATE = 100.0F


/**
 * Returns the `location` object as a human readable string.
 *
 * @param location The [Location].
 */
internal fun getLocationText(location: Location?): String {
    return if (location == null)
        "Unknown location"
    else
        "(" + location.latitude + ", " + location.longitude + ")"
}

internal fun getLocationTitle(context: Context): String {
    return context.getString(
        R.string.location_updated,
        DateFormat.getDateTimeInstance().format(Date())
    )
}

fun locationShouldUpdate(currentLocation: MutableLiveData<Location>, newLocation: Location): Boolean {
    if (currentLocation.value != null) {
        val location = currentLocation.value;
        val distance = location?.distanceTo(newLocation) ?: 0.0F
        return distance >= DISTANCE_IN_METERS_BETWEEN_PHOTO_UPDATE
    }
    return true;
}

