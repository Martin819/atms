package cz.polreich.atms.controller;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import cz.polreich.atms.AirBankService;
import cz.polreich.atms.model.Branch;
import cz.polreich.atms.model.BranchesList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Martin on 22.02.2018.
 */

public class Controller implements Callback<BranchesList> {

    private static final String BASE_URL = "https://api.airbank.cz/";
    private static final String DEBUG_TAG = "Controller";

    public void start(String apikey) {
        Log.d(DEBUG_TAG, "Controller.start called");
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        AirBankService airBankService = retrofit.create(AirBankService.class);

        Call<BranchesList> call = airBankService.getBranchesList(apikey);
        call.enqueue(this);

    }

    @Override
    public void onResponse(@NonNull Call<BranchesList> call, @NonNull Response<BranchesList> response) {
        if(response.isSuccessful()) {
            BranchesList branchesList = response.body();
            if (branchesList != null) {
                branchesList.getBranches().forEach(branch -> System.out.println(branch.getName()));
            }
        } else {
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(@NonNull Call<BranchesList> call, @NonNull Throwable t) {
        t.printStackTrace();
    }
}
