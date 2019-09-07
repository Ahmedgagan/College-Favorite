package com.collegefavourite;

import java.util.ArrayList;
import java.util.HashMap;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "demodb";
    public static final String LIST_TABLE_NAME = "list";
    public static final String LIST_COLUMN_NAME = "name";
    public static final String List_COLUMN_FAVOURITE = "Favourite";
    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table list " +
                        "(name text UNIQUE,Favourite text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS list");
        onCreate(db);
    }

    public long insertContact (String name,String favourite) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("favourite",favourite);


        return db.insert("list", null, contentValues);
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from list", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, LIST_TABLE_NAME);
        return numRows;
    }

    public boolean updateContact (String name, String favourite) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("favourite", favourite);
        db.update("list", contentValues, "name = ? ", new String[] { name } );
        return true;
    }

    public Integer deleteContact (String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("list",
                "name = ? ",
                new String[] { name });
    }

    public ArrayList<String> getAllCotacts() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from list", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(LIST_COLUMN_NAME))+" "+res.getString(res.getColumnIndex("Favourite")));
            res.moveToNext();
        }
        return array_list;
    }
    public ArrayList<String> getAllFavouriteCotacts() {
        ArrayList<String> array_list = new ArrayList<String>();
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from list where Favourite=?", new String[]{"FAVORITE"} );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(LIST_COLUMN_NAME))+" "+res.getString(res.getColumnIndex(List_COLUMN_FAVOURITE)));
            res.moveToNext();
        }
        return array_list;
    }
}
