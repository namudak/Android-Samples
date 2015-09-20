package com.sb.android.myapplication.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sb.android.myapplication.R;

import java.util.Random;

/**
 * Created by Administrator on 2015-09-18.
 */
public class ColorFragment extends Fragment {

    private ImageView mImageView;

    // View를 만드는 곳
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_color, container, false);
        mImageView = (ImageView) view.findViewById(R.id.iv_image);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mImageView.setBackgroundColor(getRandomColor());
    }

    private int getRandomColor() {
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    public void setColor(int color) {
        mImageView.setBackgroundColor(color);
    }
}