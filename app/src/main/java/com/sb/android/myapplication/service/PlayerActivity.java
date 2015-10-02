package com.sb.android.myapplication.service;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.sb.android.myapplication.R;

import java.io.IOException;

/**
 * Created by student on 2015-10-02.
 */
public class PlayerActivity extends Activity implements SurfaceHolder.Callback {
    String path;
    private MediaPlayer mp;
    private SurfaceView mPreview;
    private SurfaceHolder holder;
    boolean pausing = false;
    public static String filepath;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        getWindow().setFormat(PixelFormat.UNKNOWN);
        mPreview = (SurfaceView)findViewById(R.id.iv_thumbnail);
        holder = mPreview.getHolder();
        holder.setFixedSize(800, 480);
        holder.addCallback(this);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        mp = new MediaPlayer();


//        try{
//            Intent intent = getIntent();
//
//            Uri fileuri = intent.getData();
//            filepath=fileuri.getPath();
//        }catch(Exception e){}


    }
    protected void onPause(){
        super.onPause();
        mp.release();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        // TODO Auto-generated method stub

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // TODO Auto-generated method stub
        mp.setDisplay(holder);
        play();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO Auto-generated method stub

    }
    void play(){
        try {
            mp.setDataSource(filepath);

            mp.prepare();

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mp.start();
    }
}