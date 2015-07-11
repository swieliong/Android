package ryanman.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;

import ryanman.example.activity.HelloActivity;
import ryanman.example.asynctask.AsyncTaskActivity;
import ryanman.example.audio.AudioCaptureActivity;
import ryanman.example.broadcastreceiver.BroadcastReceiverActivity;
import ryanman.example.camera.CameraCaptureActivity;
import ryanman.example.contentprovider.ContentProviderActivity;
import ryanman.example.fragment.FragmentActivity;
import ryanman.example.intent.IntentActivity;
import ryanman.example.layout.FrameLayoutActivity;
import ryanman.example.layout.GridViewLayoutActivity;
import ryanman.example.layout.LinearLayoutActivity;
import ryanman.example.layout.ListViewLayoutActivity;
import ryanman.example.layout.RelativeLayoutActivity;
import ryanman.example.layout.TableLayoutActivity;
import ryanman.example.layout.WebViewLayoutActivity;
import ryanman.example.mail.MailActivity;
import ryanman.example.network.HttpConnActivity;
import ryanman.example.notification.NotificationActivity;
import ryanman.example.phone.PhoneCallActivity;
import ryanman.example.service.ServiceActivity;
import ryanman.example.sharedpreference.SharedPreferenceActivity;
import ryanman.example.sms.SmsActivity;
import ryanman.example.sqlite.SQLiteActivity;
import ryanman.example.ui.ContextMenuActivity;
import ryanman.example.ui.DialogActivity;
import ryanman.example.ui.OptionMenuActivity;
import ryanman.example.ui.PopupMenuActivity;
import ryanman.example.ui.TabBarActivity;
import ryanman.example.webservice.WebServiceActivity;
import ryanman.example.wifi.WifiActivity;

/*
BEST PRACTICE
    http://www.tutorialspoint.com/android/android_ui_patterns.htm

Not Yet
http://www.tutorialspoint.com/android/android_event_handling.htm
http://www.tutorialspoint.com/android/android_custom_components.htm
http://www.tutorialspoint.com/android/android_location_based_services.htm
Backup Data http://www.tutorialspoint.com/android/android_data_backup.htm
Bluetooth
    http://www.tutorialspoint.com/android/android_bluetooth.htm
    http://www.javatpoint.com/android-bluetooth-tutorial
    http://www.javatpoint.com/android-bluetooth-list-paired-devices-example
Clipboard http://www.tutorialspoint.com/android/android_clipboard.htm
DRAW
    Dnd http://www.tutorialspoint.com/android/android_drag_and_drop.htm
    Gesture http://www.tutorialspoint.com/android/android_gestures.htm
    Graphic http://www.javatpoint.com/android-simple-graphics-example
    Animation
        http://www.tutorialspoint.com/android/android_animations.htm
        http://www.javatpoint.com/android-animation-example
    Image Effect http://www.tutorialspoint.com/android/android_image_effects.htm
    Multitouch http://www.tutorialspoint.com/android/android_multitouch.htm
    Render Script http://www.tutorialspoint.com/android/android_renderscript.htm
EMULATOR:
    http://www.tutorialspoint.com/android/android_emulator.htm
    http://www.tutorialspoint.com/android/android_developer_tools.htm
Localization
    http://programmerguru.com/android-tutorial/android-localization-example/
    http://programmerguru.com/android-tutorial/android-localization-at-runtime/
MULTIMEDIA
    http://www.tutorialspoint.com/android/android_jetplayer.htm
    http://www.tutorialspoint.com/android/android_mediaplayer.htm
    Video
        http://www.tutorialspoint.com/android/android_textureview.htm
        http://www.javatpoint.com/playing-video-in-android-example
NETWORK
    SIP http://www.tutorialspoint.com/android/android_sip_protocol.htm
PUBLISH
    http://www.tutorialspoint.com/android/android_publishing_application.htm
    http://developer.android.com/tools/publishing/preparing.html
Push Notification
    https://parse.com/tutorials/android-push-notifications
    https://mixpanel.com/help/reference/android-push-notifications
Ring Mode http://www.tutorialspoint.com/android/android_audiomanager.htm
RSS Reader http://www.tutorialspoint.com/android/android_rss_reader.htm
Sensor
    http://www.tutorialspoint.com/android/android_sensors.htm
    http://www.javatpoint.com/android-sensor-tutorial
SOCIAL
    Facebook http://www.tutorialspoint.com/android/android_facebook_integration.htm
    GMap http://www.tutorialspoint.com/android/android_google_maps.htm
    LinkedIn http://www.tutorialspoint.com/android/android_linkedin_integration.htm
    Tweeter http://www.tutorialspoint.com/android/android_twitter_integration.htm
SQLite
    http://programmerguru.com/android-tutorial/how-to-sync-sqlite-on-android-to-mysql-db/
    http://programmerguru.com/android-tutorial/how-to-sync-remote-mysql-db-to-sqlite-on-android/
STORAGE
    Internal Storage
        http://www.javatpoint.com/android-internal-storage-example
        http://www.tutorialspoint.com/android/android_internal_storage.htm
    External Storage
        http://www.javatpoint.com/android-external-storage-example
        http://programmerguru.com/android-tutorial/how-to-create-android-app-with-movable-to-sd-card-feature/
TEXT
    Spelling checker http://www.tutorialspoint.com/android/android_spelling_checker.htm
    Text to Speech
        http://www.tutorialspoint.com/android/android_text_to_speech.htm
        http://www.javatpoint.com/android-texttospeech-tutorial

UTIL
    Xml Parser
        http://www.tutorialspoint.com/android/android_xml_parsers.htm
        http://www.javatpoint.com/android-xml-parsing-using-dom-parser
        http://www.javatpoint.com/android-xml-parsing-using-sax-parser
        http://www.javatpoint.com/android-XMLPullParser-tutorial
    Json
        Parser http://www.javatpoint.com/android-json-parsing-tutorial
WIDGET
    http://www.tutorialspoint.com/android/android_widgets.htm
 */
public class MainActivity extends Activity implements OnChildClickListener {
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        prepareListData();

        // set list adapter
        expListView.setAdapter(new ExpandableListAdapter(this, listDataHeader, listDataChild));

        // on child click listener
        expListView.setOnChildClickListener(this);
    }

    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        String category = listDataHeader.get(groupPosition);
        String item = listDataChild.get(category).get(childPosition);
        Class clazz = null;
        switch (category) {
            case "Basic" : {
                switch (item) {
                    case "Activity" : { clazz = HelloActivity.class; break; }
                    case "Service" : { clazz = ServiceActivity.class; break; }
                    case "Broadcast Receiver" : { clazz = BroadcastReceiverActivity.class; break; }
                    case "Content Provider" : { clazz = ContentProviderActivity.class; break; }
                    case "Fragment" : { clazz = FragmentActivity.class; break; }
                    case "Intent" : { clazz = IntentActivity.class; break; }
                    case "Shared Preference" : { clazz = SharedPreferenceActivity.class; break; }
                }
                break;
            }
            case "UI Layout" : {
                switch (item) {
                    case "Linear Layout" : { clazz = LinearLayoutActivity.class; break; }
                    case "Relative Layout" : { clazz = RelativeLayoutActivity.class; break; }
                    case "Table Layout" : { clazz = TableLayoutActivity.class; break; }
                    case "Frame Layout" : { clazz = FrameLayoutActivity.class; break; }
                    case "List View" : { clazz = ListViewLayoutActivity.class; break; }
                    case "Grid View" : { clazz = GridViewLayoutActivity.class; break; }
                    case "Web View" : { clazz = WebViewLayoutActivity.class; break; }
                }
                break;
            }
            case "UI Control" : {
                switch (item) {
                    case "Option Menu" : { clazz = OptionMenuActivity.class; break; }
                    case "Context Menu" : { clazz = ContextMenuActivity.class; break; }
                    case "Popup Menu" : { clazz = PopupMenuActivity.class; break; }
                    case "Dialog" : { clazz = DialogActivity.class; break; }
                    case "Notification" : { clazz = NotificationActivity.class; break; }
                    case "TabBar" : { clazz = TabBarActivity.class; break; }
//                    ActionBar.ViewPager http://www.androidhive.info/2013/10/android-tab-layout-with-swipeable-views-1/
//                        http://android-developers.blogspot.com/2011/08/horizontal-view-swiping-with-viewpager.html
                }
                break;
            }
            case "Multimedia" : {
                switch (item) {
                    case "Audio Record" : { clazz = AudioCaptureActivity.class; break; }
                    case "Camera Capture" : { clazz = CameraCaptureActivity.class; break; }
                }
                break;
            }
            case "Network" : {
                switch (item) {
                    case "Http Connection" : { clazz = HttpConnActivity.class; break; }
                    case "Web Service" : { clazz = WebServiceActivity.class; break; }
                    case "Wifi" : { clazz = WifiActivity.class; break; }
                }
                break;
            }
            case "Others" : {
                switch (item) {
                    case "AsyncTask" : { clazz = AsyncTaskActivity.class; break; }
                    case "Send Email" : { clazz = MailActivity.class; break; }
                    case "Send SMS" : { clazz = SmsActivity.class; break; }
                    case "Phone Call" : { clazz = PhoneCallActivity.class; break; }
                    case "SQLite" : { clazz = SQLiteActivity.class; break; }
                }
                break;
            }
        }
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
        return false;
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();

        listDataHeader.add("Basic");
        List<String> basic = new ArrayList<String>();
        basic.add("Activity");
        basic.add("Service");
        basic.add("Broadcast Receiver");
        basic.add("Content Provider");
        basic.add("Fragment");
        basic.add("Intent");
        basic.add("Shared Preference");

        listDataHeader.add("UI Layout");
        List<String> layouts = new ArrayList<String>();
        layouts.add("Linear Layout");
        layouts.add("Relative Layout");
        layouts.add("Table Layout");
        layouts.add("Frame Layout");
        layouts.add("List View");
        layouts.add("Grid View");
        layouts.add("Web View");

        listDataHeader.add("UI Control");
        List<String> ui = new ArrayList<String>();
        ui.add("Option Menu");
        ui.add("Context Menu");
        ui.add("Popup Menu");
        ui.add("Dialog");
        ui.add("Notification");
        ui.add("TabBar");

        listDataHeader.add("Multimedia");
        List<String> multimedia = new ArrayList<String>();
        multimedia.add("Audio Record");
        multimedia.add("Camera Capture");

        listDataHeader.add("Network");
        List<String> network = new ArrayList<String>();
        network.add("Http Connection");
        network.add("Web Service");
        network.add("Wifi");

        listDataHeader.add("Others");
        List<String> other = new ArrayList<String>();
        other.add("AsyncTask");
        other.add("Send Email");
        other.add("Send SMS");
        other.add("Phone Call");
        other.add("SQLite");

        listDataChild = new HashMap<String, List<String>>();
        listDataChild.put(listDataHeader.get(0), basic);
        listDataChild.put(listDataHeader.get(1), layouts);
        listDataChild.put(listDataHeader.get(2), ui);
        listDataChild.put(listDataHeader.get(3), multimedia);
        listDataChild.put(listDataHeader.get(4), network);
        listDataChild.put(listDataHeader.get(5), other);
    }

    /*
    Add back button to handle exit dialog
    http://programmerguru.com/android-tutorial/how-to-change-the-back-button-behaviour/
     */
    @Override
    public void onBackPressed() {
        //Display alert message when back button has been pressed
        backButtonHandler();
        return;
    }

    public void backButtonHandler() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        // Setting Dialog Title
        alertDialog.setTitle("Leave application?");
        // Setting Dialog Message
        alertDialog.setMessage("Are you sure you want to leave the application?");
        // Setting Icon to Dialog
//        alertDialog.setIcon(R.drawable.dialog_icon);
        // Setting Positive "Yes" Button
        alertDialog.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        // Setting Negative "NO" Button
        alertDialog.setNegativeButton("NO",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to invoke NO event
                        dialog.cancel();
                    }
                });
        // Showing Alert Message
        alertDialog.show();
    }
}