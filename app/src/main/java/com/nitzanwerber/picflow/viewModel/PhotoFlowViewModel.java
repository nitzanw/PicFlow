package com.nitzanwerber.picflow.viewModel;

import android.location.Location;
import android.util.Log;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import com.nitzanwerber.picflow.dataModel.LocationRepository;
import com.nitzanwerber.picflow.dataModel.dto.FlickrPhotosSearchResponse;
import com.nitzanwerber.picflow.dataModel.PhotoRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PhotoFlowViewModel extends ViewModel {

    private LiveData<FlickrPhotosSearchResponse> photoResponse;

    private PhotoRepository photoRepository;
    private LocationRepository locationRepository;

    @Inject
    public PhotoFlowViewModel(PhotoRepository photoRepository, LocationRepository locationRepository) {
        this.photoRepository = photoRepository;
        this.locationRepository = locationRepository;
    }

    public void init() {
        if (this.photoResponse != null) {
            // ViewModel is created on a per-Fragment basis, so the userId
            // doesn't change.
            return;
        }

        photoResponse = Transformations.switchMap(locationRepository.getLocation(), new Function<Location, LiveData<FlickrPhotosSearchResponse>>() {
            @Override
            public LiveData<FlickrPhotosSearchResponse> apply(Location location) {
                return photoRepository.getPhotoPerLocation(
                        String.valueOf(location.getLatitude()), String.valueOf(location.getLongitude()));
            }
        });
        Log.wtf("photoflowviewmodel!!!!addImageAccordingToLocation", "Im here:)");

    }



    public LiveData<FlickrPhotosSearchResponse> getPhotoResponse() {
        return photoResponse;
    }

    public boolean requestingLocationUpdates() {
        return locationRepository.requestingLocationUpdates();
    }
}
