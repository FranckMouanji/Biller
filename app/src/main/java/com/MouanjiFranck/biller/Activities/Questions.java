package com.MouanjiFranck.biller.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.MouanjiFranck.biller.R;
import com.MouanjiFranck.biller.controller.Controller;
import com.MouanjiFranck.biller.controller.FirebaseControle;
import com.MouanjiFranck.biller.model.Answers;
import com.MouanjiFranck.biller.model.Users;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class Questions extends AppCompatActivity {
    Button quest_send;
    TextInputLayout quest_answer1;
    TextInputLayout quest_answer2;
    TextInputLayout quest_answer3;
    String provient = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        init();
        Users users;
        String mail;
        boolean use = false;

        Intent intent = getIntent();
        if(intent.hasExtra("user")){
            users = (Users) intent.getSerializableExtra("user");
            use = true;
            provient = "creation";
        }else{
            users = new Users();
        }

        if(intent.hasExtra("mail")){
            mail = intent.getStringExtra("mail");
            provient = "login";
        }else{
            mail = "";
        }

        Users finalUsers = users;
        boolean finalUse = use;
        quest_send.setOnClickListener(view -> {
            if(provient.equals("creation")){
                String a = users.getEmail() + users.getName() + users.getSurname();
                try {
                    String answer1 = Controller.encrypt(Objects.requireNonNull(quest_answer1.getEditText()).getText().toString(), a);
                    String answer2 = Controller.encrypt(Objects.requireNonNull(quest_answer2.getEditText()).getText().toString(), a);
                    String answer3 = Controller.encrypt(Objects.requireNonNull(quest_answer3.getEditText()).getText().toString(), a);

                    if(finalUse){
                        FirebaseControle.logout();
                        Answers answers = new Answers(finalUsers.getEmail(), answer1, answer2, answer3);
                        FirebaseControle.createUser(finalUsers, answers, Questions.this);
                    }else{
                        //todo
                        Toast.makeText(Questions.this, mail, Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                String answer1 = Objects.requireNonNull(quest_answer1.getEditText()).getText().toString();
                String answer2 = Objects.requireNonNull(quest_answer2.getEditText()).getText().toString();
                String answer3 = Objects.requireNonNull(quest_answer3.getEditText()).getText().toString();
                Answers answers = new Answers(mail, answer1, answer2, answer3);
                FirebaseControle.VerifAnswer("Users", "email", Questions.this, answers);
            }
        });
    }

    private void init() {

        quest_send = findViewById(R.id.quest_send);
        quest_answer1 = findViewById(R.id.quest_answer1);
        quest_answer2 = findViewById(R.id.quest_answer2);
        quest_answer3 = findViewById(R.id.quest_answer3);
    }


    @Override
    public void onBackPressed() {
        Intent intent;
        if(provient.equals("creation")){
            intent = new Intent(Questions.this, CreateAccount.class);
        }else{
            intent = new Intent(Questions.this, Login.class);
        }
        startActivity(intent);
        finish();

    }
}