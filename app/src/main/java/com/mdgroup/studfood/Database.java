package com.mdgroup.studfood;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class Database {

    private DBHelper dbHelper;
    private Context context;

    Database(Context context) {
        this.context = context;
    }

    public boolean baseNotEmpty() {
        dbHelper = new DBHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query(DBHelper.TABLE_NAME, null, null, null, null, null, null);

        if (cursor.getCount() != 0) {
            return true;
        } else return false;
    }

    public void deleteBase() {
        dbHelper = new DBHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.delete(DBHelper.TABLE_NAME, null, null);
        dbHelper.close();
    }

    public void fillBase() {
        String[] keys = new String[] {"name", "pictureID", "ingredients", "technology"};
        dbHelper = new DBHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.beginTransaction();
        ContentValues contentValues = new ContentValues();

        contentValues.put(keys[0], context.getResources().getString(R.string.borsch));
        contentValues.put(keys[1], R.drawable.borsch);
        contentValues.put(keys[2], context.getResources().getString(R.string.borsch2));
        contentValues.put(keys[3], context.getResources().getString(R.string.borsch3));
        database.insert(DBHelper.TABLE_NAME, null, contentValues);

        contentValues.put(keys[0], context.getResources().getString(R.string.buckwheat));
        contentValues.put(keys[1], R.drawable.buckwheat);
        contentValues.put(keys[2], context.getResources().getString(R.string.buckwheat2));
        contentValues.put(keys[3], context.getResources().getString(R.string.buckwheat3));
        database.insert(DBHelper.TABLE_NAME, null, contentValues);

        contentValues.put(keys[0], context.getResources().getString(R.string.goulash));
        contentValues.put(keys[1], R.drawable.goulash);
        contentValues.put(keys[2], context.getResources().getString(R.string.goulash2));
        contentValues.put(keys[3], context.getResources().getString(R.string.goulash3));
        database.insert(DBHelper.TABLE_NAME, null, contentValues);

        contentValues.put(keys[0], context.getResources().getString(R.string.fried_potato));
        contentValues.put(keys[1], R.drawable.fried_potato);
        contentValues.put(keys[2], context.getResources().getString(R.string.fried_potato2));
        contentValues.put(keys[3], context.getResources().getString(R.string.fried_potato3));
        database.insert(DBHelper.TABLE_NAME, null, contentValues);

        contentValues.put(keys[0], context.getResources().getString(R.string.fried_chicken));
        contentValues.put(keys[1], R.drawable.fried_chicken);
        contentValues.put(keys[2], context.getResources().getString(R.string.fried_chicken2));
        contentValues.put(keys[3], context.getResources().getString(R.string.fried_chicken3));
        database.insert(DBHelper.TABLE_NAME, null, contentValues);

        contentValues.put(keys[0], context.getResources().getString(R.string.crab_salad));
        contentValues.put(keys[1], R.drawable.crab_salad);
        contentValues.put(keys[2], context.getResources().getString(R.string.crab_salad2));
        contentValues.put(keys[3], context.getResources().getString(R.string.crab_salad3));
        database.insert(DBHelper.TABLE_NAME, null, contentValues);

        contentValues.put(keys[0], context.getResources().getString(R.string.macaroni));
        contentValues.put(keys[1], R.drawable.macaroni);
        contentValues.put(keys[2], context.getResources().getString(R.string.macaroni2));
        contentValues.put(keys[3], context.getResources().getString(R.string.macaroni3));
        database.insert(DBHelper.TABLE_NAME, null, contentValues);

        contentValues.put(keys[0], context.getResources().getString(R.string.ragout));
        contentValues.put(keys[1], R.drawable.ragout);
        contentValues.put(keys[2], context.getResources().getString(R.string.ragout2));
        contentValues.put(keys[3], context.getResources().getString(R.string.ragout3));
        database.insert(DBHelper.TABLE_NAME, null, contentValues);

        contentValues.put(keys[0], context.getResources().getString(R.string.veg_salad));
        contentValues.put(keys[1], R.drawable.veg_salad);
        contentValues.put(keys[2], context.getResources().getString(R.string.veg_salad2));
        contentValues.put(keys[3], context.getResources().getString(R.string.veg_salad3));
        database.insert(DBHelper.TABLE_NAME, null, contentValues);

        contentValues.put(keys[0], context.getResources().getString(R.string.omelet));
        contentValues.put(keys[1], R.drawable.omelet);
        contentValues.put(keys[2], context.getResources().getString(R.string.omelet2));
        contentValues.put(keys[3], context.getResources().getString(R.string.omelet3));
        database.insert(DBHelper.TABLE_NAME, null, contentValues);

        contentValues.put(keys[0], context.getResources().getString(R.string.pilaf));
        contentValues.put(keys[1], R.drawable.pilaf);
        contentValues.put(keys[2], context.getResources().getString(R.string.pilaf2));
        contentValues.put(keys[3], context.getResources().getString(R.string.pilaf3));
        database.insert(DBHelper.TABLE_NAME, null, contentValues);

        contentValues.put(keys[0], context.getResources().getString(R.string.pork));
        contentValues.put(keys[1], R.drawable.pork);
        contentValues.put(keys[2], context.getResources().getString(R.string.pork2));
        contentValues.put(keys[3], context.getResources().getString(R.string.pork3));
        database.insert(DBHelper.TABLE_NAME, null, contentValues);

        contentValues.put(keys[0], context.getResources().getString(R.string.spaghetti));
        contentValues.put(keys[1], R.drawable.spaghetti);
        contentValues.put(keys[2], context.getResources().getString(R.string.spaghetti2));
        contentValues.put(keys[3], context.getResources().getString(R.string.spaghetti3));
        database.insert(DBHelper.TABLE_NAME, null, contentValues);

        contentValues.put(keys[0], context.getResources().getString(R.string.fish_soup));
        contentValues.put(keys[1], R.drawable.fish_soup);
        contentValues.put(keys[2], context.getResources().getString(R.string.fish_soup2));
        contentValues.put(keys[3], context.getResources().getString(R.string.fish_soup3));
        database.insert(DBHelper.TABLE_NAME, null, contentValues);

        contentValues.put(keys[0], context.getResources().getString(R.string.pie));
        contentValues.put(keys[1], R.drawable.pie);
        contentValues.put(keys[2], context.getResources().getString(R.string.pie2));
        contentValues.put(keys[3], context.getResources().getString(R.string.pie3));

        long inserted = database.insert(DBHelper.TABLE_NAME, null, contentValues);

        database.setTransactionSuccessful();
        database.endTransaction();
        Log.d(MainActivity.TAG, "inserted = " + inserted);
        dbHelper.close();
    }

    public ArrayList<RecipeModel> readBase() {
        String[] keys = new String[] {"name", "pictureID", "ingredients", "technology"};
        ArrayList<RecipeModel> recipeList = new ArrayList<>();
        dbHelper = new DBHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query(DBHelper.TABLE_NAME, null, null, null, null, null, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int[] columnIndexes = new int[keys.length];
            for (int i = 0; i < columnIndexes.length; i++) {
                columnIndexes[i] = cursor.getColumnIndex(keys[i]);
            }
            for (int i = 0; i < cursor.getCount(); i++) {
                RecipeModel recipeModel = new RecipeModel();
                recipeModel.setName(cursor.getString(columnIndexes[0]));
                recipeModel.setPictureID(cursor.getInt(columnIndexes[1]));
                recipeModel.setIngredients(cursor.getString(columnIndexes[2]));
                recipeModel.setTechnology(cursor.getString(columnIndexes[3]));
                recipeList.add(recipeModel);
                cursor.moveToNext();
            }
        }
        dbHelper.close();
        return recipeList;
    }
}
