package com.sb.android.myapplication.json;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.sb.android.myapplication.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
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
                if(keyCode== KeyEvent.KEYCODE_ENTER) {
                    showWeatherInfo();
                    return true;
                }

                return false;
            }
        });

        showWeatherInfo();
    }

    public void showWeatherInfo() {
        mProgressBar.setVisibility(View.VISIBLE);

        final String aCity= mCityEditText.getText().toString();

        // Need thread on network task
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String jsonString= getReturnString(getUrlConnection(aCity));

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
                    }

                    mAdapter= new WeatherItemAdapter(WeatherJson.this, weatherList);

                    mHandler.sendEmptyMessage(0);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }){}.start();

    }


    /**
     * getUrlConnection
     * @Note : url 커넥션
     * @return
     * @throws Exception
     *
     *
     */
    public static URLConnection getUrlConnection( String city )
            throws Exception {

        if(city.equals("")) city= "suwon";

        // URL 조합
        String urlString = String.format(URL_FORECAST, city);

        URL url = new URL( urlString ); // 넘어오는 URL밎정보
        URLConnection connection = url.openConnection(); // 커넥션
        connection.setDoOutput( true );
        return connection;
    }


    /**
     * getReturnString
     * @Note : 커넥션된 결과값
     * @param connection
     * @return
     * @throws IOException
     *
     *
     */
    public static String getReturnString( URLConnection connection )
            throws IOException {
        BufferedReader in = new BufferedReader( new InputStreamReader(
                connection.getInputStream(), "UTF-8" ) ); // 반환되는 값이 UTF-8 경우
        StringBuffer buffer = new StringBuffer();
        String decodedString;

        while( ( decodedString = in.readLine() ) != null ) {
            buffer.append( decodedString );

        }

        in.close();

        return buffer.toString();
    }
}
