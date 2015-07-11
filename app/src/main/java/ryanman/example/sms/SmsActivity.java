package ryanman.example.sms;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ryanman.example.R;

/*
Note
To experiment with this example, you will need actual Mobile device equipped with latest Android OS, otherwise you will have to struggle with emulator which may not work.
 */
public class SmsActivity extends Activity {
    Button sendBtn;
    Button sendBtn2;
    EditText txtphoneNo;
    EditText txtMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sms_layout);

        sendBtn = (Button) findViewById(R.id.sendSmsBtn);
        sendBtn2 = (Button) findViewById(R.id.sendSmsBtn2);
        txtphoneNo = (EditText) findViewById(R.id.phoneNoTxt);
        txtMessage = (EditText) findViewById(R.id.msgText);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SmsActivity.this.sendSMS1();
            }
        });
        sendBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            sendSMS2();
            }
        });
    }

    // 1. Using SmsManager API
    protected void sendSMS1() {
        String phoneNo = txtphoneNo.getText().toString();
        String message = txtMessage.getText().toString();

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, message, null, null);
            Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "SMS faild, please try again.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    // 2. Using implicit Intent
    protected void sendSMS2() {
        Intent smsIntent = new Intent(Intent.ACTION_VIEW);
        smsIntent.setData(Uri.parse("smsto:"));
        smsIntent.setType("vnd.android-dir/mms-sms");
        smsIntent.putExtra("address", new String ("01234"));
        smsIntent.putExtra("sms_body", "a message ");

        try {
            startActivity(smsIntent);
            finish();
            Log.i("Finished sending SMS...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(SmsActivity.this, "SMS faild, please try again later.", Toast.LENGTH_SHORT).show();
        }
    }
}
