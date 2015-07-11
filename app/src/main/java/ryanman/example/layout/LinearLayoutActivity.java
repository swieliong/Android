package ryanman.example.layout;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import ryanman.example.R;

/*
LinearLayout is a view group that aligns all children in a single direction, vertically or horizontally.
 */
public class LinearLayoutActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_layout);
    }
}
