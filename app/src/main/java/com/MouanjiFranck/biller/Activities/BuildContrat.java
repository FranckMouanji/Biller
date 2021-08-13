package com.MouanjiFranck.biller.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.MouanjiFranck.biller.R;

public class BuildContrat extends AppCompatActivity {
    ImageView back;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_contrat);

        init();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BuildContrat.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void init() {
        back = findViewById(R.id.back);
        next = findViewById(R.id.next);
    }
}