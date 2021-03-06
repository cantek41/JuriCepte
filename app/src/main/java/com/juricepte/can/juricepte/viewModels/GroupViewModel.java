package com.juricepte.can.juricepte.viewModels;

import android.content.Intent;
import android.util.Log;

import com.juricepte.can.juricepte.databinding.ActivityGroupsBinding;
import com.juricepte.can.juricepte.models.Group;
import com.juricepte.can.juricepte.views.ActionAdapter;
import com.juricepte.can.juricepte.views.CriteriaActivity;
import com.juricepte.can.juricepte.views.GroupAdapter;

import java.util.List;

import static android.content.ContentValues.TAG;

public class GroupViewModel extends BaseViewModel {
    ActivityGroupsBinding binding;
    public String eventId;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        Log.d(TAG, "setEventId: " + eventId);
        this.eventId = eventId;
    }

    public GroupViewModel(ActivityGroupsBinding binding) {
        this.binding = binding;
        this.context = binding.getRoot().getContext();
        this.object = GroupViewModel.this;
    }

    @Override
    public void init() {
        super.init();
        Log.d(TAG, "init: " + eventId);
        firebase.getGroupListByActionId(eventId);

    }

    public void doGroupListWork(List<Group> groups) {
        //groups = firebase.getGroupListByActionId(eventId);
        Log.d(TAG, "doGroupListWork: " + groups.size());
        // firebase.getRatingByGroup(  groups.get(0).getId());
        GroupAdapter groupAdapter = new GroupAdapter(groups, context);
        binding.listGroups.setAdapter(groupAdapter);

    }

    public void goScore() {
        Intent intent = new Intent(context, CriteriaActivity.class);
        intent.putExtra("groupId", "1");
        context.startActivity(intent);
    }
}
