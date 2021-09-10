package com.MouanjiFranck.biller.firebase_action;

import android.content.Context;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class StorageControle {

    public static StorageReference getFileReference(){
        return FirebaseStorage.getInstance().getReference();
    }



        }
