package com.inti.bayviewhotel;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.ContentView;
import androidx.annotation.Nullable;

class MyDatabaseHelper2 extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Housekeeping.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "my_housekeeping";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TYPE = "_type";
    private static final String COLUMN_STATUS = "_status";
    private static final String COLUMN_STAFF = "_staff";

    MyDatabaseHelper2(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db2) {
        String query = "CREATE TABLE " + TABLE_NAME +
                "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TYPE + " TEXT, " +
                COLUMN_STATUS + " TEXT, " +
                COLUMN_STAFF + " TEXT) ;";
        db2.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db2, int oldVersion, int newVersion) {
        db2.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db2);
    }

    void addRoom(String type, String status, String staff) {
        SQLiteDatabase db2 = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TYPE, type);
        cv.put(COLUMN_STATUS, status);
        cv.put(COLUMN_STAFF, staff);
        long result = db2.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Successfully added", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db2 = this.getReadableDatabase();
        Cursor cursor = null;
        if(db2 != null){
            cursor = db2.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData2(String row_id, String type, String status, String staff){
        SQLiteDatabase db2 = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TYPE, type);
        cv.put(COLUMN_STATUS, status);
        cv.put(COLUMN_STAFF, staff);

        long result2 = db2.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result2 == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }

    void deleteOneRow2(String row_id){
        SQLiteDatabase db2 = this.getWritableDatabase();
        long result = db2.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAllData(){
        SQLiteDatabase db2 = this.getWritableDatabase();
        db2.execSQL("DELETE FROM " + TABLE_NAME);
    }
}
