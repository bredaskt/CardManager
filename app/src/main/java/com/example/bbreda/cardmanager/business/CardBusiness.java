package com.example.bbreda.cardmanager.business;

import android.os.Handler;
import android.os.Looper;

import com.example.bbreda.cardmanager.data.model.Card;
import com.example.bbreda.cardmanager.data.model.Extract;
import com.example.bbreda.cardmanager.data.model.LoginResponse;
import com.example.bbreda.cardmanager.data.repository.local.card.CardLocalRepository;
import com.example.bbreda.cardmanager.data.repository.remote.card.CardRemoteRepository;
import com.example.bbreda.cardmanager.infrastructure.OperationListener;
import com.example.bbreda.cardmanager.infrastructure.OperationResult;

import java.util.List;

public class CardBusiness {

    private CardLocalRepository mCardLocalRepository;

    public CardBusiness(CardLocalRepository cardLocalRepository) {
        mCardLocalRepository = cardLocalRepository;

    }

    public void getCardDetails(final OperationListener<Card> operationListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OperationResult result;
                result = mCardLocalRepository.getCardDetails();
                sendCallbackGeneric(result, operationListener);

            }
        }).start();

    }

    public void saveCardSelected(final int selectedCard, final OperationListener<Boolean> operationListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OperationResult<Boolean> result;
                result = mCardLocalRepository.saveCardSelected(selectedCard);
                sendCallbackGeneric(result, operationListener);
            }
        }).start();

    }

    private <T> void sendCallbackGeneric(final OperationResult<T> result, final OperationListener callbackOperationListener) {

        Handler handler = new Handler(Looper.getMainLooper());

        handler.post(new Runnable() {
            @Override
            public void run() {
                if (result.getOperationType() == OperationResult.OperationType.SUCCESS) {
                    callbackOperationListener.onOperationSuccess(result.getResult());
                } else if (result.getOperationType() == OperationResult.OperationType.ERROR) {
                    callbackOperationListener.onOperationError(result.getResult());
                }
            }
        });
    }
}
