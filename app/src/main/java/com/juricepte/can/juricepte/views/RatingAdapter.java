package com.juricepte.can.juricepte.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SeekBar;
import android.widget.TextView;

import com.crystal.crystalrangeseekbar.interfaces.OnSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalSeekbar;
import com.juricepte.can.juricepte.R;
import com.juricepte.can.juricepte.databinding.RowActionBinding;
import com.juricepte.can.juricepte.models.Action;
import com.juricepte.can.juricepte.models.Rating;
import com.juricepte.can.juricepte.viewModels.ActionAdapterViewModel;

import java.util.List;

public class RatingAdapter extends BaseAdapter {
    List<Rating> raitingList;
    LayoutInflater layoutInflater;

    Context context;
    public RatingAdapter(List<Rating> raitingList, Context context) {
        this.raitingList = raitingList;
        this.context=context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return raitingList.size();
    }

    @Override
    public Object getItem(int i) {
        return raitingList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View v = view;
        if (v == null) {
            LayoutInflater vi = LayoutInflater.from(context);
            v = vi.inflate(R.layout.row_criteria, null);
        }

        Rating o = raitingList.get(position);
        if(o!=null){
            TextView text = (TextView) v.findViewById(R.id.row_txt_criteria_item);
           final TextView valuetxt = (TextView) v.findViewById(R.id.row_txt_value);
            CrystalSeekbar seekBar = (CrystalSeekbar) v.findViewById(R.id.row_seekbar);
            text.setText(o.getName());
            seekBar.setMaxValue(o.getMaxRate());
            seekBar.setMinValue(0);
            seekBar.setOnSeekbarChangeListener(new OnSeekbarChangeListener() {
                @Override
                public void valueChanged(Number value) {
                    valuetxt.setText(String.valueOf(value));
                    o.setRate(value.intValue());
                }
            });
        }
        return v;
    }
}
