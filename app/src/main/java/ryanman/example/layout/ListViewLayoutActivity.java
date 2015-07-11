package ryanman.example.layout;

import android.app.Activity;
import android.app.ListActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import ryanman.example.R;

/*
Android ListView is a view which groups several items and display them in vertical scrollable list.
The list items are automatically inserted to the list using an Adapter that pulls content from a source such as an array or database.

The ListView and GridView are subclasses of AdapterView and they can be populated by binding them to an Adapter, which retrieves data from an external source and creates a View that represents each data entry.
The common adapters are ArrayAdapter,Base Adapter, CursorAdapter, SimpleCursorAdapter,SpinnerAdapter and WrapperListAdapter.
 */
public class ListViewLayoutActivity extends ListActivity {

    static final String[] MOBILE_OS = new String[] { "Android", "iOS", "WindowsMobile", "Blackberry"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * ListView with custom "ArrayAdapter" to display different images base on the "item name" in the list.
         */
        setListAdapter(new MobileArrayAdapter(this, MOBILE_OS));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String selectedValue = (String) getListAdapter().getItem(position);
        Toast.makeText(this, selectedValue, Toast.LENGTH_SHORT).show();
    }
}
