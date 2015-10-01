package com.sb.android.myapplication.service;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.sb.android.myapplication.R;

/**
 * Created by student on 2015-10-01.
 */
public class MusicActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_PICK_MUSIC= 1;

    private ImageView mImageView;
    private MediaPlayer mMediaPlayer;

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
            case R.id.btn_play_pause:
                if (mMediaPlayer != null) {
                    if (!mMediaPlayer.isPlaying()) {
                        mMediaPlayer.start(); // no need to call prepare(); create() does that for you
                        ((ImageButton) v).setImageResource(android.R.drawable.ic_media_pause);
                    } else {
                        mMediaPlayer.pause();
                        ((ImageButton) v).setImageResource(android.R.drawable.ic_media_play);
                    }
                }
                break;
        }

    }

    private void pickFile(){
        // some Intent that points to whatever you like to play
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("audio/*");

        if(intent.resolveActivity(getPackageManager())!= null){
            startActivityForResult(
                    intent.createChooser(intent, "Select Music File"), REQUEST_PICK_MUSIC);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK &&
                requestCode == REQUEST_PICK_MUSIC) {
            ContentResolver cr = getContentResolver();
            Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            String selection =
                    "_id" + "= ? ";
            String[] selectionArgs = new String[]{

            };
            Cursor cursor = cr.query(uri,
                    null,
                    null,
                    null,
                    null);
            if (cursor == null) {
                Toast.makeText(this, "No media Files present",
                        Toast.LENGTH_SHORT).show();
            }
            int n= cursor.getCount();
            while (cursor.moveToNext()) {
                String ablum = cursor.getString(
                        cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));
                String title = cursor.getString(
                        cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                String artist = cursor.getString(
                        cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                String duration = cursor.getString(
                        cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));

                getSupportActionBar().setTitle(title + " - " + artist);

                mMediaPlayer = MediaPlayer.create(this, data.getData());
            }


            cursor.close();
        }
        // 정보들을 화면에 표시
//        if (resultCode == RESULT_OK &&
//                requestCode == REQUEST_PICK_MUSIC) {
//            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
//            retriever.setDataSource(getApplicationContext(), data.getData());
//
//            String album = retriever.extractMetadata(
//                    MediaMetadataRetriever.METADATA_KEY_ALBUM);
//            String title = retriever.extractMetadata(
//                    MediaMetadataRetriever.METADATA_KEY_TITLE);
//            String artist = retriever.extractMetadata(
//                    MediaMetadataRetriever.METADATA_KEY_ARTIST);
//            String duration = retriever.extractMetadata(
//                    MediaMetadataRetriever.METADATA_KEY_DURATION);
//
//            // 타이틀 변경
//            getSupportActionBar().setTitle(title + " - " + artist);
//
//            // 앨범 사진
//            byte albumImage[] = retriever.getEmbeddedPicture();
//            if (null != albumImage) {
//                Bitmap bitmap = BitmapFactory.decodeByteArray(albumImage, 0, albumImage.length);
//                mImageView.setImageBitmap(bitmap);
//            }
//
//            mMediaPlayer = MediaPlayer.create(this, data.getData());
//        }
    }
}