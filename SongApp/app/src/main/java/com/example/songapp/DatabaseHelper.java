package com.example.songapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

class DatabaseHelper extends SQLiteOpenHelper {


    private Context context;
    private static final String DATABASE_NAME = "SongApp.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "Songs";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_CATEGORY = "category";
    private static final String COLUMN_ARTIST = "artist";
    private static final String COLUMN_RATING = "rating";
    private static final String COLUMN_CRITIC = "critic";
    private static final String COLUMN_DURATION = "duration";
    private static final String COLUMN_LINK = "link";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = " CREATE TABLE " + TABLE_NAME +
                " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_NAME + " TEXT, " +
        COLUMN_CATEGORY + " TEXT, " +
                COLUMN_ARTIST + " TEXT, " +
                COLUMN_RATING + " INTEGER, " +
                COLUMN_CRITIC + " TEXT, " +
                COLUMN_DURATION + " INTEGER, " +
                COLUMN_LINK + " TEXT); " ;

        db.execSQL(query);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    void addSong(String name, String category, String artist, int rating, String critic, int duration, String link)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_CATEGORY, category);
        cv.put(COLUMN_ARTIST, artist);
        cv.put(COLUMN_RATING, rating);
        cv.put(COLUMN_CRITIC, critic);
        cv.put(COLUMN_DURATION, duration);
        cv.put(COLUMN_LINK, link);

        long result = db.insert(TABLE_NAME, null, cv);

        if(result == -1)
        {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show();

        }


    }

    Cursor readAllData()
    {
        String query = " SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;

        if(db!=null)
        {
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }

    void updateData(String row_id, String name, String category, String artist, String rating, String critic, String duration, String link) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_CATEGORY, category);
        cv.put(COLUMN_ARTIST, artist);
        cv.put(COLUMN_RATING, rating);
        cv.put(COLUMN_CRITIC, critic);
        cv.put(COLUMN_DURATION, duration);
        cv.put(COLUMN_LINK, link);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
}
