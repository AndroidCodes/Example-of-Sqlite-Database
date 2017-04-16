package com.example.peacock.androidsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import bean.Contact;

/**
 * Created by Peacock on 15/03/17.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "contactsManager";

    // Contacts table name
    private static final String TBL_EAT = "Eat";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_DATE = "Date";
    private static final String KEY_CHALLANNO = "ChallanNo";
    private static final String KEY_MAPPING = "Mapping";
    private static final String KEY_PARTY_NAME = "PartyName";
    private static final String KEY_RATE = "Rate";
    private static final String KEY_TOTAL = "Total";
    private static final String KEY_TRUCK_NO = "TruckNo";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_CONTACTS_TABLE1 = "CREATE TABLE " + TBL_EAT + "(" + KEY_ID +
                " INTEGER PRIMARY KEY," + KEY_DATE + " TEXT," + KEY_CHALLANNO + " TEXT,"
                + KEY_MAPPING + " TEXT," + KEY_PARTY_NAME + " TEXT," + KEY_RATE + " TEXT," +
                KEY_TOTAL + " TEXT," + KEY_TRUCK_NO + " TEXT" + ");";

        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TBL_EAT);
        // Create tables again
        onCreate(db);

    }

    public void addContact(Contact contact) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, contact.get_id());
        values.put(KEY_DATE, contact.get_Date());
        values.put(KEY_CHALLANNO, contact.get_ChallanNo());
        values.put(KEY_MAPPING, contact.get_Mapping());
        values.put(KEY_PARTY_NAME, contact.get_PartyName());
        values.put(KEY_RATE, contact.get_Rate());
        values.put(KEY_TOTAL, contact.get_Total());
        values.put(KEY_TRUCK_NO, contact.get_TruckNo());

        // Inserting Row
        long l = db.insert(TBL_EAT, null, values);
        db.close(); // Closing database connection

    }

    // Getting All Contacts
    public List<Contact> getAllContacts() {

        List<Contact> contactList = new ArrayList<Contact>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TBL_EAT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {

            do {

                Contact contact = new Contact();
                contact.set_id(Integer.parseInt(cursor.getString(0)));
                contact.set_Date(cursor.getString(1));
                contact.set_ChallanNo(cursor.getString(2));
                contact.set_Mapping(cursor.getString(3));
                contact.set_PartyName(cursor.getString(4));
                contact.set_Rate(cursor.getString(5));
                contact.set_Total(cursor.getString(6));
                contact.set_TruckNo(cursor.getString(7));

                contactList.add(contact);

            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;

    }

    // Getting All Contacts By name
    public List<Contact> getSelectedContacts(String names) {
        List<Contact> contactList = new ArrayList<Contact>();
        // Select All Query
        String query = "SELECT * FROM " + TBL_EAT + " WHERE " + KEY_PARTY_NAME + " IN (?)";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, new String[]{names});

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {

            do {

                Contact contact = new Contact();
                contact.set_id(Integer.parseInt(cursor.getString(0)));
                contact.set_id(Integer.parseInt(cursor.getString(0)));
                contact.set_Date(cursor.getString(1));
                contact.set_ChallanNo(cursor.getString(2));
                contact.set_Mapping(cursor.getString(3));
                contact.set_PartyName(cursor.getString(4));
                contact.set_Rate(cursor.getString(5));
                contact.set_Total(cursor.getString(6));
                contact.set_TruckNo(cursor.getString(7));

                contactList.add(contact);

            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;

    }

    // Updating single contact
    public int updateContact(Contact contact) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DATE, contact.get_Date());
        values.put(KEY_CHALLANNO, contact.get_ChallanNo());
        values.put(KEY_MAPPING, contact.get_Mapping());
        values.put(KEY_PARTY_NAME, contact.get_PartyName());
        values.put(KEY_RATE, contact.get_Rate());
        values.put(KEY_TOTAL, contact.get_Total());
        values.put(KEY_TRUCK_NO, contact.get_TruckNo());

        // updating row
        return db.update(TBL_EAT, values, KEY_ID + " = ?", new String[]{String.valueOf(contact.
                get_id())});

    }

    // Deleting single contact
    public void deleteContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TBL_EAT, KEY_ID + " = ?",
                new String[]{String.valueOf(contact.get_id())});
        db.close();
    }

}
