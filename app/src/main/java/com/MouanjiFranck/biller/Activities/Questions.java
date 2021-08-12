package com.MouanjiFranck.biller.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.MouanjiFranck.biller.R;

public class Questions extends AppCompatActivity {
    Button quest_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        init();

        Intent intent = getIntent();
        if(intent.hasExtra("mail")){
            Toast.makeText(this, intent.getStringExtra("mail"), Toast.LENGTH_SHORT).show();
        }

        quest_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Questions.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void init() {
        quest_send = findViewById(R.id.quest_send);
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Questions.this, CreateAccount.class);
        startActivity(intent);
        finish();
    }
}