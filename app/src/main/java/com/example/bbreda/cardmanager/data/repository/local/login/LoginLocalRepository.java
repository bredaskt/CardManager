package com.example.bbreda.cardmanager.data.repository.local.login;


import com.example.bbreda.cardmanager.data.model.Card;
import com.example.bbreda.cardmanager.data.model.LoginResponse;
import com.example.bbreda.cardmanager.data.model.User;
import com.example.bbreda.cardmanager.infrastructure.OperationResult;

import java.util.List;

public interface LoginLocalRepository {

    OperationResult<Boolean> saveData(LoginResponse loginResponse);

    OperationResult<User> getUser();

    OperationResult<List<Card>> getCard();

}