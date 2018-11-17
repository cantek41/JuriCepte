package com.juricepte.can.juricepte.viewModels;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.juricepte.can.juricepte.databinding.ActivityMainBinding;

public class MainViewModel extends BaseObservable {
    String name = "Ugur";

    public MainViewModel(ActivityMainBinding binding) {

    }


    public String getName() {
        return name;
    }

    @Bindable
    public void setName(String name) {
        notifyChange();
        this.name = name;
    }


}
