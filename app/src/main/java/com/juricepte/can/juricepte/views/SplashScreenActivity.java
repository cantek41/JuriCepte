package com.juricepte.can.juricepte.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.juricepte.can.juricepte.FirebaseMethods;
import com.juricepte.can.juricepte.R;
import com.juricepte.can.juricepte.models.Action;
import com.juricepte.can.juricepte.models.Group;

import java.util.Date;

/**
 *
 */
public class SplashScreenActivity extends AppCompatActivity {
    protected int _splashTime = 3000;

    private Thread splashTread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        //FirebaseMethods firebase = new FirebaseMethods(SplashScreenActivity.this);
        /*Action action = new Action();
        action.setName("Geleceği Yazanlar");
        action.setDate(new Date());
        action.setPasword("1234");
        action.setDescription("Geleceği Yazan Kadınlar eğitimi");

        firebase.createAction(action);
 */
        /*Group group = new Group();
        group.setProjectName("JuriCepte");
        group.setEventId("SCOaoWNf6FjaOFInYxyM");
        group.setName("Grup 15");
        group.setTotalRate(5);
        firebase.addActionGroup(group);
        firebase.addActionGroup(group); */
        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    synchronized(this){
                        wait(_splashTime);
                    }

                } catch(InterruptedException e) {}
                finally {
                    finish();

                    Intent i = new Intent();
                  i.setClass(SplashScreenActivity.this, ActionListActivity.class);
                    startActivity(i);

                    //stop();
                }
            }
        };

        splashTread.start();
    }
}
