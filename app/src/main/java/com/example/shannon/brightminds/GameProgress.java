package com.example.shannon.brightminds;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by Shannon on 11/04/2016.
 */
public class GameProgress extends BaseActivity {

    ListView mLIstView;
    RanksAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_progress);
        mLIstView = (ListView) findViewById(R.id.listView);
        mAdapter = new RanksAdapter(this, ResultsProcessor.getInstance(this.getApplicationContext()).getUsers());
        mLIstView.setAdapter(mAdapter);
    }

    public class RanksAdapter extends ArrayAdapter<UserResults> {

        public RanksAdapter(Context context, ArrayList<UserResults> objects) {
            super(context, 0, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = null;
            if (convertView == null) {
                view = getLayoutInflater().inflate(R.layout.game_result_items, null);
            } else {
                view = convertView;
            }
            UserResults user = getItem(position);
            TextView date = (TextView) view.findViewById(R.id.date);
            TextView score = (TextView) view.findViewById(R.id.score);

            date.setText(user.date + "      ");
            score.setText(user.score + "    ");

            return view;
        }

    }
}