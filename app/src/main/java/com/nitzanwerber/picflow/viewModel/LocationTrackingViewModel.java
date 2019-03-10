package com.nitzanwerber.picflow.viewModel;

import android.location.Location;
import androidx.lifecycle.ViewModel;
import com.nitzanwerber.picflow.dataModel.LocationRepository;

import javax.inject.Inject;

public class LocationTrackingViewModel extends ViewModel {

    @Inject LocationRepository locationRepository;

    public LocationTrackingViewModel() { }

    public LocationRepository getLocationRepository() {
        return locationRepository;
    }


    public boolean requestingLocationUpdates() {
        return false;
    }

    public void setRequestingLocationUpdates(boolean b) {

    }

    public CharSequence getLocationText(Location mLocation) {
        return null;
    }

    public CharSequence getLocationTitle() {
        return null;
    }
}
