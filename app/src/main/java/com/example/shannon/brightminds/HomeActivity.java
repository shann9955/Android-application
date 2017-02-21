package com.example.shannon.brightminds;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Shannon on 11/04/2016.
 */
public class HomeActivity  extends BaseActivity {
    private String[] navMenuTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

        set(navMenuTitles);

        TextView txtview1 = (TextView) findViewById(R.id.textHeader);
        TextView txtview2 = (TextView) findViewById(R.id.textBody);

    }
}