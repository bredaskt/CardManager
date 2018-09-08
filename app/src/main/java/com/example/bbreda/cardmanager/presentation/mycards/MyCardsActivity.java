package com.example.bbreda.cardmanager.presentation.mycards;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.bbreda.cardmanager.R;
import com.example.bbreda.cardmanager.presentation.baselogged.BaseLoggedActivity;

public class MyCardsActivity extends BaseLoggedActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycards);

        MyCardsFragment mycardsFragment = (MyCardsFragment) getSupportFragmentManager().findFragmentById(R.id.frame_container_mycards_fragment);

        if (mycardsFragment == null) {
            mycardsFragment = MyCardsFragment.newInstance();

            FragmentManager fragmentManager = getSupportFragmentManager();

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction.add(R.id.frame_container_mycards_fragment, mycardsFragment);

            fragmentTransaction.commit();
        }

    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);

        if (fragment instanceof MyCardsContract.View) {

            MyCardsContract.View view = (MyCardsContract.View) fragment;

            view.setPresenter(new MyCardsPresenter(view));
        }
    }
}
