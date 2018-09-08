package com.example.bbreda.cardmanager.infrastructure;

public class OperationResult<TResult> {

    private TResult result;
    private OperationResult.OperationType operationType;

    public enum OperationType{
        ERROR, SUCCESS
    }

    public TResult getResult() {
        return this.result;
    }

    public void setResult(TResult result) {
        this.result = result;
    }

    public OperationResult.OperationType getOperationType() {
        return this.operationType;
    }

    public void setOperationType(OperationResult.OperationType operationType) {
        this.operationType = operationType;
    }
}
