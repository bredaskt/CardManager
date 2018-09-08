package com.example.bbreda.cardmanager.business;

import android.os.Handler;
import android.os.Looper;

import com.example.bbreda.cardmanager.data.model.Card;
import com.example.bbreda.cardmanager.data.model.LoginRequest;
import com.example.bbreda.cardmanager.data.model.LoginResponse;
import com.example.bbreda.cardmanager.data.model.User;
import com.example.bbreda.cardmanager.data.repository.local.login.LoginLocalRepository;
import com.example.bbreda.cardmanager.data.repository.remote.login.LoginRemoteRepository;
import com.example.bbreda.cardmanager.infrastructure.OperationListener;
import com.example.bbreda.cardmanager.infrastructure.OperationResult;

import java.util.List;

public class LoginBusiness {

    private LoginRemoteRepository mLoginRemoteRepository;
    private LoginLocalRepository mLoginLocalRepository;

    public LoginBusiness(LoginRemoteRepository loginRemoteRepository, LoginLocalRepository loginLocalRepository) {
        mLoginRemoteRepository = loginRemoteRepository;
        mLoginLocalRepository = loginLocalRepository;
    }

    public void doLogin(final LoginRequest loginRequest, final OperationListener<LoginResponse> operationListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OperationResult<LoginResponse> result;
                result = mLoginRemoteRepository.doLogin(loginRequest);
                sendCallbackGeneric(result, operationListener);
            }
        }).start();
    }

    public void saveData(final LoginResponse loginResponse, final OperationListener<Boolean> operationListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OperationResult<Boolean> result;
                result = mLoginLocalRepository.saveData(loginResponse);
                sendCallbackGeneric(result, operationListener);
            }
        }).start();
    }

    public void getUser(final OperationListener<User> operationListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OperationResult result;
                result = mLoginLocalRepository.getUser();
                sendCallbackGeneric(result, operationListener);

            }
        }).start();
    }

    public void getCard(final OperationListener<List<Card>> operationListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OperationResult result;
                result = mLoginLocalRepository.getCard();
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

