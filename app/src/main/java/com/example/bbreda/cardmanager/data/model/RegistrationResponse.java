package com.example.bbreda.cardmanager.data.model;

import com.google.gson.annotations.SerializedName;

public class RegistrationResponse {

    @SerializedName("user")
    private User mUser;

    public RegistrationResponse(User mUser) {
        this.mUser = mUser;
    }

}
