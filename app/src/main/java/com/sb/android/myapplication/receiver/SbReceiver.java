package com.sb.android.myapplication.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_SHORT;

public class SbReceiver extends BroadcastReceiver {

    public SbReceiver() {
    }

    /**
     * This is only method to be implemented from BroadcastReceiver
     * @param context
     * @param intent
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //throw new UnsupportedOperationException("Not yet implemented");

        if(intent.getAction().equals(Intent.ACTION_BATTERY_LOW)) {
            Toast.makeText(context, "Battery low!", LENGTH_SHORT).show();
        } else if(intent.getAction().equals(Intent.ACTION_AIRPLANE_MODE_CHANGED)){
            Toast.makeText(context, "Airplane mode changed!", LENGTH_SHORT).show();
        } else if(intent.getAction().equals("android.intent.action.SB_BROADCAST")) {
            Toast.makeText(context, "Sb's got broadcast!", LENGTH_SHORT).show();
        }

        //abortBroadcast() stop getting broadcast
    }
}
