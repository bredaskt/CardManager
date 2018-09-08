package com.example.bbreda.cardmanager.data.repository.local.registration;

import android.content.SharedPreferences;

import com.example.bbreda.cardmanager.data.model.RegistrationResponse;
import com.example.bbreda.cardmanager.infrastructure.OperationResult;
import com.google.gson.Gson;

public class RegistrationLocalRepositoryImpl implements RegistrationLocalRepository{

    private static final String REGISTRATION= "Registration";

    public SharedPreferences mSharedPreferences;

    // construtor
    public RegistrationLocalRepositoryImpl(SharedPreferences sharedPreferences) {

        mSharedPreferences = sharedPreferences;

    }



    public OperationResult<Boolean> saveData(RegistrationResponse registrationResponse) {
        final OperationResult<Boolean> operationResult = new OperationResult<>();

        try {
            SharedPreferences.Editor editor = mSharedPreferences.edit();

            editor.putString(REGISTRATION, new Gson().toJson(registrationResponse));
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
