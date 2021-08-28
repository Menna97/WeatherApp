package com.example.android.weatherapp.model;

import androidx.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Weather.class}, version = 2, exportSchema = false)
public abstract class WeatherRoomDatabase extends RoomDatabase {
    public abstract WeatherDao weatherDao();
    private static WeatherRoomDatabase INSTANCE;

    static WeatherRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WeatherRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WeatherRoomDatabase.class, "word_database")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            // Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    /**
     * Populate the database in the background.
     */
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final WeatherDao mDao;
        String[] cities = {"London,UK", "Cairo,Egypt", "Paris,France"};
        String[] temp = {"124","16","12"};
        String[] pressure = {"124","16","12"};
        String[] humidity = {"124","16","12"};
        String[] tempMin = {"124","16","12"};
        String[] tempMax = {"124","16","12"};

        PopulateDbAsync(WeatherRoomDatabase db) {
            mDao = db.weatherDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate the database
            // when it is first created
            //mDao.deleteAll();
            if(mDao.getAnyWeather().length < 1) {
                for (int i = 0; i <= cities.length - 1; i++) {
                    Weather weather = new Weather(cities[i], temp[i], pressure[i], humidity[i],
                            tempMin[i], tempMax[i]);
                    mDao.insert(weather);
                }
            }
            return null;
        }
    }
}
