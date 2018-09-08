package com.example.bbreda.cardmanager.presentation.baselogged;

import com.example.bbreda.cardmanager.data.model.User;
import com.example.bbreda.cardmanager.presentation.BasePresenter;
import com.example.bbreda.cardmanager.presentation.BaseView;

public interface BaseLoggedContract {

    interface View extends BaseView<Presenter> {

        void showDataUser(User user);

    }


    interface Presenter extends BasePresenter{

    }
}
