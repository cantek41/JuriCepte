package com.juricepte.can.juricepte;

import com.juricepte.can.juricepte.models.Action;
import com.juricepte.can.juricepte.models.Group;
import com.juricepte.can.juricepte.models.Rating;

import java.util.List;

public interface IFirebaseMethods {
    public void signIn(String email, String password);
    public void signUp(String email, String password, final String name);
    public void setActiveGroup(final Action action, Group group);
    public String getActiveGroup(Action action);
    public void joinAction(Action action, final String password);
    public List<Action> getAllActions();
    public List<Group> getGroupListByActionId(final String actionId);
    public List<Rating> getRatingByGroupId(final String groupId);
    public void createAction(Action action);
    public void addActionGroup(final Group group);
    public void setRatingScore(Rating rating);
}
