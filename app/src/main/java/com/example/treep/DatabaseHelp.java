package com.example.treep;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DatabaseHelp extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="register.db";
    public static final String TABLE_NAME="registeruser";
    public static final String COL_1="ID";
    public static final String COL_2="Username";
    public static final String COL_3="Password";

    public DatabaseHelp( Context context) {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
      db.execSQL("CREATE TABLE registeruser(ID INTEGER PRIMARY KEY AUTOINCREMENT, Username TEXT,Password TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
        onCreate(db);

    }
    public long adduser (String username, String password){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        long res = sqLiteDatabase.insert("registeruser",null,contentValues);
        sqLiteDatabase.close();
        return res;
    }
    public boolean checkUser(String username, String password){
        String[] columns = {COL_1};
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String selection = COL_2 + "=?" + "and" + COL_3 + "=?";
        String[] selectionArgs = {username,password};
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        sqLiteDatabase.close();
        if(count>0)
            return true;
        else
            return false;

    }
}
