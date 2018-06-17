package cz.polreich.banks.controller;

import android.app.Activity;
import android.app.Fragment;
import android.location.Location;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import cz.polreich.banks.AppDatabase;
import cz.polreich.banks.R;
import cz.polreich.banks.adapter.ATMsAdapter;
import cz.polreich.banks.dao.ATMDao;
import cz.polreich.banks.dao.BranchDao;
import cz.polreich.banks.model.UniATM;
import cz.polreich.banks.model.UniATMsList;
import cz.polreich.banks.model.UniBranch;
import cz.polreich.banks.model.UniBranchesList;
import cz.polreich.banks.model.airBank.AirBankATM;
import cz.polreich.banks.model.airBank.AirBankATMsList;
import cz.polreich.banks.model.airBank.AirBankBranch;
import cz.polreich.banks.service.AirBankService;
import cz.polreich.banks.adapter.BranchesAdapter;
import cz.polreich.banks.model.airBank.AirBankBranchesList;
import cz.polreich.banks.utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AirBankController {

    private BranchesAdapter branchesAdapter;
    private ATMsAdapter atmsAdapter;
    private AirBankBranch retBranch;
    private static final String BASE_URL = "https://api.airbank.cz/";
    private final String DEBUG_TAG_INFO = "[INFO     ] " + this.getClass().getSimpleName();
    private final String DEBUG_TAG_ERROR = "[    ERROR] " + this.getClass().getSimpleName();
    private final String DEBUG_TAG_WARNING = "[ WARNING ] " + this.getClass().getSimpleName();
    private Activity activity;
    private Fragment fragment;
    private TextView mBranchType;
    private TextView mBranchPhone;
    private TextView mBranchAddress;
    private TextView mATMAddress;
    private ImageView mBranchImage;
    private AppDatabase database;
    private BranchDao branchDao;
    private ATMDao atmDao;

    private Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    private AirBankService airBankService = retrofit.create(AirBankService.class);
    public AirBankController(Activity activity) {
        this.activity = activity;
    }

    public void getBranchesList(String apikey, BranchesAdapter branchesAdapter, boolean forceFetch) {
        this.branchesAdapter = branchesAdapter;
        database = AppDatabase.getInstance(activity.getApplicationContext());
        branchDao = database.branchDao();
        if (forceFetch) {
            Log.d(DEBUG_TAG_INFO, "AirBankController.getBranchesList called");
            Call<AirBankBranchesList> branchesListCall = airBankService.getBranchesList(apikey);
            branchesListCall.enqueue(new Callback<AirBankBranchesList>() {
                @Override
                public void onResponse(@NonNull Call<AirBankBranchesList> call, @NonNull Response<AirBankBranchesList> response) {
                    Log.d(DEBUG_TAG_INFO, "getBranchesList.onResponse called");
                    Log.d(DEBUG_TAG_INFO, response.toString());
                    if (response.isSuccessful()) {
                        Log.d(DEBUG_TAG_INFO, "getBranchesList - response.isSuccessful()");
                        AirBankBranchesList fetchedList = response.body();
                        if (fetchedList != null) {
                            new Thread(() -> {
                                UniBranchesList uniBranchesList = new UniBranchesList(fetchedList.getBranches());
                                branchDao.insertBranches(uniBranchesList.getBranches());
                                List<UniBranch> branchesList = branchDao.getAllBranches();
                                activity.runOnUiThread(() -> branchesAdapter.updateItems(branchesList));
                                Log.d(DEBUG_TAG_INFO, "Branches - successfully fetched and updated");
                            }).start();

                        }
                    } else {
                        Log.e(DEBUG_TAG_ERROR, response.errorBody().toString());
                    }
                }

                @Override
                public void onFailure(@NonNull Call<AirBankBranchesList> call, @NonNull Throwable t) {
                    t.printStackTrace();
                }
            });
        } else {
            new Thread(() -> {
                List<UniBranch> branchesList = branchDao.getAllBranches();
                Log.d(DEBUG_TAG_INFO, "Branches - successfully updated from DB");
                activity.runOnUiThread(() -> branchesAdapter.updateItems(branchesList));
            }).start();
        }
    }

    public void getBranch(String apikey, String branchId) {
        Log.d(DEBUG_TAG_INFO, "AirBankController.getBranch called");
        mBranchType = activity.findViewById(R.id.branch_type);
        mBranchAddress = activity.findViewById(R.id.branch_address);
        mBranchPhone = activity.findViewById(R.id.branch_phone);
        mBranchImage = activity.findViewById(R.id.branch_imageView);

        Call<AirBankBranch> branchCall = airBankService.getBranch(branchId, apikey);
        branchCall.enqueue(new Callback<AirBankBranch>() {
            @Override
            public void onResponse(@NonNull Call<AirBankBranch> call, @NonNull Response<AirBankBranch> response) {
                Log.d(DEBUG_TAG_INFO, "getBranch.onResponse called");
                Log.d(DEBUG_TAG_INFO, response.toString());
                if(response.isSuccessful()) {
                    Log.d(DEBUG_TAG_INFO, "getBranch - response.isSuccessful()");
                    AirBankBranch branch = response.body();
                    if (branch != null) {
                        Log.d(DEBUG_TAG_INFO, "getBranch - branch != null");
                        Log.d(DEBUG_TAG_INFO, "getBranch - response: " + branch.getName());
                        activity.setTitle(branch.getName());
                        mBranchType.setText(R.string.title_branch);
                        mBranchAddress.setText(utils.getFullAddress(branch.getAddress()));
                        mBranchPhone.setText(utils.getAllPhones(branch.getPhones()));
                        utils.getBranchOpeningHours(branch.getOpeningHours(), activity);
                        String branchServices[] = branch.getServices();
                        Log.d(DEBUG_TAG_INFO, "Found " + branchServices.length + " branch services.");
                        utils.setServices(branchServices, activity);
                        String branchPictures[] = branch.getPictures();
                        Log.d(DEBUG_TAG_INFO, "Found " + branchPictures.length + " branch pictures.");
                        if (branchPictures.length > 0){
                            Glide.with(activity)
                                    .load(branchPictures[0])
                                    .placeholder(R.drawable.ic_home_black_24dp)
                                    .into(mBranchImage);
                        }
                    } else {
                        Log.e(DEBUG_TAG_ERROR, "branch = null");
                    }
                }
                else {
                    Log.e(DEBUG_TAG_ERROR, response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(@NonNull Call<AirBankBranch> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void getATMsList(String apikey, ATMsAdapter atmsAdapter, boolean forceFetch) {
        this.atmsAdapter = atmsAdapter;
        database = AppDatabase.getInstance(activity.getApplicationContext());
        atmDao = database.atmDao();
        if (forceFetch) {
            Log.d(DEBUG_TAG_INFO, "AirBankController.getATMsList called");
            Call<AirBankATMsList> atmsListCall = airBankService.getATMsList(apikey);
            atmsListCall.enqueue(new Callback<AirBankATMsList>() {
                @Override
                public void onResponse(@NonNull Call<AirBankATMsList> call, @NonNull Response<AirBankATMsList> response) {
                    Log.d(DEBUG_TAG_INFO, "getATMsList.onResponse called");
                    Log.d(DEBUG_TAG_INFO, response.toString());
                    if (response.isSuccessful()) {
                        Log.d(DEBUG_TAG_INFO, "getATMsList - response.isSuccessful()");
                        AirBankATMsList fetchedList = response.body();
                        if (fetchedList != null) {
                            new Thread(() -> {
                                UniATMsList uniATMsList = new UniATMsList(fetchedList.getAtms());
                                atmDao.insertATMs(uniATMsList.getAtms());
                                List<UniATM> atmsList = atmDao.getAllATMs();
                                activity.runOnUiThread(() -> atmsAdapter.updateItems(atmsList));
                                Log.d(DEBUG_TAG_INFO, "ATMs - successfully fetched and updated");
                            }).start();
                        }
                    } else {
                        Log.e(DEBUG_TAG_ERROR, response.errorBody().toString());
                    }
                }

                @Override
                public void onFailure(@NonNull Call<AirBankATMsList> call, @NonNull Throwable t) {
                    t.printStackTrace();
                }
            });
        } else {
            new Thread(() -> {
                List<UniATM> atmsList = atmDao.getAllATMs();
                Log.d(DEBUG_TAG_INFO, "ATMs - successfully updated from DB");
                activity.runOnUiThread(() -> atmsAdapter.updateItems(atmsList));
            }).start();
        }
    }

    public void getATM(String apikey, String ATMId) {
        Log.d(DEBUG_TAG_INFO, "AirBankController.getATM called");
        mATMAddress = activity.findViewById(R.id.atm_address);
        String atmNonstopTitle = activity.getResources().getString(R.string.atm_nonstopTitle);
        Call<AirBankATM> ATMCall = airBankService.getATM(ATMId, apikey);
        ATMCall.enqueue(new Callback<AirBankATM>() {
            @Override
            public void onResponse(@NonNull Call<AirBankATM> call, @NonNull Response<AirBankATM> response) {
                Log.d(DEBUG_TAG_INFO, "getATM.onResponse called");
                Log.d(DEBUG_TAG_INFO, response.toString());
                if(response.isSuccessful()) {
                    Log.d(DEBUG_TAG_INFO, "getATM - response.isSuccessful()");
                    AirBankATM atm = response.body();
                    if (atm != null) {
                        Log.d(DEBUG_TAG_INFO, "getATM - branch != null");
                        Log.d(DEBUG_TAG_INFO, "getATM - response: " + utils.getFullAddress(atm.getAddress()));
                        activity.setTitle(R.string.title_atm);
                        mATMAddress.setText(utils.getFullAddress(atm.getAddress()));
                        utils.getATMWithdrawalOpeningHours(atm.getOpeningHoursWithdrawal(), activity);
                        utils.getATMDepositOpeningHours(atm.getOpeningHoursDeposit(), activity);
                    } else {
                        Log.e(DEBUG_TAG_ERROR, "atm = null");
                    }
                }
                else {
                    Log.e(DEBUG_TAG_ERROR, response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(@NonNull Call<AirBankATM> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });
    }
}

