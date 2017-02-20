package edu.washington.mtn217.awty;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
    public AlarmReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String msg = intent.getStringExtra("msg");
        String num = intent.getStringExtra("num");
        Toast.makeText(context, "(" + num.substring(0, 3) + ") " + num.substring(3, 6) + "-" + num.substring(6) + ": " + msg, Toast.LENGTH_LONG).show();
    }
}
