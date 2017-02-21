package com.example.shannon.brightminds;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Shannon on 11/04/2016.
 */
public class QuizResultsHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "quiz_results_database";
    public static final int DATABASE_VERSION = 4;

    public static final String TABLE_USERS = "user";
    public static final String USER_ID = "user_id";
    public static final String DATE = "date";
    public static final String SCORE = "score";

    public static final String CREATE_USERS_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS " + TABLE_USERS + " ( "
            + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + DATE + " VARCHAR, " + SCORE + " INTEGER)";

    public QuizResultsHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_USERS_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

}