package com.juricepte.can.juricepte.viewModels;

import android.databinding.BaseObservable;

import com.juricepte.can.juricepte.databinding.ActivityCriteriaListBinding;
import com.juricepte.can.juricepte.models.Rating;

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

    }

    public void vote() {
        Rating rating = new Rating();
        rating.setGroupId(groupId);
        rating.setRate(65);

        firebase.setRatingScore(rating);
    }
}
