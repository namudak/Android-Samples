package com.sb.android.myapplication.service;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.sb.android.myapplication.R;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * Audio Player
 *
 * Created by student on 2015-10-01.
 */
public class MusicActivity extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    private static final String TAG = MusicActivity.class.getSimpleName();

    private static final int REQUEST_PICK_MUSIC = 1;

    private ImageView mImageView;
    private TextView mInfoTextView;
    private TextView mCurrentTimeTextView;
    private TextView mEndTimeTextView;
    private SeekBar mSeekBar;

    private SimpleDateFormat mFormat;

    private SeekBarUpdateTask mSeekBarUpdateTask;
    private ImageButton mPlayButton;

    private MediaMetadataCompat mMetaData;

    public static String getTime(long milliSeconds) {
        String result = "";

        int time = (int) milliSeconds / 1000;

        int hour = time / 3600;
        int minute = (time - (hour * 3600)) / 60;
        int second = time - ((hour * 3600) + (minute * 60));

        return String.format("%d:%02d", minute, second);
    }

    public static String getTimeToString(int time) {
        String result = "";

        if (time == 0) {
            result = "";
        } else if (time < 10) {
            result = "0" + String.valueOf(time);
        }

        return result;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_music);

        mPlayButton = (ImageButton) findViewById(R.id.btn_play_pause);
        mInfoTextView = (TextView) findViewById(R.id.tv_info);
        mCurrentTimeTextView = (TextView) findViewById(R.id.tv_current_time);
        mEndTimeTextView = (TextView) findViewById(R.id.tv_end_time);
        mSeekBar = (SeekBar) findViewById(R.id.seekBar);
        mImageView = (ImageView) findViewById(R.id.iv_thumbnail);

        mFormat = new SimpleDateFormat("mm:ss");

        findViewById(R.id.btn_file_pick).setOnClickListener(this);
        mPlayButton.setOnClickListener(this);
        mSeekBar.setOnSeekBarChangeListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_file_pick:
                pickFile();
                break;
            case R.id.btn_play_pause:
                // 뮤직 서비스에 Play 액션을 전달
                Intent intent = new Intent(this, MusicService.class);
                intent.setAction(MusicService.ACTION_PLAY);
                intent.putExtra("metadata", mMetaData);
                startService(intent);

                // 버튼 이미지 변경
                if (v.isSelected()) {
                    v.setSelected(false);
                } else {
                    v.setSelected(true);
                }

                break;
        }
    }

    private void pickFile() {
        // 음악 파일을 선택 하고
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("audio/*");

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(Intent.createChooser(intent, "음악 파일 선택"), REQUEST_PICK_MUSIC);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 정보들을 화면에 표시
        if (resultCode == RESULT_OK && requestCode == REQUEST_PICK_MUSIC) {
            // 초기화
            reset();

            loadInfo(data);

        }

    }

    private void loadInfo(Intent data) {
        Log.d(TAG, "loadInfo");

        Intent intent = new Intent(this, MusicService.class);
        intent.setAction(MusicService.ACTION_RESET);
        intent.putExtra("data", data.getData());

        startService(intent);

        // Audio information using content provider
        ContentResolver cr = getContentResolver();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] projection= {
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.ALBUM_ID,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DURATION
        };
        String selection =
                "_id" + "= ? ";
        String[] strArray= data.getDataString().split("/");
        String[] selectionArgs = new String[]{
                strArray[strArray.length- 1]
        };
        Cursor cursor = cr.query(uri,
                projection,
                selection,
                selectionArgs,
                null);

        String album = "", albumId = "", title = "", artist = "", duration = "";
        if (cursor == null) {
            Toast.makeText(this, "No media Files present",
                    Toast.LENGTH_SHORT).show();
            reset();
        } else {
            while (cursor.moveToNext()) {
                album = cursor.getString(
                        cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));
                albumId = cursor.getString(
                        cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID));
                title = cursor.getString(
                        cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                artist = cursor.getString(
                        cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                duration = cursor.getString(
                        cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
            }
        }

        // Title and artist @ actionbar
        try {
            getSupportActionBar().setTitle(title + " - " + artist);
        } catch (NullPointerException e){
            e.printStackTrace();
        }

        // Bitmap for album image
        Uri albumArtUri = ContentUris.withAppendedId(
                Uri.parse("content://media/external/audio/albumart"),
                Long.parseLong(albumId)
        );
        Bitmap bitmap= null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(
                    getApplicationContext().getContentResolver(), albumArtUri);
            mImageView.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }

        mMetaData = new MediaMetadataCompat.Builder()
                .putBitmap(MediaMetadataCompat.METADATA_KEY_ALBUM_ART, bitmap)
                .putString(MediaMetadataCompat.METADATA_KEY_ALBUM_ARTIST, artist)
                .putString(MediaMetadataCompat.METADATA_KEY_ALBUM, album)
                .putString(MediaMetadataCompat.METADATA_KEY_TITLE, title)
                .build();

        // 종료 시간 표시
        long lDuration = Long.parseLong(duration);
        String endTime = mFormat.format(lDuration);
        mEndTimeTextView.setText(endTime);
    }

    private void reset() {
        mCurrentTimeTextView.setText("0:00");
        mSeekBar.setProgress(0);
        mPlayButton.setSelected(false);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        // 유저가 SeekBar를 터치 한 것이면
        if (fromUser) {
            mCurrentTimeTextView.setText(getTime(progress));
            mService.getMediaPlayer().seekTo(progress);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    private class SeekBarUpdateTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            while (mService.getMediaPlayer() != null && mService.getMediaPlayer().isPlaying()) {
                publishProgress();

                // 1초 대기
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            mCurrentTimeTextView.setText(getTime(mService.getMediaPlayer().getCurrentPosition()));
            mSeekBar.setProgress(mService.getMediaPlayer().getCurrentPosition());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // 서비스 종료
//        stopService(new Intent(this, MusicService.class));
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Bind to LocalService
        Intent intent = new Intent(this, MusicService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();

        // Unbind from the service
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }

    }

    private MusicService mService;
    private boolean mBound;
    // 서비스 바인드 하기 위한 커넥션 객체
    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            MusicService.LocalBinder binder = (MusicService.LocalBinder) service;
            mService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };
}