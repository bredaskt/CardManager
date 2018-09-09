package com.example.bbreda.cardmanager.presentation.home;

import com.example.bbreda.cardmanager.data.model.Card;
import com.example.bbreda.cardmanager.presentation.BasePresenter;
import com.example.bbreda.cardmanager.presentation.BaseView;


public interface HomeContract {

    interface View extends BaseView<Presenter>{

        void showCardData(Card card);

        void showLoading();

        void hideLoading();

    }

    interface Presenter extends BasePresenter {



    }


}
