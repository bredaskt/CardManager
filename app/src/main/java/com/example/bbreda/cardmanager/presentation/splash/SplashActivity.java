package com.example.bbreda.cardmanager.presentation.splash;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.bbreda.cardmanager.R;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SplashFragment splashFragment = (SplashFragment) getSupportFragmentManager().findFragmentById(R.id.frame_container_splash_fragment);

        if (splashFragment == null) {
            splashFragment = SplashFragment.newInstance();

            FragmentManager fragmentManager = getSupportFragmentManager();

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction.add(R.id.frame_container_splash_fragment, splashFragment);

            fragmentTransaction.commit();

        }

    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);

        if (fragment instanceof SplashContract.View) {

            SplashContract.View view = (SplashContract.View) fragment;

            view.setPresenter(new SplashPresenter(view));

        }
    }


}
