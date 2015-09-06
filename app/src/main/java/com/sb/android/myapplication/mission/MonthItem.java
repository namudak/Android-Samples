package com.sb.android.myapplication.mission;

/**
 * Created by Administrator on 2015-09-05.
 */
public class MonthItem {

    private int dayValue;

    // Constructors
    public MonthItem() {}
    public MonthItem(int day) {
        dayValue= day;
    }

    // Getter
    public int getDay() { return dayValue; }

    // Setter
    public void setDay(int day) { this.dayValue = day; }
}
