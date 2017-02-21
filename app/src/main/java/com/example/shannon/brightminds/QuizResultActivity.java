package com.example.shannon.brightminds;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Shannon on 11/04/2016.
 */
public class QuizResultActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_activity_result);

        TextView textResult = (TextView) findViewById(R.id.textResult);
        TextView textTime = (TextView) findViewById(R.id.textTime);
        TextView textResults = (TextView) findViewById(R.id.textResults);

        Bundle b = getIntent().getExtras();

        int score = b.getInt("score");
        String hms = b.getString("hms");

        textResult.setText("Well done. Your score is " + " " + score + ".");
        textTime.setText("Your Time is" + " " + hms);
        textResults.setText("Click to view results.");
    }

    public void quizResults(View o) {

        Intent intent = new Intent(this, ProgressActivity.class);

        startActivity(intent);

    }

}