package com.example.bbreda.cardmanager.presentation.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.bbreda.cardmanager.R;
import com.example.bbreda.cardmanager.presentation.login.LoginActivity;

public class SplashFragment extends Fragment implements SplashContract.View {

    private SplashContract.Presenter mPresenter;

    public static SplashFragment newInstance() {
        return new SplashFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash, container, false);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.start();
    }

    @Override
    public void setPresenter(SplashContract.Presenter presenter) {
        
        mPresenter = presenter;

    }

    @Override
    public Context getViewContext() {
        return getContext();
    }


    @Override
    public void goToLoginScreen() {
        startActivity(new Intent(getContext(), LoginActivity.class));
        getActivity().finish();

    }
}
