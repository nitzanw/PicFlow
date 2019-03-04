package com.nitzanwerber.picflow;

import android.app.Application;
import android.content.SharedPreferences;
import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

import javax.inject.Singleton;

@Singleton
@Component(modules={AppModule.class, NetModule.class, ViewModelModule.class, })
public interface AppComponent {
    void inject(PhotoRepository repository);
    void inject(Application application);
    void inject(MainActivity activity);
    void inject(PictureFlowFragment flowFragment);

    Retrofit retrofit();
    OkHttpClient okHttpClient();
    SharedPreferences sharedPreferences();
}
