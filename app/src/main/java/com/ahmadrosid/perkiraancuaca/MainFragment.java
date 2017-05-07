package com.ahmadrosid.perkiraancuaca;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ahmadrosid.perkiraancuaca.data.Data;
import com.ahmadrosid.perkiraancuaca.data.WeatherData;

import org.json.JSONException;

/**
 * Created by ocittwo on 4/23/17.
 *
 * @Author Ahmad Rosid
 * @Email ocittwo@gmail.com
 * @Github https://github.com/ar-android
 * @Web http://ahmadrosid.com
 */

public class MainFragment extends Fragment {

    private static final String TAG = "MainFragment";

    private ListView listview_forecast;

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listview_forecast = (ListView) view.findViewById(R.id.listview_forecast);
        final ForecastListAdapter adapter = new ForecastListAdapter(getContext());

        new Data(new Data.DataCallback() {
            @Override public void onResponse(String json) {
                Log.d(TAG, "onResponse: "+json);

                WeatherData data = new WeatherData();
                try {
                    data.getWeatherDataFromJson(json);
                    adapter.updateData(data.getModelWeatherList());
                    listview_forecast.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override public void onError(Throwable throwable) {
                Log.e(TAG, "onError: ", throwable);
            }
        }).process();
    }
}