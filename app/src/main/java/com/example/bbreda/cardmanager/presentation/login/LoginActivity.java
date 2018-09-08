package com.example.bbreda.cardmanager.presentation.login;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.bbreda.cardmanager.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginFragment loginFragment = (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.frame_container_login_fragment);

        if (loginFragment == null) {
            loginFragment = LoginFragment.newInstance();

            FragmentManager fragmentManager = getSupportFragmentManager();

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction.add(R.id.frame_container_login_fragment, loginFragment);

            fragmentTransaction.commit();
        }

    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);

        if (fragment instanceof LoginContract.View) {

            LoginContract.View view = (LoginContract.View) fragment;

            view.setPresenter(new LoginPresenter(view));
        }
    }

}
