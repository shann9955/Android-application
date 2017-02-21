package com.example.shannon.brightminds;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Shannon on 11/04/2016.
 */
public class ResultsProcessor {

    private static ResultsProcessor instance = null;

    GameResultsHelper helper = null;
    QuizResultsHelper helper2 = null;
    SQLiteDatabase db;
    SQLiteDatabase db2;

    private ResultsProcessor(Context context) {
        helper = new GameResultsHelper(context);
        helper2 = new QuizResultsHelper(context);
    }

    public static ResultsProcessor getInstance(Context context) {
        if (instance == null) {
            instance = new ResultsProcessor(context);
        }
        return instance;
    }

    public void open() {
        if (db == null || !db.isOpen()) {
            db = helper.getWritableDatabase();

        }
    }

    public void open2(){
        if(db2 == null || !db2.isOpen()){
            db2 = helper2.getWritableDatabase();
        }
    }

    public ArrayList<UserResults> getUsers() {
        ArrayList<UserResults> users = new ArrayList<UserResults>();
        open();
        Cursor c = db.rawQuery("SELECT * FROM " + GameResultsHelper.TABLE_USERS + " ORDER BY " + GameResultsHelper.SCORE + " DESC", null);
        while (c.moveToNext()) {
            int id = c.getInt(c.getColumnIndex(GameResultsHelper.USER_ID));
            String date = c.getString(c.getColumnIndex(GameResultsHelper.DATE));
            int score = c.getInt(c.getColumnIndex(GameResultsHelper.SCORE));
            users.add(new UserResults(id, date, score));
        }
        return users;
    }

    public ArrayList<UserResults> getUser() {
        ArrayList<UserResults> user = new ArrayList<UserResults>();
        open2();
        Cursor c = db2.rawQuery("SELECT * FROM " + QuizResultsHelper.TABLE_USERS + " ORDER BY " + QuizResultsHelper.SCORE + " DESC", null);
        while (c.moveToNext()) {
            int id = c.getInt(c.getColumnIndex(QuizResultsHelper.USER_ID));
            String date = c.getString(c.getColumnIndex(QuizResultsHelper.DATE));
            int score = c.getInt(c.getColumnIndex(QuizResultsHelper.SCORE));
            user.add(new UserResults(id, date, score));
        }
        return user;
    }

    public int createNewDate(String date, int score) {
        open();
        if (isDateExist(date)) {
            ContentValues cv = new ContentValues();
            cv.put(GameResultsHelper.SCORE, score);
            int n = db.update(GameResultsHelper.TABLE_USERS, cv, GameResultsHelper.DATE + " = '" + date + "'" + "     ", null);
            return n;
        } else {
            ContentValues cv = new ContentValues();
            cv.put(GameResultsHelper.DATE, date);
            cv.put(GameResultsHelper.SCORE, score);
            long n = db.insert(GameResultsHelper.TABLE_USERS, null, cv);
            return (int) n;
        }
    }

    public int createNewDate2(String date, int score) {
        open2();
        if (isDateExist2(date)) {
            ContentValues cv = new ContentValues();
            cv.put(QuizResultsHelper.SCORE, score);
            int n = db2.update(QuizResultsHelper.TABLE_USERS, cv, QuizResultsHelper.DATE + " = '" + date + "'" + "     ", null);
            return n;
        } else {
            ContentValues cv = new ContentValues();
            cv.put(QuizResultsHelper.DATE, date);
            cv.put(QuizResultsHelper.SCORE, score);
            long n = db2.insert(QuizResultsHelper.TABLE_USERS, null, cv);
            return (int) n;
        }
    }

    public boolean isDateExist(String date) {
        open();
        Cursor c = db.rawQuery("SELECT * FROM " + GameResultsHelper.TABLE_USERS + " WHERE " + GameResultsHelper.DATE + " = '" + date + "'", null);
        return c.getCount() == 0 ? false : true;
    }

    public boolean isDateExist2(String date) {
        open2();
        Cursor c = db2.rawQuery("SELECT * FROM " + QuizResultsHelper.TABLE_USERS + " WHERE " + QuizResultsHelper.DATE + " = '" + date + "'", null);
        return c.getCount() == 0 ? false : true;
    }
}