package com.MouanjiFranck.biller.controller;

import android.app.DatePickerDialog;
import android.app.Dialog;
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
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
//
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.firestore.DocumentSnapshot;
//import com.google.firebase.firestore.QueryDocumentSnapshot;
//import com.google.firebase.firestore.QuerySnapshot;

import com.MouanjiFranck.biller.Activities.BuildContrat;
import com.MouanjiFranck.biller.Activities.Login;
import com.MouanjiFranck.biller.R;
import com.MouanjiFranck.biller.model.Users;

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

    public static void delete_file(Context context){
        ProgressDialog progressDialog = ProgressDialog.show(context, null, "un instant");
        progressDialog.show();
        context.deleteFile(FILE_NAME);
        FirebaseControle.logout();
        Intent intent  = new Intent(context, Login.class);
        progressDialog.dismiss();
        context.startActivity(intent);
        ((Activity) context).finish();
    }


    //create a file
    public static void create_file(Users users, Context context){
        if (users != null){
            String user_mail = users.getEmail();
            String user_name = users.getName()+" "+users.getSurname();
            String contenu_ecrit = user_mail+"  "+user_name;

            FileOutputStream fileOutputStream = null;

            try {
                fileOutputStream = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
                fileOutputStream.write(contenu_ecrit.getBytes());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (fileOutputStream != null){
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }else{
            String contenu_ecrit = " ";
            FileOutputStream fileOutputStream = null;

            try {
                fileOutputStream = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
                fileOutputStream.write(contenu_ecrit.getBytes());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (fileOutputStream != null){
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    //read information of file

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

    public static String recupEmailUser(Context context){
        String [] informations = take_information_of_file_users(context).split(" {2}");
        return  informations[0];
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


    //boite de dialogue personnalisé

    public static void addUserDialog(final Context context){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.add_student);
        dialog.setCancelable(true);

        //initialisation du contenu



        //les actions

        dialog.show();


    }

    public static void showDatePickerDialog(Context context, final EditText editText){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                (Activity)context,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        editText.setText(i+"/"+i1+"/"+i2);
                    }
                },
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

}
