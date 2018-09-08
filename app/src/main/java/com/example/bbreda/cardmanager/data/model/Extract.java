package com.example.bbreda.cardmanager.data.model;

import com.google.gson.annotations.SerializedName;

public class Extract {

    @SerializedName("date")
    private String date;

    @SerializedName("spent")
    private String spent;

    @SerializedName("value")
    private String value;

    public Extract(String date, String spent, String value) {
        date = date;
        spent = spent;
        value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        date = date;
    }

    public String getSpent() {
        return spent;
    }

    public void setSpent(String spent) {
        spent = spent;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        value = value;
    }
}
