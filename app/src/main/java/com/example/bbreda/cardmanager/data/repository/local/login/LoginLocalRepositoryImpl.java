package com.example.bbreda.cardmanager.data.repository.local.login;

import android.content.SharedPreferences;

import com.example.bbreda.cardmanager.data.model.Card;
import com.example.bbreda.cardmanager.data.model.Extract;
import com.example.bbreda.cardmanager.data.model.LoginResponse;
import com.example.bbreda.cardmanager.data.model.User;
import com.example.bbreda.cardmanager.infrastructure.OperationResult;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class LoginLocalRepositoryImpl implements LoginLocalRepository {

    private static final String USER = "user";
    private static final String CARD = "card";

    private SharedPreferences mSharedPreferences;

    public LoginLocalRepositoryImpl(SharedPreferences sharedPreferences) {

        mSharedPreferences = sharedPreferences;

    }

    @Override
    public OperationResult<Boolean> saveData(LoginResponse loginResponse) {
        final OperationResult<Boolean> operationResult = new OperationResult<>();

        try {
            SharedPreferences.Editor editor = mSharedPreferences.edit();

            editor.putString(USER, new Gson().toJson(loginResponse.getmUser()));
            editor.putString(CARD, new Gson().toJson(loginResponse.getmCards()));
            editor.commit();
            operationResult.setOperationType(OperationResult.OperationType.SUCCESS);
            operationResult.setResult(true);
        } catch(Exception e) {
            operationResult.setOperationType(OperationResult.OperationType.ERROR);
            operationResult.setResult(false);

        }

        return operationResult;

    }

    @Override
    public OperationResult<User> getUser() {
        final OperationResult<User> operationResult = new OperationResult<>();

        User user = new Gson().fromJson(mSharedPreferences.getString(USER, ""), User.class);
        operationResult.setOperationType(OperationResult.OperationType.SUCCESS);
        operationResult.setResult(user);

        return operationResult;

    }

    @Override
    public OperationResult<List<Card>> getCard() {
        final OperationResult<List<Card>> operationResult = new OperationResult<>();

        List<Card> mCards = new Gson().fromJson(mSharedPreferences.getString(CARD, ""), new TypeToken<List<Card>>() {}.getType());
        operationResult.setOperationType(OperationResult.OperationType.SUCCESS);
        operationResult.setResult(mCards);

        return operationResult;

    }

}