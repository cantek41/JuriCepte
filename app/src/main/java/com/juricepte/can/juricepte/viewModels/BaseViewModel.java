package com.juricepte.can.juricepte.viewModels;

import android.content.Context;
import android.databinding.BaseObservable;

import com.juricepte.can.juricepte.Firebase;

public class BaseViewModel extends BaseObservable {
    Context context;
    Firebase firebase = new Firebase();
}
