package com.example.bbreda.cardmanager.presentation.carddetails;

import android.content.res.Resources;
import android.support.annotation.Nullable;

import com.example.bbreda.cardmanager.data.model.Card;
import com.example.bbreda.cardmanager.data.model.Extract;
import com.example.bbreda.cardmanager.infrastructure.Constants;

public class CardDetailsWrapper implements CardDetailsContract.CardDetails {

    public enum Type {
        HEADER, ITEM
    }

    private Type mType;
    private Card mCard;
    private Extract mExtract;
    private Resources mResources;

    public CardDetailsWrapper(@Nullable Extract extract, Type type, Card card, Resources resources) {
        mExtract = extract;
        mType = type;
        mCard = card;
        mResources = resources;
    }

    @Override
    public String getImageFlagUrl() {
        return Constants.URL_BASE + mCard.getImageFlag();

    }

    @Override
    public String getCardNumber() {
        return mCard.getCardNumber();

    }

    @Override
    public String getValue() {
        return mExtract.getValue();

    }

    @Override
    public String getSpent() {
        return mExtract.getSpent();

    }

    @Override
    public String getPurchaseData() {
        return mExtract.getDate();

    }

    @Override
    public int getType() {
        return mType.ordinal();

    }

}

