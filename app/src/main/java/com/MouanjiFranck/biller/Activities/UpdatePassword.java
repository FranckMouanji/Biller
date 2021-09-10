package com.MouanjiFranck.biller.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.MouanjiFranck.biller.R;
import com.MouanjiFranck.biller.firebase_action.ActionAboutUsers;
import com.MouanjiFranck.biller.system.Controller;
import com.MouanjiFranck.biller.model.Users;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class UpdatePassword extends AppCompatActivity {
    TextInputLayout res_password;
    TextInputLayout res_confirm_password;
    Button res_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_password);

        init();

        Users users = null;
        String id = "";

        Intent intent = getIntent();
        if(intent.hasExtra("user")){
            users = (Users) intent.getSerializableExtra("user");
        }
        if(intent.hasExtra("id")){
            id = intent.getStringExtra("id");
        }
        String a = users.getEmail() + users.getName() + users.getSurname() + "biller";
        try {
            String password = Controller.decrypt(users.getPassword(), a);
            users.setPassword(password);
        } catch (Exception e) {
            Toast.makeText(UpdatePassword.this, "erreur de decryptage  " + e, Toast.LENGTH_LONG).show();
            Log.w("decryptage", "onCreate: "+e);
            e.printStackTrace();
        }
        Users finalUsers = users;
        String finalId = id;
        res_send.setOnClickListener(view -> {
            String password = Objects.requireNonNull(res_password.getEditText()).getText().toString();
            String confirm_password = Objects.requireNonNull(res_confirm_password.getEditText()).getText().toString();
            if(password.equals("")){
                Toast.makeText(UpdatePassword.this, "Entrez votre mot de passe", Toast.LENGTH_SHORT).show();
            }else if(!(password.equals(confirm_password))){
                Toast.makeText(UpdatePassword.this, "Les mots de passe ne correspondent pas", Toast.LENGTH_SHORT).show();
            }else{
                ActionAboutUsers.change_passWord(UpdatePassword.this, finalUsers, password, finalId);
            }
        });
    }

    private void init() {
        res_password = findViewById(R.id.res_password);
        res_confirm_password = findViewById(R.id.res_confirm_password);
        res_send = findViewById(R.id.res_send);
    }

    @Override
    public void onBackPressed() {
        ActionAboutUsers.logout();
        Intent intent = new Intent(UpdatePassword.this, Login.class);
        ActionAboutUsers.autoConnexion();
        startActivity(intent);
        finish();
    }
}