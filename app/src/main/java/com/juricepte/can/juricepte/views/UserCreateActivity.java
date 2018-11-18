package com.juricepte.can.juricepte.views;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.juricepte.can.juricepte.R;
import com.juricepte.can.juricepte.databinding.ActivityMainBinding;
import com.juricepte.can.juricepte.viewModels.MainViewModel;

public class UserCreateActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    MainViewModel mainViewModel;

    private static final String TAG = "UserCreateActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);

        setContentView(R.layout.activity_user_create);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_create);
        mainViewModel = new MainViewModel(binding);
        binding.setMainView(mainViewModel);
    }
}
