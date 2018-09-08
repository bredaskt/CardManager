package com.example.bbreda.cardmanager.presentation.registration;

import com.example.bbreda.cardmanager.presentation.BasePresenter;
import com.example.bbreda.cardmanager.presentation.BaseView;

public interface RegistrationContract {

    interface View extends BaseView<Presenter>{

        void goToTermsOfUse();

        void readCardFromCam();

    }

    interface Presenter extends BasePresenter {

        void onClickReadCardFromCam();

        void onClickRequestAccount();



    }

}
