package com.nitzanwerber.picflow.dataModel;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import com.nitzanwerber.picflow.FlickerAPIInterface;
import com.nitzanwerber.picflow.dataModel.dto.FlickrPhotosSearchResponse;
import com.nitzanwerber.picflow.views.LocationUpdatesService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PhotoRepository {

    private static final String TAG = PhotoRepository.class.getSimpleName();

    public FlickerAPIInterface flickerService;

    @Inject
    public PhotoRepository(FlickerAPIInterface apiInterface) {
        this.flickerService = apiInterface;
    }

    public MutableLiveData<FlickrPhotosSearchResponse> getPhotoPerLocation(String lat, String lon) {
        final MutableLiveData<FlickrPhotosSearchResponse> data = new MutableLiveData<>();

        flickerService.getPhotos(FlickerAPIInterface.API_KEY, FlickerAPIInterface.METHOD_FLICKR_SEARCH,lat, lon,
                1,"1", "json", "url_l").enqueue(new Callback<FlickrPhotosSearchResponse>() {
            @Override
            public void onResponse(Call<FlickrPhotosSearchResponse> call, Response<FlickrPhotosSearchResponse> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<FlickrPhotosSearchResponse> call, Throwable t) {
                Log.i(TAG, t.fillInStackTrace().getMessage());
            }

        });

        return data;
    }
}
