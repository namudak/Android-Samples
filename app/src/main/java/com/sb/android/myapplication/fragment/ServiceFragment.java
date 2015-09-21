
package com.sb.android.myapplication.fragment;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sb.android.myapplication.R;

import java.util.Random;

/**
 * A placeholder fragment containing a simple view.
 */
public class ServiceFragment extends Fragment {

    private ImageView mImageView;

    public ServiceFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_service, container, false);
        mImageView = (ImageView) view.findViewById(R.id.imageView);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle saveInstancesState) {
        super.onActivityCreated(saveInstancesState);

        mImageView.setBackgroundColor(getRandomColor());

    }

    public void setColor(int color) {
        mImageView.setBackgroundColor(color);
    }

    private int getRandomColor() {
        Random random = new Random();

        return Color.argb(255, random.nextInt(256),
                random.nextInt(256), random.nextInt(256));
    }
}
