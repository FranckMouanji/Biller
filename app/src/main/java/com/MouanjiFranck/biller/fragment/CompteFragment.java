package com.MouanjiFranck.biller.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.MouanjiFranck.biller.R;
import com.MouanjiFranck.biller.controller.Controller;
import com.MouanjiFranck.biller.databinding.FragmentCompteBinding;
import com.MouanjiFranck.biller.databinding.FragmentHomeBinding;

public class CompteFragment extends Fragment {

    TextView logout;
    private FragmentCompteBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCompteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        init(root);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Controller.delete_file(root.getContext());
            }
        });

        return root;
    }

    private void init(View root) {
        logout = root.findViewById(R.id.logout);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}