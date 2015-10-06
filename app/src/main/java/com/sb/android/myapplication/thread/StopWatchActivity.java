package com.sb.android.myapplication.thread;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sb.android.myapplication.R;


public class StopWatchActivity extends AppCompatActivity {

    private final int MODE_RUN= 1, MODE_STOP= 2, MODE_RESET= 3;
    private int mRunning= MODE_RUN;

    private TimerTask mTask = new TimerTask();
    private TextView mDisplay;
    private int mMils = 0;
    private int mSecs = 0;
    private int mMins = 0;
    private int mHour = 0;

    private final String LAPSETIME= "%04d:%02d:%02d:%02d";
    private final int MILUNIT= 1000, SECUNIT= 60, MINUNIT= 60;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mDisplay= (TextView) findViewById(R.id.textView);

        Button b1 = (Button) findViewById(R.id.start_button);
        b1.setOnClickListener(startTimer);

        Button b2 = (Button) findViewById(R.id.stop_button);
        b2.setOnClickListener(stopTimer);

        Button b3 = (Button) findViewById(R.id.reset_button);
        b3.setOnClickListener(resetTimer);

    }

    private View.OnClickListener startTimer = new View.OnClickListener() {
        public void onClick(View v) {
            if(mRunning== MODE_RUN) {
                try {
                    mTask.execute("");
                } catch (Exception e1) {
                    Toast.makeText(StopWatchActivity.this,
                            "Error starting: " + e1, Toast.LENGTH_LONG).show();
                }
            } else {
                mRunning= MODE_RUN;
            }
        }
    };

    private View.OnClickListener stopTimer = new View.OnClickListener() {
        public void onClick(View v) {
            mRunning= MODE_STOP;
        }
    };
    private View.OnClickListener resetTimer = new View.OnClickListener() {
        public void onClick(View v) {
            mRunning= MODE_RESET;
        }
    };
    /**
     * AsyncTask for stopwatch timer
     */
    public class TimerTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            while (true) {

                while (mMils < MILUNIT) {

                    if(mRunning!= MODE_STOP) {
                        if(mRunning== MODE_RESET){
                            mMils= mSecs= mMins= mHour= 0;
                            this.publishProgress(String.format(
                                    LAPSETIME, mMils, mSecs, mMins, mHour
                            ));
                            mRunning= MODE_RUN;
                        } else {
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
                                    LAPSETIME, mMils, mSecs, mMins, mHour
                            ));
                        }
                    }
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

