package com.MouanjiFranck.biller.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.MouanjiFranck.biller.Activities.BuildContrat;
import com.MouanjiFranck.biller.R;
import com.MouanjiFranck.biller.firebase_action.ActionAboutStudents;
import com.MouanjiFranck.biller.system.Controller;
import com.MouanjiFranck.biller.databinding.FragmentHomeBinding;
import com.MouanjiFranck.biller.model.Students;
import com.google.firebase.firestore.ListenerRegistration;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    ImageView bt_add_user;
    ListView list_eleve;
    TextView no_student;

    //variable de firebase
    ListenerRegistration registration;


    List<Students> liste = new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        initViews(root);

        bt_add_user.setOnClickListener(view -> {
            Intent intent = new Intent(root.getContext(), BuildContrat.class);
            root.getContext().startActivity(intent);
        });

        registration = ActionAboutStudents.getStudentsCollection().addSnapshotListener((value, error) -> {
            Toast.makeText(root.getContext(), "firestore", Toast.LENGTH_SHORT).show();
            String userMail = Controller.recupEmailUser(root.getContext());
            ActionAboutStudents.chargeStudentData(root.getContext(), list_eleve, no_student, liste, userMail);
        });

        return root;
    }

    private void initViews(View root) {
        bt_add_user = root.findViewById(R.id.bt_add_user);
        list_eleve = root.findViewById(R.id.list_eleve);
        no_student = root.findViewById(R.id.no_student);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



}