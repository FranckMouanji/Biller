package com.MouanjiFranck.biller.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.MouanjiFranck.biller.R;
import com.MouanjiFranck.biller.controller.FirebaseControle;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class Login extends AppCompatActivity {
    TextView no_account;
    TextView password_forget;
    TextInputLayout log_email;
    TextInputLayout log_password;
    Button log_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
        no_account.setOnClickListener(view -> {
            Intent intent = new Intent(Login.this, CreateAccount.class);
            startActivity(intent);
            finish();
        });

        password_forget.setOnClickListener(view -> {
            String mail = Objects.requireNonNull(log_email.getEditText()).getText().toString();
            if(mail.isEmpty()){
                Toast.makeText(Login.this, "Entrez votre mail", Toast.LENGTH_SHORT).show();
            }else{
                FirebaseControle.verifMailForPassword("Users", "email", mail, Login.this);
            }

        });

        log_send.setOnClickListener(view -> {
            String email = Objects.requireNonNull(log_email.getEditText()).getText().toString();
            String password = Objects.requireNonNull(log_password.getEditText()).getText().toString();
            if(email.equals("")){
                log_email.requestFocus();
                Toast.makeText(Login.this, "entrez votre mail", Toast.LENGTH_SHORT).show();
            }else if(password.equals("")){
                Toast.makeText(Login.this, "entrez votre mot de passe", Toast.LENGTH_SHORT).show();
                log_password.requestFocus();
            }else{
                FirebaseControle.logout();
                FirebaseControle.singUser(email, password, Login.this);
            }
        });
    }

    private void init() {

        FirebaseControle.autoConnexion();

        no_account = findViewById(R.id.no_account);
        password_forget = findViewById(R.id.password_forget);
        log_email = findViewById(R.id.log_email);
        log_password = findViewById(R.id.log_password);
        log_send = findViewById(R.id.log_send);

    }
}