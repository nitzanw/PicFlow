package com.nitzanwerber.picflow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.nitzanwerber.picflow.pojo.FlickrPhotosSearchResponse;

import javax.inject.Inject;
import java.util.List;

public class PhotoFlowViewModel extends ViewModel {

    private LiveData<List<String>> locationArray;
    private LiveData<FlickrPhotosSearchResponse> photoResponse;
    private PhotoRepository pictureRepo;

    @Inject
    public PhotoFlowViewModel(PhotoRepository pictureRepo) {
    this.pictureRepo = pictureRepo;
    }

    public void init(String lan, String lon) {
        if (this.photoResponse != null) {
            // ViewModel is created on a per-Fragment basis, so the userId
            // doesn't change.
            return;
        }
        photoResponse = pictureRepo.getPhotoPerLocation(lan, lon);
    }


    public LiveData<FlickrPhotosSearchResponse> getPhotoResponse() {
        return photoResponse;
    }
}
