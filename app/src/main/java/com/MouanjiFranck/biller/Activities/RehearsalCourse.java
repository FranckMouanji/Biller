package com.MouanjiFranck.biller.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.MouanjiFranck.biller.R;
import com.MouanjiFranck.biller.firebase_action.ActionAboutStudents;
import com.MouanjiFranck.biller.model.Students;
import com.MouanjiFranck.biller.system.Controller;
import com.MouanjiFranck.biller.system.DialogInform;
import com.google.firebase.firestore.ListenerRegistration;

import java.util.ArrayList;
import java.util.List;

public class RehearsalCourse extends AppCompatActivity {

    ImageView bt_add_user;
    ListView list_eleve;
    TextView no_student;

    //variable de firebase
    ListenerRegistration registration;


    List<Students> liste = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rehearsal_course);

        initViews();

        bt_add_user.setOnClickListener(view -> {
            int statutActuel = Controller.recupStatut(RehearsalCourse.this);
            Log.e("statut", Integer.toString(statutActuel));

            switch (statutActuel){
                case 1:
                    DialogInform.addStudent(RehearsalCourse.this);
                    break;
                case 2:
                    Intent intent = new Intent(RehearsalCourse.this, BuildContrat.class);
                    startActivity(intent);
                    break;
                case 3:
                    DialogInform.addStudentDeux(RehearsalCourse.this);
                    break;
                default:
                    Log.e("statut", Integer.toString(statutActuel));
                    break;
            }

        });

        if(Controller.file_not_empty(RehearsalCourse.this)){
            registration = ActionAboutStudents.getStudentsCollection().addSnapshotListener((value, error) -> {
                Toast.makeText(RehearsalCourse.this, "firestore", Toast.LENGTH_SHORT).show();
                String userMail = Controller.recupEmailUser(RehearsalCourse.this);
                if(userMail != null){
                    ActionAboutStudents.chargeStudentData(RehearsalCourse.this, list_eleve, no_student, liste, userMail);
                }
            });
        }
    }

    private void initViews() {
        bt_add_user = findViewById(R.id.bt_add_user);
        list_eleve = findViewById(R.id.list_eleve);
        no_student = findViewById(R.id.no_student);
    }
}