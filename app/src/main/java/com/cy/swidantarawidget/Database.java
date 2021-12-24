package com.cy.swidantarawidget;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    public Database(Context context) {
        super(context, "swidantara_database.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table User(Id INTEGER primary key autoincrement, Nama TEXT, Telepon TEXT, Alamat TEXT, JenisKelamin TEXT, Umur INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists User");
    }

    public Boolean insertUser(String Nama, String Telepon, String Alamat, String JenisKelamin, int Umur)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Nama", Nama);
        contentValues.put("Telepon", Telepon);
        contentValues.put("Alamat", Alamat);
        contentValues.put("JenisKelamin", JenisKelamin);
        contentValues.put("Umur", Umur);
        long result=DB.insert("User", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean updateUser(String Id, String Nama, String Telepon, String Alamat, String JenisKelamin, int Umur) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Nama", Nama);
        contentValues.put("Telepon", Telepon);
        contentValues.put("Alamat", Alamat);
        contentValues.put("JenisKelamin", JenisKelamin);
        contentValues.put("Umur", Umur);
        Cursor cursor = DB.rawQuery("Select * from User where Id = ?", new String[]{Id});
        if (cursor.getCount() > 0) {
            long result = DB.update("User", contentValues, "Id=?", new String[]{Id});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }}


    public Boolean deleteUser(String Id)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from User where Id = ?", new String[]{Id});
        if (cursor.getCount() > 0) {
            long result = DB.delete("User", "Id=?", new String[]{Id});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }

    }

    public Cursor getDataUser ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from User", null);
        return cursor;

    }
}