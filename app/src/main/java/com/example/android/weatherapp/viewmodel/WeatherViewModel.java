package com.example.android.weatherapp.viewmodel;

import android.app.Application;

import com.example.android.weatherapp.model.Weather;
import com.example.android.weatherapp.model.WeatherRepository;
import java.util.List;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class WeatherViewModel extends AndroidViewModel {
    private WeatherRepository mRepository;

    private LiveData<List<Weather>> mAllWeather;

    public WeatherViewModel (Application application) {
        super(application);
        mRepository = new WeatherRepository(application);
        mAllWeather = mRepository.getAllWords();
    }

    public LiveData<List<Weather>> getAllWeather() { return mAllWeather; }

    public void insert(Weather weather) { mRepository.insert(weather); }

    public void update(Weather weather) { mRepository.update(weather); }
}
