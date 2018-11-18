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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.juricepte.can.juricepte.models.Action;
import com.juricepte.can.juricepte.models.Group;
import com.juricepte.can.juricepte.models.Rating;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

    public class FirebaseMethods {
        FirebaseFirestore db;
        FirebaseAuth auth;
        private String eventCollectionName = "Events";
        private String groupCollectionName = "Groups";
        private String ratingCollectionName = "Ratings";

        public FirebaseMethods() {
            this.db = FirebaseFirestore.getInstance();
            this.auth = FirebaseAuth.getInstance();

        }
        public void signIn(String email,String password){
            auth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Toast.makeText("signIn", ""+authResult.getUser().getDisplayName(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        public void signUp(String email, String password, final String name){
            auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    FirebaseUser user = authResult.getUser();

                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                            .setDisplayName(name).build();
                    user.updateProfile(profileUpdates);
                }
            });
        }
        public List<Action> getAllEvents() {
            final List<Action>  eventList = new ArrayList<>();
            db.collection(eventCollectionName).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {

                            Action event = (Action) documentSnapshot.toObject(Action.class);
                            eventList.add(event);
                            Log.d(TAG, "onComplete: " + event.getName());
                        }
                    }
                }
            });
            return  eventList;
        }

        public List<Group> getGroupListByEventId(final String eventId) {
            final List<Group> groupList = new ArrayList<>();
            db.collection(groupCollectionName).whereEqualTo("eventId",eventId).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if(task.isSuccessful()){
                        for(QueryDocumentSnapshot queryDocumentSnapshot:task.getResult()){
                            Group group = queryDocumentSnapshot.toObject(Group.class);
                            group.setId(queryDocumentSnapshot.getId());
                            groupList.add(group);
                            Log.d(TAG, "onComplete: "+group.getId());
                        }
                    }
                }
            });
            return groupList;
        }

        public List<Rating> getRatingByGroupId(final String groupId) {
            final List<Rating> ratingList = new ArrayList<>();
            db.collection(ratingCollectionName).whereEqualTo("groupId",groupId).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
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

        public void createEvent(Action event) {
            db.collection(eventCollectionName).add(event).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
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

        public void addEventGroup(final Group group) {
            db.collection(groupCollectionName).add(group).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Log.d(TAG, "onSuccess: " + group.getId() + " group added");
                }
            });
        }

        public void setRatingScore(Action event, Group group, Rating rating, int score) {
            rating.setRate(score);
            db.collection(eventCollectionName).document();
        }

        public void setRatingScore(Rating rating) {
            db.collection(ratingCollectionName).document(rating.getId()).set(rating).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Log.d(TAG, "onSuccess: successfull");
                }
            });
        }

    }
