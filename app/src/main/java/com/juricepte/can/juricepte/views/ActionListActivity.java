package com.juricepte.can.juricepte.views;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.juricepte.can.juricepte.R;
import com.juricepte.can.juricepte.databinding.ActivityActionListBinding;
import com.juricepte.can.juricepte.viewModels.ActionListViewModel;

public class ActionListActivity extends AppCompatActivity {
    ActivityActionListBinding binding;
    ActionListViewModel actionListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_action_list);
        actionListViewModel = new ActionListViewModel(binding);
        binding.setActionListView(actionListViewModel);
        getSupportActionBar().setTitle(getString(R.string.actions));
    }
}
