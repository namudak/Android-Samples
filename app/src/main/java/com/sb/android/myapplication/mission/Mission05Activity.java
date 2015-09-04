package com.sb.android.myapplication.mission;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.sb.android.myapplication.R;

import java.util.Calendar;
import java.util.Date;

public class Mission05Activity extends AppCompatActivity implements View.OnClickListener{

    private EditText mNameEditText;
    private EditText mAgeEditText;
    private TextView mBirthTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission05);

        // Make member fields
        mNameEditText = (EditText)findViewById(R.id.name_edit_text);
        mAgeEditText = (EditText)findViewById(R.id.age_edit_text);
        mBirthTextView = (TextView)findViewById(R.id.birth_text_view);

        // Set today as default
        Calendar cal= Calendar.getInstance();
        Date today= new Date();



    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mission05, menu);
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
