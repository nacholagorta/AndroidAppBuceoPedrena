package com.example.pccom.androidappbuceopedrena;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText etEmail , etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = (EditText)findViewById(R.id.et_mail);
        etPassword = (EditText)findViewById(R.id.et_password);
    }

    public void OnLogin(View view){
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String type ="login";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);

        backgroundWorker.execute(type, email, password);

    }
}
