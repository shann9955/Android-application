package com.example.shannon.brightminds;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Shannon on 11/04/2016.
 */
public class GameResultActivity  extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity_result);

        TextView textResult = (TextView) findViewById(R.id.textResult);
        TextView textResults = (TextView) findViewById(R.id.textResults);

        Bundle b = getIntent().getExtras();

        int score = b.getInt("score");

        textResult.setText("Your score is " + " " + score);
        textResults.setText("Click to view results.");
    }

    public void gameResults(View o) {

        Intent intent = new Intent(this, ProgressActivity.class);

        startActivity(intent);


    }


}
