package ryanman.example.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CustomReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        CharSequence msg = intent.getCharSequenceExtra("message");
        Toast.makeText(context, "Message: " + msg, Toast.LENGTH_LONG).show();
    }
}
