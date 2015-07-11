package ryanman.example.contentprovider;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ryanman.example.R;

/*
http://www.tutorialspoint.com/android/android_content_providers.htm

Content provider to share data across applications.
A content provider can use different ways to store its data and the data can be stored in a database, in files, or even over a network.

 */
public class ContentProviderActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_provider_layout);
    }

    public void addName(View view) {
        ContentValues values = new ContentValues();
        values.put(MyContentProvider.NAME, ((EditText) findViewById(R.id.nameTxt)).getText().toString());

        Uri uri = getContentResolver().insert(MyContentProvider.CONTENT_URI, values);
        Toast.makeText(getBaseContext(), uri.toString(), Toast.LENGTH_LONG).show();
    }

    public void showStudents(View view) {
        Cursor c = managedQuery(MyContentProvider.CONTENT_URI, null, null, null, "name");
        if (c.moveToFirst()) {
            do{
                Toast.makeText(this,
                        c.getString(c.getColumnIndex(MyContentProvider._ID)) + ", " +  c.getString(c.getColumnIndex( MyContentProvider.NAME)),
                        Toast.LENGTH_SHORT).show();
            } while (c.moveToNext());
        }
    }

    public void deleteAll (View view) {
        int count = getContentResolver().delete(MyContentProvider.CONTENT_URI, null, null);
        Toast.makeText(getBaseContext(), count +" records are deleted.", Toast.LENGTH_LONG).show();
    }
}
