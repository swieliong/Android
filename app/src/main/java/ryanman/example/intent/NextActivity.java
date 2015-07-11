package ryanman.example.intent;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ryanman.example.R;

public class NextActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.next_layout);

        TextView urlTxt = (TextView) findViewById(R.id.nextTxt);
        urlTxt.setText(getIntent().getExtras().get("urlValue").toString());
    }
}
