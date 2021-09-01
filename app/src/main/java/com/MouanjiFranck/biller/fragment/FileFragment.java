package com.MouanjiFranck.biller.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.MouanjiFranck.biller.databinding.FragmentFileBinding;

public class FileFragment extends Fragment {

    private FragmentFileBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentFileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        init(root);


        return root;
    }

    private void init(View root) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}