package com.github.ali.saveanddisplaysql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;


class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "people_table";
    private static final String COL1 = "ID";
    private static final String COL2 = "name";
    Context mContext;

    public DatabaseHelper(Context context) {

        super(context, TABLE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable = "CREATE TABLE " + TABLE_NAME + " (" + COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                COL2 + " TEXT);";
        db.execSQL(createTable);

        // it was commented bcz mContext was null at this point
      //  Toast.makeText(mContext, "table created", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String item) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, item);

        Log.d("addData", " Adding " + item + " to " + TABLE_NAME);
        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }


    }
    /*
     * return all the data from the database
     *
     * */

    public Cursor getData() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        return cursor;
    }

    /**
     * get item id from the db
     */

    public Cursor getItemID(String name) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query = "SELECT " + COL1 + " FROM " + TABLE_NAME +
                " WHERE " + COL2 + " = '" + name + "'";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        return cursor;

    }

    public void updateName(String newName, int ID, String oldName) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL2 + " = '" + newName + "'" +
                " WHERE " + COL1 + "  = '" + ID + "' AND " + COL2 + " = '" + oldName + "'";

        sqLiteDatabase.execSQL(query);

    }

    public void deleteName(int ID, String name) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query = " DELETE " + " FROM " + TABLE_NAME
                + " WHERE " + COL1 + " = '" + ID + "'" + " AND "
                + COL2 + " ='" + name + "'";
        sqLiteDatabase.execSQL(query);
    }


}
