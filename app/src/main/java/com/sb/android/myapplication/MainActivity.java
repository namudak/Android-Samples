
package com.sb.android.myapplication;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.sb.android.myapplication.activity.ActivityExamActivity;
import com.sb.android.myapplication.activity.WebActivity;
import com.sb.android.myapplication.database.LoginActivity;
import com.sb.android.myapplication.fragment.FragmentActivity;
import com.sb.android.myapplication.graphic.GraphicActivity;
import com.sb.android.myapplication.json.WeatherJson;
import com.sb.android.myapplication.layout.FrameLayoutActivity;
import com.sb.android.myapplication.mission.Mission01Activity;
import com.sb.android.myapplication.mission.Mission02Activity;
import com.sb.android.myapplication.mission.Mission031Activity;
import com.sb.android.myapplication.mission.Mission05Activity;
import com.sb.android.myapplication.mission.Mission06Activity;
import com.sb.android.myapplication.mission.Mission07Activity;
import com.sb.android.myapplication.provider.ContactLoaderActivity;
import com.sb.android.myapplication.provider.PhotoViewActivity;
import com.sb.android.myapplication.receiver.BroadcastActivity;
import com.sb.android.myapplication.thread.ThreadActivity;
import com.sb.android.myapplication.viewpager.ScreenSlideActivity;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
// import com.example.suwonsmartapp.androidexam.animation.TransitionDrawableExamActivity;

public class MainActivity extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new SimpleAdapter(this, getData(),
                android.R.layout.simple_list_item_1, new String[] {
                        "title"
                },
                new int[] {
                        android.R.id.text1
                }));

        getListView().setTextFilterEnabled(true);
    }

    protected List<Map<String, Object>> getData() {

        List<Map<String, Object>> myData = new ArrayList<>();

        // 메뉴 추가 부분
        //addItem(myData, "TransitionDrawable",
        // TransitionDrawableExamActivity.class);
        addItem(myData, "01.FrameLayout",
                FrameLayoutActivity.class);
        addItem(myData, "02.Mission01",
                Mission01Activity.class);
        addItem(myData, "03.Mission02",
                Mission02Activity.class);
        addItem(myData, "04.Activity Move",
                ActivityExamActivity.class);
        addItem(myData, "05.Mission031",
                Mission031Activity.class);
        addItem(myData, "06.Mission05(Customer data Screen",
                Mission05Activity.class);
        addItem(myData, "07.Mission06(URL)",
                Mission06Activity.class);
        addItem(myData, "08.Mission07(Calendar)",
                Mission07Activity.class);
        addItem(myData, "09.WebView",
                WebActivity.class);
        addItem(myData, "10.ASync(Thread)",
                ThreadActivity.class);
        addItem(myData, "11.Weather",
                WeatherJson.class);
        addItem(myData, "12.Fragment",
                FragmentActivity.class);
        addItem(myData, "13.ScreenSlidebyViewPager",
                ScreenSlideActivity.class);
        addItem(myData, "14.Broadcast Receiver",
                BroadcastActivity.class);
        addItem(myData, "15.Graphic",
                GraphicActivity.class);
        addItem(myData, "16.Database",
                LoginActivity.class);
        addItem(myData, "17.Parse",
                LoginActivity.class);
        addItem(myData, "18.Provider",
                ContactLoaderActivity.class);
        addItem(myData, "19.PhotoLoader",
                PhotoViewActivity.class);
                        // ----- 메뉴 추가 여기까지

        // 이름 순 정렬
        Collections.sort(myData, sDisplayNameComparator);

        return myData;
    }

    private final static Comparator<Map<String, Object>> sDisplayNameComparator =
            new Comparator<Map<String, Object>>() {
                private final Collator collator = Collator.getInstance();

                public int compare(Map<String, Object> map1, Map<String, Object> map2) {
                    return collator.compare(map1.get("title"), map2.get("title"));
                }
            };

    protected void addItem(List<Map<String, Object>> data, String name, Intent intent) {
        Map<String, Object> temp = new HashMap<>();

        temp.put("title", name);
        temp.put("intent", intent);
        data.add(temp);
    }

    protected void addItem(List<Map<String, Object>> data, String name, Class aClass) {
        this.addItem(data, name, new Intent(this, aClass));
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void onListItemClick(ListView lv, View v, int position, long id) {
        Map<String, Object> map = (Map<String, Object>) lv.getItemAtPosition(position);

        Intent intent = (Intent) map.get("intent");
        startActivity(intent);
    }
}
