package com.sb.android.myapplication.database.contract;

import android.provider.BaseColumns;

/**
 * Created by student on 2015-09-18.
 */

public final class DbContract {
    public DbContract() {}

    public static abstract class UserEntry implements BaseColumns {
        public static final String TABLE_NAME= "User";
        public static final String COLUMN_NAME_NICKNAME= "nickname";
        public static final String COLUMN_NAME_EMAIL= "email";
        public static final String COLUMN_NAME_PASSWORD= "password";

        public static final String COLUMN_NAME_NULLABLE= null;
    }

}