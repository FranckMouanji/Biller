package com.MouanjiFranck.biller.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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
    TextInputLayout reg_choose_image_profile;
    Button choose_file;

    CheckBox professeur;
    CheckBox repetiteur;
    CheckBox eleve;
    CheckBox etudiant;
    Spinner niveau;

    private Uri file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        init();

        String[] list= getResources().getStringArray(R.array.classe);


        ArrayAdapter adapter = new ArrayAdapter(this,
                R.layout.spinner_item, list);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        niveau.setAdapter(adapter);

        choose_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("application/jpg");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, getResources().getString(R.string.selection_fichier)), 1);
            }
        });

        professeur.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    eleve.setChecked(false);
                    niveau.setVisibility(View.GONE);
                }
            }
        });

        repetiteur.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    eleve.setChecked(false);
                    niveau.setVisibility(View.GONE);
                }
            }
        });

        etudiant.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    eleve.setChecked(false);
                    niveau.setVisibility(View.GONE);
                }
            }
        });

        eleve.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    professeur.setChecked(false);
                    repetiteur.setChecked(false);
                    etudiant.setChecked(false);
                    niveau.setVisibility(View.VISIBLE);
                }
            }
        });

        reg_send.setOnClickListener(view -> {
            String name = Objects.requireNonNull(reg_name.getEditText()).getText().toString();
            String surname = Objects.requireNonNull(reg_prenom.getEditText()).getText().toString();
            String email = Objects.requireNonNull(reg_email.getEditText()).getText().toString();
            String password = Objects.requireNonNull(reg_password.getEditText()).getText().toString();
            String confirmPassword = Objects.requireNonNull(reg_password_confirm.getEditText()).getText().toString();
            String niveauChaine = niveau.getSelectedItem().toString();

            String image = reg_choose_image_profile.getEditText().getText().toString();
            int statut = 0;

            if(professeur.isChecked()){
                statut = 1;
            }else if(repetiteur.isChecked()){
                statut = 2;
            }else if(eleve.isChecked()){
                statut = 3;
            }else if(etudiant.isChecked()){
                statut = 4;
            }
            if(professeur.isChecked() && etudiant.isChecked()){
                statut = 5;
            }else if(repetiteur.isChecked() && etudiant.isChecked()){
                statut = 6;
            }else if(professeur.isChecked() && repetiteur.isChecked()){
                statut = 7;
            }

            if(statut == 0){
                Toast.makeText(CreateAccount.this, "precisé votre statut", Toast.LENGTH_SHORT).show();
            }else if(name.equals("")){
                reg_name.requestFocus();
                Toast.makeText(CreateAccount.this, "entrez votre nom", Toast.LENGTH_SHORT).show();
            }else if(surname.equals("")){
                reg_prenom.requestFocus();
                Toast.makeText(CreateAccount.this, "entrez votre prenom", Toast.LENGTH_SHORT).show();
            }else if(email.equals("")){
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
            }else if(niveauChaine.equals("Niveau scolaire") && eleve.isChecked()){
                Toast.makeText(CreateAccount.this, "choisissez votre niveau", Toast.LENGTH_SHORT).show();
            }else{
                String[] partMail = email.split("@");
                if(partMail.length == 2){
                    String elt = partMail[1];
                    String[] part2Mail = elt.split("\\.");
                    if(!(part2Mail.length == 2)){
                        Toast.makeText(CreateAccount.this, "Cette adresse mail n'est pas correct 2 ", Toast.LENGTH_LONG).show();
                    }else {
                        Users users = new Users(image, name, surname, email, password, statut, niveauChaine);
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
        reg_choose_image_profile = findViewById(R.id.reg_choose_image_profile);
        choose_file = findViewById(R.id.choose_file);

        professeur = findViewById(R.id.professeur);
        repetiteur = findViewById(R.id.repetiteur);
        eleve = findViewById(R.id.eleve);
        etudiant = findViewById(R.id.etudiant);
        niveau = findViewById(R.id.niveau);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CreateAccount.this, Login.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1  && resultCode == RESULT_OK &&

                data != null && data.getData() != null){
            file = data.getData();
            reg_choose_image_profile.getEditText().setText(file.toString());
        }
    }
}