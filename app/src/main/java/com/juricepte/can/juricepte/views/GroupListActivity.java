package com.juricepte.can.juricepte.views;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.juricepte.can.juricepte.R;
import com.juricepte.can.juricepte.databinding.ActivityGroupsBinding;
import com.juricepte.can.juricepte.viewModels.GroupViewModel;

public class GroupListActivity extends AppCompatActivity {
    ActivityGroupsBinding binding;
    GroupViewModel groupViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_groups);
        groupViewModel = new GroupViewModel(binding);
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            groupViewModel.eventId = bundle.getString("eventId");
        }
        binding.setGroupsView(groupViewModel);
        getSupportActionBar().setTitle(getString(R.string.btn_gruops));


    }
}
