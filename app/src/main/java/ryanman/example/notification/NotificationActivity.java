package ryanman.example.notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ryanman.example.R;

/*
see detail http://developer.android.com/guide/topics/ui/notifiers/notifications.html
 */
public class NotificationActivity extends Activity {

    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_layout);

        b1=(Button)findViewById(R.id.notifBtn);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notify("Title", "You've received new message");
            }
        });
    }

    private void Notify(String notificationTitle, String notificationMessage){
        Intent notificationIntent = new Intent(this, NotificationView.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ios_logo)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.windowsmobile_logo))
                .setTicker(getResources().getString(R.string.title_activity_notification))
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setContentTitle(notificationTitle)
                .setContentText(notificationMessage);
        Notification notification = builder.build();
        notificationManager.notify(9999, notification);
    }
}
