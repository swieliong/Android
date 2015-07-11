package ryanman.example.intent;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.provider.ContactsContract;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import ryanman.example.R;

/*
http://www.tutorialspoint.com/android/android_intents_filters.htm
http://developer.android.com/reference/android/content/Intent.html

Android Intent is the message that is passed between components such as activities, content providers, broadcast receivers, services etc.
An Intent object is a bundle of information which is used by the component that receives the intent as well as information used by the Android system.
Android intents are mainly used to:
    Start the service
    Launch an activity
    Display a web page
    Display a list of contacts
    Broadcast a message
    Dial a phone call etc.

Category
The category is an optional part of Intent object and it's a string containing additional information about the kind of component that should handle the intent.
See docs for detail

Flags
These flags are optional part of Intent object and instruct the Android system how to launch an activity, and how to treat it after it's launched etc.
See docs for detail

Types of Android Intents:
    1. Implicit Intent doesn't specify the component. In such case, intent provides information of available components provided by the system that is to be invoked.
    2. Explicit Intent specifies the component. In such case, intent provides the external class to be invoked.

Intent Filter
http://developer.android.com/guide/components/intents-filters.html

 */
public class IntentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent_layout);
    }

    public void go(View view) {
        EditText urlTxt = (EditText) findViewById(R.id.urlTxt);
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_VIEW);   // see docs for Android Intent Action type
        intent.setData(Uri.parse(urlTxt.getText().toString())); // open in webview
//        intent.setData(ContactsContract.Contacts.CONTENT_URI);    show contact
        startActivity(intent);
    }

    public void goToNextActivity(View view) {
        EditText urlTxt = (EditText) findViewById(R.id.urlTxt);
        Intent intent=new Intent(getApplicationContext(), NextActivity.class);
        intent.putExtra("urlValue", urlTxt.getText());  // see docs for Android Extra Data type
        startActivity(intent);
    }
}
