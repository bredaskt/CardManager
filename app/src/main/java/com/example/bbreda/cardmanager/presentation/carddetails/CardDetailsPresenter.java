package com.example.bbreda.cardmanager.presentation.carddetails;

import com.example.bbreda.cardmanager.business.CardBusiness;
import com.example.bbreda.cardmanager.data.model.Card;
import com.example.bbreda.cardmanager.data.model.Extract;
import com.example.bbreda.cardmanager.data.repository.SharedPreferenceManager;
import com.example.bbreda.cardmanager.data.repository.local.card.CardLocalRepositoryImpl;
import com.example.bbreda.cardmanager.infrastructure.OperationListener;
import java.util.ArrayList;
import java.util.List;

public class CardDetailsPresenter implements CardDetailsContract.Presenter {

    private CardBusiness mCardBusiness;

    private CardDetailsContract.View mView;

    public CardDetailsPresenter(CardDetailsContract.View view) {
        mView = view;
        mCardBusiness = new CardBusiness(new CardLocalRepositoryImpl(SharedPreferenceManager.getInstance(mView.getViewContext())));
    }

    private OperationListener<Card> operationListenerExtractDetails = new OperationListener<Card>() {

        @Override
        public void onOperationSuccess(Card card) {
            List<CardDetailsWrapper> cardDetailsList = new ArrayList<>();
            cardDetailsList.add(new CardDetailsWrapper(null, CardDetailsWrapper.Type.HEADER, card, mView.getViewContext().getResources()));

            for (Extract extract : card.getmExtract() ) {
                cardDetailsList.add(new CardDetailsWrapper(extract, CardDetailsWrapper.Type.ITEM, card, mView.getViewContext().getResources()));
            }

            mView.showCardDetails(cardDetailsList);
        }

        @Override
        public void onOperationError(Card card) {
            // TODO
        }
    };

    @Override
    public void start() {
        mCardBusiness.getCardDetails(operationListenerExtractDetails);
    }
}
