package com.sb.android.myapplication.thread;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sb.android.myapplication.R;

public class ThreadActivity extends AppCompatActivity {
    private static final String TAG = ThreadActivity.class.getSimpleName();

    private Button mThread2Button;
    private TextView mNumberTextView;
    private ProgressBar mProgressBar;

    private DownloadTask mDownloadtask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        mThread2Button= (Button)findViewById(R.id.thread_button);
        mNumberTextView= (TextView)findViewById(R.id.number_text_view);
        mProgressBar= (ProgressBar)findViewById(R.id.progressbar);

        mThread2Button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.thread_button:
                        if (mDownloadtask == null ||
                                mDownloadtask.getStatus() == AsyncTask.Status.FINISHED) {
                            mDownloadtask = new DownloadTask();
                            mDownloadtask.execute();
                        }
                        break;
                }
            }

        });
    }

private class DownloadTask extends AsyncTask<Void, Integer, Void> {
    private AlertDialog.Builder aBuilder;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        aBuilder= new AlertDialog.Builder(ThreadActivity.this);
        aBuilder.setMessage("Download complete");
        aBuilder.setNegativeButton("Close", null);

        mProgressBar.setProgress(0);
    }
    @Override
    protected Void doInBackground(Void... params) {
        for(int i= 0; i< 100; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Log.e("", e.getMessage());
            }
            // call onProgressUpdate
            publishProgress(i+ 1);
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {//as array
        super.onProgressUpdate(values);

        mProgressBar.setProgress(values[0]);
        mNumberTextView.setText(values[0] + "%");
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        aBuilder.show();
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();

        Log.i(TAG, "Task is cancelled - 1");
    }

    @Override
    protected void onCancelled(Void aVoid) {
        super.onCancelled(aVoid);

        Log.i(TAG, "Task is cancelled - 2");
    }

}

    @Override
    protected void onStart() {
        super.onStart();

        Log.d("", "onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.d("", "onRestrat");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d("", "onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d("", "onDestroy");

        if(mDownloadtask!= null) {
            mDownloadtask.cancel(true);
            mDownloadtask = null;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d("", "onStop");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("", "onResume");
    }

}
