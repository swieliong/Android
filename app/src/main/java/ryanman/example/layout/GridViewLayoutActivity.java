package ryanman.example.layout;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import ryanman.example.R;

/*
Android GridView shows items in two-dimensional scrolling grid (rows & columns) and the grid items are not necessarily predetermined but they automatically inserted to the layout using a ListAdapter.

 */
public class GridViewLayoutActivity extends Activity {
    static final String[] MOBILE_OS = new String[] { "Android", "iOS","Windows", "Blackberry" };

    GridView gridView;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gridview_layout);

        gridView = (GridView) findViewById(R.id.gridView1);
        // set custom adapter to display image and text in GridView layout
        gridView.setAdapter(new ImageAdapter(this, MOBILE_OS));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(getApplicationContext(), ((TextView) v.findViewById(R.id.grid_item_label)).getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
