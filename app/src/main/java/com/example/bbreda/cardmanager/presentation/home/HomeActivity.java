package com.example.bbreda.cardmanager.presentation.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.bbreda.cardmanager.R;
import com.example.bbreda.cardmanager.presentation.baselogged.BaseLoggedActivity;

public class HomeActivity extends BaseLoggedActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        HomeFragment homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentById(R.id.frame_container_home_fragment);

        if (homeFragment == null) {
            homeFragment = HomeFragment.newInstance();

            FragmentManager fragmentManager = getSupportFragmentManager();

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction.add(R.id.frame_container_home_fragment, homeFragment);

            fragmentTransaction.commit();
        }
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);

        if (fragment instanceof HomeContract.View) {

            HomeContract.View view = (HomeContract.View) fragment;

            view.setPresenter(new HomePresenter(view));
        }
    }

}

