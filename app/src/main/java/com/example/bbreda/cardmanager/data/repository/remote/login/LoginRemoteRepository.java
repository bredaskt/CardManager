package com.example.bbreda.cardmanager.data.repository.remote.login;

import com.example.bbreda.cardmanager.data.model.LoginResponse;
import com.example.bbreda.cardmanager.data.model.LoginRequest;
import com.example.bbreda.cardmanager.infrastructure.OperationResult;

public interface LoginRemoteRepository {

    OperationResult<LoginResponse> doLogin(LoginRequest loginRequest);


}
