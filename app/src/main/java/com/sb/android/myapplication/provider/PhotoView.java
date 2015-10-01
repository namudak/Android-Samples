package com.sb.android.myapplication.provider;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sb.android.myapplication.R;

/**
 * Created by Administrator on 2015-09-26.
 */
public class PhotoView extends LinearLayout {

    private ImageView mImageView;

    public PhotoView(Context context) {

        super(context);

    }

    public PhotoView(Context context, Bitmap aItem) {

        super(context);

        LayoutInflater layoutInflater= (LayoutInflater)context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);

        layoutInflater.inflate(R.layout.item_photo_view, this, true);

        mImageView = (ImageView)findViewById(R.id.imageView);
        mImageView.setImageBitmap(aItem);

    }

    // Get ImageView field

    public ImageView getImageView() {

        return mImageView;

    }
    // Set ImageView  as received
    public void setImageView(Bitmap aItem) {

        mImageView.setImageBitmap(aItem);

    }


}
