package com.nitzanwerber.picflow;

import android.app.Application;
import android.content.Context;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class AppModule {

    private final Application application;
    private final Context context;

    public AppModule(Application application) {
        this.application = application;
        this.context = application.getApplicationContext();
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return application;
    }

    @Provides
    @Singleton
    Context providesContext() {
        return context;
    }
}
