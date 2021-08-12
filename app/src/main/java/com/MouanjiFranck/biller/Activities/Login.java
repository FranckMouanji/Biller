package com.MouanjiFranck.biller.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.MouanjiFranck.biller.R;
import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {
    TextView no_account;
    TextView password_forget;
    TextInputLayout log_email;
    Button log_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

        no_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, CreateAccount.class);
                startActivity(intent);
                finish();
            }
        });

        password_forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = log_email.getEditText().getText().toString();
                if(mail.isEmpty()){
                    Toast.makeText(Login.this, "Empty", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(Login.this, Questions.class);
                    intent.putExtra("mail", mail);
                    startActivity(intent);
                    finish();
                }

            }
        });

        log_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void init() {
        no_account = findViewById(R.id.no_account);
        password_forget = findViewById(R.id.password_forget);
        log_email = findViewById(R.id.log_email);
        log_send = findViewById(R.id.log_send);
    }
}