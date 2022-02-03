package com.MouanjiFranck.biller.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.MouanjiFranck.biller.R;

public class StudentsAdapter  extends ArrayAdapter<String> {
    Context context;
    String[] niveau;
    String[] nomEleve;


    public StudentsAdapter(Context m_context, String[] niveau, String[] nom){
        super(m_context, R.layout.layout_content_student, niveau);
        context = m_context;
        this.niveau = niveau;
        nomEleve = nom;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert layoutInflater != null;
        @SuppressLint("ViewHolder") View view = layoutInflater.inflate(R.layout.layout_content_student, parent, false);
        TextView niveau = view.findViewById(R.id.item2);
        TextView  nom_eleve = view.findViewById(R.id.item1);

        niveau.setText(this.niveau[position]);
        nom_eleve.setText(this.nomEleve[position]);

        return view;
    }
}
