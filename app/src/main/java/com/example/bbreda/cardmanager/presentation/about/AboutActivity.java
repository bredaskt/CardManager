package com.example.bbreda.cardmanager.presentation.about;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.bbreda.cardmanager.R;
import com.example.bbreda.cardmanager.presentation.baselogged.BaseLoggedActivity;

public class AboutActivity extends BaseLoggedActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        AboutFragment aboutFragment = (AboutFragment) getSupportFragmentManager().findFragmentById(R.id.frame_container_about_fragment);

        if (aboutFragment == null) {
            aboutFragment = AboutFragment.newInstance();

            FragmentManager fragmentManager = getSupportFragmentManager();

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction.add(R.id.frame_container_about_fragment, aboutFragment);

            fragmentTransaction.commit();

        }
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);

        if (fragment instanceof AboutContract.View) {

            AboutContract.View view = (AboutContract.View) fragment;

            view.setPresenter(new AboutPresenter(view));
        }
    }

}
