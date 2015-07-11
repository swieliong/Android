package ryanman.example.wifi;

import android.app.Activity;
import android.content.BroadcastReceiver;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import ryanman.example.R;

/*
Android allows applications to access to view the access the state of the wireless connections at very low level.
Application can access almost all the information of a wifi connection.
The information that an application can access includes connected network's link speed, IP address, negotiation state, other networks information.
Applications can also scan, add, save, terminate and initiate Wi-Fi connections.

Here is an example demonstrating the use of WIFI like scans a list of wireless networks and populate them in a list view, check wifi connection.
To experiment with this example, you need to run this on an actual device on which wifi is turned on.
 */
public class WifiActivity extends Activity  {
    ListView wifiList;
    WifiManager wifiManager;
    String wifis[];
    WifiScanReceiver wifiScanReceiver;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wifi_layout);

        wifiList =(ListView)findViewById(R.id.wifiList);
        wifiManager =(WifiManager)getSystemService(Context.WIFI_SERVICE);
        wifiScanReceiver = new WifiScanReceiver();
        wifiManager.startScan();

        b1=(Button)findViewById(R.id.checkConnBtn);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(WifiActivity.this, " is connected " + checkMobileInternetConn(), Toast.LENGTH_LONG).show();
            }
        });
    }

    protected void onPause() {
        unregisterReceiver(wifiScanReceiver);
        super.onPause();
    }

    protected void onResume() {
        registerReceiver(wifiScanReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        super.onResume();
    }

    private class WifiScanReceiver extends BroadcastReceiver{
        public void onReceive(Context c, Intent intent) {
            List<ScanResult> wifiScanList = wifiManager.getScanResults();
            wifis = new String[wifiScanList.size()];
            for(int i = 0; i < wifiScanList.size(); i++){
                wifis[i] = ((wifiScanList.get(i)).toString());
            }
            wifiList.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, wifis));
        }
    }

    public boolean checkMobileInternetConn() {
        //Create object for ConnectivityManager class which returns network related info
        ConnectivityManager connectivity = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        //If connectivity object is not null
        if (connectivity != null) {
            //Get network info - WIFI internet access
            NetworkInfo info = connectivity.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (info != null) {
                //Look for whether device is currently connected to WIFI network
                if (info.isConnected()) {
                    return true;
                }
            }
        }
        return false;
    }
}
