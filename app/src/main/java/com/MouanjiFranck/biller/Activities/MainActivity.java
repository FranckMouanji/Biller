package com.MouanjiFranck.biller.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.MouanjiFranck.biller.R;
import com.MouanjiFranck.biller.system.Controller;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Controller.haveStoragePermission(MainActivity.this);

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {


                if(verifConnexion()){

                    if(Controller.file_not_empty(MainActivity.this)){
                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        intent.putExtra("connexion", "true");
                        startActivity(intent);
                        finish();
                    }else {
                        Intent intent = new Intent(MainActivity.this, Login.class);
                        intent.putExtra("connexion", "true");
                        startActivity(intent);
                        finish();
                    }
                }else{

                    if(Controller.file_not_empty(MainActivity.this)){
                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        intent.putExtra("connexion", "false");
                        startActivity(intent);
                        finish();
                    }else {
                        Intent intent = new Intent(MainActivity.this, Login.class);
                        intent.putExtra("connexion", "false");
                        startActivity(intent);
                        finish();
                    }
                }

            }
        }, 1000);
    }

    private boolean verifConnexion(){
        String url ="https://www.google.com";


        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Response response = client.newCall(request).execute();
            Log.d("pass", response.toString());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("error_connect", e.getMessage());
            return false;
        }
    }


}