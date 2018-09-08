package com.example.bbreda.cardmanager.presentation.carddetails;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.bbreda.cardmanager.R;
import com.example.bbreda.cardmanager.presentation.baselogged.BaseLoggedActivity;


public class CardDetailsActivity extends BaseLoggedActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_details);

        Fragment cardDetailsFragment = getSupportFragmentManager().findFragmentById(R.id.frame_container_card_details_fragment);

        if (cardDetailsFragment == null) {
            cardDetailsFragment = CardDetailsFragment.newInstance();

            FragmentManager fragmentManager = getSupportFragmentManager();

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction.add(R.id.frame_container_card_details_fragment, cardDetailsFragment);

            fragmentTransaction.commit();
        }

    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);

        if (fragment instanceof CardDetailsContract.View) {

            CardDetailsContract.View view = (CardDetailsContract.View) fragment;

            view.setPresenter(new CardDetailsPresenter(view));
        }
    }

}
