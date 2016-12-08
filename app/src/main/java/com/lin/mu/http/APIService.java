package com.lin.mu.http;

import com.lin.mu.Constants;
import com.lin.mu.model.Music;
import com.lin.mu.model.PhotoModel;
import com.lin.mu.model.PhotoType;

import java.util.Map;
import java.util.StringTokenizer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by lin on 2016/8/25.
 */
public interface APIService {

    @GET(Constants.PICTURES_URL)
    @Headers("apikey: " + Constants.API_KEY)
    Call<ShowApiResponse<PhotoModel>> getPhotos(@Query("type") String type,
                                                @Query("page") int page
    );


    @GET(Constants.PICTURES_TYPE_URL)
    @Headers("apikey: " + Constants.API_KEY)
    Call<ShowApiResponse<PhotoType>> getPhotoType();

}
