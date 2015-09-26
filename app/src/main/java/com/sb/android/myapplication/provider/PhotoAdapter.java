package com.sb.android.myapplication.provider;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v4.widget.CursorAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.suwonsmartapp.abl.AsyncBitmapLoader;

/**
 * Created by student on 2015-09-25.
 */
public class PhotoAdapter extends CursorAdapter implements
                            AsyncBitmapLoader.BitmapLoadListener {

    PhotoView itemView;

    // Dynamic bitmap loader
    private AsyncBitmapLoader mAsyncBitmapLoader;

    public PhotoAdapter(Context context, Cursor c, boolean autoRequery) {

        super(context, c, autoRequery);

        // Create and envent link dynamic bitmap loader
        mAsyncBitmapLoader = new AsyncBitmapLoader(context);
        mAsyncBitmapLoader.setBitmapLoadListener(this);
    }

    // Initialize layout
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        Bitmap aItem = getBitmap(cursor.getPosition());

        itemView = new PhotoView(context, aItem);

        return itemView;
    }

    // Set active data
    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        itemView = (PhotoView) view;

        // 이미지 셋팅
        mAsyncBitmapLoader.loadBitmap(cursor.getPosition(), itemView.getImageView());

    }

    @Override
    public Bitmap getBitmap(int position) {

        // id 가져오기
        long id = getItemId(position);

        // Bitmap 샘플링
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 1; // 2의 배수

        // id 로부터 bitmap 생성
        return MediaStore.Images.Thumbnails.getThumbnail(mContext.getContentResolver(),
                id,
                MediaStore.Images.Thumbnails.MINI_KIND,
                options);
    }
}