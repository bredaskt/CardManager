package com.example.bbreda.cardmanager.presentation.schedulepayment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.bbreda.cardmanager.R;
import com.example.bbreda.cardmanager.presentation.baselogged.BaseLoggedActivity;

public class SchedulePaymentActivity extends BaseLoggedActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_payment);

        SchedulePaymentFragment schedulePaymentFragment = (SchedulePaymentFragment) getSupportFragmentManager().findFragmentById(R.id.frame_container_schedule_payment_fragment);

        if (schedulePaymentFragment == null) {
            schedulePaymentFragment = SchedulePaymentFragment.newInstance();

            FragmentManager fragmentManager = getSupportFragmentManager();

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction.add(R.id.frame_container_schedule_payment_fragment, schedulePaymentFragment);

            fragmentTransaction.commit();
        }

    }

    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);

        if (fragment instanceof SchedulePaymentContract.View) {

            SchedulePaymentContract.View view = (SchedulePaymentContract.View) fragment;

            view.setPresenter(new SchedulePaymentPresenter(view));
        }
    }


}
