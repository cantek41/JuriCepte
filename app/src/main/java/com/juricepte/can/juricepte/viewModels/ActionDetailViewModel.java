package com.juricepte.can.juricepte.viewModels;

import static br.com.zbra.androidlinq.Linq.stream;

import android.content.Intent;
import android.databinding.ObservableField;

import com.juricepte.can.juricepte.databinding.ActivityActionDetailBinding;
import com.juricepte.can.juricepte.models.Action;
import com.juricepte.can.juricepte.views.CriteriaActivity;
import com.juricepte.can.juricepte.views.GroupListActivity;

import java.util.List;

public class ActionDetailViewModel extends BaseViewModel {
    public ObservableField<String> details = new ObservableField<>();
    public String eventId;

    ActivityActionDetailBinding binding;
    Action action;

    public ActionDetailViewModel(ActivityActionDetailBinding binding) {
        this.binding = binding;
        context = binding.getRoot().getContext();
        object = ActionDetailViewModel.this;
        init();
    }

    @Override
    public void init() {
        super.init();
        firebase.getAllActions();
    }

    public void doGetAllEventWorks(List<Action> actions) {
        action = stream(actions).where(i -> i.getId().equals(eventId)).firstOrNull();
        if (actions != null) {
            details.set(action.getDescription());
        }
    }

    public void goGroup() {
        Intent intent = new Intent(context, GroupListActivity.class);
        intent.putExtra("eventId", eventId);
        context.startActivity(new Intent(context, GroupListActivity.class));
    }

    public void goScore() {
        firebase.getActiveGroup(action);
    }

    public void doActiveGroupWorks(String  groupId) {
        Intent intent = new Intent(context, CriteriaActivity.class);
        intent.putExtra("groupId", groupId);
        context.startActivity(intent);
    }
}
