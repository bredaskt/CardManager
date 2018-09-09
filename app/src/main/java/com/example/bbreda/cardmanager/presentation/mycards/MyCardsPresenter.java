package com.example.bbreda.cardmanager.presentation.mycards;

import com.example.bbreda.cardmanager.business.CardBusiness;
import com.example.bbreda.cardmanager.business.LoginBusiness;
import com.example.bbreda.cardmanager.data.ApiManager;
import com.example.bbreda.cardmanager.data.model.Card;
import com.example.bbreda.cardmanager.data.repository.SharedPreferenceManager;

import com.example.bbreda.cardmanager.data.repository.local.card.CardLocalRepositoryImpl;
import com.example.bbreda.cardmanager.data.repository.local.login.LoginLocalRepositoryImpl;
import com.example.bbreda.cardmanager.data.repository.remote.login.LoginRemoteRepositoryImpl;
import com.example.bbreda.cardmanager.infrastructure.OperationListener;
import com.example.bbreda.cardmanager.infrastructure.OperationResult;

import java.util.List;

public class MyCardsPresenter implements MyCardsContract.Presenter {

    private MyCardsContract.View mView;

    private LoginBusiness mLoginBusiness;

    private CardBusiness mCardBusiness;

    public MyCardsPresenter(MyCardsContract.View view) {
        mView = view;

        mLoginBusiness = new LoginBusiness(new LoginRemoteRepositoryImpl(ApiManager.getInstance()),
                new LoginLocalRepositoryImpl(SharedPreferenceManager.getInstance(mView.getViewContext())));

        mCardBusiness = new CardBusiness(new CardLocalRepositoryImpl(SharedPreferenceManager.getInstance(mView.getViewContext())));

    }

    private OperationListener<Boolean> operationListenerSaveSelectedCard = new OperationListener<Boolean>() {

        @Override
        public void onOperationSuccess(Boolean resultStatus) {
            if (resultStatus) {
                mView.goToHomeScreen();
            }

        }

        @Override
        public void onOperationError(Boolean resultStatus) {

        }
    };

    private OperationListener<List<Card>> operationListenerCardList = new OperationListener<List<Card>>() {
        @Override
        public void onOperationSuccess(List<Card> cardList) {
            mView.showCards(cardList);
        }

        @Override
        public void onOperationError(List<Card> cardList) {

            // TODO

        }
    };

    @Override
    public void start() {
        // TODO

        mLoginBusiness.getCard(operationListenerCardList);

    }

    @Override
    public void onCardItemClick(int cardPosition) {

        mCardBusiness.saveCardSelected(cardPosition, operationListenerSaveSelectedCard);

    }
}
