package ryanman.example.layout;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import ryanman.example.R;

/*
Android TableLayout going to be arranged groups of views into rows and columns.
You will use the <TableRow> element to build a row in the table.
Each row has zero or more cells; each cell can hold one View object.
 */
public class TableLayoutActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table_layout);
    }
}
