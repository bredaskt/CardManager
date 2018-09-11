package com.example.bbreda.cardmanager.presentation.login;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;
import com.example.bbreda.cardmanager.R;
import com.example.bbreda.cardmanager.business.LoginBusiness;
import com.example.bbreda.cardmanager.data.ApiManager;
import com.example.bbreda.cardmanager.data.model.LoginRequest;
import com.example.bbreda.cardmanager.data.model.LoginResponse;
import com.example.bbreda.cardmanager.data.repository.SharedPreferenceManager;
import com.example.bbreda.cardmanager.data.repository.local.login.LoginLocalRepositoryImpl;
import com.example.bbreda.cardmanager.data.repository.remote.login.LoginRemoteRepositoryImpl;
import com.example.bbreda.cardmanager.infrastructure.OperationListener;

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View mView;
    private LoginBusiness mLoginBusiness;

    public LoginPresenter(LoginContract.View view) {
        mView = view;

        mLoginBusiness = new LoginBusiness(new LoginRemoteRepositoryImpl(ApiManager.getInstance()),
                new LoginLocalRepositoryImpl(SharedPreferenceManager.getInstance(mView.getViewContext())));
    }

    private OperationListener<LoginResponse> loginOperationListener = new OperationListener<LoginResponse>() {
        @Override
        public void onOperationSuccess(LoginResponse loginResponse) {
            mView.goToHomeScreen();
            mLoginBusiness.saveData(loginResponse, operationListenerPersistData);
        }

        @Override
        public void onOperationError(LoginResponse loginResponse) {
            Toast.makeText(mView.getViewContext(), R.string.string_algo_deu_errado_login, Toast.LENGTH_SHORT).show();
        }
    };

    private OperationListener<Boolean> operationListenerPersistData = new OperationListener<Boolean>() {

        @Override
        public void onOperationSuccess(Boolean isPersisted) {
            if (isPersisted) {
                mView.goToHomeScreen();
            }
        }

        @Override
        public void onOperationError(Boolean isPersisted) {
            // TODO
        }
    };

    @Override
    public void onButtonClickLogin(String email, String password) {
        if (!checkNetworkAvailable()) {
            mView.showMessageNetworkError();
        } else {
            if (!validateEmptyLogin(email)) {
                mView.showMessageErrorFilledFormLoginGeneric();
            } else {
                if (!validateEmptyPassword(password)) {
                    mView.showMessageErrorFilledFormPasswordGeneric();
                } else {
                    mLoginBusiness.doLogin(new LoginRequest(email, password), loginOperationListener);
                }
            }
        }
    }

    private boolean checkNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) mView.getViewContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        return (wifi.isAvailable() && wifi.isConnectedOrConnecting() || (mobile.isAvailable() && mobile.isConnectedOrConnecting()));
    }

//    private boolean validateEmptyLogin(String email, String password) {
//        if ((email.isEmpty()) || (password.isEmpty())) {
//            mView.showMessageErrorFilledFormLoginGeneric();
//            return false;
//        } else {
//            return true;
//        }
//    }

//    private boolean validateEmptyLogin(String email, String password) {
//        if ((email.isEmpty()) || (password.isEmpty())) {
//            mView.showMessageErrorFilledFormLoginGeneric();
//            return false;
//        } else {
//            return true;
//        }
//    }

    private boolean validateEmptyLogin(String email) {
        if (email.isEmpty()) {
            mView.showMessageErrorFilledFormLoginGeneric();
            return false;
        } else {
            return true;
        }
    }

    private boolean validateEmptyPassword (String password){
        if (password.isEmpty()) {
            mView.showMessageErrorFilledFormLoginGeneric();
            return false;
        } else {
            return true;
        }
    }


    @Override
    public void onButtonClickToRegistrationScreen() {
        mView.goToRegistrationScreen();
    }

    @Override
    public void start() {
        // TODO
    }
}

