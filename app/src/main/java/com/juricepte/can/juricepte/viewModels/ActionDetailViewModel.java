package com.juricepte.can.juricepte.viewModels;

import android.databinding.ObservableField;

import com.juricepte.can.juricepte.databinding.ActivityActionListBinding;

public class ActionDetailViewModel extends BaseViewModel {
    public ObservableField<String> details = new ObservableField<>();

    ActivityActionListBinding binding;

    public ActionDetailViewModel(ActivityActionListBinding binding) {
        this.binding = binding;
        context = binding.getRoot().getContext();
    }

    public void goGroup() {

    }

    public void goScore() {

    }
}
