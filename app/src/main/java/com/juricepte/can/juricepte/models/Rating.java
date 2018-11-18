package com.juricepte.can.juricepte.models;

import java.util.HashMap;

public class Rating {
    private String id;
    private String name;
    private String groupId;

    private int MaxRate;
    private int rate;

    public Rating() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxRate() {
        return MaxRate;
    }

    public void setMaxRate(int maxRate) {
        MaxRate = maxRate;
    }

    public int getRate() {
        return rate;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
