package com.juricepte.can.juricepte.models;

import java.util.HashMap;

public class Rating {
    private HashMap<Integer, String> ratings;

    public HashMap<Integer, String> getRatings() {
        return ratings;
    }

    public void setRatings(HashMap<Integer, String> ratings) {
        this.ratings = ratings;
    }
}
