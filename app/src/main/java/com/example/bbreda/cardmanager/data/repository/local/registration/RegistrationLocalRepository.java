package com.example.bbreda.cardmanager.data.repository.local.registration;

import com.example.bbreda.cardmanager.data.model.RegistrationResponse;
import com.example.bbreda.cardmanager.infrastructure.OperationResult;

public interface RegistrationLocalRepository {


    OperationResult<Boolean> saveData (RegistrationResponse registrationResponse);

}
