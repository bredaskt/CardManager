package com.example.bbreda.cardmanager.data.repository.local.card;

import com.example.bbreda.cardmanager.data.model.Card;
import com.example.bbreda.cardmanager.data.model.LoginResponse;
import com.example.bbreda.cardmanager.infrastructure.OperationListener;
import com.example.bbreda.cardmanager.infrastructure.OperationResult;

public interface CardLocalRepository {

    OperationResult<Card> getCardDetails();

    OperationResult<Boolean> saveCardSelected(int selectedCard);

}
