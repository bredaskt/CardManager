package com.example.bbreda.cardmanager.presentation.login;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bbreda.cardmanager.R;
import com.example.bbreda.cardmanager.presentation.home.HomeActivity;
import com.example.bbreda.cardmanager.presentation.registration.RegistrationActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginFragment extends Fragment implements LoginContract.View {

    @BindView(R.id.btn_login)
    Button mButtonLogin;

    @BindView(R.id.btn_registration)
    Button mButtonGoToRegistration;

    @BindView(R.id.et_email_log)
    EditText mEmail;

    @BindView(R.id.et_senha)
    EditText mPassword;

    private LoginContract.Presenter mPresenter;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }

    // clique do botao (listener) para abrir a tela apos clicar em login
    private View.OnClickListener mButtonLoginListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mPresenter.onButtonClickLogin(mEmail.getText().toString(), mPassword.getText().toString());
        }
    };

    // clique do botao (listener) para abrir a tela apos clicar em solicitar cadastro
    private View.OnClickListener mButtonRegistrationListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mPresenter.onButtonClickToRegistrationScreen();
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mButtonLogin.setOnClickListener(mButtonLoginListener);
        mButtonGoToRegistration.setOnClickListener(mButtonRegistrationListener);
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.start();
    }

    @Override
    public void showMessageErrorFilledFormLoginGeneric() {
        Toast.makeText(getContext(), "Login inválido! Verifique os dados e tente novamente!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessageNetworkError() {
        Toast.makeText(getContext(), "Você está sem conexão com a Internet! Verifique e tente novamente mais tarde!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goToHomeScreen() {
        startActivity(new Intent(getContext(), HomeActivity.class));
        getActivity().finish();
    }

    @Override
    public void goToRegistrationScreen() {
        startActivity(new Intent(getContext(), RegistrationActivity.class));
        getActivity().finish();
    }

    @Override
    public Context getViewContext() { return getContext(); }

}

