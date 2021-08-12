package com.MouanjiFranck.biller.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.MouanjiFranck.biller.R;
import com.google.android.material.textfield.TextInputLayout;

public class CreateAccount extends AppCompatActivity {
    Button reg_send;
    TextInputLayout reg_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        init();

        reg_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = reg_email.getEditText().getText().toString();
                Intent intent = new Intent(CreateAccount.this, Questions.class);
                intent.putExtra("mail", mail);
                startActivity(intent);
                finish();

            }
        });
    }

    private void init() {
        reg_send = findViewById(R.id.reg_send);
        reg_email = findViewById(R.id.reg_email);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CreateAccount.this, Login.class);
        startActivity(intent);
        finish();
    }
}