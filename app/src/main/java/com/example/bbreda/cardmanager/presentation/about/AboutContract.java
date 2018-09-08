package com.example.bbreda.cardmanager.presentation.about;

import com.example.bbreda.cardmanager.presentation.BasePresenter;
import com.example.bbreda.cardmanager.presentation.BaseView;

public interface AboutContract {

    interface View extends BaseView<Presenter> {

        void goToAboutScreen();

    }

    interface Presenter extends BasePresenter {

        void onClickAbout();

    }
}
