package com.sb.android.myapplication.json;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.sb.android.myapplication.R;
import com.sb.android.myapplication.utility.network.NetworkUtility;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 2015-09-14.
 */
public class WeatherJson extends Activity {
    private static String TAG= WeatherJson.class.getSimpleName();
    private static String URL_FORECAST=
            "http://api.openweathermap.org/data/2.5/forecast/weather?q=%s&units=metric&mode=%s";

    private ProgressBar mProgressBar;
    private EditText mCityEditText;
    private ListView mWeatherListView;

    private WeatherItemAdapter mAdapter;

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

    private class RetrieveTask extends AsyncTask<String, Void, List> {

        @Override
        protected void onPreExecute() {//UI

            mProgressBar.setVisibility(View.VISIBLE);

        }
        @Override
        protected List doInBackground(String... params) {//1st parameter

            String query= params[0];//city
            String mode= "xml";

            List<WeatherItem> weatherList= null;

            //publishProgress()

            String time= null, temp= null ,desc= null;
            try {
                String jsonString= NetworkUtility.getReturnString(
                        String.format(URL_FORECAST, query, mode));

                if(mode.equals("json")) {
                    JSONObject jsonObject = new JSONObject(jsonString);
                    JSONArray jsonArray = jsonObject.getJSONArray("list");

                    weatherList = new ArrayList<WeatherItem>();

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);

                        time = object.getString("dt_txt");
                        time = time.split(" ")[1].substring(0, 5);
                        temp = object.getJSONObject("main").getString("temp");
                        desc = (String) object.getJSONArray("weather").
                                getJSONObject(0).get("description");

                        weatherList.add(new WeatherItem(time, temp, desc));
                    }

                } else {
                    JSONObject jsonObject = XML.toJSONObject(jsonString);
                    JSONArray jsonArray = jsonObject.getJSONObject("weatherdata").
                            getJSONObject("forecast").getJSONArray("time");

                    weatherList = new ArrayList<WeatherItem>();

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);

                        time = object.getString("from");
                        time = time.split("T")[1].substring(0, 5);
                        temp = object.getJSONObject("temperature").getString("value");
                        desc = object.getJSONObject("symbol").getString("name");

                        weatherList.add(new WeatherItem(time, temp, desc));
                    }
                }



            } catch (Exception e) {
                e.printStackTrace();
            }

            return weatherList;
        }
        @Override
        protected void onProgressUpdate(Void...values) {//2nd parameter

        }

        @Override
        protected void onPostExecute(List list) {//3rd parameter

            mAdapter= new WeatherItemAdapter(WeatherJson.this, list);
            mWeatherListView.setAdapter(mAdapter);

            mProgressBar.setVisibility(View.GONE);
        }

    }


}
