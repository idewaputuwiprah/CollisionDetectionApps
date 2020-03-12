package com.example.speed2.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.speed2.database.model.User;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "Collision";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create notes table
        db.execSQL(User.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + User.TABLE_NAME);
        //db.execSQL("DROP TABLE IF EXISTS " + Login.TABLE_LOGIN);

        // Create tables again
        onCreate(db);
    }

    public void insertData(String nik, String nama, String tanggal_lahir, String alamat, String no_hp) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(User.COLUMN_NIK, nik);
        values.put(User.COLUMN_NAMA, nama);
        values.put(User.COLUMN_TGLLAHIR, tanggal_lahir);
        values.put(User.COLUMN_ALAMAT, alamat);
        values.put(User.COLUMN_NOHP, no_hp);

        long id = db.insert(User.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
    }

    public long insertNoAlert(String no_alert, String nama) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(User.COLUMN_NOHP, no_alert);

//        long id = db.insert(User.TABLE_NAME, null, values);
        return db.update(User.TABLE_NAME, values, User.COLUMN_NAMA + " = ?",
                new String[]{nama});

        // return newly inserted row id
    }
}
