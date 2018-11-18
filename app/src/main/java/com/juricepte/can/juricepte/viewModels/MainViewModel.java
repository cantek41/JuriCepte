package com.juricepte.can.juricepte.viewModels;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;

import com.juricepte.can.juricepte.databinding.ActivityMainBinding;
import com.juricepte.can.juricepte.views.ActionListActivity;

public class MainViewModel extends BaseViewModel {
    ActivityMainBinding binding;

    public MainViewModel(ActivityMainBinding binding) {
        this.binding = binding;
        this.context = binding.getRoot().getContext();
        init();
    }

    public void getActionList() {
        context.startActivity(new Intent(context, ActionListActivity.class));
    }
}
