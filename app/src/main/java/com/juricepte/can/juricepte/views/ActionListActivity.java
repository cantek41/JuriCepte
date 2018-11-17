package com.juricepte.can.juricepte.views;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.juricepte.can.juricepte.Firebase;
import com.juricepte.can.juricepte.R;
import com.juricepte.can.juricepte.databinding.ActivityActionListBinding;
import com.juricepte.can.juricepte.models.Action;
import com.juricepte.can.juricepte.viewModels.ActionListViewModel;

import java.util.List;

public class ActionListActivity extends AppCompatActivity {
    ActivityActionListBinding binding;
    ActionListViewModel actionListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_action_list);
        actionListViewModel = new ActionListViewModel(binding);
        binding.setActionListView(actionListViewModel);
    }
}
