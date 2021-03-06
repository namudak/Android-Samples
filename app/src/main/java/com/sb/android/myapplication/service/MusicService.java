package com.sb.android.myapplication.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.MediaSessionCompat.Callback;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.sb.android.myapplication.R;

/**
 * Created by Administrator on 2015-10-05.
 */
public class MusicService extends Service {
    private static final String TAG = MusicService.class.getSimpleName();

    public static final String ACTION_PLAY = "com.example.suwonsmartapp.androidexam.musicplayer.ACTION_TEST";
    public static final String ACTION_PAUSE = "com.example.suwonsmartapp.androidexam.musicplayer.ACTION_PAUSE";
    public static final String ACTION_RESET = "com.example.suwonsmartapp.androidexam.musicplayer.ACTION_RESET";

    // Binder given to clients
    private final IBinder mBinder = new LocalBinder();

    private MediaPlayer mMediaPlayer;
    private MediaSessionCompat mSession;
    private MediaControllerCompat mController;

    public MusicService() {
    }

    public class LocalBinder extends Binder {
        MusicService getService() {
            // Return this instance of LocalService so clients can call public methods
            return MusicService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "뮤직 서비스 시작!!");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }

        Log.d(TAG, "뮤직 서비스 종료!!");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String action = intent.getAction();

        switch (action) {
            case ACTION_RESET:
                Log.d(TAG, "뮤직 서비스 : RESET");
                if (mMediaPlayer != null) {
                    mMediaPlayer.reset();
                }
                Uri data = intent.getParcelableExtra("data");
                mMediaPlayer = MediaPlayer.create(this, data);
                break;
            case ACTION_PLAY:
                // 세션에 메타데이터 셋 (Notification 에서 사용할 것임)
                MediaMetadataCompat metadata = intent.getParcelableExtra("metadata");
                if (mSession == null) {
                    mSession = new MediaSessionCompat(this, "tag", null, null);
                    mSession.setMetadata(metadata);
                    mSession.setActive(true);
                    mSession.setCallback(new Callback() {
                        @Override
                        public void onPlay() {
                            super.onPlay();
                            Toast.makeText(MusicService.this, "onPlay", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                if (!mMediaPlayer.isPlaying()) {
                    Log.d(TAG, "뮤직 서비스 : PLAY");
                    mMediaPlayer.start(); // no need to call prepare(); create() does that for you
                } else {
                    Log.d(TAG, "뮤직 서비스 : PAUSE");
                    mMediaPlayer.pause();
                }
                showNotification(metadata);


                break;
            case ACTION_PAUSE:
                mMediaPlayer.pause();
                break;
        }

        return START_NOT_STICKY;    // 강제종료 되었을 때 재시작 하지 않는다

//        return START_STICKY;    // 서비스를 재시동 할 때 intent 를 null 을 받으며 재시동 한다
//        return START_REDELIVER_INTENT;      // 서비스가 재시동 될 때 종료 직전의 intent를 다시 받아서 재시동 한다
//        return START_STICKY_COMPATIBILITY;      // 재시동 보장 안 됨. 거의 안 쓸 것 같음
    }

    private void showNotification(MediaMetadataCompat metadata) {
        // Notification 작성
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
        // Hide the timestamp
        builder.setShowWhen(false);
        // Set the Notification style
        builder.setStyle(new NotificationCompat.MediaStyle()
                .setMediaSession(mSession.getSessionToken())
                .setShowActionsInCompactView(0, 1, 2));
        // Set the Notification color
        builder.setColor(0xFFDB4437);
        // Set the large and small icons
        Bitmap bitmap = metadata.getBitmap(MediaMetadataCompat.METADATA_KEY_ALBUM_ART);
        if (bitmap == null) {
            bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        }
        builder.setLargeIcon(bitmap);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle(metadata.getString(MediaMetadataCompat.METADATA_KEY_TITLE));
        builder.setContentText(metadata.getString(MediaMetadataCompat.METADATA_KEY_ALBUM) + "\n" +
                metadata.getString(MediaMetadataCompat.METADATA_KEY_ARTIST));
        builder.addAction(android.R.drawable.ic_media_previous, "prev", null);

        int icon;
        if (mMediaPlayer.isPlaying()) {
            icon = android.R.drawable.ic_media_pause;
        } else {
            icon = android.R.drawable.ic_media_play;
        }
        Intent musicStopIntent = new Intent(getApplicationContext(), MusicService.class);
        musicStopIntent.putExtra("metadata", metadata);
        musicStopIntent.setAction(ACTION_PLAY);

        PendingIntent musicStopPendingIntent = PendingIntent.getService(getApplicationContext(),
                2,
                musicStopIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        builder.addAction(icon, "pause", musicStopPendingIntent);
        builder.addAction(android.R.drawable.ic_media_next, "next", null);

        // Notification 터치 했을 때 실행할 PendingIntent 지정
        Intent activityStartIntent = new Intent(getApplicationContext(), MusicActivity.class);
        PendingIntent activityStartPendingIntent = PendingIntent.getActivity(getApplicationContext(),
                1,
                activityStartIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(activityStartPendingIntent);

        // Notification 띄우기
        ((NotificationManager) getSystemService(NOTIFICATION_SERVICE)).notify(1, builder.build());
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "뮤직 서비스 : onBind");
        return mBinder;
    }

    @Override
    public void unbindService(ServiceConnection conn) {
        Log.d(TAG, "뮤직 서비스 : unBind");
        super.unbindService(conn);
    }

    public MediaPlayer getMediaPlayer() {
        return mMediaPlayer;
    }

}