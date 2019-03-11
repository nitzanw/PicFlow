package com.nitzanwerber.picflow.dataModel;

import android.content.SharedPreferences;
import android.location.Location;
import android.os.Build;
import androidx.lifecycle.MutableLiveData;

import javax.inject.Inject;
import javax.inject.Singleton;

import java.util.Optional;

import static com.nitzanwerber.picflow.utils.LocationUtilKt.KEY_REQUESTING_LOCATION_UPDATES;
import static com.nitzanwerber.picflow.utils.LocationUtilKt.LAST_KNOWN_LOCATION;

@Singleton
public class LocationRepository {

    private SharedPreferences sharedPreferences;
    private MutableLiveData<Location> currentLocation;

    @Inject
    public LocationRepository(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public MutableLiveData<Location> getLocation() {
        if (currentLocation != null) {
            return currentLocation;
        }
        currentLocation = new MutableLiveData<>();
        return currentLocation;
    }

    public void setLocation(Location location) {
        currentLocation.setValue(location);
    }


    /**
     * Returns true if requesting location updates, otherwise returns false.
     */
    public boolean requestingLocationUpdates() {
        return sharedPreferences
                .getBoolean(KEY_REQUESTING_LOCATION_UPDATES, false);
    }

    /**
     * Stores the location updates state in SharedPreferences.
     *
     * @param requestingLocationUpdates The location updates state.
     */
    public void setRequestingLocationUpdates(boolean requestingLocationUpdates) {
        sharedPreferences
                .edit()
                .putBoolean(KEY_REQUESTING_LOCATION_UPDATES, requestingLocationUpdates)
                .apply();
    }


    public void saveLastKnownLocation(Location location) {
        sharedPreferences
                .edit()
                .putString(LAST_KNOWN_LOCATION,
                        String.valueOf(location.getLatitude()) + "," +
                                String.valueOf(location.getLongitude()))
                .apply();
    }

    public Location getLastKnownLocation() {
        String locationString = sharedPreferences
                .getString(LAST_KNOWN_LOCATION, "");
        Location location = new Location("Last_known_location");
        if (!locationString.isEmpty()) {
            String[] latLon = locationString.split(",");

            location.setLatitude(Double.valueOf(latLon[0]));
            location.setLongitude(Double.valueOf(latLon[1]));
            return location;
        }
        return location;
    }

}
