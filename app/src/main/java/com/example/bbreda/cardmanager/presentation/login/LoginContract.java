    package com.example.bbreda.cardmanager.presentation.login;

import com.example.bbreda.cardmanager.presentation.BasePresenter;
import com.example.bbreda.cardmanager.presentation.BaseView;

public interface LoginContract {

    interface View extends BaseView<Presenter>{

        void goToHomeScreen();

        void goToRegistrationScreen();

        void showMessageErrorFilledFormLoginGeneric();

        void showMessageNetworkError();

    }

    interface Presenter extends BasePresenter {

        void onButtonClickToRegistrationScreen();

        void onButtonClickLogin(String email, String password);

    }

}
