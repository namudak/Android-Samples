package com.sb.android.myapplication.mission;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Administrator on 2015-09-05.
 */
public class MonthItemView extends TextView {

    private MonthItem item;

    // Constructors
    public MonthItemView(Context context) {
        super(context);

        init();
    }
    public MonthItemView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init() {

        setBackgroundColor(Color.WHITE);
    }

    // Getter
    public MonthItem getItem() {
        return item;
    }

    // Setter
    public void setItem(MonthItem item) {
        this.item = item;

        int day= item.getDay();
        if (day!= 0) {

            setText(String.valueOf(day));
        } else {
            setText("");
        }
    }


}

