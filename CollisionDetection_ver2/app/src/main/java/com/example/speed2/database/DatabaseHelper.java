package com.example.speed2.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.speed2.database.model.Dataset;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "Collision";

    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create notes table
        db.execSQL(Dataset.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Dataset.TABLE_NAME);
        //db.execSQL("DROP TABLE IF EXISTS " + Login.TABLE_LOGIN);

        // Create tables again
        onCreate(db);
    }

    public void insertData(double x, double y, double z, boolean sensor, double kecepatan) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Dataset.COLUMN_X, x);
        values.put(Dataset.COLUMN_Y, y);
        values.put(Dataset.COLUMN_Z, z);
        values.put(Dataset.COLUMN_VELO, kecepatan);

        long id = db.insert(Dataset.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
    }
}
