package com.juricepte.can.juricepte.models;

import java.util.Date;
import java.util.List;

public class Action {
    private int id;
    private String name;
    private Date date;
    private String description;
    private String pasword;
    private List<Group> groupList;

    public Action(int id, String name, Date date, String description, String pasword, List<Group> groupList) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.description = description;
        this.pasword = pasword;
        this.groupList = groupList;
    }

    public Action(String name, Date date) {
        this.name = name;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPasword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }

    public List<Group> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<Group> groupList) {
        this.groupList = groupList;
    }
}
