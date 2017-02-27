package edu.washington.mtn217.awty;

import android.Manifest;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
    public AlarmReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String msg = intent.getStringExtra("msg");
        String num = intent.getStringExtra("num");

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(num, null, msg, null, null);
        } catch (Exception e) {
            Toast.makeText(context, "SMS failed, please try again later!", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View layout = inflater.inflate(R.layout.custom_toast, null);

//        TextView message = (TextView) layout.findViewById(R.id.text);
//        TextView text = (TextView) layout.findViewById(R.id.textView3);
//        text.setText("Texting " + "(" + num.substring(0, 3) + ") " + num.substring(3, 6) + "-" + num.substring(6));
//        message.setText(msg);
//
//        Toast toast = new Toast(context);
//        toast.setGravity(Gravity.BOTTOM, 0, 0);
//        toast.setDuration(Toast.LENGTH_LONG);
//        toast.setView(layout);
//        toast.show();
    }
}
