package com.MouanjiFranck.biller.system;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.MouanjiFranck.biller.R;

public class DialogInform {

    public static void connexionDialog(final Context context){

        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.error_connexion);
        dialog.setCancelable(false);

        //variable du layout
        Button validate = dialog.findViewById(R.id.validate);

        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity)context).finish();
                dialog.dismiss();
            }
        });


        dialog.show();

    }

    public static void addStudent(final Context context){

//        final Dialog dialog = new Dialog(context);
//        dialog.setContentView(R.layout.error_connexion);
//        dialog.setCancelable(false);
//
//        //variable du layout
//        Button validate = dialog.findViewById(R.id.validate);
//
//        validate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ((Activity)context).finish();
//                dialog.dismiss();
//            }
//        });
//
//
//        dialog.show();

        Toast.makeText(context, "add student exist", Toast.LENGTH_SHORT).show();

    }


    public static void addStudentDeux(Context context) {
    }
}
