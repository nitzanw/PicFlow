package com.nitzanwerber.picflow;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.nitzanwerber.picflow.pojo.FlickerPhotos;
import com.nitzanwerber.picflow.pojo.FlickrPhotosSearchResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PhotoRepository {

    public static final String MYAPP = "PicFlow";
    public FlickerAPIInterface flickerAPIInterface;

    @Inject
    public PhotoRepository(FlickerAPIInterface flickerAPIInterface) {
        this.flickerAPIInterface = flickerAPIInterface;
    }

    public LiveData<FlickrPhotosSearchResponse> getPhotoPerLocation(String lan, String lon) {
        final MutableLiveData<FlickrPhotosSearchResponse> data = new MutableLiveData<>();

        flickerAPIInterface.getPhotos(flickerAPIInterface.API_KEY, flickerAPIInterface.METHOD_FLICKR_SEARCH,"32.085300", "34.781769", "love", 1,"1", "json").enqueue(new Callback<FlickrPhotosSearchResponse>() {
            @Override
            public void onResponse(Call<FlickrPhotosSearchResponse> call, Response<FlickrPhotosSearchResponse> response) {
                if(response.isSuccessful()){
                    Log.d(MYAPP, "Woopiiii!");
                }
                data.setValue(response.body());
                Log.d(MYAPP, data.getValue().component1().component5().get(0).getId());

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
