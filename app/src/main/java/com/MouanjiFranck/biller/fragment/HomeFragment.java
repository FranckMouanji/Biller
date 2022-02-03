package com.MouanjiFranck.biller.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.MouanjiFranck.biller.R;
import com.MouanjiFranck.biller.databinding.FragmentHomeBinding;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    Spinner choose_course;
    ListView list_cours;
    TextView no_course;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //initialisation
        initViews(root);

        //charge data to spinner
        String[] list= getResources().getStringArray(R.array.elementAffichage);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(root.getContext(), R.layout.spinner_item, list);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        choose_course.setAdapter(adapter);



        return root;
    }


    void initViews(View root){
        choose_course = root.findViewById(R.id.choose_course);
        list_cours = root.findViewById(R.id.list_cours);
        no_course = root.findViewById(R.id.no_course);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



}