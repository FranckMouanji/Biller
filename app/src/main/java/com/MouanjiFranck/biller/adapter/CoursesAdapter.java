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


public class CoursesAdapter  extends ArrayAdapter<String>  {
    Context context;
    String[] nomSpecialite;
    String[] nomFichier;
    String[] dateEnvoi;


    public CoursesAdapter(Context m_context, String[] nomSpecialite, String[] nomFichier, String[] dateEnvoi){
        super(m_context, R.layout.layout_content_file, nomSpecialite);
        context = m_context;
        this.nomSpecialite = nomSpecialite;
        this.nomFichier = nomFichier;
        this.dateEnvoi = dateEnvoi;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert layoutInflater != null;
        @SuppressLint("ViewHolder") View view = layoutInflater.inflate(R.layout.layout_content_student, parent, false);
        TextView nomSpecialite = view.findViewById(R.id.nomSpecialite);
        TextView  nomFichier = view.findViewById(R.id.nomFichier);
        TextView  dateEnvoi = view.findViewById(R.id.dateEnvoi);

        nomSpecialite.setText(this.nomSpecialite[position]);
        nomFichier.setText(this.nomFichier[position]);
        dateEnvoi.setText(this.dateEnvoi[position]);

        return view;
    }
}
