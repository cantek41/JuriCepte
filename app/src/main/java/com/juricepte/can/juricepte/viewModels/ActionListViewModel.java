package com.juricepte.can.juricepte.viewModels;

import android.app.AlertDialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.juricepte.can.juricepte.R;
import com.juricepte.can.juricepte.databinding.ActivityActionListBinding;
import com.juricepte.can.juricepte.models.Action;
import com.juricepte.can.juricepte.views.ActionAdapter;
import com.juricepte.can.juricepte.views.ActionDetailActivity;
import com.juricepte.can.juricepte.views.ActionListActivity;

import java.util.List;

public class ActionListViewModel extends BaseViewModel {
    ActivityActionListBinding binding;
    List<Action> actionList;
    AlertDialog b;
    private EditText edt;
    Action action;

    public ActionListViewModel(ActivityActionListBinding binding) {
        this.binding = binding;
        this.context = binding.getRoot().getContext();
        this.object = ActionListViewModel.this;
        init();

    }

    @Override
    public void init() {
        super.init();
        firebase.signIn("test@test.com", "123456");
    }

    public void doSignInWorks() {
        actionList = firebase.getAllActions();
    }

    public void showPasswordDialog(final Action action) {
        this.action = action;
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((ActionListActivity) context).getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.event_dialog, null);
        dialogBuilder.setView(dialogView);

        b = dialogBuilder.create();

        final TextView title = (TextView) dialogView.findViewById(R.id.tv_eventDialog_title);
        edt = (BootstrapEditText) dialogView.findViewById(R.id.edt_eventDialog);
        final BootstrapButton btnOk = dialogView.findViewById(R.id.btn_eventDailogOk);
        final BootstrapButton btnCancel = dialogView.findViewById(R.id.btn_eventDialogCancel);

        title.setText("Please enter event password");

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebase.joinAction(action, edt.getText().toString());

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b.dismiss();

            }
        });

        b.show();
    }

    public void doGetAllEventWorks(List<Action> actions) {
        ActionAdapter actionAdapter = new ActionAdapter(actions, context);
        binding.listViewAction.setAdapter(actionAdapter);

        binding.listViewAction.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showPasswordDialog(actionList.get(i));
            }
        });
    }

    public void doActionJoinWorks(boolean isOk) {
        if (isOk) {
            b.dismiss();
            Intent intent = new Intent(context, ActionDetailActivity.class);
            intent.putExtra("selectedActionId", action.getId());
            context.startActivity(intent);
        } else {
            Toast.makeText(context, "Wrong password", Toast.LENGTH_SHORT).show();
        }
    }
}

