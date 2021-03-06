package com.example.android.weatherapp.model;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static Retrofit retrofit = null;

    public static  Retrofit getClient(){
        if (retrofit == null){

            retrofit = new Retrofit.Builder()
                    .baseUrl("http://api.openweathermap.org")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }


        return retrofit;
    }
}
