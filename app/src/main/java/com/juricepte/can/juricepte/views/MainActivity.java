package com.juricepte.can.juricepte.views;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.juricepte.can.juricepte.FirebaseMethods;
import com.juricepte.can.juricepte.R;
import com.juricepte.can.juricepte.models.Action;
import com.juricepte.can.juricepte.models.Group;
import com.juricepte.can.juricepte.viewModels.MainViewModel;

import java.util.ArrayList;
import java.util.List;

import com.juricepte.can.juricepte.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    MainViewModel mainViewModel;

    private static final String TAG = "Main Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);

        setContentView(R.layout.activity_main);
        Action action = new Action();

        action.setId("7R618NZxRpmBLMc7DQrC");
        FirebaseMethods methods = new FirebaseMethods();
        //methods.signUp("kayali.furkan@gmail.com","testPassword","furkan kayalı");
       methods.signIn("kayali.furkan@gmail.com","testPassword");
       methods.joinAction(action,"1234");
        Group group = new Group();
        group.setId("1GrARqk373PgUBeG9I9R");
        methods.setActiveGroup(action,group);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainViewModel = new MainViewModel(binding);
        binding.setMainView(mainViewModel);
    }
}
