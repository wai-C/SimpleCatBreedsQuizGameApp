package com.example.catbreedsquizgame.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BreedsDatabase extends SQLiteOpenHelper {

    public BreedsDatabase(@Nullable Context context) {
        super(context, "catbreedsquizgame.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //execute the following:
        db.execSQL("CREATE TABLE IF NOT EXISTS \"cat_breeds\" (\n" +
                "\t\"id\"\tINTEGER,\n" +
                "\t\"name\"\tTEXT,\n" +
                "\t\"image\"\tTEXT\n" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*
        It completely removes the table structure and associated indexes, statistics, permissions, triggers and constraints
         */
        db.execSQL("DROP TABLE IF EXISTS flag");
        onCreate(db);
    }
}
