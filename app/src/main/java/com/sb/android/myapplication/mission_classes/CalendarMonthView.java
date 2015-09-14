package com.sb.android.myapplication.mission_classes;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;

/**
 * Created by Administrator on 2015-09-05.
 */
public class CalendarMonthView extends GridView {

    // Listener object for day selection
    private OnDataSelectionListener selectionListener;

    // Adapter object
    private CalendarMonthAdapter adapter;

    // Constructors
    public CalendarMonthView(Context context) {
        super(context);

        init();
    }
    public CalendarMonthView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    // Initialize attributes
    private void init() {
        setBackgroundColor(Color.GRAY);
        setVerticalSpacing(1);
        setHorizontalSpacing(1);
        setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
        //setStretchMode(GridView.STRETCH_SPACING_UNIFORM);

        // Column number
        setNumColumns(7);

        // Set listener of gridview
        setOnItemClickListener(new OnItemClickAdapter());
    }

    /**
     * Set adapter
     *
     * @param adapter
     */
    public void setAdapter(BaseAdapter adapter) {
        super.setAdapter(adapter);

        this.adapter = (CalendarMonthAdapter)adapter;
    }

    /**
     * Return adapter object
     *
     * @return
     */
    public BaseAdapter getAdapter() {
        return (BaseAdapter) super.getAdapter();
    }

    /**
     * Set listener
     *
     * @param listener
     */
    public void setOnDataSelectionListener(OnDataSelectionListener listener) {
        this.selectionListener = listener;
    }

    /**
     * Return listener object
     *
     * @return
     */
    public OnDataSelectionListener getOnDataSelectionListener() {
        return selectionListener;
    }

    class OnItemClickAdapter implements OnItemClickListener {

        public OnItemClickAdapter() {}

        public void onItemClick(AdapterView parent, View v, int position, long id) {

            if (adapter != null) {
                adapter.setSelectedPosition(position);
                adapter.notifyDataSetInvalidated();
            }

            if (selectionListener != null) {
                selectionListener.onDataSelected(parent, v, position, id);
            }

        }

    }

}