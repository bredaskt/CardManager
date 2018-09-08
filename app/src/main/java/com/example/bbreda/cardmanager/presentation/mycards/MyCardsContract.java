package com.example.bbreda.cardmanager.presentation.mycards;

import com.example.bbreda.cardmanager.data.model.Card;
import com.example.bbreda.cardmanager.presentation.BasePresenter;
import com.example.bbreda.cardmanager.presentation.BaseView;

import java.util.List;

public interface MyCardsContract {

    interface View extends BaseView<Presenter> {

        void showCards(List<Card> cardList);

        void goToHomeScreen();

    }

    interface Presenter extends BasePresenter {

        void onCardItemClick(int cardPosition);

    }

}
