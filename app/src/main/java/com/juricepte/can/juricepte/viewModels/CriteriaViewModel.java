package com.juricepte.can.juricepte.viewModels;

import android.databinding.BaseObservable;
import android.util.Log;
import android.widget.ListView;

import com.google.gson.Gson;
import com.juricepte.can.juricepte.databinding.ActivityCriteriaListBinding;
import com.juricepte.can.juricepte.models.ListRating;
import com.juricepte.can.juricepte.models.Rating;
import com.juricepte.can.juricepte.views.CriteriaActivity;
import com.juricepte.can.juricepte.views.RatingAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static android.content.ContentValues.TAG;

public class CriteriaViewModel extends BaseViewModel {
    ActivityCriteriaListBinding binding;
    public String groupId;

    public CriteriaViewModel(ActivityCriteriaListBinding binding) {
        this.binding = binding;
        context = binding.getRoot().getContext();
        object = CriteriaViewModel.this;
        init();
    }

    @Override
    public void init() {
        super.init();
        preperList();
    }

    public void vote() {
      /*  Rating rating = new Rating();
        rating.setGroupId(groupId);
        rating.setRate(65);
        firebase.setRatingScore(rating);*/
        ((CriteriaActivity) context).finish();

    }

    private void preperList() {
        String json = null;
        try {
            InputStream inputStream = context.getAssets().open("criteria.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }
        Gson gson = new Gson();
        ListRating raitingList = gson.fromJson(json, ListRating.class);
        RatingAdapter adapter = new RatingAdapter(raitingList.getRatings(), context);
        binding.listCriteria.setAdapter(adapter);
    }

    public void doYourRatingScoreWorks() {
      //  ((CriteriaActivity) context).finish();
    }
}
