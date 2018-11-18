package com.juricepte.can.juricepte.viewModels;

import static android.content.ContentValues.TAG;
import static br.com.zbra.androidlinq.Linq.stream;

import android.content.Intent;
import android.databinding.ObservableField;
import android.util.Log;
import android.view.View;

import com.juricepte.can.juricepte.databinding.ActivityActionDetailBinding;
import com.juricepte.can.juricepte.models.Action;
import com.juricepte.can.juricepte.models.Group;
import com.juricepte.can.juricepte.views.CriteriaActivity;
import com.juricepte.can.juricepte.views.GroupListActivity;

import java.util.List;

public class ActionDetailViewModel extends BaseViewModel {
    public ObservableField<String> details = new ObservableField<>();
    public String eventId;

    ActivityActionDetailBinding binding;
    Action action;

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
    }

    public void doGetAllEventWorks(List<Action> actions) {
        action = stream(actions).where(i -> i.getId().equals(eventId)).firstOrNull();
        if (actions != null) {
         //   details.set(action.getDescription());
            details.set("20+ şehirde binlerce kadının uygulama geliştirme eğitimi alacağı Geleceği Yazan Kadınlar projesinde Gönüllü Android Eğitmeni olmak ister misiniz?\n" +
                    "\n" +
                    "Gönüllü eğitmenlerde aradığımız temel kriterler :\n" +
                    "\n" +
                    "Android 401 başarı belgesine sahip\n" +
                    "Mobil uygulama geliştirme tecrübesine sahip \n" +
                    "Bilgisini paylaşmayı seven\n" +
                    "Şehir dışı seyahat engeli bulunmayan kişiler");
        }
    }

    public void goGroup() {
        Intent intent = new Intent(context, GroupListActivity.class);
        Log.d(TAG, "goGroup: "+eventId);
        intent.putExtra("eventId", eventId);
        context.startActivity(intent);
    }

    public void goScore() {
        firebase.getActiveGroup(action);
    }

    public void doActiveGroupWorks(String  groupId) {
        Intent intent = new Intent(context, CriteriaActivity.class);
        intent.putExtra("groupId", groupId);
        context.startActivity(intent);
    }
}
