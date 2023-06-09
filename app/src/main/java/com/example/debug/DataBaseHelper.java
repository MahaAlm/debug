package com.example.debug;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String TOOL_TABLE = "TOOL_TABLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_NAME = "NAME ";
    public static final String COLUMN_RATE = "RATE ";
    public static final String COLUMN_MODEL = "MODEL ";
    public static final String COLUMN = "user";
    public static final String COLUMN_OVERVIEW = "TOOL_OVERVIEW ";
    public static final String COLUMN_COST = "TOOL_USAGE ";
    public static final String COLUMN_PRODUCTION = "PRODUCTION";
    public static final String COLUMN_RATENUM = "RATENUM ";
    public static final String TABLENAME = "users";
    public static final String COL1 = "username";
    public static final String COL2 = "password";


    public static final String TABLERENT = "RENT";
    public static final String IT_renter = "renter";
    public static final String IT_ID = "ID";



    public DataBaseHelper(@Nullable Context context) {

        super(context, "tool.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement="CREATE TABLE " +TOOL_TABLE+
                "(" +COLUMN_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +COLUMN_NAME+ " TEXT,"+
                COLUMN_RATE+" INT, "
                +COLUMN_MODEL+ " TEXT, "
                +COLUMN_OVERVIEW+ " TEXT, "
                +COLUMN_COST+ " INTEGER, "
                +COLUMN_PRODUCTION+ " TEXT, "
                +COLUMN_RATENUM+ " INTEGER, "
                + COLUMN + " TEXT, "
                + "FOREIGN KEY(" + COLUMN + ") REFERENCES " + TABLENAME + "(" + COL1 + ")"
                + ")";


        db.execSQL("create Table " + TABLERENT + "(" + IT_ID + " INTEGER primary key, " +IT_renter+ " TEXT "+ ")" );

        db.execSQL("create Table " + TABLENAME + "(" + COL1 + " TEXT primary key, " + COL2 + " TEXT)");
        db.execSQL(createTableStatement);



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop Table if exists " + TABLENAME);
    }

    public Boolean insertData(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL1, username);
        contentValues.put(COL2, password);
        long result = MyDB.insert(TABLENAME, null, contentValues);
        if(result==-1) return false;
        return true;
    }


    public Boolean getRdata(String name,int id) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from " + TABLERENT + " where " + IT_renter + " = ? AND ID = ?", new String[]{name,String.valueOf(id)});
        if (cursor.getCount() > 0) return true;
        return false;
    }

    public Boolean getRdata2(String name,int id) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from " + TOOL_TABLE + " where " + COLUMN + " = ? AND ID = ?", new String[]{name,String.valueOf(id)});
        if (cursor.getCount() > 0) return true;
        return false;
    }
    public Boolean checkRented(int id) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from " + TABLERENT + " where " + IT_ID + " = ?", new String[]{String.valueOf(id)});
        if (cursor.getCount() > 0) return true;
        return false;
    }
    public Boolean checkUsername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from " + TABLENAME + " where " + COL1 + " = ?", new String[]{username});
        if (cursor.getCount() > 0) return true;
        return false;
    }
    public Boolean checkUsernameDel(String username, int IDD) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM TOOL_TABLE WHERE  user = ? AND ID = ?", new String[] {username, String.valueOf(IDD)});//IDD المفروض انه رقم
        if (cursor.getCount() > 0) return true;
        return false;
    }
    public Boolean checkUsernamePassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from " + TABLENAME + " where " + COL1 + " = ? and " + COL2 + " = ?", new String[] {username,password});
        if(cursor.getCount()>0) return true;
        return false;
    }

    public boolean addOne(toolModel toolmodel, String ue){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv =new ContentValues();
        cv.put(COLUMN_RATE,0);
        cv.put(COLUMN_NAME,toolmodel.getName());
        cv.put(COLUMN_MODEL,toolmodel.getModel());
        cv.put(COLUMN_OVERVIEW,toolmodel.getOverview());
        cv.put(COLUMN_COST,toolmodel.getCost());
        cv.put(COLUMN_PRODUCTION,toolmodel.getProdYear());
        cv.put(COLUMN_RATENUM,0);
        cv.put(COLUMN, ue);
        long insert = db.insert(TOOL_TABLE, null, cv);
        return insert != -1;
    }

    public boolean addToRent(int id, String ue){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv =new ContentValues();
        cv.put(IT_ID,id);
        cv.put(IT_renter,ue);
        long insert = db.insert(TABLERENT, null, cv);
        return insert != -1;
    }

    public boolean deleteFromRent(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString= "Delete From " + TABLERENT + " WHERE " + IT_ID + " = " + id ;
        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()){
            return true;
        } else{
            // nothing happens. no one is added.
            return false;
        }
        //close
    }

    public Cursor getOne(int id){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from TOOL_TABLE Where id="+id, null);
        return cursor;
    }




    public boolean DeleteOne(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString= "Delete From " + TOOL_TABLE + " WHERE " + COLUMN_ID + " = " + id ;
        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()){
            return true;
        } else{
            // nothing happens. no one is added.
            return false;
        }
        //close
    }

    public Cursor getdata() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from TOOL_TABLE ", null);
        return cursor;
    }



}
