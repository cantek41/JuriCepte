package com.juricepte.can.juricepte.viewModels;

import android.widget.ArrayAdapter;

import com.juricepte.can.juricepte.databinding.ActivityActiveGroupListBinding;

public class ActionStatusViewModel extends BaseViewModel {
    ActivityActiveGroupListBinding binding;

    public ActionStatusViewModel(ActivityActiveGroupListBinding binding) {
        this.binding = binding;
        context = binding.getRoot().getContext();
        object = ActionStatusViewModel.this;
        init();
    }

    @Override
    public void init() {
        super.init();

    }
}
