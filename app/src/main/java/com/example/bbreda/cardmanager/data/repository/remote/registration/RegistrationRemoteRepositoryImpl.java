package com.example.bbreda.cardmanager.data.repository.remote.registration;

import com.example.bbreda.cardmanager.data.Api;
import com.example.bbreda.cardmanager.data.model.RegistrationRequest;
import com.example.bbreda.cardmanager.data.model.RegistrationResponse;
import com.example.bbreda.cardmanager.infrastructure.OperationResult;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Call;

public class RegistrationRemoteRepositoryImpl implements RegistrationRemoteRepository {

    private Api mApi;

    public RegistrationRemoteRepositoryImpl(Api api) {
        mApi = api;
    }

    public OperationResult<RegistrationResponse> doRegistration(RegistrationRequest registrationRequest) {
        final OperationResult<RegistrationResponse> operationResult = new OperationResult<>();

        Call<ResponseBody> call = mApi.doRequest(registrationRequest);

        try {
            ResponseBody response = call.execute().body();

            if (response != null) {
                JSONObject jsonObject = new JSONObject(response.string());
                Gson gson = new Gson();
                RegistrationResponse registrationResponse = gson.fromJson(jsonObject.toString(), RegistrationResponse.class);
                operationResult.setOperationType(OperationResult.OperationType.SUCCESS);
                operationResult.setResult(registrationResponse);
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
