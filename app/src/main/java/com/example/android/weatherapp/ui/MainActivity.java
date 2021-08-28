package com.example.android.weatherapp.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.android.weatherapp.R;
import com.example.android.weatherapp.model.Weather;
import com.example.android.weatherapp.viewmodel.WeatherViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private WeatherViewModel mWeatherViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final WeatherListAdapter adapter = new WeatherListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mWeatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);
        mWeatherViewModel.getAllWeather().observe(this, new Observer<List<Weather>>() {
            @Override
            public void onChanged(@Nullable final List<Weather> weather) {
                // Update the cached copy of the words in the adapter.
                adapter.setWeather(weather);
            }
        });
    }
}
