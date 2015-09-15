package com.sb.android.myapplication.json;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.sb.android.myapplication.R;
import com.sb.android.myapplication.utility.network.NetworkUtility;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 2015-09-14.
 */
public class WeatherJson extends Activity {
    private static String TAG= WeatherJson.class.getSimpleName();
    private static String URL_FORECAST=
            "http://api.openweathermap.org/data/2.5/forecast/weather?q=%s&units=metric";

    private ProgressBar mProgressBar;
    private EditText mCityEditText;
    private ListView mWeatherListView;

    private WeatherItemAdapter mAdapter;

    private Handler mHandler= new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mWeatherListView.setAdapter(mAdapter);
            mProgressBar.setVisibility(View.GONE);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weatherjson);

        mProgressBar= (ProgressBar)findViewById(R.id.progressbar);
        mCityEditText= (EditText)findViewById(R.id.city_edit_text);
        mWeatherListView= (ListView)findViewById(R.id.weather_list_view);

        mCityEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    new RetrieveTask().execute(mCityEditText.getText().toString());
                    return true;
                }

                return false;
            }
        });

        //showWeatherInfo();
        new RetrieveTask().execute("suwon");
    }

    private class RetrieveTask extends AsyncTask<String, Void, Void> {

        @Override
        protected void onPreExecute() {

            mProgressBar.setVisibility(View.VISIBLE);

        }
        @Override
        protected Void doInBackground(String... params) {

            String query= params[0];//city

            try {
                String jsonString= NetworkUtility.getReturnString(
                        String.format(URL_FORECAST, query));

                JSONObject jsonObject= new JSONObject((jsonString));
                JSONArray jsonArray= jsonObject.getJSONArray("list");

                List<WeatherItem> weatherList= new ArrayList<WeatherItem>();

                for(int i= 0;  i< jsonArray.length(); i++) {
                    JSONObject object= jsonArray.getJSONObject(i);

                    String time= object.getString("dt_txt");
                    time= time.split(" ")[1].substring(0, 5);
                    String temp= object.getJSONObject("main").getString("temp");
                    String desc= (String) object.getJSONArray("weather").
                            getJSONObject(0).get("description");

                    weatherList.add(new WeatherItem(time, temp, desc));

                    mAdapter= new WeatherItemAdapter(WeatherJson.this, weatherList);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }



            return null;
        }
        @Override
        protected void onProgressUpdate(Void... values) {//as array
            super.onProgressUpdate(values);

        }
        @Override
        protected void onPostExecute(Void aVoid) {

            mWeatherListView.setAdapter(mAdapter);
            mProgressBar.setVisibility(View.GONE);
        }

        @Override
        protected void onCancelled() {

            Log.i(TAG, "Task is cancelled - 1");
        }

        @Override
        protected void onCancelled(Void aVoid) {

            Log.i(TAG, "Task is cancelled - 2");
        }
    }


}
