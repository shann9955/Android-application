package com.example.shannon.brightminds;

import android.content.Context;
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
public class QuizProgress extends BaseActivity{


    ListView mLIstView;
    RanksAdapter2 mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_progress);

        mLIstView = (ListView) findViewById(R.id.listView2);
        mAdapter = new RanksAdapter2(this, ResultsProcessor.getInstance(this.getApplicationContext()).getUser());
        mLIstView.setAdapter(mAdapter);
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
            // TextView timer = (TextView) view.findViewById(R.id.timer);

            date.setText(user.date + "      ");
            score.setText(user.score + "    ");
            //  timer.setText(hms + "    ");
            //   rank.setText((position + 1) + "");

            return view;
        }
    }
}