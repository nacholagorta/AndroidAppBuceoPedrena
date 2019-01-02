package com.example.pccom.androidappbuceopedrena;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class RegisterFragment extends Fragment {

    public EditText etEmailReg;
    public EditText etPasswordReg;
    public Button btnConfirmaRegistro;
    public Button btnCancelaRegistro;
    public RegisterFragmentListener listener;

    public RegisterFragment() {
        // Required empty public constructor
    }

public void setListener(RegisterFragmentListener listener){
        this.listener = listener;
}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_register, container, false);
        etEmailReg = v.findViewById(R.id.etEmailRegister);
        etPasswordReg = v.findViewById(R.id.etPasswordRegister);
        btnConfirmaRegistro = v.findViewById(R.id.btnConfirmRegister);
        btnCancelaRegistro =v.findViewById(R.id.btnCancelaReg);

        RegisterFragmentEvents events = new RegisterFragmentEvents(this);

        btnConfirmaRegistro.setOnClickListener(events);
        btnCancelaRegistro.setOnClickListener(events);
        return v;

    }



}


class RegisterFragmentEvents implements View.OnClickListener {

    private RegisterFragment registerFragment;

    public RegisterFragmentEvents(RegisterFragment registerFragment) {
        this.registerFragment = registerFragment;
    }

    @Override
    public void onClick(View v) {
    if(v.getId()==this.registerFragment.btnConfirmaRegistro.getId()){
    this.registerFragment.listener.btnConfirmaRegistroClicked(
            registerFragment.etEmailReg.getText().toString(),
            registerFragment.etPasswordReg.getText().toString());
        }
        if(v.getId()==this.registerFragment.btnCancelaRegistro.getId()){
            this.registerFragment.listener.btnCancelaRegistroClicked();
        }
    }

}
