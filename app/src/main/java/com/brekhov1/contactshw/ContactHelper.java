package com.brekhov1.contactshw;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import static com.brekhov1.contactshw.DBHelper.TABLE_NAME;

public class ContactHelper {

    SQLiteDatabase database;

    ContactHelper(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    long insert(String name, String phoneNr, String birthDate) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(DBHelper.COLUMN_NAME, name);
        contentValues.put(DBHelper.COLUMN_PHONENR, phoneNr);
        contentValues.put(DBHelper.COLUMN_BIRTHDATE, birthDate);

        return database.insert(TABLE_NAME, null, contentValues);
    }

    ArrayList<Contact> getAll() {

        Cursor mCursor = database.query(TABLE_NAME, null, null, null, null, null, null);
        ArrayList<Contact> arr = new ArrayList<>();

        mCursor.moveToFirst();
        if (!mCursor.isAfterLast()) {
            do {
                long id = mCursor.getLong(DBHelper.NUM_COLUMN_ID);
                String name = mCursor.getString(DBHelper.NUM_COLUMN_NAME);
                String phoneNr = mCursor.getString(DBHelper.NUM_COLUMN_PHONENR);
                String birthDate = mCursor.getString(DBHelper.NUM_COLUMN_BIRTHDATE);

                arr.add(new Contact(id, name, phoneNr, birthDate));

            } while (mCursor.moveToNext());
        }
        database.close();
        return arr;
    }

    Contact getContact(int id){
        Cursor c = database.query(TABLE_NAME, null, null, null, null, null,
                null);
        c.moveToPosition(id);
        Contact contact = new Contact(c.getLong(0),c.getString(1),c.getString(2),c.getString(3));
        database.close();
        return contact;
    }
    void update(Contact contact){
        ContentValues cv = new ContentValues();

        cv.put(DBHelper.COLUMN_NAME, contact.getName());
        cv.put(DBHelper.COLUMN_PHONENR, contact.getPhoneNr());
        cv.put(DBHelper.COLUMN_BIRTHDATE, contact.getBirthDate());

        Log.e("update",contact.toString());

        database.update(DBHelper.TABLE_NAME,cv,"_id=?",new String[]{String.valueOf(contact.getId())});
        database.close();


    }

    void delete(long id){
        database.delete(DBHelper.TABLE_NAME,"_id=" + id,null);
        database.close();
    }

}
