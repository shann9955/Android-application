package com.example.shannon.brightminds;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shannon on 12/04/2016.
 */
public class QuizQuestionHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "mathsnow";
    // tasks table name
    private static final String TABLE_QUEST = "quest";
    // tasks Table Columns names
    private static final String KEY_ID = "qid";
    private static final String KEY_QUES = "question";
    private static final String KEY_ANSWER = "answer"; // correct option
    private static final String KEY_OPTA = "opta"; // option a
    private static final String KEY_OPTB = "optb"; // option b
    private static final String KEY_OPTC = "optc"; // option c

    private SQLiteDatabase dbase;

    public QuizQuestionHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        dbase = db;
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUEST + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
                + " TEXT, " + KEY_ANSWER + " TEXT, " + KEY_OPTA + " TEXT, "
                + KEY_OPTB + " TEXT, " + KEY_OPTC + " TEXT)";
        db.execSQL(sql);
        addQuestion();
        // db.close();
    }

    private void addQuestion() {
        QuizQuestion q1 = new QuizQuestion("What was the colour of the home screen = ?", "Grey", "White", "Orange", "White");
        this.addQuestion(q1);
        QuizQuestion q2 = new QuizQuestion("What is the name is this app = ?", "SharpMinds", "Brainy App", "BrightMinds", "BrightMinds");
        this.addQuestion(q2);
        QuizQuestion q3 = new QuizQuestion("What is the name of Prince Williams first son = ?", "Charlie", "George", "Harry", "George");
        this.addQuestion(q3);
        QuizQuestion q4 = new QuizQuestion("How many 5p make up £10 = ?", "250", "200", "100", "200");
        this.addQuestion(q4);
        QuizQuestion q5 = new QuizQuestion("If you buy shopping worth £13.45. How much change would you receive back from £20 = ?", "£6.55", "£7.55", "£7.15", "£6.55");
        this.addQuestion(q5);
        QuizQuestion q6 = new QuizQuestion("If you buy shopping worth £1.95. How much change would you receive back from £5 = ?", "£4.05", "£3.05", "£3.95", "£3.05");
        this.addQuestion(q6);
        QuizQuestion q7 = new QuizQuestion("Which one is a fruit = ?", "Tomato", "Potato", "Broccoli", "Tomato");
        this.addQuestion(q7);
        QuizQuestion q8 = new QuizQuestion("How many 2p make up £1 = ?", "100", "25", "50", "50");
        this.addQuestion(q8);
        QuizQuestion q9 = new QuizQuestion("Which one is a vegetable = ?", "Cucumber", "Cranberries", "Avocado", "Cucumber");
        this.addQuestion(q9);
        QuizQuestion q10 = new QuizQuestion("Which one is a fruit = ?", "Peppers", "Rhubarb", "Plum", "Plum");
        this.addQuestion(q10);
        QuizQuestion q11 = new QuizQuestion("If you buy 4 packets of crisps worth 66p each. How much are they all together? = ?", "£3.82", "£2.64", "£2.32", "£2.64");
        this.addQuestion(q11);
        QuizQuestion q12 = new QuizQuestion("Which one is a country = ?", "Tokyo", "Denmark", "Beijing", "Denmark");
        this.addQuestion(q12);
        QuizQuestion q13 = new QuizQuestion("Which one is a country = ?", "Moscow", "Cuba", "Paris", "Cuba");
        this.addQuestion(q13);
        QuizQuestion q14 = new QuizQuestion("How many 10p make up £2.80 = ?", "18", "28", "24", "28");
        this.addQuestion(q14);
        QuizQuestion q15 = new QuizQuestion("If you buy shopping worth £2.55. How much change would you receive back from £5 = ?", "£2.45", "£2.15", "£2.55", "£2.45");
        this.addQuestion(q15);
        QuizQuestion q16 = new QuizQuestion("Which of these is a country = ?", "Sydney", "Austria", "Athens", "Austria");
        this.addQuestion(q16);
        QuizQuestion q17 = new QuizQuestion("What was the colour of the screen before you started this quiz = ?", "Black", "Grey", "Red", "Grey");
        this.addQuestion(q17);
        QuizQuestion q18 = new QuizQuestion("On a clock(without looking), if the smaller hand was on 6 and the larger hand was on 12, what would the time be = ?", "12:00", "6:00", "00:00", "6:00");
        this.addQuestion(q18);
        QuizQuestion q19 = new QuizQuestion("Which of these are a country = ?", "Rome", "Dublin", "Guinea", "Guinea");
        this.addQuestion(q19);
        QuizQuestion q20 = new QuizQuestion("On a clock(without looking), if the smaller hand was on 8 and the larger hand was on 2, what would the time be = ?", "10:20", "2:40", "8:10", "8:10");
        this.addQuestion(q20);
        QuizQuestion q21 = new QuizQuestion("What is the name of our current Queen = ?", "Mary", "Victoria", "Elizabeth", "Elizabeth");
        this.addQuestion(q21);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);
        // Create tables again
        onCreate(db);
    }

    // Adding new question
    public void addQuestion(QuizQuestion quest) {
        // SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_QUES, quest.getQUESTION());
        values.put(KEY_ANSWER, quest.getANSWER());
        values.put(KEY_OPTA, quest.getOPTA());
        values.put(KEY_OPTB, quest.getOPTB());
        values.put(KEY_OPTC, quest.getOPTC());

        // Inserting Row
        dbase.insert(TABLE_QUEST, null, values);
    }

    public List<QuizQuestion> getAllQuestions() {
        List<QuizQuestion> quesList = new ArrayList<QuizQuestion>();
        // Select All Query
        String selectQuery = "SELECT  * FROM quest ORDER BY RANDOM()";
        dbase = this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                QuizQuestion quest = new QuizQuestion();
                quest.setID(cursor.getInt(0));
                quest.setQUESTION(cursor.getString(1));
                quest.setANSWER(cursor.getString(2));
                quest.setOPTA(cursor.getString(3));
                quest.setOPTB(cursor.getString(4));
                quest.setOPTC(cursor.getString(5));

                quesList.add(quest);
            } while (cursor.moveToNext());
        }
        // return quest list
        return quesList;
    }


}