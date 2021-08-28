package com.example.android.weatherapp.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "weather_table")
public class Weather {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "city")
    private String mCity;

    @NonNull
    @ColumnInfo(name = "temp")
    private String mTemp;

    @NonNull
    @ColumnInfo(name = "pressure")
    private String mPressure;

    @NonNull
    @ColumnInfo(name = "humidity")
    private String mHumidity;

    @NonNull
    @ColumnInfo(name = "temp_min")
    private String mTempMin;

    @NonNull
    @ColumnInfo(name = "temp_max")
    private String mTempMax;

    public Weather(@NonNull String city, @NonNull String temp, @NonNull String pressure, @NonNull String humidity,
                   @NonNull String tempMin, @NonNull String tempMax) {
        this.mCity = city;
        this.mTemp = temp;
        this.mPressure = pressure;
        this.mHumidity = humidity;
        this.mTempMin = tempMin;
        this.mTempMax = tempMax;
    }

    public String getCity(){return this.mCity;}
    public String getTemp(){return this.mTemp;}
    public String getPressure(){return this.mPressure;}
    public String getHumidity(){return this.mHumidity;}
    public String getTempMin(){return this.mTempMin;}
    public String getTempMax(){return this.mTempMax;}
}
