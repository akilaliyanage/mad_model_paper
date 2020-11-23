package com.example.modelpaper;

import android.provider.BaseColumns;

public final class UserProfile {

    private UserProfile() {
    }

    public static class Users implements BaseColumns{
        //db table

        public static final String TABALE_NAME = "UserInfo";
        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_DATEOFBIRTH = "date_of_birth";
        public static final String COLUMN_NAME_GENDER = "gender";
        public static final String COLUMN_NAME_PASSWORD = "password";

    }
}
