package com.juricepte.can.juricepte;

import android.content.Context;
import android.support.annotation.NonNull;
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
    private Group group;
    private Object object;

    public FirebaseMethods(Object object) {
        this.object = object;
        this.db = FirebaseFirestore.getInstance();
        this.auth = FirebaseAuth.getInstance();
        this.realDb = FirebaseDatabase.getInstance();
        this.dbRef = realDb.getReference("Actions");

    }

    @Override
    public void signIn(String email, String password) {
        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                if (object instanceof ActionListViewModel) {
                    ((ActionListViewModel) object).doSignInWorks();
                }
            }
        });
    }

    @Override
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

    @Override
    public void setActiveGroup(final Action action, Group group) {

        dbRef.child(action.getId()).child("activeGroup").setValue(group.getId());
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

    @Override
    public Group getActiveGroup(Action action) {
        group = null;
        dbRef.child(action.getId()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                group = dataSnapshot.getValue(Group.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return group;
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
                    } else {
                        ((ActionListViewModel) object).doActionJoinWorks(false);
                    }
                }
            }
        });
    }

    @Override
    public List<Action> getAllActions() {
        final List<Action> actionList = new ArrayList<>();
        db.collection(actionCollectionName).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {

                        Action Action = (Action) documentSnapshot.toObject(Action.class);
                        Action.setId(documentSnapshot.getId());
                        actionList.add(Action);
                        Log.d(TAG, "onComplete: " + Action.getName());
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

    @Override
    public List<Group> getGroupListByActionId(final String actionId) {
        final List<Group> groupList = new ArrayList<>();
        db.collection(groupCollectionName).whereEqualTo("actionId", actionId).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {
                        Group group = queryDocumentSnapshot.toObject(Group.class);
                        group.setId(queryDocumentSnapshot.getId());
                        groupList.add(group);
                        Log.d(TAG, "onComplete: " + group.getId());
                    }
                }
            }
        });
        return groupList;
    }

    @Override
    public List<Rating> getRatingByGroupId(final String groupId) {
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

    @Override
    public void createAction(Action action) {
        db.collection(actionCollectionName).add(action).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d(TAG, "onSuccess: " + documentReference.getId());

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "onFailure: ", e);
            }
        });
    }

    @Override
    public void addActionGroup(final Group group) {
        db.collection(groupCollectionName).add(group).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d(TAG, "onSuccess: " + group.getId() + " group added");
            }
        });
    }

    @Override
    public void setRatingScore(Rating rating) {
        db.collection(ratingCollectionName).document(rating.getId()).set(rating).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "onSuccess: successfull");
            }
        });
    }
}
