package com.example.pccom.androidappbuceopedrena;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class FireBaseAdmin {

    private FirebaseAuth mAuth;
    public FireBaseAdminListener listerner;
    public FirebaseUser user;

    public FireBaseAdmin() {

        mAuth = FirebaseAuth.getInstance();

    }

    public void setListener(FireBaseAdminListener listener) {
        this.listerner = listener;
    }

    public void registerConEmailYPassword(String email, String password, Activity activity) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            user = FirebaseAuth.getInstance().getCurrentUser();
                            Log.v("MAINACTIVITYEVENTS", "createUserWithEmail:success");

                            listerner.fireBaseAdmin_RegisterOK(true);
                        } else {
                            listerner.fireBaseAdmin_RegisterOK(false);
                        }
                    }
                });

    }

    public void loginConEmailYPassword(String email, String password, Activity activity) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            user = FirebaseAuth.getInstance().getCurrentUser();
                            Log.v("MAINACTIVITYEVENTS", "createUserWithEmail:success");

                            listerner.fireBaseAdmin_LoginOK(true);
                        } else {
                            listerner.fireBaseAdmin_LoginOK(false);
                        }
                    }
                });

    }
}
