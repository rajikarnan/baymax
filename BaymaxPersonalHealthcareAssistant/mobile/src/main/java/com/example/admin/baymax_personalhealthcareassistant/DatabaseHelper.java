package com.example.admin.baymax_personalhealthcareassistant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.NoCopySpan;

import static android.database.sqlite.SQLiteDatabase.*;

/**
 * Created by Admin on 3/6/2019.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="registerDB";
    public static final String TABLE_NAME="registeruser";
    public static final String COL_1="ID";
    public static final String COL_2="USERNAME";
    public static final String COL_3="PASSWORD";
    public static final String COL_4="EMAIL";
    public static final String COL_5="PHONE";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE registeruser(ID INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME TEXT,PASSWORD TEXT, EMAIL TEXT,PHONE INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    public long addUser (String user, String password, String mail, String phone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Username",user);
        contentValues.put("PASSWORD", password);
        contentValues.put("EMAIL",mail);
        contentValues.put("PHONE",phone);
        long res = db.insert("registeruser", null, contentValues);
        db.close();
        return res;
    }
    public boolean checkUser (String username, String password){
        String[] columns = { COL_1};
        SQLiteDatabase db = getReadableDatabase();
        String selection = COL_2 + "=?" + " and " + COL_3 + "=?";
        String[] selectionArgs = {username,password};
        Cursor cursor = db.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
        //Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + "WHERE" + username + " =? AND " + password + " =? ",null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        if (count>0)
            return true;
        else
            return false;
    }
}
