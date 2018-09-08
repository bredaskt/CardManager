package com.example.bbreda.cardmanager.presentation.splash;

import com.example.bbreda.cardmanager.presentation.BasePresenter;
import com.example.bbreda.cardmanager.presentation.BaseView;

public interface SplashContract {


    interface View extends BaseView<Presenter> {

        void goToLoginScreen();

    }

    interface Presenter extends BasePresenter {

    }

}
