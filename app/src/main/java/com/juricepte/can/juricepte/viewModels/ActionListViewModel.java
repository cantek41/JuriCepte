package com.juricepte.can.juricepte.viewModels;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.juricepte.can.juricepte.Firebase;
import com.juricepte.can.juricepte.FirebaseMethods;
import com.juricepte.can.juricepte.databinding.ActivityActionListBinding;
import com.juricepte.can.juricepte.models.Action;
import com.juricepte.can.juricepte.views.ActionAdapter;

import java.util.List;

public class ActionListViewModel extends BaseViewModel {
    ActivityActionListBinding binding;
    List<Action> actionList;

    public ActionListViewModel(ActivityActionListBinding binding) {
        this.binding = binding;
        this.context = binding.getRoot().getContext();
        init();

    }

    @Override
    public void init() {
        super.init();
        new FirebaseMethods().getGroupListByEventId("1");
        //actionList = firebase.GetAllActions();
/*
        ActionAdapter actionAdapter = new ActionAdapter(actionList, context);
        binding.listViewAction.setAdapter(actionAdapter);

        binding.listViewAction.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(context, actionList.get(i).getName(), Toast.LENGTH_SHORT).show();
                //showPasswordDialog(actionList.get(i));
            }
        });*/

    }
    /*
    public void showPasswordDialog(final Action action) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText edt = (EditText) dialogView.findViewById(R.id.edit1);

        dialogBuilder.setTitle("Custom dialog");
        dialogBuilder.setMessage("Enter text below");
        dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Toast.makeText(this, "" + edt.getText().toString(), Toast.LENGTH_SHORT).show();
                if (edt.getText().toString().equals("testPassword")) {
                    Intent intent = new Intent(ActionListActivity.this, EventDetailActivity.class);
                    intent.putExtra("selectedActionId", action.getId());
                    startActivity(intent);
                }
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }
*/

}
