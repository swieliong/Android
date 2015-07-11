package ryanman.example.phone;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import ryanman.example.R;

/*

Note
To experiment with this example, you will need actual Mobile device equipped with latest Android OS, otherwise you will have to struggle with emulator which may not work.
 */
public class PhoneCallActivity extends Activity {
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_call_layout);

        b1=(Button)findViewById(R.id.phoneCallBtn);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                call();
            }
        });

        // get the information of call state
        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        PhoneStateListener callStateListener = new PhoneStateListener() {
            public void onCallStateChanged(int state, String incomingNumber) {
                if(state== TelephonyManager.CALL_STATE_RINGING){
                    Toast.makeText(getApplicationContext(),"Phone Is Riging", Toast.LENGTH_LONG).show();
                }
                if(state==TelephonyManager.CALL_STATE_OFFHOOK){
                    Toast.makeText(getApplicationContext(),"Phone is Currently in A call", Toast.LENGTH_LONG).show();
                }
                if(state==TelephonyManager.CALL_STATE_IDLE){
                    Toast.makeText(getApplicationContext(),"phone is neither ringing nor in a call", Toast.LENGTH_LONG).show();
                }
            }
        };
        telephonyManager.listen(callStateListener, PhoneStateListener.LISTEN_CALL_STATE);
    }

    private void call() {
        Intent in = new Intent(Intent.ACTION_CALL, Uri.parse("tel:08802177690"));
        try {
            startActivity(in);
        } catch (android.content.ActivityNotFoundException ex){
            Toast.makeText(getApplicationContext(),"yourActivity is not founded", Toast.LENGTH_SHORT).show();
        }
    }

}
