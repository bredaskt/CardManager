package com.example.bbreda.cardmanager.presentation.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
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

    ProgressDialog progressDoalog;

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
            progressDoalog = new ProgressDialog(getViewContext());
            progressDoalog.setMax(100);
            progressDoalog.setMessage("Verificando seus dados de acesso, aguarde por favor...");
            progressDoalog.setTitle("CardManager");
            progressDoalog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDoalog.show();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (progressDoalog.getProgress() <= progressDoalog
                                .getMax()) {
                            Thread.sleep(200);
                            handle.sendMessage(handle.obtainMessage());
                            if (progressDoalog.getProgress() == progressDoalog
                                    .getMax()) {
                                progressDoalog.dismiss();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        Handler handle = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                progressDoalog.incrementProgressBy(45);
            }
        };

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
        Toast.makeText(getContext(), R.string.string_fields_error_login, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessageNetworkError() {
        Toast.makeText(getContext(), R.string.string_network_error, Toast.LENGTH_SHORT).show();
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

