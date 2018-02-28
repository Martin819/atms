package cz.polreich.atms.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cz.polreich.atms.adapter.ATMsAdapter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ATMsController {
    private ATMsAdapter mAdapter;
    private static final String BASE_URL = "https://api.airbank.cz/";
    private static final String DEBUG_TAG_INFO = "[INFO     ] Controller";
    private static final String DEBUG_TAG_ERROR = "[    ERROR] Controller";
    private static final String DEBUG_TAG_WARNING = "[ WARNING ] Controller";

    private Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
}
