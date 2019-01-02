package com.example.pccom.androidappbuceopedrena;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Log.v("SecondActivity", "-----EMAIL DEL USUARIO: " + DataHolder.instance.fireBaseAdmin.user.getEmail());
    }
}
