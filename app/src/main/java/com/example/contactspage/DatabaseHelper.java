package com.example.contactspage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import androidx.annotation.Nullable;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    //defining table and column names
    public static final String CONTACT = "CONTACT";
    public static final String COLUMNFNAME = "FNAME";
    public static final String COLUMNLNAME = "LNAME";
    public static final String COLUMNPNUMBER = "PNUMBER";
    public static final String COLUMNEMAIL = "EMAIL";

    //constructor for dbhelper
    public DatabaseHelper(@Nullable Context context) {
        super(context, "contacts.db", null, 1);
    }



    //the first method called, table creation and schema definition
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + CONTACT
                + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMNFNAME + " TEXT, "
                + COLUMNLNAME + " TEXT, "
                + COLUMNPNUMBER + " TEXT, "
                + COLUMNEMAIL + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    //method that adds a new contact to the db
    public boolean add(ContactModel contactModel) {
        SQLiteDatabase db = this.getWritableDatabase(); // for getting writable db data for reference
        ContentValues cv = new ContentValues(); // object to store keyvalue pairs for insertion

        //putting data in the object
        cv.put(COLUMNFNAME, contactModel.getFirstname());
        cv.put(COLUMNLNAME, contactModel.getLastname());
        cv.put(COLUMNPNUMBER, contactModel.getPhonenumber());
        cv.put(COLUMNEMAIL, contactModel.getEmail());

        long insert = db.insert(CONTACT, null, cv);
        db.close();

        return insert != -1;
    }

    //method that retrieves all the data from db
    public ArrayList<ContactModel> getAllData() {
        ArrayList<ContactModel> arrayList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + CONTACT; //quering the db

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {//going in each row in the cursor
            do {
                ContactModel contactModel = new ContactModel(
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMNFNAME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMNLNAME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMNPNUMBER)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMNEMAIL))
                );
                arrayList.add(contactModel);
                Log.d("DatabaseHelper","Contact retrieved: " +contactModel.toString());
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return arrayList;
    }

    //method for searching contacts based on the search terms
    public ArrayList<ContactModel> searchContacts(String searchTerm) {
        ArrayList<ContactModel> filteredList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + CONTACT +
                " WHERE LOWER(FNAME) LIKE '%" + searchTerm + "%'" +
                " OR LOWER(LNAME) LIKE '%" + searchTerm + "%'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ContactModel contactModel = new ContactModel(
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMNFNAME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMNLNAME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMNPNUMBER)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMNEMAIL))
                );
                filteredList.add(contactModel);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return filteredList;
}}
