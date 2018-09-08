package com.example.bbreda.cardmanager.presentation.registration;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.bbreda.cardmanager.R;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        RegistrationFragment registrationFragment = (RegistrationFragment) getSupportFragmentManager().findFragmentById(R.id.frame_container_registration_fragment);

        if (registrationFragment == null) {
            registrationFragment = RegistrationFragment.newInstance();

            FragmentManager fragmentManager = getSupportFragmentManager();

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction.add(R.id.frame_container_registration_fragment, registrationFragment);

            fragmentTransaction.commit();

        }

    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);

        if (fragment instanceof RegistrationContract.View) {

            RegistrationContract.View view = (RegistrationContract.View) fragment;

            view.setPresenter(new RegistrationPresenter(view));
        }

    }

}


