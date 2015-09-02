package com.sb.android.myapplication.layout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.sb.android.myapplication.R;

public class FrameLayoutActivity extends AppCompatActivity {

    private Button mChangeButton;
    private ImageView mImageView1;
    private ImageView mImageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_layout);

        mChangeButton= (Button)findViewById(R.id.change);
        mImageView1= (ImageView)findViewById(R.id.image1);
        mImageView2= (ImageView)findViewById(R.id.image2);

        mChangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeImage();
            }
        });
    }

    public void changeImage(){

        mImageView1.setVisibility(mImageView1.getVisibility()== View.VISIBLE
                ? View.INVISIBLE :View.VISIBLE);
        mImageView2.setVisibility(mImageView2.getVisibility()== View.VISIBLE
                ? View.INVISIBLE :View.VISIBLE);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_frame_layout, menu);
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
