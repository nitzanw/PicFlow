package com.nitzanwerber.picflow.dataModel;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.nitzanwerber.picflow.FlickerService;
import com.nitzanwerber.picflow.dataModel.dto.FlickrPhotosSearchResponse;
import com.nitzanwerber.picflow.dataModel.dto.FlickrPrePhoto;
import com.nitzanwerber.picflow.db.dao.FlickrPrePhotoDao;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;

@Singleton
public class PhotoRepository {

    private static final String TAG = PhotoRepository.class.getSimpleName();

    private final FlickerService flickerService;
    private final FlickrPrePhotoDao flickrPrePhotoDao;
    private final Executor executor;

    @Inject
    PhotoRepository(FlickerService flickerService,
                    FlickrPrePhotoDao flickrPrePhotoDao,
                    Executor executor) {
        this.flickerService = flickerService;
        this.flickrPrePhotoDao = flickrPrePhotoDao;
        this.executor = executor;
    }


    public MutableLiveData<FlickrPhotosSearchResponse> getPhotoPerLocation(final String lat, final String lon) {
        final MutableLiveData<FlickrPhotosSearchResponse> data = new MutableLiveData<>();

        executor.execute(new Runnable() {
            @Override
            public void run() {


                flickerService.getPhotos(FlickerService.API_KEY, FlickerService.METHOD_FLICKR_SEARCH, lat, lon,
                        1, "1", "json", "url_l").enqueue(new Callback<FlickrPhotosSearchResponse>() {
                    @Override
                    public void onResponse(@NotNull Call<FlickrPhotosSearchResponse> call, @NotNull Response<FlickrPhotosSearchResponse> response) {
                        //insert to db
                        FlickrPhotosSearchResponse obj = response.body();
                        final FlickrPrePhoto result = obj.component1().component5().get(0);
                        result.setInsertDate(new Date().getTime());
                        executor.execute(new Runnable() {
                            @Override
                            public void run() {
                                flickrPrePhotoDao.save(result);
                            }
                        });

                        // set data to ui
                        data.setValue(response.body());
                    }

                    @Override
                    public void onFailure(@NotNull Call<FlickrPhotosSearchResponse> call, @NotNull Throwable t) {
                        Log.i(TAG, t.fillInStackTrace().getMessage());
                    }

                });
            }
        });
        return data;
    }

    public LiveData<List<FlickrPrePhoto>> loadAllPhotosFromDB() {
        return flickrPrePhotoDao.loadAll();

    }
}
