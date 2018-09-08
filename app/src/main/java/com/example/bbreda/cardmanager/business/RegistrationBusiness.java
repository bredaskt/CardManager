package com.example.bbreda.cardmanager.business;

import android.os.Handler;
import android.os.Looper;

import com.example.bbreda.cardmanager.data.model.RegistrationRequest;
import com.example.bbreda.cardmanager.data.model.RegistrationResponse;
import com.example.bbreda.cardmanager.data.repository.local.registration.RegistrationLocalRepository;
import com.example.bbreda.cardmanager.data.repository.remote.registration.RegistrationRemoteRepository;
import com.example.bbreda.cardmanager.infrastructure.OperationListener;
import com.example.bbreda.cardmanager.infrastructure.OperationResult;

public class RegistrationBusiness {

    private RegistrationLocalRepository mRegistrationLocalRepository;
    private RegistrationRemoteRepository mRegistrationRemoteRepository;

    public RegistrationBusiness(RegistrationRemoteRepository registrationRemoteRepository, RegistrationLocalRepository registrationLocalRepository) {
        mRegistrationRemoteRepository = registrationRemoteRepository;
        mRegistrationLocalRepository = registrationLocalRepository;
    }

    public void doRequest(final RegistrationRequest registrationRequest, final OperationListener<RegistrationResponse> operationListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OperationResult<RegistrationResponse> result;
                result = mRegistrationRemoteRepository.doRegistration(registrationRequest);
                sendCallbackGeneric(result, operationListener);
            }
        }).start();
    }

    public void saveData (final RegistrationResponse registrationResponse, final OperationListener<RegistrationResponse> operationListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OperationResult<Boolean> result;
                result = mRegistrationLocalRepository.saveData(registrationResponse);
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
