package com.juricepte.can.juricepte.viewModels;

import static br.com.zbra.androidlinq.Linq.stream;

import android.databinding.ObservableField;
import android.view.View;

import com.juricepte.can.juricepte.databinding.ActivityActionDetailBinding;
import com.juricepte.can.juricepte.models.Action;

import java.util.List;

public class ActionDetailViewModel extends BaseViewModel {
    public ObservableField<String> details = new ObservableField<>();
    public String eventId;

    ActivityActionDetailBinding binding;

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

        binding.btnActionDetailGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        binding.btnActionDetailGotoScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    public void doGetAllEventWorks(List<Action> actions) {
        details.set(stream(actions).where(i -> i.getId().equals(eventId)).first().getDescription());

    }

    public void goGroup() {

    }

    public void goScore() {

    }
}
