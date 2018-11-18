package com.juricepte.can.juricepte.viewModels;

import android.content.Context;
import android.databinding.BaseObservable;

import com.google.firebase.FirebaseApp;
import com.juricepte.can.juricepte.Firebase;

public class BaseViewModel extends BaseObservable {
    Context context;
    Firebase firebase = new Firebase();


    public void init() {
        FirebaseApp.initializeApp(context);
    }
}
