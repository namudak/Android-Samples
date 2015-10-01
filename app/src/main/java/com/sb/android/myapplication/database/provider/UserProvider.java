package com.sb.android.myapplication.database.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import com.sb.android.myapplication.database.contract.DbContract;
import com.sb.android.myapplication.database.helper.DbHelper;

public class UserProvider extends ContentProvider {

    private static final String AUTHORITY= "com.sbandroid.myapplication.UserProvider";

    public static final Uri CONTENT_URI= Uri.parse("content://"+
                            AUTHORITY+ "/"+ DbContract.UserEntry.TABLE_NAME);

    private static final UriMatcher sUriMatcher= new UriMatcher(UriMatcher.NO_MATCH);
    private static final int ALL= 1;
    private static final int SINGLE= 2;

    static {
        sUriMatcher.addURI(AUTHORITY,DbContract.UserEntry.TABLE_NAME, ALL);
        sUriMatcher.addURI(AUTHORITY,DbContract.UserEntry.TABLE_NAME+ "/@", SINGLE);
    }

    private DbHelper mHelper;

    public UserProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        mHelper= new DbHelper(getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        SQLiteQueryBuilder qb= new SQLiteQueryBuilder();
        qb.setTables(DbContract.UserEntry.TABLE_NAME);

        Cursor cursor= null;

        switch (sUriMatcher.match(uri)){
            case ALL:
                // check if null and ""
                if(TextUtils.isEmpty(sortOrder)){
                    sortOrder= DbContract.UserEntry._ID+ " ASC";
                }
                break;
            case SINGLE:
                selection+= DbContract.UserEntry._ID+ uri.getLastPathSegment();
                break;
        }

        cursor= qb.query(mHelper.getReadableDatabase(),
                    projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);

        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;


    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
