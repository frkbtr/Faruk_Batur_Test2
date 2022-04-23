package com.example.faruk_batur_test2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "Questiondata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Questiondetails (question TEXT primary key, answer1 TEXT, answer2 TEXT, answer3 TEXT, answer4 TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Questiondetails");
    }

    public boolean insertquestiondata(String question, String answer1, String answer2, String answer3, String answer4){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("question", question);
        contentValues.put("answer1", answer1);
        contentValues.put("answer2", answer2);
        contentValues.put("answer3", answer3);
        contentValues.put("answer4", answer4);

        long result = DB.insert("Questiondetails", null, contentValues);
        if ( result == -1){
            return false;
        } else {
            return true;
        }
    }

    public boolean updatequestiondata(String question, String answer1, String answer2, String answer3, String answer4){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("answer1", answer1);
        contentValues.put("answer2", answer2);
        contentValues.put("answer3", answer3);
        contentValues.put("answer4", answer4);
        Cursor cursor = DB.rawQuery("Select * from Questiondetails where question = ?", new String[] {question});


        if (cursor.getCount() > 0) {

            long result = DB.update("Questiondetails", contentValues, "question=?", new String[] {question});
            if ( result == -1){
                return false;
            } else {
                return true;
            }
        } else{
            return false;
        }
    }


    public boolean deletedata(String question){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Questiondetails where question = ?", new String[] {question});

        if (cursor.getCount() > 0) {

            long result = DB.delete("Questiondetails", "question=?", new String[] {question});
            if ( result == -1){
                return false;
            } else {
                return true;
            }
        } else{
            return false;
        }

    }

    public Cursor getdata(){

        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Questiondetails", null);

        return cursor;
    }


}
