package com.example.android.weatherapp.model;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import androidx.lifecycle.LiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class WeatherRepository {

    private WeatherDao mWeatherDao;
    private LiveData<List<Weather>> mAllWeather;

    public WeatherRepository(Application application) {
        WeatherRoomDatabase db = WeatherRoomDatabase.getDatabase(application);
        mWeatherDao = db.weatherDao();
        mAllWeather = mWeatherDao.getAllWeather();
        WeatherAPI weatherAPI = APIClient.getClient().create(WeatherAPI.class);
        Call<WeatherResponse> call = weatherAPI.getWeatherResponse("London,uk");
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if(!response.isSuccessful()){
                    Log.d("weatherresult","----Not successful");
                }
                else{
                    WeatherResponse weatherresponse = response.body();
                    Log.d("weatherresult",weatherresponse.getMain().getTemp()+"Temp");
                    Log.d("weatherresult",weatherresponse.getMain().getPressure()+"Pressure");
                    Log.d("weatherresult",weatherresponse.getMain().getHumidity()+"Hum");
                    Log.d("weatherresult",weatherresponse.getMain().getTemp_min()+"Min");
                    Log.d("weatherresult",weatherresponse.getMain().getTemp_max()+"Max");

                    String temp = weatherresponse.getMain().getTemp();
                    String pressure = weatherresponse.getMain().getPressure();
                    String hum = weatherresponse.getMain().getHumidity();
                    String temp_min = weatherresponse.getMain().getTemp_min();
                    String temp_max = weatherresponse.getMain().getTemp_max();
                    Weather weather = new Weather("London,UK",temp,pressure,hum,temp_min,temp_max);
                    update(weather);

                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Log.d("weatherresult","----Failed"+t.getMessage());
            }
        });

        call = weatherAPI.getWeatherResponse("Cairo,egypt");
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if(!response.isSuccessful()){
                    Log.d("weatherresult","----Not successful");
                }
                else{
                    WeatherResponse weatherresponse = response.body();
                    Log.d("weatherresult",weatherresponse.getMain().getTemp()+"Temp");
                    Log.d("weatherresult",weatherresponse.getMain().getPressure()+"Pressure");
                    Log.d("weatherresult",weatherresponse.getMain().getHumidity()+"Hum");
                    Log.d("weatherresult",weatherresponse.getMain().getTemp_min()+"Min");
                    Log.d("weatherresult",weatherresponse.getMain().getTemp_max()+"Max");

                    String temp = weatherresponse.getMain().getTemp();
                    String pressure = weatherresponse.getMain().getPressure();
                    String hum = weatherresponse.getMain().getHumidity();
                    String temp_min = weatherresponse.getMain().getTemp_min();
                    String temp_max = weatherresponse.getMain().getTemp_max();
                    Weather weather = new Weather("Cairo,Egypt",temp,pressure,hum,temp_min,temp_max);
                    update(weather);

                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Log.d("weatherresult","----Failed"+t.getMessage());
            }
        });

        call = weatherAPI.getWeatherResponse("Paris,France");
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if(!response.isSuccessful()){
                    Log.d("weatherresult","----Not successful");
                }
                else{
                    WeatherResponse weatherresponse = response.body();
                    Log.d("weatherresult",weatherresponse.getMain().getTemp()+"Temp");
                    Log.d("weatherresult",weatherresponse.getMain().getPressure()+"Pressure");
                    Log.d("weatherresult",weatherresponse.getMain().getHumidity()+"Hum");
                    Log.d("weatherresult",weatherresponse.getMain().getTemp_min()+"Min");
                    Log.d("weatherresult",weatherresponse.getMain().getTemp_max()+"Max");

                    String temp = weatherresponse.getMain().getTemp();
                    String pressure = weatherresponse.getMain().getPressure();
                    String hum = weatherresponse.getMain().getHumidity();
                    String temp_min = weatherresponse.getMain().getTemp_min();
                    String temp_max = weatherresponse.getMain().getTemp_max();
                    Weather weather = new Weather("Paris,France",temp,pressure,hum,temp_min,temp_max);
                    update(weather);

                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Log.d("weatherresult","----Failed"+t.getMessage());
            }
        });

    }

    public LiveData<List<Weather>> getAllWords() {
        return mAllWeather;
    }

    public void insert (Weather weather) {
        new insertAsyncTask(mWeatherDao).execute(weather);
    }

    private static class insertAsyncTask extends AsyncTask<Weather, Void, Void> {

        private WeatherDao mAsyncTaskDao;

        insertAsyncTask(WeatherDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Weather... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    public void update (Weather weather) {
        new updateAsyncTask(mWeatherDao).execute(weather);
    }

    private static class updateAsyncTask extends AsyncTask<Weather, Void, Void> {

        private WeatherDao mAsyncTaskDao;

        updateAsyncTask(WeatherDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Weather... params) {
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }
}
