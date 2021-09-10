package com.MouanjiFranck.biller.Activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.MouanjiFranck.biller.R;
import com.MouanjiFranck.biller.system.Controller;

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
                if(Controller.file_not_empty(MainActivity.this)){
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Intent intent = new Intent(MainActivity.this, Login.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 3000);
    }
}