package com.example.android.weatherapp.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherAPI {

    @GET("/data/2.5/weather?appid=f3e821a9e5a69c4406157414585da3c6&units=metric")
    Call<WeatherResponse> getWeatherResponse(@Query("q")String name);
}
