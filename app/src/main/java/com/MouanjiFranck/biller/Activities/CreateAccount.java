package com.MouanjiFranck.biller.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.MouanjiFranck.biller.R;
import com.MouanjiFranck.biller.model.Users;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class CreateAccount extends AppCompatActivity {
    Button reg_send;

    TextInputLayout reg_name;
    TextInputLayout reg_prenom;
    TextInputLayout reg_email;
    TextInputLayout reg_password;
    TextInputLayout reg_password_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        init();

        reg_send.setOnClickListener(view -> {
            String name = Objects.requireNonNull(reg_name.getEditText()).getText().toString();
            String surname = Objects.requireNonNull(reg_prenom.getEditText()).getText().toString();
            String email = Objects.requireNonNull(reg_email.getEditText()).getText().toString();
            String password = Objects.requireNonNull(reg_password.getEditText()).getText().toString();
            String confirmPassword = Objects.requireNonNull(reg_password_confirm.getEditText()).getText().toString();

            if(name.equals("")){
                reg_name.requestFocus();
                Toast.makeText(CreateAccount.this, "entrez votre nom", Toast.LENGTH_SHORT).show();
            }else if(surname.equals("")){
                reg_prenom.requestFocus();
                Toast.makeText(CreateAccount.this, "entrez votre prenom", Toast.LENGTH_SHORT).show();
            }if(email.equals("")){
                reg_name.requestFocus();
                Toast.makeText(CreateAccount.this, "entrez votre mail", Toast.LENGTH_SHORT).show();
            }else if(password.equals("")){
                Toast.makeText(CreateAccount.this, "entrez votre mot de passe", Toast.LENGTH_SHORT).show();
                reg_password.requestFocus();
            }else if(password.length() < 6){
                Toast.makeText(CreateAccount.this, "votre mot de passe doit avoir au moins 6 caracteres", Toast.LENGTH_SHORT).show();
                reg_password.requestFocus();
            }else if(!(password.equals(confirmPassword))){
                Toast.makeText(CreateAccount.this, "les mots de passe ne correspondent pas", Toast.LENGTH_SHORT).show();
            }else{
                String[] partMail = email.split("@");
                if(partMail.length == 2){
                    String elt = partMail[1];
                    String[] part2Mail = elt.split("\\.");
                    if(!(part2Mail.length == 2)){
                        Toast.makeText(CreateAccount.this, "Cette adresse mail n'est pas correct 2 ", Toast.LENGTH_LONG).show();
                    }else {
                        Users users = new Users(name, surname, email, password);
                        Intent intent = new Intent(CreateAccount.this, Questions.class);
                        intent.putExtra("user", users);
                        startActivity(intent);
                        finish();
                    }
                }else {
                    Toast.makeText(CreateAccount.this, "Cette adresse mail n'est pas correct 1", Toast.LENGTH_LONG).show();
                }
            }

        });
    }

    private void init() {
        reg_send = findViewById(R.id.reg_send);
        reg_name = findViewById(R.id.reg_name);
        reg_prenom = findViewById(R.id.reg_prenom);
        reg_email = findViewById(R.id.reg_email);
        reg_password = findViewById(R.id.reg_password);
        reg_password_confirm = findViewById(R.id.reg_password_confirm);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CreateAccount.this, Login.class);
        startActivity(intent);
        finish();
    }
}