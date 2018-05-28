package cz.polreich.banks.controller;

import android.app.Activity;
import android.app.Fragment;
import android.arch.persistence.room.Transaction;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import cz.polreich.banks.AppDatabase;
import cz.polreich.banks.R;
import cz.polreich.banks.adapter.ATMsAdapter;
import cz.polreich.banks.dao.ATMDao;
import cz.polreich.banks.dao.BranchDao;
import cz.polreich.banks.model.airBank.ATM;
import cz.polreich.banks.model.airBank.ATMsList;
import cz.polreich.banks.model.airBank.Branch;
import cz.polreich.banks.service.AirBankService;
import cz.polreich.banks.adapter.BranchesAdapter;
import cz.polreich.banks.model.airBank.BranchesList;
import cz.polreich.banks.utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AirBankController {

    private BranchesAdapter branchesAdapter;
    private ATMsAdapter atmsAdapter;
    private Branch retBranch;
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
            Call<BranchesList> branchesListCall = airBankService.getBranchesList(apikey);
            branchesListCall.enqueue(new Callback<BranchesList>() {
                @Override
                public void onResponse(@NonNull Call<BranchesList> call, @NonNull Response<BranchesList> response) {
                    Log.d(DEBUG_TAG_INFO, "getBranchesList.onResponse called");
                    Log.d(DEBUG_TAG_INFO, response.toString());
                    if (response.isSuccessful()) {
                        Log.d(DEBUG_TAG_INFO, "getBranchesList - response.isSuccessful()");
                        BranchesList fetchedList = response.body();
                        if (fetchedList != null) {
                            new Thread(() -> {
                                branchDao.insertBranches(fetchedList.getBranches());
                                List<Branch> branchesList = branchDao.getAllBranches();
                                activity.runOnUiThread(() -> branchesAdapter.updateItems(branchesList));
                                Log.d(DEBUG_TAG_INFO, "Branches - successfully fetched and updated");
                            }).start();

                        }
                    } else {
                        Log.e(DEBUG_TAG_ERROR, response.errorBody().toString());
                    }
                }

                @Override
                public void onFailure(@NonNull Call<BranchesList> call, @NonNull Throwable t) {
                    t.printStackTrace();
                }
            });
        } else {
            new Thread(() -> {
                List<Branch> branchesList = branchDao.getAllBranches();
                Log.d(DEBUG_TAG_INFO, "Branches - successfully updated from DB");
                activity.runOnUiThread(() -> branchesAdapter.updateItems(branchesList));
            }).start();
        }
    }

    public void getBranch(String apikey, String branchId) {
        Log.d(DEBUG_TAG_INFO, "AirBankController.getBranch called");
        mBranchType = (TextView) activity.findViewById(R.id.branch_type);
        mBranchAddress = (TextView) activity.findViewById(R.id.branch_address);
        mBranchPhone = (TextView) activity.findViewById(R.id.branch_phone);
        mBranchImage = (ImageView) activity.findViewById(R.id.branch_imageView);

        Call<Branch> branchCall = airBankService.getBranch(branchId, apikey);
        branchCall.enqueue(new Callback<Branch>() {
            @Override
            public void onResponse(@NonNull Call<Branch> call, @NonNull Response<Branch> response) {
                Log.d(DEBUG_TAG_INFO, "getBranch.onResponse called");
                Log.d(DEBUG_TAG_INFO, response.toString());
                if(response.isSuccessful()) {
                    Log.d(DEBUG_TAG_INFO, "getBranch - response.isSuccessful()");
                    Branch branch = response.body();
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
            public void onFailure(@NonNull Call<Branch> call, @NonNull Throwable t) {
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
            Call<ATMsList> atmsListCall = airBankService.getATMsList(apikey);
            atmsListCall.enqueue(new Callback<ATMsList>() {
                @Override
                public void onResponse(@NonNull Call<ATMsList> call, @NonNull Response<ATMsList> response) {
                    Log.d(DEBUG_TAG_INFO, "getATMsList.onResponse called");
                    Log.d(DEBUG_TAG_INFO, response.toString());
                    if (response.isSuccessful()) {
                        Log.d(DEBUG_TAG_INFO, "getATMsList - response.isSuccessful()");
                        ATMsList fetchedList = response.body();
                        if (fetchedList != null) {
                            new Thread(() -> {
                                atmDao.insertATMs(fetchedList.getAtms());
                                List<ATM> atmsList = atmDao.getAllATMs();
                                activity.runOnUiThread(() -> atmsAdapter.updateItems(atmsList));
                                Log.d(DEBUG_TAG_INFO, "ATMs - successfully fetched and updated");
                            }).start();
                        }
                    } else {
                        Log.e(DEBUG_TAG_ERROR, response.errorBody().toString());
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ATMsList> call, @NonNull Throwable t) {
                    t.printStackTrace();
                }
            });
        } else {
            new Thread(() -> {
                List<ATM> atmsList = atmDao.getAllATMs();
                Log.d(DEBUG_TAG_INFO, "ATMs - successfully updated from DB");
                activity.runOnUiThread(() -> atmsAdapter.updateItems(atmsList));
            }).start();
        }
    }

    public void getATM(String apikey, String ATMId) {
        Log.d(DEBUG_TAG_INFO, "AirBankController.getATM called");
        mATMAddress = (TextView) activity.findViewById(R.id.atm_address);
        String atmNonstopTitle = activity.getResources().getString(R.string.atm_nonstopTitle);
        Call<ATM> ATMCall = airBankService.getATM(ATMId, apikey);
        ATMCall.enqueue(new Callback<ATM>() {
            @Override
            public void onResponse(@NonNull Call<ATM> call, @NonNull Response<ATM> response) {
                Log.d(DEBUG_TAG_INFO, "getATM.onResponse called");
                Log.d(DEBUG_TAG_INFO, response.toString());
                if(response.isSuccessful()) {
                    Log.d(DEBUG_TAG_INFO, "getATM - response.isSuccessful()");
                    ATM atm = response.body();
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
            public void onFailure(@NonNull Call<ATM> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });
    }
}

