package com.juricepte.can.juricepte.Adapters;

import android.widget.BaseAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.juricepte.can.juricepte.R;
import com.juricepte.can.juricepte.models.Group;

import java.util.List;

public class GroupAdapter extends BaseAdapter {
    List<Group> groupList;
    LayoutInflater layoutInflater;

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
        View rowView;

        rowView = layoutInflater.inflate(R.layout.row_action,null);

        TextView name = (TextView) rowView.findViewById(R.id.row_txt_criteria_item);
        TextView date = (TextView) rowView.findViewById(R.id.row_txt_criteria_item_score);

        name.setText(groupList.get(i).getName());

        date.setText(""+groupList.get(i).getDate());
        return rowView;
    }
}
