package com.sb.android.myapplication.thread;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sb.android.myapplication.R;

public class StopWatchActivity extends AppCompatActivity {

    private boolean mRunning = true;

    private TimerTask mTask = new TimerTask();
    private TextView mDisplay = (TextView) findViewById(R.id.textView);
    private int mMils = 0;
    private int mSecs = 0;
    private int mMins = 0;
    private int mHour = 0;

    private final String LAPSETIME= "%d:%d:%d:%d";
    private final int MILUNIT= 1000, SECUNIT= 60, MINUNIT= 60;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch);

        Button b1 = (Button) findViewById(R.id.start_button);
        b1.setOnClickListener(startTimer);

        Button b2 = (Button) findViewById(R.id.stop_button);
        b2.setOnClickListener(stopTimer);

        Button b3 = (Button) findViewById(R.id.reset_button);
        b2.setOnClickListener(resetTimer);

    }

    private View.OnClickListener startTimer = new View.OnClickListener() {
        public void onClick(View v) {
            if(!mRunning){
                startWatch();
            }
            else if(mRunning){
                try{
                    mTask.execute(""); //start the task
                }
                catch(Exception e1){
                    Toast.makeText(StopWatchActivity.this,
                            "Error starting: " + e1, Toast.LENGTH_LONG).show();
                }
            }
        }
    };

    private View.OnClickListener stopTimer = new View.OnClickListener() {
        public void onClick(View v) {
            if(mRunning){
                stopWatch();
            }
        }
    };
    private View.OnClickListener resetTimer = new View.OnClickListener() {
        public void onClick(View v) {
            if(mRunning){
                resetWatch();
            }
        }
    };
    public void startWatch(){
        mRunning = true;
    }
    public void stopWatch(){
        mRunning= false;
    }
    public void resetWatch(){
        mRunning= false;
        mDisplay.setText(String.format(LAPSETIME, 0, 0, 0, 0));
    }
    /**
     * AsyncTask for stopwatch timer
     */
    public class TimerTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            while (true) {

                while (mMils < MILUNIT && mRunning) {

                    mRunning = true;
                    mMils++;

                    try {
                        Thread.sleep(1);
                    } catch (Exception e1) {
                        System.out.println(e1);
                    }

                    if (mMils == MILUNIT) {
                        mMils = 0;
                        mSecs++;
                    }
                    if (mSecs == SECUNIT) {
                        mSecs = 0;
                        mMins++;
                    }
                    if (mMins == MINUNIT) {
                        mHour++;
                        mMins = 0;
                    }

                    this.publishProgress(String.format(
                            LAPSETIME, mMils, mSecs, mMins, mHour));
                }
            }
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            mDisplay.setText(values[0]);
        }

    }

}

