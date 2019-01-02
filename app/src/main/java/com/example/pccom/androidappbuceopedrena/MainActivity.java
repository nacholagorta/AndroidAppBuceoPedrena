package com.example.pccom.androidappbuceopedrena;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    LoginFragment loginFragment;
    RegisterFragment registerFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginFragment = (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentLogin);
        registerFragment = (RegisterFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentRegister);
        MainActivityEvents mainActivityEvents = new MainActivityEvents(this);

        loginFragment.setListener(mainActivityEvents);
        registerFragment.setListener(mainActivityEvents);
        DataHolder.instance.fireBaseAdmin.setListener(mainActivityEvents);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.show(loginFragment);
        transaction.hide(registerFragment);
        transaction.commit();

        //Hacer el Login Automático
        // DataHolder.instance.fireBaseAdmin.loginConEmailYPassword("nacholagorta@hotmail.com", "123456", this);


    }
}

class MainActivityEvents implements LoginFragmentListener, RegisterFragmentListener, FireBaseAdminListener {
    MainActivity mainActivity;

    public MainActivityEvents(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void loginButtonClicked(String sUser, String sPass) {
        DataHolder.instance.fireBaseAdmin.loginConEmailYPassword(sUser, sPass, mainActivity);
    }

    @Override
    public void registerButtonClicked() {
        FragmentTransaction transaction = mainActivity.getSupportFragmentManager().beginTransaction();
        transaction.show(mainActivity.registerFragment);
        transaction.hide(mainActivity.loginFragment);
        transaction.commit();
    }

    @Override
    public void btnConfirmaRegistroClicked(String sUser, String sPass) {
        Log.v("MAINACTIVITYEVENTS", "Datos del registro " + sUser + " --- " + sPass);
        DataHolder.instance.fireBaseAdmin.registerConEmailYPassword(sUser, sPass, mainActivity);
    }

    @Override
    public void btnCancelaRegistroClicked() {
        FragmentTransaction transaction = mainActivity.getSupportFragmentManager().beginTransaction();
        transaction.hide(mainActivity.registerFragment);
        transaction.show(mainActivity.loginFragment);

        transaction.commit();
    }

    @Override
    public void fireBaseAdmin_RegisterOK(boolean blOk) {

        Log.v("MAINACTIVITYEVENTS", "RESULTADO DE REGISTER " + blOk);
        if (blOk) {
            Intent intent = new Intent(mainActivity, SecondActivity.class);
            mainActivity.startActivity(intent);
            mainActivity.finish();
        }
    }

    @Override
    public void fireBaseAdmin_LoginOK(boolean blOk) {
        Log.v("MAINACTIVITYEVENTS", "RESULTADO DE LOGIN " + blOk);
        if (blOk) {
            Intent intent = new Intent(mainActivity, SecondActivity.class);
            mainActivity.startActivity(intent);
            mainActivity.finish();
        }

    }

}
