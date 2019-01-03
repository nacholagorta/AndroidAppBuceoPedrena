package com.example.pccom.androidappbuceopedrena;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.pccom.androidappbuceopedrena.FragmentNavigationBar.InstructoresFragment;
import com.example.pccom.androidappbuceopedrena.FragmentNavigationBar.MisSalidasFragment;
import com.example.pccom.androidappbuceopedrena.FragmentNavigationBar.PerfilFragment;
import com.example.pccom.androidappbuceopedrena.FragmentNavigationBar.SalidasFragment;

public class SecondActivity extends AppCompatActivity {

    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;

    private InstructoresFragment instructoresFragment;
    private SalidasFragment salidasFragment;
    private MisSalidasFragment misSalidasFragment;
    private PerfilFragment perfilFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mMainFrame = (FrameLayout) findViewById(R.id.main_frame);
        mMainNav = (BottomNavigationView) findViewById(R.id.main_nav);

        salidasFragment = new SalidasFragment();
        instructoresFragment = new InstructoresFragment();
        misSalidasFragment = new MisSalidasFragment();
        perfilFragment = new PerfilFragment();

        setFragment(salidasFragment);

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_salidas :
                        setFragment(salidasFragment);
                        return true;

                    case R.id.nav_instructores :
                        setFragment(instructoresFragment);
                        return true;

                    case R.id.nav_mis_salidas :
                        setFragment(misSalidasFragment);
                        return true;

                    case R.id.nav_perfil :

                        setFragment(perfilFragment);
                        return true;
                    default:
                        return false;
                }
            }
        });
        //Log.v("SecondActivity", "-----EMAIL DEL USUARIO: " + DataHolder.instance.fireBaseAdmin.user.getEmail());
    }

    private void setFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }
}
