package ryanman.example.sqlite;

import android.os.Bundle;
import android.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;

import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import ryanman.example.R;

public class DisplayContactActivity extends ActionBarActivity {
    int from_Where_I_Am_Coming = 0;
    private ContactDBHelper contactDBHelper;
    TextView name ;
    TextView phone;
    int currentId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_contact_layout);

        name = (TextView) findViewById(R.id.editTextName);
        phone = (TextView) findViewById(R.id.editTextPhone);

        contactDBHelper = new ContactDBHelper(this);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            int id = extras.getInt("id");
            if(id>0){
                //means this is the view part not the add contact part.
                Contact contact = contactDBHelper.getContactById(id);
                currentId = id;

                Button b = (Button)findViewById(R.id.saveContactBtn);
                b.setVisibility(View.INVISIBLE);

                name.setText(contact.getName());
                name.setFocusable(false);
                name.setClickable(false);

                phone.setText(contact.getPhone());
                phone.setFocusable(false);
                phone.setClickable(false);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            int id = extras.getInt("id");
            if(id>0){
                getMenuInflater().inflate(R.menu.contact_view_menu, menu);
            } else{
                getMenuInflater().inflate(R.menu.contact_main_menu, menu);
            }
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch(item.getItemId()) {
            case R.id.editContact:
                Button b = (Button)findViewById(R.id.saveContactBtn);
                b.setVisibility(View.VISIBLE);

                name.setEnabled(true);
                name.setFocusableInTouchMode(true);
                name.setClickable(true);

                phone.setEnabled(true);
                phone.setFocusableInTouchMode(true);
                phone.setClickable(true);

                return true;
            case R.id.deleteContact:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Are you sure, you want to delete it.")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                contactDBHelper.deleteContact(currentId);
                                Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), SQLiteActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });
                AlertDialog d = builder.create();
                d.setTitle("Are you sure");
                d.show();

                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public void run(View view) {
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            int id = extras.getInt("id");
            if(id>0){
                if(contactDBHelper.updateContact(currentId, name.getText().toString(), phone.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), SQLiteActivity.class);
                    startActivity(intent);
                } else{
                    Toast.makeText(getApplicationContext(), "not Updated", Toast.LENGTH_SHORT).show();
                }
            } else{
                if(contactDBHelper.insertContact(name.getText().toString(), phone.getText().toString())){
                    Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(getApplicationContext(), "not done", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(getApplicationContext(), SQLiteActivity.class);
                startActivity(intent);
            }
        }
    }
}
