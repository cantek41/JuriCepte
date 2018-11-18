package com.juricepte.can.juricepte.viewModels;

import com.juricepte.can.juricepte.databinding.ActivityGroupsBinding;
import com.juricepte.can.juricepte.models.Group;
import com.juricepte.can.juricepte.views.ActionAdapter;
import com.juricepte.can.juricepte.views.GroupAdapter;

import java.util.List;

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
        Group groups1 = new Group();
        groups1.setName("Group 15");
        groups.add(groups1);

        Group groups2 = new Group();
        groups2.setName("Group 16");
        groups.add(groups2);

        Group groups3 = new Group();
        groups3.setName("Group 17");
        groups.add(groups3);

        GroupAdapter groupAdapter = new GroupAdapter(groups, context);
        binding.listGroups.setAdapter(groupAdapter);

    }

    public void goScore() {



    }
}
