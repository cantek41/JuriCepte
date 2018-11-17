package com.juricepte.can.juricepte.views;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.juricepte.can.juricepte.models.Action;

public class EventDetailActivity extends AppCompatActivity {
    private Action action;
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_group);

        fragmentManager = getFragmentManager();

/*        Button buttonGroup = (Button) findViewById(R.id.buttonGroup);

        buttonGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(savedInstanceState == null){
                   GroupListFragment groupListFragment = new GroupListFragment();
                   fragmentManager.beginTransaction().add(R.id.groupListFragment).addToBackStack(null).commit();
               }
            }
        });*/



    }

}
