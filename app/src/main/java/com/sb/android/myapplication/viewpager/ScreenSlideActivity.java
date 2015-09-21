package com.sb.android.myapplication.viewpager;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.sb.android.myapplication.R;
import com.sb.android.myapplication.fragment.ColorFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 2015-09-16.
 */
public class ScreenSlideActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private List<Fragment> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_screen_slide);

        mTabLayout=(TabLayout)findViewById(R.id.tab_layout);
        mViewPager=(ViewPager)findViewById(R.id.viewpager);

        mList= new ArrayList<>();

        for(int i= 0; i< 10; i++) {
            mList.add(new ColorFragment());
            mTabLayout.addTab(mTabLayout.newTab().setText("Tab"+ i+ 1));//.setIcon();
        }

        ScrrenSlidePageAdapter adapter= new ScrrenSlidePageAdapter(
                                        getSupportFragmentManager(), mList);

        mViewPager.setAdapter(adapter);

        mViewPager.addOnPageChangeListener(
                new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

        //mTabLayout.setupWithViewPager(mViewPager);// Some bug
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    /**
     * Adapter for viewpager ( we can use FragmentstatePagerAdapter )
     */
    private class ScrrenSlidePageAdapter extends FragmentPagerAdapter {

        public ScrrenSlidePageAdapter(FragmentManager fm, List<Fragment> list) {
            super(fm);

            mList= list;
        }

        @Override
        public Fragment getItem(int position) {
            return mList.get(position);
        }

        @Override
        public int getCount() {
            return mList.size();
        }
    }
}
