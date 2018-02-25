package cz.polreich.atms.controller;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cz.polreich.atms.AirBankService;
import cz.polreich.atms.BranchesAdapter;
import cz.polreich.atms.model.airBank.BranchesList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Martin on 22.02.2018.
 */

public class Controller implements Callback<BranchesList> {

    private BranchesAdapter mAdapter;
    private static final String BASE_URL = "https://api.airbank.cz/";
    private static final String DEBUG_TAG_INFO = "[INFO     ] Controller";
    private static final String DEBUG_TAG_ERROR = "[    ERROR] Controller";

    public void start(String apikey, BranchesAdapter mAdapter) {
        this.mAdapter = mAdapter;
        Log.d(DEBUG_TAG_INFO, "Controller.start called");
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
        Log.d(DEBUG_TAG_INFO, "Controller.onRespose called");
        Log.d(DEBUG_TAG_INFO, response.toString());
        if(response.isSuccessful()) {
            Log.d(DEBUG_TAG_INFO, "Controller - response.isSuccessful()");
            BranchesList branchesList = response.body();
            if (branchesList != null) {
                Log.d(DEBUG_TAG_INFO, "Controller - branchesList != null");
                branchesList.getBranches().forEach(branch -> Log.d(DEBUG_TAG_INFO, branch.getName()));
                mAdapter.updateItems(branchesList.getBranches());
            }
        } else {
            Log.d(DEBUG_TAG_ERROR, response.errorBody().toString());
        }
    }

    @Override
    public void onFailure(@NonNull Call<BranchesList> call, @NonNull Throwable t) {
        t.printStackTrace();
    }
}
