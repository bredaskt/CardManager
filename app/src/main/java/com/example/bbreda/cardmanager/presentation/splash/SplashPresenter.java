package com.example.bbreda.cardmanager.presentation.splash;

import android.os.Handler;

public class SplashPresenter implements SplashContract.Presenter {

    private SplashContract.View mView;

    public SplashPresenter(SplashContract.View view) {
        mView = view;
    }

    private void startTimerLaunchSplash() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mView.goToLoginScreen();
            }
        }, 1400);
    }

    @Override
    public void start() {
        startTimerLaunchSplash();
    }

}
