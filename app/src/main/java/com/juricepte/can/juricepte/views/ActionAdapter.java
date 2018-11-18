package com.juricepte.can.juricepte.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.juricepte.can.juricepte.R;
import com.juricepte.can.juricepte.databinding.RowActionBinding;
import com.juricepte.can.juricepte.models.Action;
import com.juricepte.can.juricepte.viewModels.ActionAdapterViewModel;

import java.util.List;

public class ActionAdapter extends BaseAdapter {
    List<Action> actionList;
    LayoutInflater layoutInflater;
    RowActionBinding binding;
    ActionAdapterViewModel actionAdapterViewModel;

    public ActionAdapter(List<Action> actionList, Context context) {
        this.actionList = actionList;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return actionList.size();
    }

    @Override
    public Object getItem(int i) {
        return actionList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.row_action, viewGroup, false);
        actionAdapterViewModel = new ActionAdapterViewModel();
        binding.setActionAdapterView(actionAdapterViewModel);
        //actionAdapterViewModel.actionFirst.set(actionList.get(i).getName());
        actionAdapterViewModel.actionFirst.set("Geleceği Yazan Kadınlar Gönüllü Eğitimi");
        actionAdapterViewModel.actionSecond.set("17-19 Kasım 2018");
      //  actionAdapterViewModel.actionSecond.set(actionList.get(i).getDate().toString());
        return binding.getRoot();
    }
}
