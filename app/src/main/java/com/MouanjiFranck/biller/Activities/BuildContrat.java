package com.MouanjiFranck.biller.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.MouanjiFranck.biller.R;

public class BuildContrat extends AppCompatActivity {
    ImageView back;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_contrat);

        init();

        back.setOnClickListener(view -> finish());

        next.setOnClickListener(view -> {
            Intent intent = new Intent(BuildContrat.this, HomeActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void init() {
        back = findViewById(R.id.back);
        next = findViewById(R.id.next);
    }
}