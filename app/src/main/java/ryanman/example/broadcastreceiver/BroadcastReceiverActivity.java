package ryanman.example.broadcastreceiver;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.widget.ProgressBar;
import android.widget.TextView;
import ryanman.example.R;

/*
http://www.tutorialspoint.com/android/android_broadcast_receivers.htm

Broadcast Receivers simply respond to broadcast messages from other applications or from the system itself.
These messages are sometime called events or intents.
For example, applications can also initiate broadcasts to let other applications know that some data has been downloaded to the device and is available for them to use, so this is broadcast receiver who will intercept this communication and will initiate appropriate action.
 */
public class BroadcastReceiverActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.broadcastreceiver_layout);

        //Register the receiver which triggers event when battery charge is changed
        registerReceiver(mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

    public void broadcastCustomIntent(View view){
        Intent intent = new Intent();
        EditText msg = (EditText)findViewById(R.id.msgText);
        intent.putExtra("message", (CharSequence)msg.getText().toString());  // add data to the Intent
        intent.setAction("com.ryanman.CUSTOM_INTENT");
        sendBroadcast(intent);
    }

    private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver() {
        @Override
        //When Event is published, onReceive method is called
        public void onReceive(Context c, Intent i) {
            int level = i.getIntExtra("level", 0);
            TextView tv = (TextView) findViewById(R.id.bateryTxt);
            tv.setText("Battery Level: " + Integer.toString(level) + "%");
        }
    };
}
