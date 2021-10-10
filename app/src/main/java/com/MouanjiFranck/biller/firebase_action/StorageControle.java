package com.MouanjiFranck.biller.firebase_action;

import android.net.Uri;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class StorageControle {

    private static StorageReference getFileReference(){
        return FirebaseStorage.getInstance().getReference();
    }


    public static UploadTask setFile(String folderName, String extension, Uri data){
        StorageReference reference = StorageControle.getFileReference().child(folderName + System.currentTimeMillis() + extension);
        return reference.putFile(data);
    }

        }
