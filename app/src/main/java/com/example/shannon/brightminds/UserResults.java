package com.example.shannon.brightminds;

import java.io.Serializable;

/**
 * Created by Shannon on 11/04/2016.
 */
public class UserResults implements Serializable {

    public int id,score;
    public String date;

    public UserResults(int id, String date , int score) {
        this.id = id;
        this.date = date;
        this.score=score;

    }

}
