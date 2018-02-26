package cz.polreich.atms.controller;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.w3c.dom.Text;

import cz.polreich.atms.R;
import cz.polreich.atms.model.airBank.Branch;
import cz.polreich.atms.model.airBank.OpeningHoursDay;
import cz.polreich.atms.service.AirBankService;
import cz.polreich.atms.BranchesAdapter;
import cz.polreich.atms.model.airBank.BranchesList;
import cz.polreich.atms.utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Martin on 22.02.2018.
 */

public class BranchController {

    private BranchesAdapter mAdapter;
    private Branch retBranch;
    private static final String BASE_URL = "https://api.airbank.cz/";
    private static final String DEBUG_TAG_INFO = "[INFO     ] BranchController";
    private static final String DEBUG_TAG_ERROR = "[    ERROR] BranchController";
    private Activity activity;
    private TextView mBranchName;
    private TextView mBranchPhone;
    private TextView mBranchOpeningMonday;
    private TextView mBranchOpeningTuesday;
    private TextView mBranchOpeningWednesday;
    private TextView mBranchOpeningThursday;
    private TextView mBranchOpeningFriday;
    private TextView mBranchOpeningSaturday;
    private TextView mBranchOpeningSunday;

    private Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    private AirBankService airBankService = retrofit.create(AirBankService.class);
    public BranchController(Activity activity) {
        this.activity = activity;
    }

    public void getBranchesList(String apikey, BranchesAdapter mAdapter) {
        this.mAdapter = mAdapter;
        Log.d(DEBUG_TAG_INFO, "BranchController.getBranchesList called");
        Call<BranchesList> branchesListCall = airBankService.getBranchesList(apikey);
        branchesListCall.enqueue(new Callback<BranchesList>() {
            @Override
            public void onResponse(@NonNull Call<BranchesList> call, @NonNull Response<BranchesList> response) {
                Log.d(DEBUG_TAG_INFO, "getBranchesList.onResponse called");
                Log.d(DEBUG_TAG_INFO, response.toString());
                if(response.isSuccessful()) {
                    Log.d(DEBUG_TAG_INFO, "getBranchesList - response.isSuccessful()");
                    BranchesList branchesList = response.body();
                    if (branchesList != null) {
                        Log.d(DEBUG_TAG_INFO, "getBranchesList - branchesList != null");
                        Log.d(DEBUG_TAG_INFO, "getBranchesList - branchesList.size = " + branchesList.getBranches().size());
                        branchesList.getBranches().forEach(branch -> Log.d(DEBUG_TAG_INFO, branch.getName()));
                        Log.d(DEBUG_TAG_INFO, "getBranchesList - All branches rendered");
                        mAdapter.updateItems(branchesList.getBranches());
                        Log.d(DEBUG_TAG_INFO, "getBranchesList - mAdapter updated");
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
    }

    public void getBranch(String apikey, String branchId) {
        Log.d(DEBUG_TAG_INFO, "BranchController.getBranch called");
        mBranchName = (TextView) activity.findViewById(R.id.branch_name);
        mBranchPhone = (TextView) activity.findViewById(R.id.branch_phone);
        mBranchOpeningMonday = (TextView) activity.findViewById(R.id.branch_opening_mondayValue);
        mBranchOpeningTuesday = (TextView) activity.findViewById(R.id.branch_opening_tuesdayValue);
        mBranchOpeningWednesday = (TextView) activity.findViewById(R.id.branch_opening_wednesdayValue);
        mBranchOpeningThursday = (TextView) activity.findViewById(R.id.branch_opening_thursdayValue);
        mBranchOpeningFriday = (TextView) activity.findViewById(R.id.branch_opening_fridayValue);
        mBranchOpeningSaturday = (TextView) activity.findViewById(R.id.branch_opening_saturdayValue);
/*        mBranchOpeningSunday = (TextView) activity.findViewById(R.id.branch_opening_sundayValue);*/
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
                        Log.d(DEBUG_TAG_INFO, "getBranch - response: " + response.body().getName());
                        mBranchName.setText(branch.getName());
                        mBranchPhone.setText(utils.getAllPhones(branch.getPhones()));
                        OpeningHoursDay[] openingHoursDay = branch.getOpeningHours().getDays();
                        for (OpeningHoursDay day : openingHoursDay) {
                            switch (day.getDayOfWeek()) {
                                case 1: {
                                    mBranchOpeningMonday.setText(day.getOpening() + " - " + day.getClosing());
                                }
                                case 2: {
                                    mBranchOpeningTuesday.setText(day.getOpening() + " - " + day.getClosing());
                                }
                                case 3: {
                                    mBranchOpeningWednesday.setText(day.getOpening() + " - " + day.getClosing());
                                }
                                case 4: {
                                    mBranchOpeningThursday.setText(day.getOpening() + " - " + day.getClosing());
                                }
                                case 5: {
                                    mBranchOpeningFriday.setText(day.getOpening() + " - " + day.getClosing());
                                }
                                case 6: {
                                    mBranchOpeningSaturday.setText(day.getOpening() + " - " + day.getClosing());
                                }
                            }
                        }
                    }
                } else {
                    Log.e(DEBUG_TAG_ERROR, response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Branch> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
