package com.juricepte.can.juricepte.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.juricepte.can.juricepte.R;
import com.juricepte.can.juricepte.models.Action;

import java.util.List;

public class ActionAdapter extends BaseAdapter {
    List<Action> actionList;
    LayoutInflater layoutInflater;

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
        View rowView;

        rowView = layoutInflater.inflate(R.layout.row_action,null);

        @SuppressLint("CutPasteId") TextView name = (TextView) rowView.findViewById(R.id.row_txt_first);
        @SuppressLint("CutPasteId") TextView date = (TextView) rowView.findViewById(R.id.row_txt_second);

        name.setText(actionList.get(i).getName());

        date.setText(""+actionList.get(i).getDate());
        return rowView;
    }
}
