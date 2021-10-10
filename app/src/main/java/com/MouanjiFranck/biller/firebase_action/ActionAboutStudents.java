package com.MouanjiFranck.biller.firebase_action;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.MouanjiFranck.biller.adapter.StudentsAdapter;
import com.MouanjiFranck.biller.model.Contrats;
import com.MouanjiFranck.biller.model.Students;
import com.MouanjiFranck.biller.system.Controller;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ActionAboutStudents {

    final static String STUDENTCOLLECTION = "Students";
    final static String CONTRATCOLLECTION = "Contrats";
    final static String SIGNATUREPATH = "Signature/";

    /**
     * fonction de gestion d'élèves
     */

    public static CollectionReference getStudentsCollection(){
        return FirestoreControle.getCollection(STUDENTCOLLECTION);
    }

    public static void addStudent(Context context, Students students, Contrats contrats){
        ProgressDialog progressDialog = ProgressDialog.show(context, null, "un instant");
        progressDialog.show();
        FirestoreControle.addData(STUDENTCOLLECTION, students).addOnSuccessListener(documentReference -> {
            String id = documentReference.getId();
            FirestoreControle.getCollection(STUDENTCOLLECTION).document(id).update("id", id);
            contrats.setId_student(id);
            FirestoreControle.addData(CONTRATCOLLECTION, contrats).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference2) {
                    String id2 = documentReference2.getId();
                    FirestoreControle.getCollection(CONTRATCOLLECTION).document(id2).update("id_contrat", id2);
                    progressDialog.dismiss();
                    ((Activity)context).finish();
                }
            });

        }).addOnFailureListener(e -> {
            progressDialog.dismiss();
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        });
    }



    public static void setFile(Context context, Contrats contrats, Students students, Uri data, String path) {
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Uploading...");
        progressDialog.show();

        StorageControle.setFile(SIGNATUREPATH,"png", data)
                .addOnSuccessListener(taskSnapshot -> {
                    Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
                    while (!uri.isComplete()) ;
                    Uri url = uri.getResult();
                    contrats.setSign(url.toString());
                    addStudent(context, students, contrats);
                    File file = new File(path);
                    if(file.exists()){
                        file.delete();
                    }
                    Toast.makeText(context, "uploaded", Toast.LENGTH_SHORT).show();
                })
                .addOnProgressListener(taskSnapshot -> {
                    double progess = (100.0*taskSnapshot.getBytesTransferred())/ taskSnapshot.getTotalByteCount();
                    progressDialog.setMessage("Uploaded: "+(int)progess+"%");

                });
    }




    public static void chargeStudentData(final Context context, final ListView list, final TextView error, final List<Students> liste, String mail){
        final List<Students> students = new ArrayList<>();
        FirestoreControle.getParticularData(STUDENTCOLLECTION, "email_prof", mail).addOnSuccessListener(queryDocumentSnapshots -> {
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
