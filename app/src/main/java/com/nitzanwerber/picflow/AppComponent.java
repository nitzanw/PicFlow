package com.nitzanwerber.picflow;

import android.app.Application;
import android.content.SharedPreferences;
import com.nitzanwerber.picflow.dataModel.LocationRepository;
import com.nitzanwerber.picflow.module.AppModule;
import com.nitzanwerber.picflow.module.PicassoModule;
import com.nitzanwerber.picflow.module.ServiceUtilModule;
import com.nitzanwerber.picflow.module.ViewModelModule;
import com.nitzanwerber.picflow.views.MainActivity;
import com.nitzanwerber.picflow.views.PictureFlowFragment;
import com.nitzanwerber.picflow.dataModel.PhotoRepository;
import com.squareup.picasso.Picasso;
import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

import javax.inject.Singleton;

@Singleton
@Component(modules={AppModule.class, PicassoModule.class,
        ServiceUtilModule.class, ViewModelModule.class})
public interface AppComponent {
    void inject(PhotoRepository repository);
    void inject(Application application);
    void inject(MainActivity activity);
    void inject(PictureFlowFragment flowFragment);
    void inject(LocationRepository locationRepository);

    Retrofit retrofit();
    OkHttpClient okHttpClient();
    SharedPreferences sharedPreferences();
    Picasso picasso();
}
