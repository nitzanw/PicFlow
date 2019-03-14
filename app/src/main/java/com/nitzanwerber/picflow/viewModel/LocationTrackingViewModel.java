package com.nitzanwerber.picflow.viewModel;

import android.content.Context;
import android.location.Location;
import androidx.lifecycle.ViewModel;
import com.nitzanwerber.picflow.repository.LocationRepository;
import com.nitzanwerber.picflow.utils.LocationUtilKt;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.ListIterator;

public class LocationTrackingViewModel extends ViewModel {

    @Inject
    LocationRepository locationRepository;

    public LocationTrackingViewModel() {

    }

    public void setLocation(Location location){
        locationRepository.setLocation(location);
    }

    public boolean isRequestingLocationUpdates() {
        return locationRepository.isRequestingLocationUpdates();
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

    public void setLocationQueue(LinkedList<Location> locationsQueue) {
        ListIterator<Location> listIterator = locationsQueue.listIterator();
        while (listIterator.hasNext()) {
            Location curr = listIterator.next();
            setLocation(curr);
        }
    }
}
