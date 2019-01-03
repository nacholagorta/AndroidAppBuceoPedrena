package com.example.pccom.androidappbuceopedrena.FragmentNavigationBar;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pccom.androidappbuceopedrena.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SalidasFragment extends Fragment {


    public SalidasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_salidas, container, false);
    }

}
