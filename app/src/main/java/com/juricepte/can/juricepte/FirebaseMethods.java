package com.juricepte.can.juricepte;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.juricepte.can.juricepte.models.Action;
import com.juricepte.can.juricepte.models.Group;
import com.juricepte.can.juricepte.models.Rating;
import com.juricepte.can.juricepte.viewModels.ActionDetailViewModel;
import com.juricepte.can.juricepte.viewModels.ActionListViewModel;
import com.juricepte.can.juricepte.viewModels.CriteriaViewModel;
import com.juricepte.can.juricepte.viewModels.GroupViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.content.ContentValues.TAG;


public class FirebaseMethods implements IFirebaseMethods {
    FirebaseFirestore db;
    FirebaseAuth auth;
    FirebaseDatabase realDb;
    DatabaseReference dbRef;

    private String actionCollectionName = "Actions";
    private String groupCollectionName = "Groups";
    private String ratingCollectionName = "Ratings";
    private String group;
    private Object object;
    private int mahmut;

    public FirebaseMethods(Object object) {
        this.object = object;
        this.db = FirebaseFirestore.getInstance();
        this.auth = FirebaseAuth.getInstance();
        this.realDb = FirebaseDatabase.getInstance();
        this.dbRef = realDb.getReference("Actions");

    }

    public void signIn(String email, String password) {
        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                if (object instanceof ActionListViewModel) {
                    ((ActionListViewModel) object).doSignInWorks();
                }
                       /*   if (object instanceof ActionDetailViewModel) {
                            ((ActionDetailViewModel)object).doGetAllEventWorks(actionList);
                        } */
            }
        });
    }

    public void signUp(String email, String password, final String name) {
        auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                FirebaseUser user = authResult.getUser();
                Log.d(TAG, "onSuccess: user:" + user.getEmail());
                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                        .setDisplayName(name).build();
                user.updateProfile(profileUpdates);
            }
        });
    }

    public void setActiveGroup(final Action action, Group group) {

        dbRef.child(action.getId()).child("activeGroupId").setValue(group.getId());
        dbRef.child(action.getId()).child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapShot : dataSnapshot.getChildren()) {
                    Log.d(TAG, "onDataChange: " + snapShot.child("vote").getValue());
                    Log.d(TAG, "onDataChange: " + snapShot.getRef());

                    snapShot.getRef().child("vote").setValue(false);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public String getActiveGroup(Action action) {
        group = null;
        dbRef.child(action.getId()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                group = dataSnapshot.child("activeGroupId").getValue(String.class);
                Log.d(TAG, "onDataChange: " + group);
                if (object instanceof ActionDetailViewModel) {
                    ((ActionDetailViewModel) object).doActiveGroupWorks(group);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return group;
    }

    public Group getGroupId(String id) {
        db.collection(groupCollectionName).document(id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    Group group = task.getResult().toObject(Group.class);
                    group.setId(id);
                    Log.d(TAG, "onComplete: group" + group.getName());
                }
            }
        });
        return null;
    }

    @Override
    public void joinAction(Action action, final String password) {
        db.collection("Actions").document(action.getId()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    if (action.getPasword().equals(password)) {
                        FirebaseUser user = auth.getCurrentUser();
                        HashMap<String, Object> map = new HashMap<>();
                        map.put("name", user.getDisplayName());
                        map.put("vote", false);
                        dbRef.child(action.getId()).child("users").child(user.getUid()).setValue(map);

                        if (object instanceof ActionListViewModel) {
                            ((ActionListViewModel) object).doActionJoinWorks(true);
                        }
                    }
                } else {
                    ((ActionListViewModel) object).doActionJoinWorks(false);
                }
            }
        });
    }

    public List<Action> getAllActions() {
        final List<Action> actionList = new ArrayList<>();
        db.collection(actionCollectionName).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {

                        Action action = (Action) documentSnapshot.toObject(Action.class);
                        action.setId(documentSnapshot.getId());
                        actionList.add(action);

                        Log.d(TAG, "onComplete: " + action.getName());
                    }
                    if (object instanceof ActionListViewModel) {
                        ((ActionListViewModel) object).doGetAllEventWorks(actionList);
                    }
                    if (object instanceof ActionDetailViewModel) {
                        ((ActionDetailViewModel) object).doGetAllEventWorks(actionList);
                    }

                }
            }
        });
        return actionList;
    }

    public List<Group> getGroupListByActionId(final String actionId) {
        final List<Group> groupList = new ArrayList<>();
        Log.d(TAG, "getGroupListByActionId: actionId:" + actionId);
        db.collection(groupCollectionName).whereEqualTo("eventId", actionId).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                Log.d(TAG, "onComplete: task" + task.isSuccessful());
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {
                        Log.d(TAG, "onComplete: " + queryDocumentSnapshot.getData());
                        Group group = queryDocumentSnapshot.toObject(Group.class);
                        group.setId(queryDocumentSnapshot.getId());
                        Log.d(TAG, "onComplete: " + group.getId());
                        group.setId(queryDocumentSnapshot.getId());


                        final int[] userCount = new int[1];
                        db.collection(groupCollectionName).document(group.getId()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    Group group = (Group) task.getResult().toObject(Group.class);
                                    Log.d(TAG, "onComplete: " + group.getTotalRate());
                                    dbRef.child(group.getEventId()).child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            userCount[0] = 0;
                                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {


                                                if ((boolean) snapshot.child("vote").getValue(boolean.class)) {
                                                    userCount[0] += 1;
                                                }


                                            }
                                            if (userCount[0] != 0) {
                                                group.setAvarageScore(group.getTotalRate() / userCount[0]);

                                            }

                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                }

                            }
                        });

                        Log.d(TAG, "onComplete: " + group.getId());

                        String[] names = new String[]{"Group 15", "Group 16", "Group 17", "Group 18"};
                        int[] averages = new int[]{68, 74, 56, 95};
                        group.setName(names[groupList.size()]);
                        group.setAvarageScore(averages[groupList.size()]);
                        groupList.add(group);

                    }
                    if (object instanceof GroupViewModel) {
                        ((GroupViewModel) object).doGroupListWork(groupList);
                    }
                } else {
                    Log.w(TAG, "onComplete: ", task.getException());
                }
            }
        });

        return groupList;
    }

    @Override
    public List<Rating> getRatingListByGroupId(final String groupId) {
        final List<Rating> ratingList = new ArrayList<>();
        db.collection(ratingCollectionName).whereEqualTo("groupId", groupId).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {
                        Rating rating = queryDocumentSnapshot.toObject(Rating.class);
                        ratingList.add(rating);
                    }
                }
            }
        });
        return ratingList;
    }

    public void createAction(Action action) {
        db.collection(actionCollectionName).add(action).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d(TAG, "onSuccess: " + documentReference.getId());
                HashMap<String, Object> map = new HashMap<>();
                map.put("activeGroupId", "0");
                map.put("Users", new ArrayList<>());
                dbRef.child(documentReference.getId()).setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "onSuccess: child added");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "onFailure: ", e);
                    }
                });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "onFailure: ", e);
            }
        });
    }

    public void addActionGroup(final Group group) {
        db.collection(groupCollectionName).add(group).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d(TAG, "onSuccess: " + group.getId() + " group added");
            }
        });
    }

    public void setRatingScore(Rating rating) {
        FirebaseUser user = auth.getCurrentUser();

        db.collection(groupCollectionName).document(rating.getGroupId()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, "onComplete: success");
                    Group group = task.getResult().toObject(Group.class);
                    group.setId(rating.getGroupId());
                    group.setTotalRate(group.getTotalRate() + rating.getRate());
                    db.collection(groupCollectionName).document(rating.getGroupId()).update("totalRate", group.getTotalRate());
                    HashMap<String, Object> map = new HashMap();
                    map.put("name", user.getDisplayName());
                    map.put("vote", true);
                    dbRef.child(group.getEventId()).child("users").child(user.getUid()).setValue(map);
                } else {
                    Log.d(TAG, "onComplete: " + task.getException());
                }

                if (object instanceof CriteriaViewModel) {
                    ((CriteriaViewModel) object).doYourRatingScoreWorks();
                }
            }
        });
    }
}