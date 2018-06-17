package cz.polreich.banks.service;

import cz.polreich.banks.model.airBank.AirBankATM;
import cz.polreich.banks.model.airBank.AirBankATMsList;
import cz.polreich.banks.model.airBank.AirBankBranch;
import cz.polreich.banks.model.airBank.AirBankBranchesList;
import cz.polreich.banks.model.erste.ErsteATM;
import cz.polreich.banks.model.erste.ErsteATMsList;
import cz.polreich.banks.model.erste.ErsteBranch;
import cz.polreich.banks.model.erste.ErsteBranchesList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface ErsteService {

    @GET("api/csas/sandbox/v2/places/branches/")
    Call<ErsteBranchesList> getBranchesList(@Header("web-api-key") String apikey);

    @GET("api/csas/sandbox/v2/places/branches/{id}")
    Call<ErsteBranch> getBranch(@Path("id") String id, @Header("web-api-key") String apikey);

    @GET("api/csas/sandbox/v2/places/atms/")
    Call<ErsteATMsList> getATMsList(@Header("web-api-key") String apikey);

    @GET("api/csas/sandbox/v2/places/atms/{id}")
    Call<ErsteATM> getATM(@Path("id") String id, @Header("web-api-key") String apikey);
}
