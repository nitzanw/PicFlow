package com.nitzanwerber.picflow.repository;

import android.content.SharedPreferences;
import android.location.Location;
import androidx.lifecycle.MutableLiveData;
import com.nitzanwerber.picflow.liveData.SharedPreferenceBooleanLiveData;
import com.nitzanwerber.picflow.utils.LocationUtilKt;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.nitzanwerber.picflow.utils.LocationUtilKt.KEY_REQUESTING_LOCATION_UPDATES;

@Singleton
public class LocationRepository {

    private SharedPreferences sharedPreferences;
    private MutableLiveData<Location> currentLocation;
    private SharedPreferenceBooleanLiveData sharedPreferenceLiveData;

    @Inject
    public LocationRepository(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
        this.sharedPreferenceLiveData = new SharedPreferenceBooleanLiveData(sharedPreferences,
                KEY_REQUESTING_LOCATION_UPDATES,false);
    }

    public MutableLiveData<Location> getLocation() {
        if (currentLocation != null) {
            return currentLocation;
        }
        currentLocation = new MutableLiveData<>();
        return currentLocation;
    }

    public void setLocation(Location newLocation) {
        //check if it has been ~100 meter since last location
        if(true ){//|| isRequestingLocationUpdates() && LocationUtilKt.locationShouldUpdate(currentLocation,newLocation)){
            currentLocation.setValue(newLocation);
        }
    }

    /**
     * Returns true if requesting location updates, otherwise returns false.
     */
    public boolean isRequestingLocationUpdates() {
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

    public SharedPreferenceBooleanLiveData locationRequestState() {
        return sharedPreferenceLiveData;
    }
}
