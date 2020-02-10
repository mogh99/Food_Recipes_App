package com.example.bakingapp.NetworkUtils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HTTPRequest {

    private final static String BASE_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/";

    private static RetrofitAPI retrofitAPI;

    public static RetrofitAPI getResponse(){
        if(retrofitAPI == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            retrofitAPI = retrofit.create(RetrofitAPI.class);
        }

        return retrofitAPI;
    }
}
