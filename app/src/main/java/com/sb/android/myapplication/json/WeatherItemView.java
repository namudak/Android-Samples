package com.sb.android.myapplication.json;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sb.android.myapplication.R;

/**
 * Created by student on 2015-09-14.
 */
public class WeatherItemView extends LinearLayout{

    private TextView mTimeTextView;
    private TextView mTempTextView;
    private TextView mDescTextView;

    public WeatherItemView(Context context) {
        super(context);
    }

    public WeatherItemView(Context context, WeatherItem aItem) {
        super(context);

        LayoutInflater layoutInflater= (LayoutInflater)context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);

        layoutInflater.inflate(R.layout.weatheritem_listview, this, true);

        mTimeTextView = (TextView)findViewById(R.id.time_text_view);
        mTimeTextView.setText(aItem.getTime());
        mTempTextView = (TextView)findViewById(R.id.temp_text_view);
        mTempTextView.setText(aItem.getTemp());
        mDescTextView = (TextView)findViewById(R.id.desc_text_view);
        mDescTextView.setText(aItem.getDesc());
    }

    // Set time editview as customed
    public void setTimeTextView(String time) {
        mTimeTextView.setText(time);}
    // Set temp editview as customed
    public void setTempTextView(String temp) {
        mTempTextView.setText(temp);}
    // set Weather editview as customed
    public void setDescTextView(String desc) {
        mDescTextView.setText(desc);}

}
