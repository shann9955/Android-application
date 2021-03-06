package com.example.shannon.brightminds;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Shannon on 11/04/2016.
 */
public class QuizStart extends BaseActivity {
    private String[] navMenuTitles;
    Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_start);
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items); // load
        // titles
        // from
        // strings.xml

/*        mDrawerList.setItemChecked(position, true);
        setTitle(listArray[position]);*/

/*        navMenuIcons = getResources()
                .obtainTypedArray(R.array.nav_drawer_icons);// load icons from
        // strings.xml*/

        set(navMenuTitles);


        button1 = (Button) findViewById(R.id.quizbutton);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(QuizStart.this, QuizActivity.class));


                // passing the button text to other method
                // to check whether the anser is correct or not
                // same for all three buttons
            }
        });
    }


}
