package com.example.bbreda.cardmanager.data;

import com.example.bbreda.cardmanager.infrastructure.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class ApiManager {

    public static Api getInstance () {
         return new Retrofit.Builder()
                    .baseUrl(Constants.URL_BASE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(Api.class);

    }

}
