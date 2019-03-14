package com.nitzanwerber.picflow.module;

import android.content.Context;
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
    HttpLoggingInterceptor provideInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        return interceptor;
    }
    @Provides
    @Singleton
    Cache provideCache(File cacheFile) {
        return new Cache(cacheFile, 10 * 1000 * 1000);  // 10 MiB cache
    }

    @Provides
    @Singleton
    File provideFile(@Singleton Context context) {
        File file = new File(context.getFilesDir(), "cache_dir");
        if (!file.exists())
            file.mkdirs();
        return file;
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor interceptor, Cache cache) {
        OkHttpClient.Builder client = new OkHttpClient.Builder().addInterceptor(interceptor);
        client.cache(cache);
        return client.build();
    }
}