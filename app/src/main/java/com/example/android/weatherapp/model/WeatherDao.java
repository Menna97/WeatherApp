package com.example.android.weatherapp.model;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Weather weather);

    @Query("DELETE FROM weather_table")
    void deleteAll();

    @Query("SELECT * from weather_table ORDER BY city ASC")
    LiveData<List<Weather>> getAllWeather();

    @Update
    void update(Weather... weather);

    @Query("SELECT * from weather_table LIMIT 1")
    Weather[] getAnyWeather();

}
