package ryanman.example.sqlite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class ContactDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String CONTACTS_TABLE_NAME = "contacts";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_NAME = "name";
    public static final String CONTACTS_COLUMN_PHONE = "phone";

    public ContactDBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + CONTACTS_TABLE_NAME +
                        "(" + CONTACTS_COLUMN_ID + " integer primary key, " +
                        CONTACTS_COLUMN_NAME + " text, " +
                        CONTACTS_COLUMN_PHONE + " text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CONTACTS_TABLE_NAME);
        onCreate(db);
    }

    public boolean insertContact(String name, String phone) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(CONTACTS_TABLE_NAME, null, contentValues);
        db.close();
        return true;
    }

    public Contact getContactById(int id){
        Contact contact=null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery("select * from contacts where id=" + id + "", null);
        if (cursor != null) {
            cursor.moveToFirst();

            contact = new Contact(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(cursor.getColumnIndex(ContactDBHelper.CONTACTS_COLUMN_NAME)),
                    cursor.getString(cursor.getColumnIndex(ContactDBHelper.CONTACTS_COLUMN_PHONE)));
        }
        cursor.close();
        return contact;
    }

    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<Contact>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT  * FROM " + CONTACTS_TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            Contact contact=null;
            do {
                contact = new Contact(Integer.parseInt(cursor.getString(0)),
                        cursor.getString(cursor.getColumnIndex(ContactDBHelper.CONTACTS_COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndex(ContactDBHelper.CONTACTS_COLUMN_PHONE)));

                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        return contactList;
    }

    public boolean updateContact (Integer id, String name, String phone) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);

        SQLiteDatabase db = this.getWritableDatabase();
        db.update(CONTACTS_TABLE_NAME, contentValues, "id = ? ", new String[]{Integer.toString(id)});
        db.close();
        return true;
    }

    public void deleteContact (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(CONTACTS_TABLE_NAME, "id = ? ", new String[] { Integer.toString(id) });
        db.close();
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME);
        return numRows;
    }
}
