package com.sb.android.myapplication.database.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sb.android.myapplication.database.contract.DbContract;

/**
 * Created by student on 2015-09-18.
 */
public class DbHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "User.db";
    public static final int DB_VER = 1;
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE "+ DbContract.UserEntry.TABLE_NAME+ "("+
                    DbContract.UserEntry._ID+ " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DbContract.UserEntry.COLUMN_NAME_NICKNAME+ " TEXT NOT NULL," +
                    DbContract.UserEntry.COLUMN_NAME_EMAIL+ " TEXT NOT NULL," +
                    DbContract.UserEntry.COLUMN_NAME_PASSWORD+ " TEXT NOT NULL);";

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Cursor query(ContentValues values) {
        SQLiteDatabase db = getReadableDatabase();

        String selection= null;
        String[] selectionArgs= null;
        String[] projection= null;
        if(values.getAsString("Mode").equals("LoginIdCheck")) {
            selection = DbContract.UserEntry.COLUMN_NAME_EMAIL + "= ? and "+
                    DbContract.UserEntry.COLUMN_NAME_PASSWORD + "= ?;";
            values.remove("Mode");
            selectionArgs = new String[]{
                    values.getAsString(DbContract.UserEntry.COLUMN_NAME_EMAIL),
                    DbContract.UserEntry.COLUMN_NAME_PASSWORD
            };

            // Define a projection that specifies which columns from the database
            // you will actually use after this query.
            projection = new String[]{
                    DbContract.UserEntry.COLUMN_NAME_EMAIL,
                    DbContract.UserEntry.COLUMN_NAME_PASSWORD,
            };
        } else {
            projection= new String[] {
                    DbContract.UserEntry.COLUMN_NAME_NICKNAME,
                    DbContract.UserEntry.COLUMN_NAME_EMAIL,
                    DbContract.UserEntry.COLUMN_NAME_PASSWORD,
            };
        }
        // How you want the results sorted in the resulting Cursor
        //String sortOrder =
        //        DbContract.UserEntry.COLUMN_NAME_UPDATED + " DESC";

        Cursor cursor = db.query(
                DbContract.UserEntry.TABLE_NAME,   // The table to query
                projection,                        // The columns to return
                selection,//selection,             // The columns for the WHERE clause
                selectionArgs,//selectionArgs,     // The values for the WHERE clause
                null,                              // group by- don't group the rows
                null,                              // having- don't filter by row groups
                null//sortOrder                    // order by- The sort order
        );

        return cursor;
    }

    public long insert(ContentValues values) {
        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                DbContract.UserEntry.TABLE_NAME,
                null,
                values);

        return newRowId;
    }

    public int update(ContentValues values) {
        SQLiteDatabase db = getReadableDatabase();


        String selection = DbContract.UserEntry.COLUMN_NAME_EMAIL + "= ?";
        String[] selectionArgs = {values.getAsString(DbContract.UserEntry.COLUMN_NAME_EMAIL)};

        values.remove(DbContract.UserEntry.COLUMN_NAME_EMAIL);

        int count = db.update(
                DbContract.UserEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        return count;
    }

    public int delete(ContentValues values) {
        SQLiteDatabase db= getWritableDatabase();

        // Define 'where' part of query.
        String email= values.getAsString(DbContract.UserEntry.COLUMN_NAME_EMAIL);
        String selection =  DbContract.UserEntry.COLUMN_NAME_EMAIL + " = '"+ email+ "'";

        // Issue SQL statement.
        int deleted= db.delete(DbContract.UserEntry.TABLE_NAME, selection, null);

        return deleted;
    }

}
