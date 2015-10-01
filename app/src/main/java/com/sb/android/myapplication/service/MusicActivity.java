package com.sb.android.myapplication.service;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.sb.android.myapplication.R;

/**
 * Created by student on 2015-10-01.
 */
public class MusicActivity extends AppCompatActivity implements View.OnClickListener {

    private int REQUEST_PICK_MUSIC= 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_music);

        findViewById(R.id.btn_file_pick).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_file_pick:
                pickFile();
                break;
        }

    }

    private void pickFile(){
        // some Intent that points to whatever you like to play
        Intent play = new Intent(Intent.ACTION_GET_CONTENT);
        play.setType("audio/*");

        if(play.resolveActivity(getPackageManager())!= null){
            startActivityForResult(play.createChooser(play,
                        "Select Music File"), REQUEST_PICK_MUSIC);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode== RESULT_OK || requestCode== REQUEST_PICK_MUSIC){
            getSupportActionBar().setTitle("Title");

        }
    }
}