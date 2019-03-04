package com.nitzanwerber.picflow.Module;

import android.app.Application;
import android.content.Context;
import com.nitzanwerber.picflow.AppModule;
import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import javax.inject.Singleton;
import java.io.File;

@Module(includes = AppModule.class)
public class NetworkModule {
    @Provides
    @Singleton
    HttpLoggingInterceptor getInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        return interceptor;
    }
    @Provides
    @Singleton
    Cache getCache(File cacheFile) {
        return new Cache(cacheFile, 10 * 1000 * 1000);  // 10 MiB cache
    }
    @Provides
    @Singleton
    File getFile(@Singleton Context context) {
        File file = new File(context.getFilesDir(), "cache_dir");
        if (!file.exists())
            file.mkdirs();
        return file;
    }
//    @Provides
//    @Singleton
//    OkHttpClient getOkHttpClient(HttpLoggingInterceptor interceptor, Cache cache) {
//        return new OkHttpClient.Builder()
//                .writeTimeout(15, TimeUnit.SECONDS)
//                .readTimeout(15, TimeUnit.SECONDS)
//                .connectTimeout(15, TimeUnit.SECONDS)
//                .cache(cache)
//                .addInterceptor(interceptor)
//                .build();
//    }

//    @Provides
//    @Singleton
//    Cache provideOkHttpCache(Application application) {
//        int cacheSize = 10 * 1024 * 1024; // 10 MiB
//        Cache cache = new Cache(application.getCacheDir(), cacheSize);
//        return cache;
//    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor interceptor, Cache cache) {
        OkHttpClient.Builder client = new OkHttpClient.Builder().addInterceptor(interceptor);
        client.cache(cache);
        return client.build();
    }
}