package com.example.pccom.androidappbuceopedrena;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    LoginFragment loginFragment;
    RegisterFragment registerFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginFragment = (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentLogin);
        registerFragment = (RegisterFragment)getSupportFragmentManager().findFragmentById(R.id.fragmentRegister);
        MainActivityEvents mainActivityEvents = new MainActivityEvents(this);

        loginFragment.setListener(mainActivityEvents);
        registerFragment.setListener(mainActivityEvents);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.show(loginFragment);
        transaction.hide(registerFragment);
        transaction.commit();
    }
}

class MainActivityEvents implements LoginFragmentListener, RegisterFragmentListener {
    MainActivity mainActivity;

    public MainActivityEvents(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void loginButtonClicked(String sUser, String sPass) {

    }

    @Override
    public void registerButtonClicked() {
        FragmentTransaction transaction = mainActivity.getSupportFragmentManager().beginTransaction();
        transaction.hide(mainActivity.loginFragment);
        transaction.show(mainActivity.registerFragment);
        transaction.commit();
    }

    @Override
    public void btnConfirmaRegistroClicked() {

    }

    @Override
    public void btnCancelaRegistroClicked() {
 FragmentTransaction transaction = mainActivity.getSupportFragmentManager().beginTransaction();
 transaction.show(mainActivity.loginFragment);
 transaction.hide(mainActivity.registerFragment);
 transaction.commit();
    }
}
