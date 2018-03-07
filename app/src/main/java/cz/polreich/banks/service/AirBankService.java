package cz.polreich.banks.service;

import cz.polreich.banks.model.airBank.ATM;
import cz.polreich.banks.model.airBank.ATMsList;
import cz.polreich.banks.model.airBank.Branch;
import cz.polreich.banks.model.airBank.BranchesList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface AirBankService {

    @GET("openapi/public/v1/branches/own")
    Call<BranchesList> getBranchesList(@Header("apikey") String apikey);

    @GET("openapi/public/v1/branches/own/{id}")
    Call<Branch> getBranch(@Path("id") String id, @Header("apikey") String apikey);

    @GET("openapi/public/v1/atms/own")
    Call<ATMsList> getATMsList(@Header("apikey") String apikey);

    @GET("openapi/public/v1/atms/own/{id}")
    Call<ATM> getATM(@Path("id") String id, @Header("apikey") String apikey);
}
