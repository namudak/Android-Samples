package com.sb.android.myapplication.json;

/**
 * Created by student on 2015-09-14.
 */
public class WeatherItem {
    private String time;
    private String curTemp;
    private String desc;

    public WeatherItem(String time, String curTemp, String desc) {
        this.time= time;
        this.curTemp= curTemp;
        this.desc= desc;
    }
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTemp() {
        return curTemp;
    }

    public void setTemp(String curTemp) {
        this.curTemp = curTemp;
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
