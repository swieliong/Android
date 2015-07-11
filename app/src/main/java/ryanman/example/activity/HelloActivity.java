package ryanman.example.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import ryanman.example.R;

/*
    How to demo: http://www.javatpoint.com/android-life-cycle-of-activity
    http://www.tutorialspoint.com/android/android_acitivities.htm
 */
public class HelloActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {    // called when activity is first created
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);
        Log.d("lifecycle","onCreate invoked");
    }

    @Override
    protected void onStart() { // called when activity is becoming visible to the user
        super.onStart();
        Log.d("lifecycle","onStart invoked");
    }

    @Override
    protected void onResume() { // called when user starts interacting with the application
        super.onResume();
        Log.d("lifecycle","onResume invoked");
    }

    @Override
    protected void onPause() { // called when activity is not visible to the user
        super.onPause();
        Log.d("lifecycle","onPause invoked");
    }

    @Override
    protected void onStop() { // called when activity is no longer visible to the user
        super.onStop();
        Log.d("lifecycle","onStop invoked");
    }

    @Override
    protected void onDestroy() { // called before the activity is destroyed
        super.onDestroy();
        Log.d("lifecycle","onDestroy invoked");
    }

    @Override
    protected void onRestart() { // called when the activity restarts after stopping it
        super.onRestart();
        Log.d("lifecycle","onRestart invoked");
    }
}
