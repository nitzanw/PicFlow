package com.nitzanwerber.picflow.viewModel;

import android.content.Context;
import android.location.Location;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.nitzanwerber.picflow.dataModel.LocationRepository;
import com.nitzanwerber.picflow.utils.LocationUtilKt;

import javax.inject.Inject;

public class LocationTrackingViewModel extends ViewModel {

    @Inject
    LocationRepository locationRepository;

    public LocationTrackingViewModel() {

    }

    public LocationRepository getLocationRepository() {
        return locationRepository;
    }

    public void setLocation(Location location){
        locationRepository.setLocation(location);
    }

    public boolean requestingLocationUpdates() {
        return locationRepository.requestingLocationUpdates();
    }

    public void setRequestingLocationUpdates(boolean b) {
        locationRepository.setRequestingLocationUpdates(b);
    }

    public CharSequence getLocationText(Location location) {
        return LocationUtilKt.getLocationText(location);
    }

    public CharSequence getLocationTitle(Context context) {
        return LocationUtilKt.getLocationTitle(context);
    }

    public void saveLastKnownLocation(Location location) {
        locationRepository.saveLastKnownLocation(location);
    }

}
