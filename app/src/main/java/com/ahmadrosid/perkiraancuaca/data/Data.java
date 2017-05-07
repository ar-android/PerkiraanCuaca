package com.ahmadrosid.perkiraancuaca.data;

import android.net.Uri;
import android.os.AsyncTask;

import com.ahmadrosid.perkiraancuaca.BuildConfig;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ocittwo on 5/1/17.
 *
 * @Author Ahmad Rosid
 * @Email ocittwo@gmail.com
 * @Github https://github.com/ar-android
 * @Web http://ahmadrosid.com
 */

public class Data {

    private static final String TAG = "Data";

    public interface DataCallback {
        void onResponse(String json);
        void onError(Throwable throwable);
    }

    private DataCallback callback;

    public Data(DataCallback callback) {
        this.callback = callback;
    }

    public void process(){
        String url = buildUrl();
        new DataProcess().execute(url);
    }

    public String buildUrl() {
        final String FORECAST_BASE_URL =
                "http://api.openweathermap.org/data/2.5/forecast/daily?";
        final String QUERY_PARAM = "q";
        final String FORMAT_PARAM = "mode";
        final String UNITS_PARAM = "units";
        final String DAYS_PARAM = "cnt";
        final String APPID_PARAM = "APPID";

        String format = "json";
        String units = "metric";
        int numDays = 14;
        String locationQuery = "94043";

        Uri builtUri = Uri.parse(FORECAST_BASE_URL).buildUpon()
                .appendQueryParameter(QUERY_PARAM, locationQuery)
                .appendQueryParameter(FORMAT_PARAM, format)
                .appendQueryParameter(UNITS_PARAM, units)
                .appendQueryParameter(DAYS_PARAM, Integer.toString(numDays))
                .appendQueryParameter(APPID_PARAM, BuildConfig.API_KEY)
                .build();

        return builtUri.toString();
    }

    public class DataProcess extends AsyncTask<String, String, String> {

        private HttpURLConnection urlConnection;
        private BufferedReader reader;
        private String json;

        @Override protected String doInBackground(String... url) {

            try {

                URL urls = new URL(url[0]);
                urlConnection = (HttpURLConnection) urls.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();

                if (inputStream == null) {
                    return null;
                }

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }

                json = buffer.toString();

            } catch (MalformedURLException e) {
                callback.onError(e);
            } catch (IOException e) {
                callback.onError(e);
            }

            return json;
        }

        @Override protected void onPostExecute(String s) {
            super.onPostExecute(s);
            callback.onResponse(s);
        }
    }
}
