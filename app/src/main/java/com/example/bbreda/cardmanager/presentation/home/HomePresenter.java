package com.example.bbreda.cardmanager.presentation.home;

import android.widget.Toast;

import com.example.bbreda.cardmanager.business.CardBusiness;
import com.example.bbreda.cardmanager.business.LoginBusiness;
import com.example.bbreda.cardmanager.data.ApiManager;
import com.example.bbreda.cardmanager.data.model.Card;
import com.example.bbreda.cardmanager.data.model.User;
import com.example.bbreda.cardmanager.data.repository.SharedPreferenceManager;
import com.example.bbreda.cardmanager.data.repository.local.card.CardLocalRepositoryImpl;
import com.example.bbreda.cardmanager.data.repository.local.login.LoginLocalRepositoryImpl;
import com.example.bbreda.cardmanager.data.repository.remote.card.CardRemoteRepository;
import com.example.bbreda.cardmanager.data.repository.remote.login.LoginRemoteRepositoryImpl;
import com.example.bbreda.cardmanager.infrastructure.OperationListener;

import java.util.List;

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View mView;

    private CardBusiness mCardBusiness;

    public HomePresenter(HomeContract.View view) {
        mView = view;

        mCardBusiness = new CardBusiness(new CardLocalRepositoryImpl(SharedPreferenceManager.getInstance(mView.getViewContext())));

    }

    private OperationListener<Card> operationListener = new OperationListener<Card>() {

        @Override
        public void onOperationSuccess(Card card) {

            mView.showCardData(card);

        }

        @Override
        public void onOperationError(Card card) {
            // TODO
        }
    };


    @Override
    public void start() {
        mCardBusiness.getCardDetails(operationListener);

    }

}
