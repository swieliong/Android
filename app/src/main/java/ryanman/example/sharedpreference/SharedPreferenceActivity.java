package ryanman.example.sharedpreference;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ryanman.example.R;

/*
http://www.javatpoint.com/android-preferences-example
http://developer.android.com/training/basics/data-storage/shared-preferences.html

Shared Preferences allow you to save and retrieve data in the form of key, value pair.
It is widely used to get information from user such as in settings.

 */
public class SharedPreferenceActivity extends Activity {

    TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shared_preference_layout);

        textView = (TextView) findViewById(R.id.dataTxt);
    }

    public void addPreference(View v) {
            Intent intent = new Intent(SharedPreferenceActivity.this, HomeActivity.class);
            startActivity(intent);
    }

    public void displaySharedPreferences(View v) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(SharedPreferenceActivity.this);
        String username = prefs.getString("username", "Default NickName");
        String passw = prefs.getString("password", "Default Password");
        boolean checkBox = prefs.getBoolean("checkBox", false);
        String listPrefs = prefs.getString("listpref", "Default list prefs");

        StringBuilder builder = new StringBuilder();
        builder.append("Username: " + username + "\n");
        builder.append("Password: " + passw + "\n");
        builder.append("Keep me logged in: " + String.valueOf(checkBox) + "\n");
        builder.append("List preference: " + listPrefs);

        textView.setText(builder.toString());
    }
}
