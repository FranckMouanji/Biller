package com.MouanjiFranck.biller.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.MouanjiFranck.biller.Activities.BuildContrat;
import com.MouanjiFranck.biller.R;
import com.MouanjiFranck.biller.databinding.FragmentGalleryBinding;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;
    Button next;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        init(root);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(root.getContext(), BuildContrat.class);
                root.getContext().startActivity(intent);
            }
        });

        return root;
    }

    private void init(View root) {
        next = root.findViewById(R.id.next);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}