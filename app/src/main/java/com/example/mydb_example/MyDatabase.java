package com.example.mydb_example;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDatabase extends SQLiteOpenHelper {
    public MyDatabase(@Nullable Context context) {
        super(context, "mydb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table student(id integer primary key autoincrement , username varchar(20),Email varchar(20),password varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    long insertData(String username,String Email,String Password)
    {
        SQLiteDatabase sd = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username",username);
        cv.put("Email",Email);
        cv.put("Password",Password);
       long id= sd.insert("student",null,cv);
       return id;
    }

    Cursor getallData()
    {
        SQLiteDatabase sd = this.getReadableDatabase();
        Cursor c = sd.rawQuery("select * from student ", null);
        return c;
    }

    void updateData(String username,String Email,String Password,String id)
    {
        SQLiteDatabase sd= this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("username",username);
        cv.put("Email",Email);
        cv.put("password",Password);

        sd.update("student",cv,"id =?",new String[]{id});
    }

    Cursor getSpecificData(String id)
    {
        SQLiteDatabase sd = this.getReadableDatabase();
        Cursor c=sd.rawQuery("select * from student where id=? ",new String[]{id});
        return c;
    }


    void deleteSpecificData(String id)
    {
        SQLiteDatabase sd=this.getReadableDatabase();
        sd.delete("student","id =?",new String[]{id});
    }

    Cursor LoginUser(String email,String password)
    {
        SQLiteDatabase sd=this.getReadableDatabase();
        Cursor c = sd.rawQuery("select * from student where email =? and password=?"  ,new String[]{email,password});
        return c;
    }

}
