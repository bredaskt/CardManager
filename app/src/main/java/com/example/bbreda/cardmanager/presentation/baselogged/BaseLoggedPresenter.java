package com.example.bbreda.cardmanager.presentation.baselogged;

import com.example.bbreda.cardmanager.business.LoginBusiness;
import com.example.bbreda.cardmanager.data.ApiManager;
import com.example.bbreda.cardmanager.data.model.User;
import com.example.bbreda.cardmanager.data.repository.SharedPreferenceManager;
import com.example.bbreda.cardmanager.data.repository.local.login.LoginLocalRepositoryImpl;
import com.example.bbreda.cardmanager.data.repository.remote.login.LoginRemoteRepositoryImpl;
import com.example.bbreda.cardmanager.infrastructure.OperationListener;

public class BaseLoggedPresenter implements BaseLoggedContract.Presenter{

    private BaseLoggedContract.View mView;

    private LoginBusiness mLoginBusiness;

    public BaseLoggedPresenter(BaseLoggedContract.View view) {

        mView = view;

        mLoginBusiness = new LoginBusiness(new LoginRemoteRepositoryImpl(ApiManager.getInstance()),
                new LoginLocalRepositoryImpl(SharedPreferenceManager.getInstance(mView.getViewContext())));

    }

    private OperationListener<User> operationListener = new OperationListener<User>() {

        @Override
        public void onOperationSuccess(User user) {

            mView.showDataUser(user);

        }

        @Override
        public void onOperationError(User user) {
            // TODO
        }

    };


    @Override
    public void start() {

        mLoginBusiness.getUser(operationListener);

    }

}
