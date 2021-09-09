package com.MouanjiFranck.biller.controller;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;

import com.MouanjiFranck.biller.Activities.HomeActivity;
import com.MouanjiFranck.biller.Activities.Questions;
import com.MouanjiFranck.biller.Activities.UpdatePassword;
import com.MouanjiFranck.biller.adapter.StudentsAdapter;
import com.MouanjiFranck.biller.model.Answers;
import com.MouanjiFranck.biller.model.Contrats;
import com.MouanjiFranck.biller.model.Students;
import com.MouanjiFranck.biller.model.Users;
import com.google.android.gms.tasks.OnFailureListener;
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

public class FirebaseControle {

    @SuppressLint("StaticFieldLeak")
    static FirebaseFirestore db = FirebaseFirestore.getInstance();
    static FirebaseAuth mAuth = FirebaseAuth.getInstance();
    final static String USERCOLLECTION = "Users";
    final static String ANSWERCOLLECTION = "Answers";
    final static String STUDENTCOLLECTION = "Students";
    final static String CONTRATCOLLECTION = "Contrats";


    /**
     * Mouanji Franck
     * use firebaseAuth
     */

    //create account in firebaseAuth
    public static void createUser(Users users, Answers answers, Context context){
        ProgressDialog progressDialog = ProgressDialog.show(context, null, "un instant");
        progressDialog.show();
        if(users.getEmail().equals("biller@gmail.com")){
            Toast.makeText(context, "ce Compte n'est pas utilisable", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
            return;
        }
        mAuth.createUserWithEmailAndPassword(users.getEmail(), users.getPassword()).addOnSuccessListener(authResult -> {
            String collectionName = USERCOLLECTION;
            try {
                //key
                String b = users.getEmail() + users.getName() + users.getSurname() + "biller";

                String password = Controller.encrypt(users.getPassword(), b);
                users.setPassword(password);
            } catch (Exception e) {
                e.printStackTrace();
            }
            addData(collectionName, users).addOnSuccessListener(documentReference -> {
                addData(ANSWERCOLLECTION, answers);
                Controller.create_file(users, context);
                Intent intent = new Intent(context, HomeActivity.class);
                context.startActivity(intent);
                progressDialog.dismiss();
                ((Activity)context).finish();
            }).addOnFailureListener(e -> {
                //todo
                progressDialog.dismiss();
            });

        }).addOnFailureListener(e -> {
            Toast.makeText(context, "Verifier votre connexion internet", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        });
    }

    //sign in firebaseAuth
    public static void singUser(String mail, String password, Context context){
        ProgressDialog progressDialog = ProgressDialog.show(context, null, "un instant");
        progressDialog.show();
        if(mail.equals("biller@gmail.com")){
            Toast.makeText(context, "ce Compte n'est pas utilisable", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
            return;
        }
        mAuth.signInWithEmailAndPassword(mail, password).addOnSuccessListener(authResult -> {
            String collectionName = USERCOLLECTION;
            getParticularData(collectionName, "email", mail).addOnSuccessListener(queryDocumentSnapshots -> {
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                    Users users = documentSnapshot.toObject(Users.class);
                    Controller.create_file(users, context);
                }
                Intent intent = new Intent(context, HomeActivity.class);
                context.startActivity(intent);
                progressDialog.dismiss();
                ((Activity)context).finish();
            });
        }).addOnFailureListener(e -> {

            progressDialog.dismiss();
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        });
    }

    //defaut user
    public static void autoConnexion(){
        String mail = "biller@gmail.com";
        String password = "password";
        mAuth.signInWithEmailAndPassword(mail, password);

    }


    //logout to firebaseAuth
    public static void logout(){
        mAuth.signOut();
    }


    /**
     * Mouanji Franck
     * user firebase firestore
     */

    //get collection
    private static CollectionReference getCollection(String collectionName){
        return db.collection(collectionName);
    }

    public static CollectionReference getStudentsCollection(){
        return getCollection(STUDENTCOLLECTION);
    }

    //add data to firebase
    private static Task<DocumentReference> addData(String collectionName, Object data){
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

    private static Task<QuerySnapshot> getParticularData(String collectionName, String field, String value){
        return getCollection(collectionName).whereEqualTo(field, value).get();
    }

    private static Task<QuerySnapshot> getParticularDataOdrer(String collectionName, String field, String value, String fieldOrder){
        return getCollection(collectionName).orderBy(fieldOrder, Query.Direction.DESCENDING).whereEqualTo(field, value).get();
    }

    /**
     * fonctions lié à l'utilisateur
     */

    public static void verifMailForPassword(String collectionName, String field, String value, Context context){
        ProgressDialog progressDialog = ProgressDialog.show(context, null, "un instant");
        progressDialog.show();
        getParticularData(collectionName, field, value).addOnSuccessListener(queryDocumentSnapshots -> {
            Users users = null;
            for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                users = documentSnapshot.toObject(Users.class);
            }
            if(users == null){
                Toast.makeText(context, "Compte introuvable", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }else{
                Intent intent = new Intent(context, Questions.class);
                intent.putExtra("mail", value);
                progressDialog.dismiss();
                context.startActivity(intent);
                ((Activity)context).finish();
            }
        });
    }

    public static void VerifAnswer(String collectionName, String field, Context context, Answers answers){
        ProgressDialog progressDialog = ProgressDialog.show(context, null, "un instant");
        progressDialog.show();
        getParticularData(collectionName, field, answers.getEmail()).addOnSuccessListener(queryDocumentSnapshots -> {
            Users users = null;
            String id = null;
            for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                users = documentSnapshot.toObject(Users.class);
                id = documentSnapshot.getId();
            }
            if(users == null){
                Toast.makeText(context, "Compte introuvable", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }else{
                String a = users.getEmail() + users.getName() + users.getSurname();
                Users finalUsers = users;
                String finalId = id;
                getParticularData(ANSWERCOLLECTION, "email", answers.getEmail()).addOnSuccessListener(queryDocumentSnapshots1 -> {
                    Answers answers1 = null;
                    for(QueryDocumentSnapshot documentSnapshot: queryDocumentSnapshots1){
                        answers1 = documentSnapshot.toObject(Answers.class);
                    }
                    if(answers1 != null){
                        try {
                            String answer1 = Controller.decrypt(answers1.getAnswer1(), a);
                            String answer2 = Controller.decrypt(answers1.getAnswer2(), a);
                            String answer3 = Controller.decrypt(answers1.getAnswer3(), a);
                            if(answers.getAnswer1().equals(answer1) && answers.getAnswer2().equals(answer2) && answers.getAnswer3().equals(answer3)){
                                FirebaseControle.logout();
//                                Toast.makeText(context, finalUsers.getEmail(), Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(context, UpdatePassword.class);
                                intent.putExtra("user", finalUsers);
                                intent.putExtra("id", finalId);
                                progressDialog.dismiss();
                                context.startActivity(intent);
                                ((Activity)context).finish();

                            }else{
                                Toast.makeText(context, "Reponses incorrect", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                        } catch (Exception e) {
                            progressDialog.dismiss();
                            e.printStackTrace();
                        }
                    }
                }).addOnFailureListener(e -> {
                    progressDialog.dismiss();
                    Toast.makeText(context, "erreur lors de la lecture des reponse", Toast.LENGTH_SHORT).show();
                });
            }
        }).addOnFailureListener(e -> {
            progressDialog.dismiss();
            Toast.makeText(context, "erreur de connexion", Toast.LENGTH_SHORT).show();
        });
    }

    public static void updatePassWordUser(String id, Users users){
        String work_key = users.getEmail() + users.getName() + users.getSurname() + "biller";
        String passWord_encryp = null;
        try {
            passWord_encryp = Controller.encrypt(users.getPassword(), work_key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        FirebaseControle.getCollection(USERCOLLECTION).document(id).update("password", passWord_encryp);
    }


    public  static void change_passWord(Context context, Users users, String new_password, String id){
        ProgressDialog progressDialog = ProgressDialog.show(context, null, "un instant");
        progressDialog.show();
        AuthCredential credential = EmailAuthProvider.getCredential(users.getEmail(), users.getPassword());
        mAuth.signInWithCredential(credential).addOnSuccessListener(authResult -> {
            FirebaseUser user = mAuth.getCurrentUser();
            assert user != null;
            user.updatePassword(new_password).addOnCompleteListener(task -> {
                if (task.isSuccessful()){
                    users.setPassword(new_password);
                    Controller.create_file(users, context);
                    updatePassWordUser(id, users);
                    Toast.makeText(context, "success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, HomeActivity.class);
                    context.startActivity(intent);
                    progressDialog.dismiss();
                    ((Activity)context).finish();
                }else{
                    progressDialog.dismiss();
                    Toast.makeText(context, "erreur de mise a jour du mot de passe", Toast.LENGTH_SHORT).show();
                }
            });
        }).addOnFailureListener(e -> {
            progressDialog.dismiss();
            Toast.makeText(context, "Erreur de connection", Toast.LENGTH_SHORT).show();
        });
    }

    /**
     * fonction de gestion d'élèves
     */

    public static void addStudent(Context context, Students students, Contrats contrats){
        ProgressDialog progressDialog = ProgressDialog.show(context, null, "un instant");
        progressDialog.show();
        addData(STUDENTCOLLECTION, students).addOnSuccessListener(documentReference -> {
            String id = documentReference.getId();
            FirebaseControle.getCollection(STUDENTCOLLECTION).document(id).update("id", id);
            contrats.setId_student(id);
            addData(CONTRATCOLLECTION, contrats).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference2) {
                    String id2 = documentReference2.getId();
                    FirebaseControle.getCollection(CONTRATCOLLECTION).document(id2).update("id_contrat", id2);
                    progressDialog.dismiss();
                    ((Activity)context).finish();
                }
            });

        }).addOnFailureListener(e -> {
            progressDialog.dismiss();
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        });
    }


    public static void chargeStudentData(final Context context, final ListView list, final TextView error, final List<Students> liste, String mail){
        final List<Students> students = new ArrayList<>();
        getParticularData(STUDENTCOLLECTION, "email_prof", mail).addOnSuccessListener(queryDocumentSnapshots -> {
            StringBuilder builder = new StringBuilder();
            Students student = null;
            for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                student =documentSnapshot.toObject(Students.class);
                students.add(student);
                liste.add(student);
            }

            if (student == null){
                error.setVisibility(View.VISIBLE);
                list.setVisibility(View.GONE);
            }else {
                error.setVisibility(View.GONE);
                list.setVisibility(View.VISIBLE);

                String [] nameStudents = new String[students.size()];
                String [] niveau = new String[students.size()];
                Collections.sort(liste, Students.comparatorDate);
                Collections.sort(students, Students.comparatorDate);

                for (int i=0; i<nameStudents.length; i++){
                    nameStudents[i] = students.get(i).getNom() + " " + students.get(i).getPrenom();
                    niveau[i] = students.get(i).getClasse();
                }
                StudentsAdapter adapter = new StudentsAdapter(context, niveau, nameStudents);
                list.setAdapter(adapter);
            }
        });
    }
}
