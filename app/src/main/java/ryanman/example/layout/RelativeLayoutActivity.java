package ryanman.example.layout;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import ryanman.example.R;

/*
Android RelativeLayout enables you to specify how child views are positioned relative to each other.
The position of each view can be specified as relative to sibling elements or relative to the parent.
 */
public class RelativeLayoutActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relative_layout);
    }
}
