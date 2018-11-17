package com.juricepte.can.juricepte.views;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.juricepte.can.juricepte.Adapters.ActionAdapter;
import com.juricepte.can.juricepte.EventDetailActivity;
import com.juricepte.can.juricepte.R;
import com.juricepte.can.juricepte.models.Action;
import com.juricepte.can.juricepte.viewModels.MainViewModel;

import java.util.ArrayList;
import java.util.List;

import com.juricepte.can.juricepte.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    MainViewModel mainViewModel;

    private static final String TAG ="Main Activity" ;
    ListView actionListView;
    List<Action> actionList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainViewModel = new MainViewModel(binding);
        binding.setMainView(mainViewModel);

//        actionListView = (ListView) findViewById(R.id.actionListView);

        actionList = new ArrayList<>();

        //actionList = firebase.getAllAction();


//        ActionAdapter actionAdapter = new ActionAdapter(actionList,getApplicationContext());
//
//        actionListView.setAdapter(actionAdapter);
//
//        actionListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                showPasswordDialog(actionList.get(i));
//
//            }
//        });

    }
    public void showPasswordDialog(final Action action) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
//        final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
//        dialogBuilder.setView(dialogView);
//
//        final EditText edt = (EditText) dialogView.findViewById(R.id.edit1);
//
//        dialogBuilder.setTitle("Custom dialog");
//        dialogBuilder.setMessage("Enter text below");
//        dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int whichButton) {
//                Toast.makeText(MainActivity.this, ""+edt.getText().toString(), Toast.LENGTH_SHORT).show();
//                if(edt.getText().toString().equals("testPassword")) {
//                    Intent intent = new Intent(MainActivity.this, EventDetailActivity.class);
//                    intent.putExtra("selectedActionId", action.getId());
//                    startActivity(intent);
//                }
//            }
//        });
//        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int whichButton) {
//                dialog.dismiss();
//            }
//        });
//        AlertDialog b = dialogBuilder.create();
//        b.show();
    }
}
