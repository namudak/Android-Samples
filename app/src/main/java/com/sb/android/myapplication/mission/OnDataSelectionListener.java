package com.sb.android.myapplication.mission;

import android.view.View;
import android.widget.AdapterView;

/**
 * Created by Administrator on 2015-09-05.
 */
public interface OnDataSelectionListener {
    /**
     * The calling method when item was selected
     *
     * @param parent Parent View
     * @param v Target View
     * @param row Row Index
     * @param column Column Index
     * @param id ID for the View
     */
    public void onDataSelected(AdapterView parent, View v, int position, long id);

}
