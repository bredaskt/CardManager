package com.example.bbreda.cardmanager.data.repository.remote.registration;

import com.example.bbreda.cardmanager.data.model.RegistrationRequest;
import com.example.bbreda.cardmanager.data.model.RegistrationResponse;
import com.example.bbreda.cardmanager.infrastructure.OperationResult;

public interface RegistrationRemoteRepository {

    OperationResult<RegistrationResponse> doRegistration(RegistrationRequest registrationRequest);

}
