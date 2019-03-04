package com.nitzanwerber.picflow;

import android.app.Application;
import android.content.SharedPreferences;
import com.nitzanwerber.picflow.Module.NetworkModule;
import com.nitzanwerber.picflow.Module.PicassoModule;
import com.nitzanwerber.picflow.Module.ViewModelModule;
import com.nitzanwerber.picflow.dataModel.PhotoRepository;
import com.squareup.picasso.Picasso;
import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

import javax.inject.Singleton;

@CustomApplicationScope
@Singleton
@Component(modules={AppModule.class, PicassoModule.class,
        ServiceUtilModule.class, ViewModelModule.class})
public interface AppComponent {
    void inject(PhotoRepository repository);
    void inject(Application application);
    void inject(MainActivity activity);
    void inject(PictureFlowFragment flowFragment);

    Retrofit retrofit();
    OkHttpClient okHttpClient();
    SharedPreferences sharedPreferences();
    Picasso picasso();
}
