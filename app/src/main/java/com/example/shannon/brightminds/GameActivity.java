package com.example.shannon.brightminds;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 * Created by Shannon on 11/04/2016.
 */
public class GameActivity extends BaseActivity {

    int highScore = 0;
    int current = 1;        //represents current size of computer sequence, increments with level
    int cellInc = 0;
    int level = 1;          //as level increases so does current
    int showTime = 1000;    //decreases as level

    ArrayList<Integer> compSequence = new ArrayList<Integer>();
    ArrayList<Integer> userSequence = new ArrayList<Integer>();

    Random r = new Random();
    final Handler handler = new Handler();

    TextView cell;
    TextView text;
    TextView score;
    Button play, play2;

    TextView[] cellList = new TextView[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        cellList[0] = (TextView) findViewById(R.id.cell1);
        cellList[1] = (TextView) findViewById(R.id.cell2);
        cellList[2] = (TextView) findViewById(R.id.cell3);
        cellList[3] = (TextView) findViewById(R.id.cell4);

        text = (TextView) findViewById(R.id.tracker);
        score = (TextView) findViewById(R.id.Score);
        play = (Button) findViewById(R.id.play);
        play2 = (Button) findViewById(R.id.play2);
        deActivate();
        fillComp();
        play.setVisibility(View.VISIBLE);
        play2.setVisibility(View.INVISIBLE);
    }

    public void fillComp() {
        for (int i = 0; i < current; i++) {       //fill comp move array with randomized numbers 1 - 9
            compSequence.add(r.nextInt(4) + 1);
        }
    }

    public void cellClick(View v) {
        //Get the id of the clicked object and assign it to a Textview variable
        text.setText("Move " + (cellInc + 1) + " of " + current + "               " + "Level " + level);
        cell = (TextView) findViewById(v.getId());
        cell.setBackgroundResource(R.drawable.user);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                cell.setBackgroundResource(R.drawable.back);
            }
        }, (200));

        if (cell.getId() == cellList[compSequence.get(cellInc) - 1].getId()) {
            userSequence.add(compSequence.get(cellInc));
            cellInc += 1;

        } else {
            lose();
        }

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (userSequence.equals(compSequence)) {    //Go to next level and have computer turn begin
                    current += 1;
                    userSequence.clear();

                    if (showTime > 200) {
                        showTime = showTime - 2;
                    }

                    compPlay(current + 1);
                    cellInc = 0;
                    level++;
                    highScore += 1;

                    score.setText("Score:" + "  " + highScore);
                }
            }
        }, 1000);
    }

    public void playClick(View v) {
        compPlay(current);
        play.setVisibility(View.INVISIBLE);

    }

    public void highScoreClick(View v){
        Intent intent = new Intent(GameActivity.this,
                GameResultActivity.class);
        Bundle b = new Bundle();
        b.putInt("score", highScore); // Your score
        intent.putExtras(b); // Put your score to your next
        startActivity(intent);
        finish();

    }

    private void lose() {

        text.setText("Game over. Click Results to view your score!");
        play2.setVisibility(View.VISIBLE);
        userSequence.clear();
        compSequence.clear();
        level = 1;
        showTime = 1000;
        current = 1;
        cellInc = 0;
        fillComp();
        deActivate();
        showHighScores();
    }

    public void showHighScores(){
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat(("E dd.MM.yyyy '  ' HH:mm:ss"));
        ResultsProcessor.getInstance(GameActivity.this).createNewDate((ft.format(date)), highScore);
    }

    public void deActivate() {
        //deActivate the board while comp plays
        for (int i = 0; i < cellList.length; i++) {
            cellList[i].setClickable(false);
        }
    }

    private void activate() {
        for (int i = 0; i < cellList.length; i++) {
            cellList[i].setClickable(true);
            score.setText("Score:" + "  " + highScore);
        }
    }

    public void compPlay(int c) {

        text.setText("Computers Turn");
        deActivate();

        for (int i = current; i < c; i++) {       //fill comp move array with randomized numbers 1 - 9
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    compSequence.add(r.nextInt(4) + 1);
                }
            }, 1000);
        }

        int incre = 2;
        for (int i = 0; i < current; i++) {   //Go through randomized computer move
            final int t = i;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    cellList[(compSequence.get(t) - 1)].setBackgroundResource(R.drawable.comp);
                }

            }, ((showTime * incre + 50)));

            incre = incre + 1;

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    cellList[compSequence.get(t) - 1].setBackgroundResource(R.drawable.back);
                    if ((t + 1) == current) {
                        text.setText("Users Turn");
                        activate();
                    }
                }
            }, showTime * incre);
        }

    }



}