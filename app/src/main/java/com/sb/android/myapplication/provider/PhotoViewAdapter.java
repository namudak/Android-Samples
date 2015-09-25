package com.sb.android.myapplication.provider;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sb.android.myapplication.R;
import com.suwonsmartapp.abl.AsyncBitmapLoader;

/**
 * Created by student on 2015-09-25.
 */
public class PhotoViewAdapter extends CursorAdapter {

    private LayoutInflater mLayoutInflater;
    private AsyncBitmapLoader mAsyncBitMapLoader;

    public PhotoViewAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);

        mLayoutInflater = LayoutInflater.from(context);
    }

    public PhotoViewAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        ViewHolder vh= new ViewHolder();

        View view= mLayoutInflater.inflate(R.layout.photo, parent, false);
        vh.iv= (ImageView)view.findViewById(R.id.imageView);

        view.setTag(vh);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder vh= (ViewHolder)view.getTag();

        long id= cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID));

        BitmapFactory.Options options= new BitmapFactory.Options();
        options.inSampleSize= 2;

        // id 로부터 bitmap 생성
        Bitmap bitmap = MediaStore.Images.Thumbnails.getThumbnail(context.getContentResolver(),
                id,
                MediaStore.Images.Thumbnails.MINI_KIND,
                options);

        // 이미지 셋팅
        vh.iv.setImageBitmap(bitmap);


    }

    static class ViewHolder {
        ImageView iv;
    }
}
