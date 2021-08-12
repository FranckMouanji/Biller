package com.MouanjiFranck.biller.controller;

import android.content.Context;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
//
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.firestore.DocumentSnapshot;
//import com.google.firebase.firestore.QueryDocumentSnapshot;
//import com.google.firebase.firestore.QuerySnapshot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.util.Calendar;
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Controller {

    private static final String TAG = "Controller";
    private static final String FILE_NAME = "user_Information";


    private static boolean verif_file_exist(Context context){
        File file = new File(context.getFilesDir(), FILE_NAME);
        return file.isFile();
    }

    public static boolean file_not_empty(Context context){
        if (verif_file_exist(context)){
            String content_of_file = Controller.take_information_of_file_users(context);
            return content_of_file != null && !content_of_file.trim().equals("");
        }else {
            return false;
        }
    }

    // Deleted the file.
//    public static void delete_file(Context context){
//        if (verif_file_exist(context)){
//            Temp_information_user temp_information_user = Trans_to_Temp_information_user(context);
//            ActionAboutUsersConnected.getParticularConnected(temp_information_user.getLogin()).addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                @Override
//                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                    String id  = null;
//                    for (QueryDocumentSnapshot documentSnapshot1 : queryDocumentSnapshots){
//                        id = documentSnapshot1.getId();
//                    }
//                    if (id != null){
//                        ActionAboutUsersConnected.getConnectedsCollection().document(id).delete();
//                        FirebaseAuth.getInstance().signOut();
//                    }
//                }
//            });
//
//            context.deleteFile(FILE_NAME);
//        }
//
//    }
//
//    public static void delete_file(String mail, Context context){
//        ActionAboutUsersConnected.getParticularConnected(mail).addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//            @Override
//            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                String id  = null;
//                for (QueryDocumentSnapshot documentSnapshot1 : queryDocumentSnapshots){
//                    id = documentSnapshot1.getId();
//                }
//                if (id != null){
//                    ActionAboutUsersConnected.getConnectedsCollection().document(id).delete();
//                }
//            }
//        });
//
//    }

//    public static void create_file(Users users, Context context){
//        if (users != null){
//            String user_mail = users.getEmail();
//            String user_name = users.getName()+" "+users.getSurname();
//            String contenu_ecrit = user_mail+"  "+user_name;
//
//            FileOutputStream fileOutputStream = null;
//
//            try {
//                fileOutputStream = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
//                fileOutputStream.write(contenu_ecrit.getBytes());
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }finally {
//                if (fileOutputStream != null){
//                    try {
//                        fileOutputStream.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }else{
//            String contenu_ecrit = " ";
//            FileOutputStream fileOutputStream = null;
//
//            try {
//                fileOutputStream = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
//                fileOutputStream.write(contenu_ecrit.getBytes());
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }finally {
//                if (fileOutputStream != null){
//                    try {
//                        fileOutputStream.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//    }

    private static String take_information_of_file_users(Context context){
        String information_user_take;

        FileInputStream fis = null;
        try {
            fis = context.openFileInput(FILE_NAME);
            Log.d(TAG, "take_information_of_file_users: fis : " + fis);
        } catch (FileNotFoundException e) {
            Log.e(TAG, "take_information_of_file_users: Error when open file user info", e);
        }
        assert fis != null;
        InputStreamReader inputStreamReader = new InputStreamReader(fis);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line).append('\n');
                line = reader.readLine();
            }

            reader.close();
            inputStreamReader.close();
        } catch (IOException e) {
            // Error occurred when opening raw file for reading.
            Log.e(TAG, "take_information_of_file_users: ", e);
        } finally {
            information_user_take = stringBuilder.toString();
        }

        return information_user_take;
    }


    /**
     * fonction d'adaptation de donne recuperer
     *
     */
//    public static Temp_information_user Trans_to_Temp_information_user(Context context){
//        Temp_information_user temp_information_user;
//        String information_user = Garder.take_information_of_file_users(context);
//        if(information_user != null){
//            String [] partition = information_user.split(" {2}");
//            temp_information_user = new Temp_information_user(partition[0], partition[1]);
//        }else {
//            temp_information_user = new Temp_information_user();
//        }
//        return temp_information_user;
//    }




            /**
             * fonction de gestion de l'abonnement by MOUANJI FRANCK
             *
             */
//            //pour les videos
//            public static void verifySubscription(Context context, MovieFactory factory){
//                ProgressDialog progressDialog = ProgressDialog.show(context, null, context.getResources().getString(R.string.waiting));
//                if (verif_file_exist(context)){
//                    Temp_information_user temp_information_user = Garder.Trans_to_Temp_information_user(context);
//                    ActionAboutUsers.getParticularUser(temp_information_user.getLogin()).addOnSuccessListener(queryDocumentSnapshots -> {
//                        Users users = null;
//                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
//                            users = documentSnapshot.toObject(Users.class);
//                        }
//
//                        assert users != null;
//                        ActionPerformAbonnement.getParticularAbonne(users.getId_abonne()).addOnSuccessListener(queryDocumentSnapshots1 -> {
//                            Abonne abonne = null;
//                            for (QueryDocumentSnapshot documentSnapshot1 : queryDocumentSnapshots1) {
//                                abonne = documentSnapshot1.toObject(Abonne.class);
//                            }
//                            assert abonne != null;
//                            Date period = abonne.getEnd_date();
//                            Calendar cal = Calendar.getInstance();
//                            cal.setTime(period);// all done
//                            Calendar calendar = Calendar.getInstance();
//                            int comparison = calendar.compareTo(cal);
//                            if (comparison == 0){
//
//                                if (progressDialog.isShowing())
//                                    progressDialog.dismiss();
//
//                                AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                                builder.setTitle(context.getResources().getString(R.string.abonnemnt));
//                                builder.setMessage(context.getResources().getString(R.string.message_fin_abonnement));
//                                builder.setPositiveButton(context.getResources().getString(R.string.subscribe), (dialog, which) -> buy_subscription());
//                                builder.show();
//                            }
//                            if (comparison < 0){
//                                if (progressDialog.isShowing())
//                                    progressDialog.dismiss();
//
//                                Utils.getStarted(context, factory);
//                            }
//                            if (comparison > 0 ){
//                                if (progressDialog.isShowing())
//                                    progressDialog.dismiss();
//
//                                AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                                builder.setTitle(context.getResources().getString(R.string.abonnemnt));
//                                builder.setMessage(context.getResources().getString(R.string.message_ancien_abonnement));
//                                builder.setPositiveButton(context.getResources().getString(R.string.subscribe), (dialog, which) -> buy_subscription());
//                                builder.show();
//
//                            }
//                        });
//                    });
//                }else {
//                    if (progressDialog.isShowing())
//                        progressDialog.dismiss();
//
//                    startWebCine(context);
//                }
//
//
//            }


            /**
             * Function to managed the subscription api.
             */
            private static void buy_subscription() {
                // TODO managed the part of subscription api.
            }



            private static SecretKeySpec generateKey(String encoding_word_pass) throws Exception{
                final MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte [] bytes = encoding_word_pass.getBytes("UTF-8");
                digest.update(bytes, 0, bytes.length);
                byte [] key = digest.digest();
                SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
                return secretKeySpec;
            }

            public static String encrypt(String password_to_encrypt, String word_key) throws Exception{
                SecretKeySpec key = generateKey(word_key);
                Cipher cipher = Cipher.getInstance("AES");
                cipher.init(Cipher.ENCRYPT_MODE, key);
                byte [] encVal = cipher.doFinal(password_to_encrypt.getBytes());
                String word_encrypted = Base64.encodeToString(encVal, Base64.DEFAULT);
                return word_encrypted;
            }

            public static String decrypt(String crypted_word, String word_key) throws Exception{
                SecretKeySpec key = generateKey(word_key);
                Cipher cipher = Cipher.getInstance("AES");
                cipher.init(Cipher.DECRYPT_MODE, key);
                byte [] decodedValue = Base64.decode(crypted_word, Base64.DEFAULT);
                byte [] decValue = cipher.doFinal(decodedValue);
                String decrypted_value = new String(decValue);
                return decrypted_value;
            }






        }
