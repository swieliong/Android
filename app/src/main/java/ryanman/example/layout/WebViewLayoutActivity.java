package ryanman.example.layout;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import ryanman.example.R;

/*
Android's WebView allows you to open an own windows for viewing URL or custom html markup page.
 */
public class WebViewLayoutActivity extends Activity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_layout);

        webView = (WebView) findViewById(R.id.webView1);
        webView.getSettings().setJavaScriptEnabled(true);

        // load url
        webView.loadUrl("http://www.google.com");

        // custom html markup
//        String customHtml = "<html><body><h1>Hello, WebView</h1></body></html>";
//        webView.loadData(customHtml, "text/html", "UTF-8");
    }
}
