package com.example.bbreda.cardmanager.infrastructure;

public abstract class OperationListener<TResult> {

    public abstract void onOperationSuccess(TResult result);

    public abstract void onOperationError(TResult result);

}
