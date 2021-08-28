package com.example.android.weatherapp.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.weatherapp.R;
import com.example.android.weatherapp.model.Weather;

import java.util.List;
import androidx.recyclerview.widget.RecyclerView;

public class WeatherListAdapter extends RecyclerView.Adapter<WeatherListAdapter.WeatherViewHolder> {
    private final LayoutInflater mInflater;
    private List<Weather> mWeather; // Cached copy of words

    WeatherListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new WeatherViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WeatherViewHolder holder, int position) {
        if (mWeather != null) {
            Weather current = mWeather.get(position);
            holder.cityItemView.setText(current.getCity());
            holder.tempItemView.setText(current.getTemp()+"°");
            holder.pressureItemView.setText("Pressure: "+current.getPressure());
            holder.humidityItemView.setText("Humidity: "+current.getHumidity());
            holder.tempMinItemView.setText("Min Temperature: "+current.getTempMin());
            holder.tempMaxItemView.setText("Max Temperature: "+current.getTempMax());
        } else {
            // Covers the case of data not being ready yet.
            holder.cityItemView.setText("Unknown");
            holder.tempItemView.setText("Unknown");
            holder.pressureItemView.setText("Unknown");
            holder.humidityItemView.setText("Unknown");
            holder.tempMinItemView.setText("Unknown");
            holder.tempMaxItemView.setText("Unknown");
        }
    }

    void setWeather(List<Weather> weather){
        mWeather = weather;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mWeather != null)
            return mWeather.size();
        else return 0;
    }

    class WeatherViewHolder extends RecyclerView.ViewHolder {
        private final TextView cityItemView;
        private final TextView tempItemView;
        private final TextView pressureItemView;
        private final TextView humidityItemView;
        private final TextView tempMinItemView;
        private final TextView tempMaxItemView;

        private WeatherViewHolder(View itemView) {
            super(itemView);
            cityItemView = itemView.findViewById(R.id.textView_city);
            tempItemView = itemView.findViewById(R.id.textView_temp);
            pressureItemView = itemView.findViewById(R.id.textView_pressure);
            humidityItemView = itemView.findViewById(R.id.textView_humidity);
            tempMinItemView = itemView.findViewById(R.id.textView_temp_min);
            tempMaxItemView = itemView.findViewById(R.id.textView_temp_max);
        }
    }

}
