package com.nitzanwerber.picflow.dataModel;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import com.nitzanwerber.picflow.FlickerAPIInterface;
import com.nitzanwerber.picflow.dataModel.dto.FlickrPhotosSearchResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PhotoRepository {

    public static final String MYAPP = "PicFlow";
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
                Log.d(MYAPP, Log.getStackTraceString(new Exception()));
            }

            @Override
            public void onFailure(Call<FlickrPhotosSearchResponse> call, Throwable t) {
                Log.d(MYAPP, t.fillInStackTrace().getMessage());
            }

        });

        return data;
    }
}
