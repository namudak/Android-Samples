package com.sb.android.myapplication.json;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public class WeatherItemAdapter extends BaseAdapter {
    private final Context mContext;

    private List<WeatherItem> mWeatherItem;

    public WeatherItemAdapter(Context context, List<WeatherItem> data) {
        this.mContext= context;
        this.mWeatherItem= data;
    }
    @Override
    public int getCount() { return mWeatherItem.size(); }
    @Override
    public Object getItem (int position) { return mWeatherItem.get(position); }
    @Override
    public long getItemId(int position) { return position; }
    /**
     * Item's layout
     * @param position
     * @param convertView
     * @param parent
     * @return View
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        String TIMEFORMAT= "%s";
        String TEMPFORMST= "%s";
        String DESCFORMST= "%s";

        WeatherItemView itemView;

        WeatherItem aItem= mWeatherItem.get(position);

        if(convertView== null) {
            itemView= new WeatherItemView(mContext, aItem);

        } else {
            itemView= (WeatherItemView)convertView;
        }

        itemView.setTimeTextView(String.format(TIMEFORMAT, aItem.getTime()));
        itemView.setTempTextView(String.format(TEMPFORMST, aItem.getTemp()+ "℃"));
        itemView.setDescTextView(String.format(DESCFORMST, aItem.getDesc()));

        // Return view
        return itemView;
    }


}
