package com.MouanjiFranck.biller.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.MouanjiFranck.biller.R;
import com.MouanjiFranck.biller.controller.Controller;
import com.MouanjiFranck.biller.controller.FirebaseControle;
import com.MouanjiFranck.biller.model.Contrats;
import com.MouanjiFranck.biller.model.Students;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.Timestamp;
import java.util.Date;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BuildContrat extends AppCompatActivity {
    ImageView back;
    Button next;
    Button build_send;

    ScrollView part_1;
    ScrollView part_2;

    TextInputLayout stu_name;
    TextInputLayout stu_prenom;
    TextInputLayout stu_name_parent;
    TextInputLayout stu_prenom_parent;


    TextInputLayout number;
    TextInputLayout second_number;
    TextInputLayout salaire;
    TextInputLayout stu_date_payement;

    Spinner stu_classe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_contrat);

        init();
        String[] list= getResources().getStringArray(R.array.classe);


        ArrayAdapter adapter = new ArrayAdapter(this,
                R.layout.spinner_item, list);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        stu_classe.setAdapter(adapter);

        Objects.requireNonNull(stu_date_payement.getEditText()).setOnClickListener(view -> Controller.showDatePickerDialog(BuildContrat.this, stu_date_payement.getEditText()));

        back.setOnClickListener(view -> finish());

        next.setOnClickListener(view -> {
            String classe = stu_classe.getSelectedItem().toString();
            if(Objects.requireNonNull(stu_name.getEditText()).getText().toString().equals("") || Objects.requireNonNull(stu_prenom.getEditText()).getText().toString().equals("") ||
                Objects.requireNonNull(stu_name_parent.getEditText()).getText().toString().equals("") || Objects.requireNonNull(stu_prenom_parent.getEditText()).getText().toString().equals("") ||
                classe.equals("Niveau scolaire")){
                Toast.makeText(BuildContrat.this, "Tous les chanps sont requis", Toast.LENGTH_SHORT).show();

            }else{
                part_1.setVisibility(View.GONE);
                part_2.setVisibility(View.VISIBLE);
            }
        });

        build_send.setOnClickListener(view -> {
            if(number.getEditText().getText().toString().equals("") || second_number.getEditText().getText().toString().equals("") ||
                    salaire.getEditText().getText().toString().equals("") || stu_date_payement.getEditText().getText().toString().equals("")){
                Toast.makeText(BuildContrat.this, "Tous les chanps sont requis", Toast.LENGTH_SHORT).show();

            }else{
                String name_student = Objects.requireNonNull(stu_name.getEditText()).getText().toString();
                String prenom_student = Objects.requireNonNull(stu_prenom.getEditText()).getText().toString();
                String classe = stu_classe.getSelectedItem().toString();
                Students student = new Students("", name_student, prenom_student, classe);

                String parent_name = Objects.requireNonNull(stu_name_parent.getEditText()).getText().toString();
                String parent_last_name = Objects.requireNonNull(stu_prenom_parent.getEditText()).getText().toString();
                String first_number_parent = number.getEditText().getText().toString();
                String second_number_parent = second_number.getEditText().getText().toString();
                String pay = salaire.getEditText().getText().toString();
                String date_payment = stu_date_payement.getEditText().getText().toString();
                String userMail = Controller.recupEmailUser(BuildContrat.this);

                Date date = new Date();
                Timestamp timestamp = new Timestamp(date);

                student.setEmail_prof(userMail);
                student.setDateCreation(timestamp);
                Contrats contrat = new Contrats("", userMail, "", parent_name, parent_last_name, first_number_parent, second_number_parent, pay, date_payment);

                FirebaseControle.addStudent(BuildContrat.this, student, contrat);
            }
        });
    }

    private void init() {
        part_1 = findViewById(R.id.part_1);
        part_2 = findViewById(R.id.part_2);

        stu_name = findViewById(R.id.stu_name);
        stu_prenom = findViewById(R.id.stu_prenom);
        stu_name_parent = findViewById(R.id.stu_name_parent);
        stu_prenom_parent = findViewById(R.id.stu_prenom_parent);

        number = findViewById(R.id.number);
        second_number = findViewById(R.id.second_number);
        salaire = findViewById(R.id.salaire);
        stu_date_payement = findViewById(R.id.stu_date_payement);

        stu_classe = findViewById(R.id.stu_classe);

        next = findViewById(R.id.next);
        back = findViewById(R.id.back);
        build_send = findViewById(R.id.build_send);
    }

    @Override
    public void onBackPressed() {
        if(part_2.getVisibility() == View.VISIBLE){
            part_2.setVisibility(View.GONE);
            part_1.setVisibility(View.VISIBLE);
        }else{
            super.onBackPressed();
        }

    }
}