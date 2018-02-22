package cz.polreich.atms;

import java.util.List;

import cz.polreich.atms.model.Branch;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface AirBankService {

    @Headers("apikey: ")
    @GET("openapi/public/v1/branches/own/{id}")
    Call<List<Branch>> listBranches(@Path("id") String id);
}
