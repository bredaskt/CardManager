package com.example.bbreda.cardmanager.presentation.schedulepayment;

import com.example.bbreda.cardmanager.presentation.BasePresenter;
import com.example.bbreda.cardmanager.presentation.BaseView;

public interface SchedulePaymentContract {

    interface View extends BaseView<Presenter> {

        void setTimePicker();


    }

    interface Presenter extends BasePresenter{

        void onClickSetTimeSchedulePayment();

    }
}
