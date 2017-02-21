package com.example.shannon.brightminds;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Shannon on 11/04/2016.
 */
public class QuizActivity  extends BaseActivity {

    List<QuizQuestion> quesList;
    int score = 0;
    int qid = 0;
    String hms = ("00:00");

    private long startTime = 0L;

    private Handler customHandler = new Handler();

    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;

    QuizQuestion currentQ;
    TextView txtQuestion, times;
    Button button1, button2, button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        QuizQuestionHelper db = new QuizQuestionHelper(this);  // my question bank class
        quesList = db.getAllQuestions();  // this will fetch all quetonall questions
        currentQ = quesList.get(qid); // the current question


        txtQuestion = (TextView) findViewById(R.id.txtQuestion);
        // the textview in which the question will be displayed

        // the three buttons,
        // the idea is to set the text of three buttons with the options from question bank
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);


        // the timer
        times = (TextView) findViewById(R.id.timers);
        startTime = SystemClock.uptimeMillis();
        customHandler.postDelayed(updateTimerThread, 0);

        // method which will set the things up for our game
        setQuestionView();

        // button click listeners
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // passing the button text to other method
                // to check whether the anser is correct or not
                // same for all three buttons
                getAnswer(button1.getText().toString());
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAnswer(button2.getText().toString());
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAnswer(button3.getText().toString());
            }
        });

    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;

            updatedTime = timeSwapBuff + timeInMilliseconds;

            int secs = (int) (updatedTime / 1000);
            int mins = secs / 60;
            secs = secs % 60;
            int milliseconds = (int) (updatedTime % 1000);
            hms = String.format ("" + String.format("%02d", mins) + ":"
                    //+ String.format("%02d", mins) + ":"
                    + String.format("%02d", secs));
            times.setText(hms);
            customHandler.postDelayed(this, 0);
        }

    };

    public void getAnswer(String AnswerString) {
        if (currentQ.getANSWER().equals(AnswerString)) {

            // if conditions matches increase the int (score) by 1
            // and set the text of the score view
            score++;

        }
        if (qid < 10) {

            // if questions are not over then do this
            currentQ = quesList.get(qid);
            //Log.d("qCurrent", currentQ.toString());
            setQuestionView();

        } else {

            // if over do this
            Intent intent = new Intent(QuizActivity.this,
                    QuizResultActivity.class);
            showHighScores();
            Bundle b = new Bundle();
            b.putInt("score", score); // Your score
            b.putString("hms", hms);
            intent.putExtras(b); // Put your score to your next
            startActivity(intent);
            finish();

        }

    }

    public void showHighScores(){
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat(("E dd.MM.yyyy ' 'HH:mm.ss"));
        ResultsProcessor.getInstance(QuizActivity.this).createNewDate2((ft.format(date)), score);

    }

    private void setQuestionView() {

        // the method which will put all things together
        txtQuestion.setText(currentQ.getQUESTION());
        button1.setText(currentQ.getOPTA());
        button2.setText(currentQ.getOPTB());
        button3.setText(currentQ.getOPTC());

        qid++;

    }




}
