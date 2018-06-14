package cz.polreich.banks.service;

import cz.polreich.banks.model.airBank.AirBankATM;
import cz.polreich.banks.model.airBank.AirBankATMsList;
import cz.polreich.banks.model.airBank.AirBankBranch;
import cz.polreich.banks.model.airBank.AirBankBranchesList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface AirBankService {

    @GET("openapi/public/v1/branches/own")
    Call<AirBankBranchesList> getBranchesList(@Header("apikey") String apikey);

    @GET("openapi/public/v1/branches/own/{id}")
    Call<AirBankBranch> getBranch(@Path("id") String id, @Header("apikey") String apikey);

    @GET("openapi/public/v1/atms/own")
    Call<AirBankATMsList> getATMsList(@Header("apikey") String apikey);

    @GET("openapi/public/v1/atms/own/{id}")
    Call<AirBankATM> getATM(@Path("id") String id, @Header("apikey") String apikey);
}
