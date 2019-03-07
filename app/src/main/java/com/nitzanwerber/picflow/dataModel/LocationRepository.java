package com.nitzanwerber.picflow.dataModel;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.preference.PreferenceManager;
import com.nitzanwerber.picflow.R;

import javax.inject.Inject;
import java.text.DateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class LocationRepository {

    static final String KEY_REQUESTING_LOCATION_UPDATES = "requesting_locaction_updates";
    private Context context;
    private SharedPreferences sharedPreferences;

    @Inject
    public LocationRepository(Context context, SharedPreferences sharedPreferences) {
        this.context = context;
        this.sharedPreferences = sharedPreferences;
    }

    public String Foo() {
        return "foo!";
    }

    /**
     * Returns true if requesting location updates, otherwise returns false.
     */
    boolean requestingLocationUpdates() {
        return sharedPreferences
                .getBoolean(KEY_REQUESTING_LOCATION_UPDATES, false);
    }

    /**
     * Stores the location updates state in SharedPreferences.
     *
     * @param requestingLocationUpdates The location updates state.
     */
    void setRequestingLocationUpdates(boolean requestingLocationUpdates) {
        sharedPreferences
                .edit()
                .putBoolean(KEY_REQUESTING_LOCATION_UPDATES, requestingLocationUpdates)
                .apply();
    }

    /**
     * Returns the {@code location} object as a human readable string.
     *
     * @param location The {@link Location}.
     */
    String getLocationText(Location location) {
        return location == null ? "Unknown location" :
                "(" + location.getLatitude() + ", " + location.getLongitude() + ")";
    }

    String getLocationTitle() {
        return context.getString(R.string.location_updated,
                DateFormat.getDateTimeInstance().format(new Date()));
    }
}
