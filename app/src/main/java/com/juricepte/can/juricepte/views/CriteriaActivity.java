package com.juricepte.can.juricepte.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;
import com.juricepte.can.juricepte.R;
import com.juricepte.can.juricepte.models.ListRating;
import com.juricepte.can.juricepte.models.Rating;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class CriteriaActivity extends AppCompatActivity {


    private static final String TAG = "CriteriaActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criteria_list);
        preperList();
    }


    private void preperList() {
        String json = null;
        try {
            InputStream inputStream = getAssets().open("criteria.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson= new Gson();
        ListRating raitingList = gson.fromJson(json, ListRating.class);
        Rating r=raitingList.getRatings().get(0);
        Log.i(TAG,r.getName());


    }
}
