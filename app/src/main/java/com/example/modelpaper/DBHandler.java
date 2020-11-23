package com.example.modelpaper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "UserInfo.db";

    public DBHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String SQL_CREATE_TABLE_QUERY = "CREATE TABLE " + UserProfile.Users.TABALE_NAME + "( " + UserProfile.Users._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + UserProfile.Users.COLUMN_NAME_USERNAME + " TEXT," + UserProfile.Users.COLUMN_NAME_DATEOFBIRTH + " TEXT," + UserProfile.Users.COLUMN_NAME_GENDER + " TEXT)";

        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_QUERY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //store user details

    public boolean addInfo(String username, String dob, String gender){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(UserProfile.Users.COLUMN_NAME_USERNAME,username);
        values.put(UserProfile.Users.COLUMN_NAME_DATEOFBIRTH,dob);
        values.put(UserProfile.Users.COLUMN_NAME_GENDER,gender);

        boolean result = db.insert(UserProfile.Users.TABALE_NAME,null,values) > 0;

        return result;
    }

    public boolean updateInfor(int ID,String username, String dob, String gender,String password){
        SQLiteDatabase db = getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(UserProfile.Users.COLUMN_NAME_USERNAME,username);
        values.put(UserProfile.Users.COLUMN_NAME_DATEOFBIRTH,dob);
        values.put(UserProfile.Users.COLUMN_NAME_GENDER,gender);
        values.put(UserProfile.Users.COLUMN_NAME_PASSWORD,password);

        String selection = UserProfile.Users._ID + " LIKE ?";
        String[] selectionArgs = {String.valueOf(ID)};

        boolean rslt = db.update(
                UserProfile.Users.TABALE_NAME,
                values,
                selection,
                selectionArgs
        ) > 0;

        return rslt;

    }

    public ArrayList<UserData> readAllInfor(){
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                UserProfile.Users._ID,
                UserProfile.Users.COLUMN_NAME_USERNAME,
                UserProfile.Users.COLUMN_NAME_DATEOFBIRTH,
                UserProfile.Users.COLUMN_NAME_GENDER,
                UserProfile.Users.COLUMN_NAME_PASSWORD
        };

        String sortOrder = UserProfile.Users.COLUMN_NAME_USERNAME + " DESC";

        Cursor cursor = db.query(
                UserProfile.Users.TABALE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );

        ArrayList<UserData> userdata = new ArrayList<UserData>();

        while (cursor.moveToNext()){
            String username = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_NAME_USERNAME));
            String dob = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_NAME_DATEOFBIRTH));
            String gender = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_NAME_GENDER));
            String password = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_NAME_PASSWORD));

            UserData userData = new UserData(username,dob,password,gender);

            userdata.add(userData);
        }

        return userdata;
    }

    public ArrayList<UserData> readAllInfor(int ID){
        SQLiteDatabase db = getWritableDatabase();

        String[] projection = {
                UserProfile.Users._ID,
                UserProfile.Users.COLUMN_NAME_USERNAME,
                UserProfile.Users.COLUMN_NAME_DATEOFBIRTH,
                UserProfile.Users.COLUMN_NAME_GENDER,
                UserProfile.Users.COLUMN_NAME_PASSWORD
        };

        String selection = UserProfile.Users._ID +  " = ?";
        String[] selectionArgs = {String.valueOf(ID)};

        Cursor cursor = db.query(
                UserProfile.Users.TABALE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        ArrayList<UserData> userdata = new ArrayList<UserData>();

        while(cursor.moveToNext()){
            String username = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_NAME_USERNAME));
            String dob = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_NAME_DATEOFBIRTH));
            String gender = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_NAME_GENDER));
            String password = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_NAME_PASSWORD));

            UserData data = new UserData(username,dob,password,gender);
            userdata.add(data);
        }

        return userdata;
    }

    public boolean deleteInfo(int ID){
        SQLiteDatabase db = getReadableDatabase();

        String selection = UserProfile.Users._ID + " LIKE ?";
        String[] selectionArgs = {String.valueOf(ID)};

        boolean rslt = db.delete(UserProfile.Users.TABALE_NAME,selection,selectionArgs) > 0;

        return rslt;
    }
}
