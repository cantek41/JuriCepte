package com.juricepte.can.juricepte.views;

import android.databinding.DataBindingUtil;
import android.widget.BaseAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.juricepte.can.juricepte.R;
import com.juricepte.can.juricepte.databinding.RowGroupBinding;
import com.juricepte.can.juricepte.models.Group;
import com.juricepte.can.juricepte.viewModels.GroupAdapterViewModel;

import java.util.List;

public class GroupAdapter extends BaseAdapter {
    List<Group> groupList;
    LayoutInflater layoutInflater;
    RowGroupBinding binding;
    GroupAdapterViewModel groupAdapterViewModel;

    public GroupAdapter(List<Group> grupList, Context context) {
        this.groupList = grupList;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return groupList.size();
    }

    @Override
    public Object getItem(int i) {
        return groupList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        binding = DataBindingUtil.inflate(layoutInflater, R.layout.row_group, viewGroup, false);
        groupAdapterViewModel = new GroupAdapterViewModel();
        binding.setGroupAdapterView(groupAdapterViewModel);

        groupAdapterViewModel.actionFirst.set(groupList.get(i).getName());
        groupAdapterViewModel.actionSecond.set(String.valueOf(groupList.get(i).getAvarageScore()));
        return binding.getRoot();

    }
}
