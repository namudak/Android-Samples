package com.sb.android.myapplication.mission;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.sb.android.myapplication.R;

public class Mission01Activity extends AppCompatActivity implements View.OnClickListener {

    private Button mUpButton;
    private Button mDownButton;
    private ImageView mImage1;
    private ImageView mImage2;

    private BitmapDrawable mBitmap;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mission01);

        mImage1= (ImageView)findViewById(R.id.image1);
        mImage2= (ImageView)findViewById(R.id.image2);

        mUpButton= (Button)findViewById(R.id.up);
        mUpButton.setOnClickListener(this);

        mDownButton= (Button)findViewById(R.id.down);
        mDownButton.setOnClickListener(this);

        Resources res= getResources();
        mBitmap= (BitmapDrawable)res.getDrawable(R.drawable.ronda, null);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mission01, menu);
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

    @Override
    public void onClick(View v) {

        switch(v.getId()) {
            case R.id.up:
                mImage1.setImageDrawable(mBitmap);
                mImage2.setImageDrawable(null);
                break;
            case R.id.down:
                mImage1.setImageDrawable(null);
                mImage2.setImageDrawable(mBitmap);
                break;
        }

    }
}
