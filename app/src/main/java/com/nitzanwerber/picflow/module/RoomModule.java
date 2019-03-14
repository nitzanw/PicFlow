package com.nitzanwerber.picflow.module;

import android.app.Application;
import androidx.room.Room;
import com.nitzanwerber.picflow.db.FlickrPrePhotoDatabase;
import com.nitzanwerber.picflow.db.dao.FlickrPrePhotoDao;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Module(includes = AppModule.class)
public class RoomModule {

    @Singleton
    @Provides
    FlickrPrePhotoDao providesFlickrPrePhotoDao(FlickrPrePhotoDatabase db) {
        return db.flickrPrePhotoDao();
    }

    @Provides
    @Singleton
    FlickrPrePhotoDatabase provideDatabase(Application application) {
        return Room.databaseBuilder(application,
                FlickrPrePhotoDatabase.class, "flickrPrePhoto-db")
                .build();
    }

    @Provides
    Executor provideExecutor() {
        return Executors.newSingleThreadExecutor();
    }
}
