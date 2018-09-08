package com.example.bbreda.cardmanager.presentation.about;

public class AboutPresenter implements  AboutContract.Presenter {

    private AboutContract.View mView;

    public AboutPresenter(AboutContract.View view) {
        mView = view;
    }

    @Override
    public void onClickAbout() {
        mView.goToAboutScreen();
    }

    @Override
    public void start() {

    }
}
