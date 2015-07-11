package ryanman.example.ui;

import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.widget.TabHost;
import android.app.TabActivity;
import android.widget.TabHost.OnTabChangeListener;

import ryanman.example.R;

/*
http://www.androidhive.info/2011/08/android-tab-layout-tutorial/

Its not recommended using tabbar, use ActionBar.ViewPager instead
 */
public class TabBarActivity extends TabActivity implements OnTabChangeListener{

    /** Called when the activity is first created. */
    TabHost tabHost;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabbar_layout);

        tabHost = getTabHost();
        tabHost.setOnTabChangedListener(this);

        TabHost.TabSpec spec;
        Intent intent;

        /************* TAB1 ************/
        // Create  Intents to launch an Activity for the tab (to be reused)
        intent = new Intent().setClass(this, Tab1Activity.class);
        spec = tabHost.newTabSpec("First").setIndicator("First", getResources().getDrawable(R.drawable.icon_windows_tab)).setContent(intent);

        //Add intent to tab
        tabHost.addTab(spec);

        /************* TAB2 ************/
        intent = new Intent().setClass(this, Tab2Activity.class);
        spec = tabHost.newTabSpec("Second").setIndicator("Second", getResources().getDrawable(R.drawable.icon_ios_tab)).setContent(intent);
        tabHost.addTab(spec);

        /************* TAB3 ************/
        intent = new Intent().setClass(this, Tab3Activity.class);
        spec = tabHost.newTabSpec("Third").setIndicator("Third", getResources().getDrawable(R.drawable.icon_android_tab)).setContent(intent);
        tabHost.addTab(spec);

        // Set background image
//        tabHost.getTabWidget().getChildAt(0).setBackgroundResource(R.drawable.windowsmobile_logo);
//        tabHost.getTabWidget().getChildAt(1).setBackgroundResource(R.drawable.ios_logo);
//        tabHost.getTabWidget().getChildAt(2).setBackgroundResource(R.drawable.android_logo);
        tabHost.getTabWidget().setCurrentTab(0);
    }

    @Override
    public void onTabChanged(String tabId) {
        //********* Check current selected tab and change background images *******/
//        for(int i=0;i<tabHost.getTabWidget().getChildCount();i++) {
//            if(i==0)
//                tabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.windowsmobile_logo);
//            else if(i==1)
//                tabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.ios_logo);
//            else if(i==2)
//                tabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.android_logo);
//        }
        Log.i("tabs", "CurrentTab: " + tabHost.getCurrentTab());
    }
}