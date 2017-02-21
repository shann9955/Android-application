package com.example.shannon.brightminds;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Shannon on 11/04/2016.
 */
public class ProgressActivity extends BaseActivity {
    private String[] navMenuTitles;

    ListView mLIstView;
    ListView mLIstView2;
    RanksAdapter mAdapter;
    RanksAdapter2 mAdapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items); // load
        // titles
        // from
        // strings.xml
        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);

        mLIstView = (ListView) findViewById(R.id.listView);
        mAdapter = new RanksAdapter(this, ResultsProcessor.getInstance(this.getApplicationContext()).getUsers());
        mLIstView.setAdapter(mAdapter);

        mLIstView2 = (ListView) findViewById(R.id.listView2);
        mAdapter2 = new RanksAdapter2(this, ResultsProcessor.getInstance(this.getApplicationContext()).getUser());
        mLIstView2.setAdapter(mAdapter2);


        tabHost.setup();
        TabHost.TabSpec tabSpec = tabHost.newTabSpec("quizResults");
        tabSpec.setContent(R.id.quizResults);
        tabSpec.setIndicator("Quiz Progress");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("Game");
        tabSpec.setContent(R.id.gameResults);
        tabSpec.setIndicator("Game Progress");
        tabHost.addTab(tabSpec);

        set(navMenuTitles);
    }

    public class RanksAdapter extends ArrayAdapter<UserResults> {

        public RanksAdapter(Context context, ArrayList<UserResults> objects) {
            super(context, 0, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = null;
            if(convertView == null) {
                view = getLayoutInflater().inflate(R.layout.game_result_items, null);
            } else {
                view = convertView;
            }
            UserResults user = getItem(position);
            TextView date = (TextView) view.findViewById(R.id.date);
            TextView score = (TextView) view.findViewById(R.id.score);
            // TextView rank = (TextView) view.findViewById(R.id.rank);

            date.setText(user.date+ "       ");
            score.setText(user.score + "");
            //   rank.setText((position + 1) + "");

            return view;
        }
    }

    public class RanksAdapter2 extends ArrayAdapter<UserResults> {

        public RanksAdapter2(Context context, ArrayList<UserResults> objects) {
            super(context, 0, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = null;
            if(convertView == null) {
                view = getLayoutInflater().inflate(R.layout.quiz_result_items, null);
            } else {
                view = convertView;
            }
            UserResults user = getItem(position);
            TextView date = (TextView) view.findViewById(R.id.date);
            TextView score = (TextView) view.findViewById(R.id.score);
            // TextView rank = (TextView) view.findViewById(R.id.rank);

            date.setText(user.date+ "       ");
            score.setText(user.score + "");
            //   rank.setText((position + 1) + "");

            return view;
        }
    }

}