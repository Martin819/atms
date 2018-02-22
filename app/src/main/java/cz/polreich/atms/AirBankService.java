package cz.polreich.atms;

import java.util.List;

import cz.polreich.atms.model.Branch;
import cz.polreich.atms.model.BranchesList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface AirBankService {

    @GET("openapi/public/v1/branches/own")
    Call<BranchesList> getBranchesList(@Header("apikey") String apikey);

/*    @Headers("apikey: " + apikey)*/
    @GET("openapi/public/v1/branches/own/{id}")
    Call<BranchesList> getBranch(@Path("id") String id, @Header("apikey") String apikey);
}
