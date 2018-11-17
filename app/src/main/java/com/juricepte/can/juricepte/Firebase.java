package com.juricepte.can.juricepte;

import com.juricepte.can.juricepte.models.Action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Firebase implements IFirebase {


    @Override
    public List<Action> GetAllActions() {
        List<Action> list = new ArrayList<>();
        list.add(new Action("Proje 1", new Date()));
        list.add(new Action("Proje2", new Date()));
        list.add(new Action("Proje3", new Date()));
        list.add(new Action("Proje4", new Date()));
        return list;
    }
}
