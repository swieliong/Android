package ryanman.example.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

/*
http://www.tutorialspoint.com/android/android_services.htm
http://www.javatpoint.com/android-service-tutorial

Service is a component that runs in the background to perform long-running operations without needing to interact with the user,
 and it works even if application is destroyed,
 like playing music, handle network transactions, interacting content providers etc.
Service can essentially take two states:
1. Started
    A service is started when an application component, such as an activity, starts it by calling startService().
    It is stopped by stopService() method. The service can stop itself by calling the stopSelf() method.
2. Bound
    A service is bound when an application component binds to it by calling bindService().
    A bound service offers a client-server interface that allows components to interact with the service, send requests, get results, and even do so across processes with interprocess communication (IPC).

Start the service using onStartCommand() or onBind() method.
 */
public class MyService extends Service {

    /*
    The system calls this method when another component wants to bind with the service by calling bindService().
    You must always implement this method, but if you don't want to allow binding, then you should return null.
    The client can unbind the service by calling the unbindService() method.
     */
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Let it continue running until it is stopped.
        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }
}
