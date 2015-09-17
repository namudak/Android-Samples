package com.sb.android.myapplication.receiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by student on 2015-09-16.
 */
public class BroadcastActivity extends Activity implements View.OnClickListener {

    private BroadcastReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // These are routines depends not on xml layout but on code
        Button button = new Button(this);
        button.setText("Send broadcast");

        LinearLayout linearLayout = new LinearLayout(this);
        ViewGroup.LayoutParams parms = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        linearLayout.setLayoutParams(parms);

        linearLayout.addView(button);

        setContentView(linearLayout);
    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent("android.intent.action.SB_BROADCAST");

        sendBroadcast(intent);

        //sendOrderedBroadcast();
    }

    @Override
    protected void onResume() {
        super.onResume();

        IntentFilter filter= new IntentFilter();
        filter.addAction("android.intent.action.SB_BROADCAST");
        filter.addAction(Intent.ACTION_BATTERY_LOW);
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);

        registerReceiver(mReceiver,filter);

    }

    @Override
    protected void onPause() {
        super.onPause();

        unregisterReceiver(mReceiver);
    }
}
