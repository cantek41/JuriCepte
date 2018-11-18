package com.juricepte.can.juricepte.viewModels;

import android.util.Log;

import com.juricepte.can.juricepte.databinding.ActivityGroupsBinding;
import com.juricepte.can.juricepte.models.Group;
import com.juricepte.can.juricepte.views.ActionAdapter;
import com.juricepte.can.juricepte.views.GroupAdapter;

import java.util.List;

import static android.content.ContentValues.TAG;

public class GroupViewModel extends BaseViewModel {
    ActivityGroupsBinding binding;
    public String eventId;

    public GroupViewModel(ActivityGroupsBinding binding) {
        this.binding = binding;
        this.context = binding.getRoot().getContext();
        this.object = GroupViewModel.this;
        init();
    }

    @Override
    public void init() {
        super.init();
        firebase.getGroupListByActionId(eventId);

    }

    public void doGroupListWork(List<Group> groups) {

        //groups = firebase.getGroupListByActionId(eventId);
        Log.d(TAG, "doGroupListWork: "+groups.size());
        GroupAdapter groupAdapter = new GroupAdapter(groups, context);
        binding.listGroups.setAdapter(groupAdapter);

    }

    public void goScore() {



    }
}
