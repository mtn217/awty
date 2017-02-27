package edu.washington.mtn217.awty;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private EditText message;
    private EditText duration;
    private EditText phoneNum;
    private Button start;
    private Button stop;
    private String msg;
    private String num;
    private PendingIntent pi;
    private AlarmManager mgr;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }

        message = (EditText) findViewById(R.id.editText);
        phoneNum = (EditText) findViewById(R.id.editText3);
        duration = (EditText) findViewById(R.id.editText2);

        start = (Button) findViewById(R.id.button2);
        stop = (Button) findViewById(R.id.button);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msg = message.getText().toString();
                num = phoneNum.getText().toString();
                String time = duration.getText().toString();


                if((time.equals("0") || time.equals("")) && msg.equals("")) {
                    Toast.makeText(getApplicationContext(), "You need to provide a message and the duration needs to be greater than 0", Toast.LENGTH_SHORT).show();
                } else if(time.equals("0") || time.equals("")) {
                    Toast.makeText(getApplicationContext(), "Duration needs to be greater than 0", Toast.LENGTH_SHORT).show();
                } else if(msg.equals("")) {
                    Toast.makeText(getApplicationContext(), "You need to provide a message.", Toast.LENGTH_SHORT).show();
                } else {
                    if(num.length() == 10) {
                        stop.setVisibility(View.VISIBLE);
                        start.setVisibility(View.INVISIBLE);
                        int period = Integer.parseInt(time);
                        int interval = (period * 60 * 1000);

                        //Start the messages
                        Calendar cal = Calendar.getInstance();
                        Context context = getApplicationContext();
                        mgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
                        Intent intent = new Intent(context, AlarmReceiver.class);
                        intent.putExtra("msg", msg);
                        intent.putExtra("num", num);
                        pi = PendingIntent.getBroadcast(context, 0, intent, 0);
                        mgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), interval, pi);

                    } else {
                        Toast.makeText(getApplicationContext(), "You need to include 10 digits for the phone number", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message.setText("");
                phoneNum.setText("");
                duration.setText("");
                stop.setVisibility(View.INVISIBLE);
                start.setVisibility(View.VISIBLE);

                //Stop the messages
                mgr.cancel(pi);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    Toast.makeText(this, "Send SMS permission is required for this app.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
