package com.nitzanwerber.picflow.viewModel;

import android.location.Location;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import com.nitzanwerber.picflow.repository.LocationRepository;
import com.nitzanwerber.picflow.repository.PhotoRepository;
import com.nitzanwerber.picflow.dataModel.dto.FlickrPhotosSearchResponse;
import com.nitzanwerber.picflow.dataModel.dto.FlickrPrePhoto;
import com.nitzanwerber.picflow.liveData.SharedPreferenceBooleanLiveData;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

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
            return;
        }


        photoResponse = Transformations.switchMap(locationRepository.getLocation(), new Function<Location, LiveData<FlickrPhotosSearchResponse>>() {
            @Override
            public LiveData<FlickrPhotosSearchResponse> apply(Location location) {
                return photoRepository.getPhotoPerLocation(
                        String.valueOf(location.getLatitude()), String.valueOf(location.getLongitude()));
            }
        });
    }

    public LiveData<FlickrPhotosSearchResponse> getPhotoResponse() {
        return photoResponse;
    }

    public boolean requestingLocationUpdates() {
        return locationRepository.isRequestingLocationUpdates();
    }

    public LiveData<List<FlickrPrePhoto>> getAllPhotosHistory() {
        return photoRepository.loadAllPhotosFromDB();
    }

    public SharedPreferenceBooleanLiveData locationRequestState() {
        return locationRepository.locationRequestState();
    }
}
