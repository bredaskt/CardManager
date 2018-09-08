package com.example.bbreda.cardmanager.presentation.registration;

import android.widget.Toast;

import com.example.bbreda.cardmanager.business.RegistrationBusiness;
import com.example.bbreda.cardmanager.data.ApiManager;
import com.example.bbreda.cardmanager.data.model.RegistrationRequest;
import com.example.bbreda.cardmanager.data.model.RegistrationResponse;
import com.example.bbreda.cardmanager.data.repository.SharedPreferenceManager;
import com.example.bbreda.cardmanager.data.repository.local.registration.RegistrationLocalRepositoryImpl;
import com.example.bbreda.cardmanager.data.repository.remote.registration.RegistrationRemoteRepositoryImpl;
import com.example.bbreda.cardmanager.infrastructure.OperationListener;

public class RegistrationPresenter implements RegistrationContract.Presenter {

    private RegistrationContract.View mView;
    private RegistrationBusiness mRegistrationBusiness;

    public RegistrationPresenter(RegistrationContract.View view) {
        mView = view;

        mRegistrationBusiness = new RegistrationBusiness(new RegistrationRemoteRepositoryImpl(ApiManager.getInstance()),
                new RegistrationLocalRepositoryImpl(SharedPreferenceManager.getInstance(mView.getViewContext())));

    }

    private OperationListener<RegistrationResponse> operationListener = new OperationListener<RegistrationResponse>() {

        @Override
        public void onOperationSuccess(RegistrationResponse registrationResponse) {
            mRegistrationBusiness.saveData(registrationResponse, operationListener);
            Toast.makeText(mView.getViewContext(), "success", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onOperationError(RegistrationResponse registrationResponse) {
            Toast.makeText(mView.getViewContext(), "error", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void onClickReadCardFromCam() {
        // TODO
    }

    @Override
    public void onClickRequestAccount() {
        mRegistrationBusiness.doRequest(new RegistrationRequest(), operationListener);
    }

    @Override
    public void start() {
        // TODO
    }
}
