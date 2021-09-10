package com.MouanjiFranck.biller.firebase_action;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.MouanjiFranck.biller.Activities.HomeActivity;
import com.MouanjiFranck.biller.Activities.Questions;
import com.MouanjiFranck.biller.Activities.UpdatePassword;
import com.MouanjiFranck.biller.adapter.StudentsAdapter;
import com.MouanjiFranck.biller.model.Answers;
import com.MouanjiFranck.biller.model.Contrats;
import com.MouanjiFranck.biller.model.Students;
import com.MouanjiFranck.biller.model.Users;
import com.MouanjiFranck.biller.system.Controller;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FirestoreControle {

    @SuppressLint("StaticFieldLeak")
    static FirebaseFirestore db = FirebaseFirestore.getInstance();



    /**
     * Mouanji Franck
     * user firebase firestore
     */

    //get collection
    public static CollectionReference getCollection(String collectionName){
        return db.collection(collectionName);
    }



    //add data to firebase
    public static Task<DocumentReference> addData(String collectionName, Object data){
        return getCollection(collectionName).add(data);
    }

    //externe interface to add data
    public static void setDataFromFirebase(String collectionName, Object data, Context context){
        ProgressDialog progressDialog = ProgressDialog.show(context, null, "un instant");
        progressDialog.show();
        addData(collectionName, data).addOnSuccessListener(documentReference -> {
            progressDialog.dismiss();
            //todo
        }).addOnFailureListener(e -> {
            progressDialog.dismiss();
            //todo
        });
    }

    //get data from firebase
    private static Task<QuerySnapshot> getAllData(String collectionName){
        return getCollection(collectionName).get();
    }

    public static Task<QuerySnapshot> getParticularData(String collectionName, String field, String value){
        return getCollection(collectionName).whereEqualTo(field, value).get();
    }

    private static Task<QuerySnapshot> getParticularDataOdrer(String collectionName, String field, String value, String fieldOrder){
        return getCollection(collectionName).orderBy(fieldOrder, Query.Direction.DESCENDING).whereEqualTo(field, value).get();
    }





}
