package com.example.bbreda.cardmanager.data.model;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("name")
    private String name;

    @SerializedName("image_profile")
    private String image_profile;

    public User(String name, String image_profile) {
        this.name = name;
        this.image_profile = image_profile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name;
    }

    public String getImage_profile() {
        return image_profile;
    }

    public void setImage_profile(String image_profile) {
        image_profile = image_profile;
    }
}
