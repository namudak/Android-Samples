package com.sb.android.myapplication.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.sb.android.myapplication.R;

import java.util.Random;

public class FragmentActivity extends AppCompatActivity
                                implements View.OnClickListener {

    private ServiceFragment mColorFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mColorFragment = (ServiceFragment)getSupportFragmentManager().
                                                findFragmentById(R.id.fragment);

        findViewById(R.id.button).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        mColorFragment.setColor(getRandomColor());
    }

    private int getRandomColor() {
        Random random= new Random();

        return Color.argb(255, random.nextInt(256),
                random.nextInt(256), random.nextInt(256));
    }
}
