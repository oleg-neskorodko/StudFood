package com.mdgroup.studfood;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private String keys[];
    public static final String DATABASE_NAME = "recipesDB";
    public static final String TABLE_NAME = "recipesTable";

    private static final String keyID = "_id";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        keys = new String[] {"name", "pictureID", "ingredients", "technology"};
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(" + keyID + " integer primary key, " + keys[0] + " text, "
                + keys[1] + " integer, " + keys[2] + " text, " + keys[3] + " text " + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(db);
    }
}
