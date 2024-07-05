//package com.example.contactspage;
//
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//import androidx.annotation.Nullable;
//
//import java.util.ArrayList;
//
//public class DatabaseHelper extends SQLiteOpenHelper {
//    public static final String CONTACT = "CONTACT";
//    public static final String COLUMNFNAME = "FNAME";
//    public static final String COLUMNLNAME = "LNAME";
//    public static final String COLUMNPNUMBER = "PNUMBER";
//    public static final String COLUMNEMAIL = "EMAIL";
//
//    public DatabaseHelper(@Nullable Context context) {
//        super(context, "contacts.db", null, 1);
//    }
//
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        String CreateTable = "CREATE TABLE " + CONTACT
//                + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
//                + COLUMNFNAME + " TEXT, "
//                + COLUMNLNAME + " TEXT, "
//                + COLUMNPNUMBER + " TEXT, "
//                + COLUMNEMAIL + "  TEXT)";
//        db.execSQL(CreateTable);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//
//    }
//
//    public boolean add(ContactModel contactModel) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        ContentValues cv = new ContentValues();
//
//        cv.put(COLUMNFNAME, contactModel.getFirstname());
//        cv.put(COLUMNLNAME, contactModel.getLastname());
//        cv.put(COLUMNPNUMBER, contactModel.getPhonenumber());
//        cv.put(COLUMNEMAIL, contactModel.getEmail());
//
//        long insert = db.insert(CONTACT, null, cv);
//
//        return true;
//    }
//
//
//    public static ArrayList<ContactModel> getAllData(){
//        ArrayList<ContactModel> arrayList = new ArrayList<>();
//        String selectQuery = "SELECT * FROM "+CONTACT;
//
//        SQLiteDatabase db = getReadableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery,null);
//
//        if (cursor.moveToFirst()){
//            do {
//                ContactModel ContactModel = new ContactModel(
//                        ""+cursor.getString(((Cursor) cursor).getColumnIndexOrThrow(COLUMNFNAME)),
//                        ""+cursor.getString(cursor.getColumnIndexOrThrow(COLUMNLNAME)),
//                        ""+cursor.getString(cursor.getColumnIndexOrThrow(COLUMNPNUMBER)),
//                        ""+cursor.getString(cursor.getColumnIndexOrThrow(COLUMNEMAIL))
//
//                );
//                arrayList.add(ContactModel);
//            }while (cursor.moveToNext());
//        }
//        db.close();
//        return arrayList;
//    }
//}

package com.example.contactspage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String CONTACT = "CONTACT";
    public static final String COLUMNFNAME = "FNAME";
    public static final String COLUMNLNAME = "LNAME";
    public static final String COLUMNPNUMBER = "PNUMBER";
    public static final String COLUMNEMAIL = "EMAIL";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "contacts.db", null, 1);
    }

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
        // Handle database upgrade if needed
    }

    public boolean add(ContactModel contactModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMNFNAME, contactModel.getFirstname());
        cv.put(COLUMNLNAME, contactModel.getLastname());
        cv.put(COLUMNPNUMBER, contactModel.getPhonenumber());
        cv.put(COLUMNEMAIL, contactModel.getEmail());

        long insert = db.insert(CONTACT, null, cv);
        db.close();

        return insert != -1;
    }

    public ArrayList<ContactModel> getAllData() {
        ArrayList<ContactModel> arrayList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + CONTACT;

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
                arrayList.add(contactModel);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return arrayList;
    }
}
