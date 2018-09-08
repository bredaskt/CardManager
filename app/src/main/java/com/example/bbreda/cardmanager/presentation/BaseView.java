package com.example.bbreda.cardmanager.presentation;

import android.content.Context;

public interface BaseView<T> {

    void setPresenter(T presenter);

    Context getViewContext();

}
