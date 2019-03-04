package com.nitzanwerber.picflow;

import com.nitzanwerber.picflow.pojo.FlickerPhotos;
import com.nitzanwerber.picflow.pojo.FlickrPhotosSearchResponse;
import dagger.Module;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;


@Module
public interface FlickerAPIInterface {

    String HTTPS_API = "https://api.flickr.com/";
    String API_KEY = "e601c0ab6569cf69e99a31522bbf2bed";
    String METHOD_FLICKR_SEARCH = "flickr.photos.search";


    @GET("services/rest/")
    @Headers("Content-Type: application/json")
    Call<FlickrPhotosSearchResponse> getPhotos(@Query("api_key") String api_key, @Query("method") String method,
                                               @Query("lat") String lat, @Query("lon") String lon,
                                               @Query("text") String text, @Query("nojsoncallback") int textnojsoncallback,
                                               @Query("per_page") String perPage, @Query("format") String format);
}
