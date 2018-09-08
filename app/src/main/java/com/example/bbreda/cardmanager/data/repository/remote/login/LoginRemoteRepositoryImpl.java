package com.example.bbreda.cardmanager.data.repository.remote.login;

import com.example.bbreda.cardmanager.data.Api;
import com.example.bbreda.cardmanager.data.model.LoginRequest;
import com.example.bbreda.cardmanager.data.model.LoginResponse;
import com.example.bbreda.cardmanager.infrastructure.OperationResult;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;

public class LoginRemoteRepositoryImpl implements LoginRemoteRepository {

    private Api mApi;

    public LoginRemoteRepositoryImpl(Api api) {
        mApi = api;
    }

    @Override
    public OperationResult<LoginResponse> doLogin(LoginRequest loginRequest) {
        final OperationResult<LoginResponse> operationResult = new OperationResult<>();

        Call<ResponseBody> call = mApi.doLogin(loginRequest);

        try {
            ResponseBody response = call.execute().body();

            if (response != null) {
                JSONObject jsonObject = new JSONObject(response.string());
                Gson gson = new Gson();
                LoginResponse loginResponse = gson.fromJson(jsonObject.toString(), LoginResponse.class);
                operationResult.setOperationType(OperationResult.OperationType.SUCCESS);
                operationResult.setResult(loginResponse);
            } else {
                operationResult.setOperationType(OperationResult.OperationType.ERROR);
            }
        } catch (IOException e) {
            e.printStackTrace();
            operationResult.setOperationType(OperationResult.OperationType.ERROR);
        } catch (JSONException e) {
            e.printStackTrace();
            operationResult.setOperationType(OperationResult.OperationType.ERROR);
        }
        return operationResult;
    }
}
