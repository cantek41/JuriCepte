package com.juricepte.can.juricepte.viewModels;

import android.content.Context;
import android.databinding.BaseObservable;

import com.google.firebase.FirebaseApp;
import com.juricepte.can.juricepte.FirebaseMethods;

public class BaseViewModel extends BaseObservable {
    Context context;
    Object object;
    FirebaseMethods firebase;

    public void init() {
        FirebaseApp.initializeApp(context);
        firebase=new FirebaseMethods(object);
    }
}
