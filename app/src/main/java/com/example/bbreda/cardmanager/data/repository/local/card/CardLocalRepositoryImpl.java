package com.example.bbreda.cardmanager.data.repository.local.card;

import android.content.SharedPreferences;

import com.example.bbreda.cardmanager.data.model.Card;
import com.example.bbreda.cardmanager.data.model.LoginResponse;
import com.example.bbreda.cardmanager.infrastructure.OperationListener;
import com.example.bbreda.cardmanager.infrastructure.OperationResult;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import static android.os.Build.USER;

public class CardLocalRepositoryImpl implements CardLocalRepository {

    private static final String CARD = "card";
    private static final String SELECTED_CARD = "selected_card";

    private SharedPreferences mSharedPreferences;

    public CardLocalRepositoryImpl(SharedPreferences sharedPreferences) {
        mSharedPreferences = sharedPreferences;
    }

    @Override
    public OperationResult<Card> getCardDetails() {

        final OperationResult<Card> operationResult = new OperationResult<>();

        int selectedCard = mSharedPreferences.getInt(SELECTED_CARD, 0);

        List<Card> mCards = new Gson().fromJson(mSharedPreferences.getString(CARD, ""), new TypeToken<List<Card>>() {}.getType());
        operationResult.setOperationType(OperationResult.OperationType.SUCCESS);
        operationResult.setResult(mCards.get(selectedCard));

        return operationResult;

    }

    @Override
    public OperationResult<Boolean> saveCardSelected(int selectedCard) {

        final OperationResult<Boolean> operationResult = new OperationResult<>();

        // TODO persistir o cardNumber

        try {
            SharedPreferences.Editor editor = mSharedPreferences.edit();

            editor.putInt(SELECTED_CARD, selectedCard);
            editor.commit();
            operationResult.setOperationType(OperationResult.OperationType.SUCCESS);
            operationResult.setResult(true);
        } catch(Exception e) {
            operationResult.setOperationType(OperationResult.OperationType.ERROR);
            operationResult.setResult(false);

        }

        return operationResult;

    }

}



