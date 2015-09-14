package com.sb.android.myapplication.json;

/**
 * Created by student on 2015-09-14.
 */
public class WeatherItem {
    private String time;
    private String temp;
    private String desc;

    public WeatherItem(String time, String temp, String desc) {
        this.time= time;
        this.temp= temp;
        this.desc= desc;
    }
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return this.toString();
    }
}
