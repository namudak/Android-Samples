package com.sb.android.myapplication.mission;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sb.android.myapplication.R;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Mission07Activity extends AppCompatActivity implements View.OnClickListener {

    private CalendarMonthView monthView;
    private CalendarMonthAdapter monthViewAdapter;

    private TextView monthText;
    private int curYear, curMonth, selectedDay;

    private EditText mTodoEditText;
    private Button mSaveButton;

    // Member field for todo
    private Map<String, String> todo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission07);

        // Set calendarview to adapter
        monthView = (CalendarMonthView)findViewById(R.id.monthView);
        monthViewAdapter = new CalendarMonthAdapter(this);
        monthView.setAdapter(monthViewAdapter);

        // Set listener
        monthView.setOnDataSelectionListener(new OnDataSelectionListener() {
            public void onDataSelected(AdapterView parent, View v, int position, long id) {
                // Get selected day
                MonthItem curItem = (MonthItem) monthViewAdapter.getItem(position);
                selectedDay = curItem.getDay();
                Log.d("CalendarMonthView", "Selected : " + selectedDay);

                mTodoEditText.setText(todo.get(
                        String.format(""+ curYear+ curMonth+ selectedDay)));

            }
        });


        monthText = (TextView) findViewById(R.id.monthText);
        setMonthText();

        // Event of previous month
        Button monthPrevious = (Button) findViewById(R.id.monthPrevious);
        monthPrevious.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                monthViewAdapter.setPreviousMonth();
                monthViewAdapter.notifyDataSetChanged();

                setMonthText();
            }
        });

        // Event of next month
        Button monthNext = (Button) findViewById(R.id.monthNext);
        monthNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                monthViewAdapter.setNextMonth();
                monthViewAdapter.notifyDataSetChanged();

                setMonthText();
            }
        });

        // Lisk member fields to layout and listener
        mTodoEditText = (EditText)findViewById(R.id.todo_edit_text);

        mSaveButton = (Button)findViewById(R.id.save_button);
        mSaveButton.setOnClickListener(this);

        // Initialize Todo and today as selected
        todo= new HashMap<>();
    }

    @Override
    public void onClick(View v) {
        if(v.getId()== R.id.save_button &&
                mTodoEditText.getText()!= null) {
            String key= String.format("" + curYear + curMonth + selectedDay);
            String val= mTodoEditText.getText().toString();
            todo.put(key, val);
        }
    }

    /**
     * Set selected month as title
     */
    private void setMonthText() {
        curYear = monthViewAdapter.getCurYear();
        curMonth = monthViewAdapter.getCurMonth();

        monthText.setText(curYear + "년 " + (curMonth+ 1) + "월");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mission07, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
