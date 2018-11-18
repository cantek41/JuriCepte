package com.juricepte.can.juricepte.views;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.juricepte.can.juricepte.R;
import com.juricepte.can.juricepte.databinding.ActivityActionDetailBinding;
import com.juricepte.can.juricepte.databinding.ActivityCriteriaListBinding;
import com.juricepte.can.juricepte.viewModels.ActionDetailViewModel;
import com.juricepte.can.juricepte.viewModels.CriteriaViewModel;

public class CriteriaActivity extends AppCompatActivity {

    ActivityCriteriaListBinding binding;
    CriteriaViewModel criteriaViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_criteria_list);
        criteriaViewModel = new CriteriaViewModel(binding);
        Bundle bundle=getIntent().getExtras();
        if (bundle != null) {
            criteriaViewModel.groupId = bundle.getString("groupId");
        }
        binding.setCriteriaView(criteriaViewModel);
    }
}
