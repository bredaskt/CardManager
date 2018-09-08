package com.example.bbreda.cardmanager.presentation.schedulepayment;

public class SchedulePaymentPresenter implements SchedulePaymentContract.Presenter {

    SchedulePaymentContract.View mView;

    public SchedulePaymentPresenter(SchedulePaymentContract.View view) {
        mView = view;
    }

    @Override
    public void onClickSetTimeSchedulePayment() {

        mView.setTimePicker();

    }

    @Override
    public void start() {

    }

}
