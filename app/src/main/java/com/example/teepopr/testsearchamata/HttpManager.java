package com.example.teepopr.testsearchamata;

import com.example.teepopr.testsearchamata.dao.ResponseDao;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by teepop.r on 8/1/2017.
 */

class HttpManager {
    private static final HttpManager ourInstance = new HttpManager();

    private Service service;

    static HttpManager getInstance() {
        return ourInstance;
    }

    private HttpManager() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://smartcity.amata.com/api/php/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(Service.class);
    }

    public Service getService() {
        return service;
    }

    interface Service {
        @Headers("Authorization: Basic YWRtaW5AaG90bWFpbC5jb206YWRtaW4=")
        @GET("search.php")
        Observable<ResponseDao> getMapSearch(@Query("query") String query, @Query("key") String key);
    }

    //API_KEY = amata_admin
}
