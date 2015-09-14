package com.sb.android.myapplication.mission;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sb.android.myapplication.R;
import com.sb.android.myapplication.mission_classes.CustomEditText;

public class Mission02Activity extends AppCompatActivity
        implements View.OnClickListener, CustomEditText.OnTextLengthListener {

    private final String PRGRESSTEXT= "%d / 80 Bytes";
    private final int MAXTEXTLENGTH= 80;

    //private EditText mSmsEditText;
    private TextView mProgressTextView;
    private Button mSendButton;
    private Button mCloseButton;

    private CustomEditText mSmsEditText;
    private int mTextLength;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission02);

        mSmsEditText= (CustomEditText)findViewById(R.id.sms_edittext);
        mProgressTextView= (TextView)findViewById(R.id.progress_textview);
        mSendButton= (Button)findViewById(R.id.send_button);
        mCloseButton= (Button)findViewById(R.id.close_button);

        // Initialize progress data and textlength to zero
        mProgressTextView.setText(String.format(PRGRESSTEXT, 0));
        mTextLength= 0;

        // Set Listener to mSmsEditText
        mSmsEditText.setOnTextLengthListener(this);
        mSendButton.setOnClickListener(this);
        mCloseButton.setOnClickListener(this);
    }
    // Implemented for CustomEditText
    @Override
    public void onTextLength(int length){
        if(length<= MAXTEXTLENGTH)
            mProgressTextView.setText(String.format(PRGRESSTEXT, length));
    }
    // Implemented for OnClickListener
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.send_button :
                Toast.makeText(getApplicationContext(), mSmsEditText.getText().toString(),
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.close_button :
                finish();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mission02, menu);
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
