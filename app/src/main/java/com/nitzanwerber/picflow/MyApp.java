package com.nitzanwerber.picflow;

import android.app.Application;
import com.nitzanwerber.picflow.module.AppModule;
import com.nitzanwerber.picflow.module.PicassoModule;
import com.nitzanwerber.picflow.module.ServiceUtilModule;
import com.nitzanwerber.picflow.module.ViewModelModule;

public class MyApp extends Application {
    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        // Dagger%COMPONENT_NAME%
        mAppComponent = DaggerAppComponent.builder()
                // list of modules that are part of this component need to be created here too
                .appModule(new AppModule(this)) // This also corresponds to the name of your module: %component_name%Module
                .serviceUtilModule(new ServiceUtilModule(FlickerAPIInterface.HTTPS_API))
                .picassoModule(new PicassoModule())
                .viewModelModule(new ViewModelModule())
                .build();

        // If a Dagger 2 component does not have any constructor arguments for any of its modules,
        // then we can use .create() as a shortcut instead:
        //  mAppComponent = com.codepath.dagger.components.DaggerAppComponent.create();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

}
