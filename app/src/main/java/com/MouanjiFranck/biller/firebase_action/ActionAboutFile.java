package com.MouanjiFranck.biller.firebase_action;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.MouanjiFranck.biller.model.Course;
import com.MouanjiFranck.biller.model.File_uploaded;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class ActionAboutFile {

    private static final String COLLECTION_FILE_NAME = "File_uploaded";
    private static final String COLLECTION_COURSE_NAME = "course";
    private static final String FILEPATH_NAME_COUR = "Cour/";
    private static final String FILEPATH_NAME_EXERCISE = "Exercice/";
    private static final String FILEPATH_NAME_ANSWER_OF_EXERCISE = "Correction_Des_Exercices/";
    private static final String NIVEAU = "niveau";
    private static final String CLASSE = "classe";
    private static final String CATEGORIE = "categorie";



    private static CollectionReference getFileCollection(){
        return FirebaseFirestore.getInstance().collection(COLLECTION_FILE_NAME);
    }

    private static CollectionReference getCourseCollection(){
        return FirebaseFirestore.getInstance().collection(COLLECTION_COURSE_NAME);
    }

    private static StorageReference getFileReference(){
        return FirebaseStorage.getInstance().getReference();
    }

    private static Task<DocumentReference> addFile(File_uploaded fileUploaded){
        return ActionAboutFile.getFileCollection().add(fileUploaded);
    }

    private static Task<DocumentReference> addCourse(Course course){
        return ActionAboutFile.getCourseCollection().add(course);
    }

    //normal student
    public static Task<QuerySnapshot> getParticularFile(String niveau, String classe, String categorie){
        return ActionAboutFile.getFileCollection()
                .whereEqualTo(NIVEAU, niveau)
                .whereEqualTo(CLASSE, classe)
                .whereEqualTo(CATEGORIE, categorie)
                .get();
    }

    public static Task<QuerySnapshot> getAllLevelFile(String niveau){
        return ActionAboutFile.getFileCollection()
                .whereEqualTo(NIVEAU, niveau)
                .get();
    }

    //teacher
    public static Task<QuerySnapshot> getParticularFile(String categorie){
        return ActionAboutFile.getFileCollection()
                .whereEqualTo(CATEGORIE, categorie)
                .get();
    }

    public static Task<QuerySnapshot> getAllFile(){
        return ActionAboutFile.getFileCollection()
                .get();
    }

    public static void setFile(Context context, File_uploaded fileUploaded, Uri data){
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Uploading...");
        progressDialog.show();

        if (fileUploaded.getCourse().getCategorie().equals("Cours")){

            StorageReference reference = getFileReference().child(FILEPATH_NAME_COUR+System.currentTimeMillis()+".pdf");
            reference.putFile(data)
                    .addOnSuccessListener(taskSnapshot -> {
                        Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
                        while (!uri.isComplete());
                        Uri url = uri.getResult();
                        fileUploaded.setFilePathInFirebase(url.toString());
                        ActionAboutFile.addFile(fileUploaded);
                        ActionAboutFile.addCourse(fileUploaded.getCourse());
                        Toast.makeText(context, "uploaded", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    })
                    .addOnProgressListener(taskSnapshot -> {
                        double progess = (100.0*taskSnapshot.getBytesTransferred())/ taskSnapshot.getTotalByteCount();
                        progressDialog.setMessage("Uploaded: "+(int)progess+"%");

                    });

        }else if (fileUploaded.getCourse().getCategorie().equals("Exercice")){

            StorageReference reference = getFileReference().child(FILEPATH_NAME_EXERCISE+System.currentTimeMillis()+".pdf");
            reference.putFile(data)
                    .addOnSuccessListener(taskSnapshot -> {
                        Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
                        while (!uri.isComplete());
                        Uri url = uri.getResult();
                        fileUploaded.setFilePathInFirebase(url.toString());
                        ActionAboutFile.addFile(fileUploaded);
                        ActionAboutFile.addCourse(fileUploaded.getCourse());
                        Toast.makeText(context, "uploaded", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    })
                    .addOnProgressListener(taskSnapshot -> {
                        double progess = (100.0*taskSnapshot.getBytesTransferred())/ taskSnapshot.getTotalByteCount();
                        progressDialog.setMessage("Uploaded: "+(int)progess+"%");

                    });

        }else if (fileUploaded.getCourse().getCategorie().equals("Correction Exercice")){

            StorageReference reference = getFileReference().child(FILEPATH_NAME_ANSWER_OF_EXERCISE+System.currentTimeMillis()+".pdf");
            reference.putFile(data)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
                            while (!uri.isComplete());
                            Uri url = uri.getResult();
                            fileUploaded.setFilePathInFirebase(url.toString());
                            ActionAboutFile.addFile(fileUploaded);
                            ActionAboutFile.addCourse(fileUploaded.getCourse());
                            Toast.makeText(context, "uploaded", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                            double progess = (100.0*taskSnapshot.getBytesTransferred())/ taskSnapshot.getTotalByteCount();
                            progressDialog.setMessage("Uploaded: "+(int)progess+"%");

                        }
                    });

        }
    }
}
