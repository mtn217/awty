package edu.washington.mtn217.awty;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
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
//        Toast.makeText(context, "(" + num.substring(0, 3) + ") " + num.substring(3, 6) + "-" + num.substring(6) + ": " + msg, Toast.LENGTH_LONG).show();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.custom_toast, null);

        TextView message = (TextView) layout.findViewById(R.id.text);
        TextView text = (TextView) layout.findViewById(R.id.textView3);
        text.setText("Texting " + "(" + num.substring(0, 3) + ") " + num.substring(3, 6) + "-" + num.substring(6));
        message.setText(msg);

        Toast toast = new Toast(context);
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
}
