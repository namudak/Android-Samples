package com.sb.android.myapplication.mission;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sb.android.myapplication.R;

import java.util.Calendar;

public class Mission05Activity extends AppCompatActivity implements View.OnClickListener{
    private final String BIRTHFORMAT= "%d년 %d월 %d일";
    // Member fields for layout
    private EditText mNameEditText;
    private EditText mAgeEditText;
    private TextView mBirthTextView;
    private Button mSaveButton;
    // Member fields for birth
    private int mYear;
    private int mMonth;
    private int mDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission05);

        // Link member fields to layout
        mNameEditText = (EditText)findViewById(R.id.name_edit_text);
        mAgeEditText = (EditText)findViewById(R.id.age_edit_text);
        mBirthTextView = (TextView)findViewById(R.id.birth_text_view);
        mSaveButton = (Button)findViewById(R.id.save_button);

        // Set today as default
        Calendar cal= Calendar.getInstance();
        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDate = cal.get(Calendar.DATE);

        String result= String.format(BIRTHFORMAT, mYear, mMonth+ 1, mDate);

        mBirthTextView.setText(result);

        // Set listeners
        mBirthTextView.setOnClickListener(this);
        mSaveButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.birth_text_view:
                DatePickerDialog dialog = new DatePickerDialog(Mission05Activity.this,
                        listener, mYear, mMonth - 1, mDate);
                dialog.show();
                break;
            case R.id.save_button:
                String birthymd= String.format(BIRTHFORMAT, mYear, mMonth+ 1, mDate);
                String result= "Name: "+ mNameEditText.getText().toString()+
                        " Age: "+ mAgeEditText.getText().toString()+
                        " Birth Day: "+ birthymd;
                Toast.makeText(Mission05Activity.this, result, Toast.LENGTH_SHORT).show();
                break;
        }


    }

    private DatePickerDialog.OnDateSetListener listener= new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYear= year;
            mMonth= monthOfYear;
            mDate= dayOfMonth;

            String result= String.format(BIRTHFORMAT, mYear, mMonth+ 1, mDate);
            mBirthTextView.setText(result);
        }
    };

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
