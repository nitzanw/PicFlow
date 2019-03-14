package com.nitzanwerber.picflow;

import android.app.Application;
import android.content.SharedPreferences;
import com.nitzanwerber.picflow.repository.LocationRepository;
import com.nitzanwerber.picflow.db.FlickrPrePhotoDatabase;
import com.nitzanwerber.picflow.db.dao.FlickrPrePhotoDao;
import com.nitzanwerber.picflow.module.*;
import com.nitzanwerber.picflow.viewModel.LocationTrackingViewModel;
import com.nitzanwerber.picflow.views.MainActivity;
import com.nitzanwerber.picflow.views.PictureFlowFragment;
import com.nitzanwerber.picflow.repository.PhotoRepository;
import com.squareup.picasso.Picasso;
import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

import javax.inject.Singleton;
import java.util.concurrent.Executor;

@Singleton
@Component(modules = {AppModule.class, PicassoModule.class,
        ServiceUtilModule.class, ViewModelModule.class,
        RoomModule.class})
public interface AppComponent {
    void inject(PhotoRepository repository);

    void inject(Application application);

    void inject(MainActivity activity);

    void inject(PictureFlowFragment flowFragment);

    void inject(LocationRepository locationRepository);

    void inject(LocationTrackingViewModel locationTrackingViewModel);


    Retrofit retrofit();

    OkHttpClient okHttpClient();

    SharedPreferences sharedPreferences();

    Picasso picasso();

    FlickrPrePhotoDatabase flickrPrePhotoDatabase();

    FlickrPrePhotoDao flickrPrePhotoDao();

    Executor executor();
}
