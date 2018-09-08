package com.example.bbreda.cardmanager.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

    public class LoginResponse {

    @SerializedName("user")
    private User mUser;

    @SerializedName("cards")
    private List<Card> mCards;

    public User getmUser() {
        return this.mUser;
    }

    public void setmUser(User mUser) {
        this.mUser = mUser;
    }

    public List<Card> getmCards() {
        return this.mCards;
    }

    public void setmCards(List<Card> mCards) {
        this.mCards = mCards;
    }

}
