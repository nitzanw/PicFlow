package com.nitzanwerber.picflow.utils

import android.content.Context
import android.location.Location
import com.nitzanwerber.picflow.R
import java.text.DateFormat
import java.util.*


const val KEY_REQUESTING_LOCATION_UPDATES = "requesting_locaction_updates"
const val LAST_KNOWN_LOCATION = "last_known_location"


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

