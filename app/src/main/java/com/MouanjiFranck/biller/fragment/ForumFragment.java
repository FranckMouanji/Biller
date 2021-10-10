package com.MouanjiFranck.biller.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.MouanjiFranck.biller.databinding.FragmentForumBinding;


public class ForumFragment extends Fragment {

    private FragmentForumBinding binding;
    private ListView list_forum;
    private TextView no_forum;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentForumBinding.inflate(inflater, container, false);
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