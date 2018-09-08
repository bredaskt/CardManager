package com.example.bbreda.cardmanager.data;

import com.example.bbreda.cardmanager.data.model.LoginRequest;
import com.example.bbreda.cardmanager.data.model.RegistrationRequest;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Api {

    @POST("login")
    Call<ResponseBody> doLogin(@Body LoginRequest loginRequest);

    @POST("signup")
    Call<ResponseBody> doRequest(@Body RegistrationRequest registrationRequest);


}
