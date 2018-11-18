package com.juricepte.can.juricepte.viewModels;

import android.databinding.ObservableField;

import com.juricepte.can.juricepte.databinding.ActivityActionDetailBinding;

public class ActionDetailViewModel extends BaseViewModel {
    public ObservableField<String> details = new ObservableField<>();

    ActivityActionDetailBinding binding;

    public ActionDetailViewModel(ActivityActionDetailBinding binding) {
        this.binding = binding;
        context = binding.getRoot().getContext();
        init();
    }

    public void goGroup() {

    }

    public void goScore() {

    }
}
