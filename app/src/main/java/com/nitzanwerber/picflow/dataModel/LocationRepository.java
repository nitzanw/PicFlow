package com.nitzanwerber.picflow.dataModel;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.preference.PreferenceManager;
import com.nitzanwerber.picflow.R;
import com.nitzanwerber.picflow.utils.LocationUtilKt;

import javax.inject.Inject;
import java.text.DateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import static com.nitzanwerber.picflow.utils.LocationUtilKt.KEY_REQUESTING_LOCATION_UPDATES;

public class LocationRepository {

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


}
