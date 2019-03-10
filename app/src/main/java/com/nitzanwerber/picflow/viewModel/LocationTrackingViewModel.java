package com.nitzanwerber.picflow.viewModel;

import android.content.Context;
import android.location.Location;
import androidx.lifecycle.ViewModel;
import com.nitzanwerber.picflow.MyApp;
import com.nitzanwerber.picflow.dataModel.LocationRepository;
import com.nitzanwerber.picflow.utils.LocationUtilKt;
import com.nitzanwerber.picflow.utils.Utils;

import javax.inject.Inject;

public class LocationTrackingViewModel extends ViewModel {

    @Inject LocationRepository locationRepository;

    public LocationTrackingViewModel() {

    }

    public LocationRepository getLocationRepository() {
        return locationRepository;
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
}
