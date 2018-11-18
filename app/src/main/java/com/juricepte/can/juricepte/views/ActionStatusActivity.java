package com.juricepte.can.juricepte.views;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.juricepte.can.juricepte.R;
import com.juricepte.can.juricepte.databinding.ActivityMainBinding;
import com.juricepte.can.juricepte.viewModels.MainViewModel;
/*
bu ekranda,
admin aktif grubu seçecek
ve oy verme durumunu list viewden izleyecek
oy veren kullanıcılar mavi renk vermeyen kullanıcılar kırmızı renk olacak

list rowu için row_group_status.xml kullanıbilinir
 */
public class ActionStatusActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    MainViewModel mainViewModel;

    private static final String TAG = "UserCreateActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);

        setContentView(R.layout.activity_active_group_list);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_active_group_list);
        mainViewModel = new MainViewModel(binding);
        binding.setMainView(mainViewModel);
    }
}
