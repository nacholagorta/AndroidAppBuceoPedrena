package com.example.pccom.androidappbuceopedrena;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class LoginFragment extends Fragment {

    public EditText etEmail;
    public EditText etPass;
    public Button btnLogin;
    public Button btnRegister;
    public LoginFragmentEvents events;
    public LoginFragmentListener listener;

    public LoginFragment() {
        // Required empty public constructor
    }

    public void setListener(LoginFragmentListener listener) {
        this.listener = listener;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        etEmail = v.findViewById(R.id.etEmail);
        etPass = v.findViewById(R.id.etPassword);
        btnLogin = v.findViewById(R.id.btnLogin);
        btnRegister = v.findViewById(R.id.btnRegister);

        events = new LoginFragmentEvents(this);
        btnLogin.setOnClickListener(events);
        btnRegister.setOnClickListener(events);

        return v;
    }


}

class LoginFragmentEvents implements View.OnClickListener {

    private LoginFragment loginFragment;

    public LoginFragmentEvents(LoginFragment fragment) {
        this.loginFragment = fragment;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == this.loginFragment.btnLogin.getId()) {


            this.loginFragment.listener.loginButtonClicked(this.loginFragment.etEmail.getText().toString(),
            this.loginFragment.etPass.getText().toString());
        } else if (v.getId() == this.loginFragment.btnRegister.getId()) {
            this.loginFragment.listener.registerButtonClicked();
        }
    }

}
