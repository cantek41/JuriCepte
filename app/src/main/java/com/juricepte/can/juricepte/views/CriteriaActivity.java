package com.juricepte.can.juricepte.views;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.juricepte.can.juricepte.R;
import com.juricepte.can.juricepte.databinding.ActivityActionDetailBinding;
import com.juricepte.can.juricepte.viewModels.ActionDetailViewModel;

public class CriteriaActivity extends AppCompatActivity {

    ActivityActionDetailBinding binding;
    ActionDetailViewModel actionDetailViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_criteria_list);
        actionDetailViewModel = new ActionDetailViewModel(binding);
        binding.setActionDetailView(actionDetailViewModel);
    }
}
