package com.sb.android.myapplication.activity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by student on 2015-09-11.
 */
public class TodoItem implements Parcelable{
    private int _id;
    private String time;
    private String weather;
    private String hour;
    private String min;
    private String todo;

    // Constructors
    public TodoItem() {}
    public TodoItem(int id, String time,
                    String hour, String min, String todo, String weather){
        this._id= id;
        this.time= time;
        this.hour= hour;
        this.min= min;
        this.todo= todo;
        this.weather= weather;

    }

    // Getters and Setters
    public int getId() {return _id;}
    public String getTime() {return time;}
    public String getWeather() {return weather;}
    public String getHour() {return hour;}
    public String getMin() {return min;}
    public String getTodo() {return todo;}

    public void setId(int id) {this._id= id;}
    public void setTime(String time){this.time= time;}
    public void setWeather(String weather) {this.weather= weather;}
    public void setHour(String hour) {this.hour= hour;}
    public void setMin(String min) {this.min= min;}
    public void setTodo(String todo) {this.todo= todo;}

    protected TodoItem(Parcel in) {
        _id = in.readInt();
        time = in.readString();
        weather = in.readString();
        hour = in.readString();
        min = in.readString();
        todo = in.readString();
    }

    public static final Creator<TodoItem> CREATOR = new Creator<TodoItem>() {
        @Override
        public TodoItem createFromParcel(Parcel in) {
            return new TodoItem(in);
        }

        @Override
        public TodoItem[] newArray(int size) {
            return new TodoItem[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(_id);
        dest.writeString(time);
        dest.writeString(weather);
        dest.writeString(hour);
        dest.writeString(min);
        dest.writeString(todo);
    }
}
