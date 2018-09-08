package com.example.bbreda.cardmanager.presentation.carddetails;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.example.bbreda.cardmanager.presentation.BasePresenter;
import com.example.bbreda.cardmanager.presentation.BaseView;

import java.io.IOException;
import java.util.List;

public interface CardDetailsContract {

    interface View extends BaseView<Presenter>{

        void showCardDetails(List<CardDetailsWrapper> extractList);

    }

    interface Presenter extends BasePresenter {

    }

    interface CardDetails {

        String getImageFlagUrl();

        String getCardNumber();

        String getValue();

        String getSpent();

        String getPurchaseData();

        int getType();


    }
}
