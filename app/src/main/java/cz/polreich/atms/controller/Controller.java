package cz.polreich.atms.controller;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

import cz.polreich.atms.R;
import cz.polreich.atms.adapter.ATMsAdapter;
import cz.polreich.atms.model.airBank.ATM;
import cz.polreich.atms.model.airBank.ATMsList;
import cz.polreich.atms.model.airBank.Branch;
import cz.polreich.atms.service.AirBankService;
import cz.polreich.atms.adapter.BranchesAdapter;
import cz.polreich.atms.model.airBank.BranchesList;
import cz.polreich.atms.utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Controller {

    private BranchesAdapter branchesAdapter;
    private ATMsAdapter atmsAdapter;
    private Branch retBranch;
    private static final String BASE_URL = "https://api.airbank.cz/";
    private static final String DEBUG_TAG_INFO = "[INFO     ] Controller";
    private static final String DEBUG_TAG_ERROR = "[    ERROR] Controller";
    private static final String DEBUG_TAG_WARNING = "[ WARNING ] Controller";
    private Activity activity;
    private TextView mBranchType;
    private TextView mBranchPhone;
    private TextView mBranchAddress;
    private TextView mBranchOpeningMonday;
    private TextView mBranchOpeningTuesday;
    private TextView mBranchOpeningWednesday;
    private TextView mBranchOpeningThursday;
    private TextView mBranchOpeningFriday;
    private TextView mBranchOpeningSaturday;
    private TextView mBranchOpeningSunday;
    private TextView mATMAddress;
    private TextView mATMOpeningWithdrawalMonday;
    private TextView mATMOpeningWithdrawalTuesday;
    private TextView mATMOpeningWithdrawalWednesday;
    private TextView mATMOpeningWithdrawalThursday;
    private TextView mATMOpeningWithdrawalFriday;
    private TextView mATMOpeningWithdrawalSaturday;
    private TextView mATMOpeningWithdrawalSunday;
    private TextView mATMOpeningDepositMonday;
    private TextView mATMOpeningDepositTuesday;
    private TextView mATMOpeningDepositWednesday;
    private TextView mATMOpeningDepositThursday;
    private TextView mATMOpeningDepositFriday;
    private TextView mATMOpeningDepositSaturday;
    private TextView mATMOpeningDepositSunday;
    private ImageView mBranchImage;

    private Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    private AirBankService airBankService = retrofit.create(AirBankService.class);
    public Controller(Activity activity) {
        this.activity = activity;
    }

    public void getBranchesList(String apikey, BranchesAdapter branchesAdapter) {
        this.branchesAdapter = branchesAdapter;
        Log.d(DEBUG_TAG_INFO, "Controller.getBranchesList called");
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
                        branchesAdapter.updateItems(branchesList.getBranches());
                        Log.d(DEBUG_TAG_INFO, "getBranchesList - branchesAdapter updated");
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
        Log.d(DEBUG_TAG_INFO, "Controller.getBranch called");
        mBranchType = (TextView) activity.findViewById(R.id.branch_type);
        mBranchAddress = (TextView) activity.findViewById(R.id.branch_address);
        mBranchPhone = (TextView) activity.findViewById(R.id.branch_phone);
        mBranchOpeningMonday = (TextView) activity.findViewById(R.id.branch_opening_mondayValue);
        mBranchOpeningTuesday = (TextView) activity.findViewById(R.id.branch_opening_tuesdayValue);
        mBranchOpeningWednesday = (TextView) activity.findViewById(R.id.branch_opening_wednesdayValue);
        mBranchOpeningThursday = (TextView) activity.findViewById(R.id.branch_opening_thursdayValue);
        mBranchOpeningFriday = (TextView) activity.findViewById(R.id.branch_opening_fridayValue);
        mBranchOpeningSaturday = (TextView) activity.findViewById(R.id.branch_opening_saturdayValue);
        mBranchOpeningSunday = (TextView) activity.findViewById(R.id.branch_opening_sundayValue);
        mBranchImage = (ImageView) activity.findViewById(R.id.branch_imageView);
        String branchNonstopTitle = activity.getResources().getString(R.string.branch_nonstopTitle);
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
                        String openingHoursDays[] = new String[7];
                        if (branch.getOpeningHours().isNonstop()) {
                            Arrays.fill(openingHoursDays, branchNonstopTitle);
                        } else {
                            openingHoursDays = utils.getOpeningHours(branch.getOpeningHours().getDays(), activity);
                        }
                            mBranchOpeningMonday.setText(openingHoursDays[0]);
                            mBranchOpeningTuesday.setText(openingHoursDays[1]);
                            mBranchOpeningWednesday.setText(openingHoursDays[2]);
                            mBranchOpeningThursday.setText(openingHoursDays[3]);
                            mBranchOpeningFriday.setText(openingHoursDays[4]);
                            mBranchOpeningSaturday.setText(openingHoursDays[5]);
                            mBranchOpeningSunday.setText(openingHoursDays[6]);
                            String branchPictures[] = branch.getPictures();
                            Log.d(DEBUG_TAG_INFO, "Found " + branchPictures.length + " branch pictures.");
                            Glide.with(activity)
                                .load(branchPictures[0])
                                .placeholder(R.drawable.ic_home_black_24dp)
                                .into(mBranchImage);
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

    public void getATMsList(String apikey, ATMsAdapter atmsAdapter) {
        this.atmsAdapter = atmsAdapter;
        Log.d(DEBUG_TAG_INFO, "Controller.getATMsList called");
        Call<ATMsList> atmsListCall = airBankService.getATMsList(apikey);
        atmsListCall.enqueue(new Callback<ATMsList>() {
            @Override
            public void onResponse(@NonNull Call<ATMsList> call, @NonNull Response<ATMsList> response) {
                Log.d(DEBUG_TAG_INFO, "getATMsList.onResponse called");
                Log.d(DEBUG_TAG_INFO, response.toString());
                if (response.isSuccessful()) {
                    Log.d(DEBUG_TAG_INFO, "getATMsList - response.isSuccessful()");
                    ATMsList atmsList = response.body();
                    if (atmsList != null) {
                        Log.d(DEBUG_TAG_INFO, "getATMsList - atmsList != null");
                        Log.d(DEBUG_TAG_INFO, "getATMsList - atmsList.size = " + atmsList.getAtms().size());
                        atmsList.getAtms().forEach(atm -> Log.d(DEBUG_TAG_INFO, utils.getFullAddress(atm.getAddress())));
                        Log.d(DEBUG_TAG_INFO, "getATMsList - All ATMs rendered");
                        atmsAdapter.updateItems(atmsList.getAtms());
                        Log.d(DEBUG_TAG_INFO, "getATMsList - ATMsAdapter updated");
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
    }

        public void getATM(String apikey, String ATMId) {
        Log.d(DEBUG_TAG_INFO, "Controller.getATM called");
        mATMAddress = (TextView) activity.findViewById(R.id.atm_address);
        mATMOpeningWithdrawalMonday = (TextView) activity.findViewById(R.id.atm_openingWithdrawal_mondayValue);
        mATMOpeningWithdrawalTuesday = (TextView) activity.findViewById(R.id.atm_openingWithdrawal_tuesdayValue);
        mATMOpeningWithdrawalWednesday = (TextView) activity.findViewById(R.id.atm_openingWithdrawal_wednesdayValue);
        mATMOpeningWithdrawalThursday = (TextView) activity.findViewById(R.id.atm_openingWithdrawal_thursdayValue);
        mATMOpeningWithdrawalFriday = (TextView) activity.findViewById(R.id.atm_openingWithdrawal_fridayValue);
        mATMOpeningWithdrawalSaturday = (TextView) activity.findViewById(R.id.atm_openingWithdrawal_saturdayValue);
        mATMOpeningWithdrawalSunday = (TextView) activity.findViewById(R.id.atm_openingWithdrawal_sundayValue);
        mATMOpeningDepositMonday = (TextView) activity.findViewById(R.id.atm_openingDeposit_mondayValue);
        mATMOpeningDepositTuesday = (TextView) activity.findViewById(R.id.atm_openingDeposit_tuesdayValue);
        mATMOpeningDepositWednesday = (TextView) activity.findViewById(R.id.atm_openingDeposit_wednesdayValue);
        mATMOpeningDepositThursday = (TextView) activity.findViewById(R.id.atm_openingDeposit_thursdayValue);
        mATMOpeningDepositFriday = (TextView) activity.findViewById(R.id.atm_openingDeposit_fridayValue);
        mATMOpeningDepositSaturday = (TextView) activity.findViewById(R.id.atm_openingDeposit_saturdayValue);
        mATMOpeningDepositSunday = (TextView) activity.findViewById(R.id.atm_openingDeposit_sundayValue);
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
                        String openingHoursDaysWithdrawal[] = new String[7];
                        String openingHoursDaysDeposit[] = new String[7];
                        if (atm.getOpeningHoursWithdrawal().isNonstop()) {
                            Arrays.fill(openingHoursDaysWithdrawal, atmNonstopTitle);
                        } else {
                            openingHoursDaysWithdrawal = utils.getOpeningHours(atm.getOpeningHoursWithdrawal().getDays(), activity);
                        }
                        if (atm.getOpeningHoursDeposit().isNonstop()) {
                            Arrays.fill(openingHoursDaysDeposit, atmNonstopTitle);
                        } else {
                            openingHoursDaysDeposit = utils.getOpeningHours(atm.getOpeningHoursDeposit().getDays(), activity);
                        }
                        mATMOpeningWithdrawalMonday.setText(openingHoursDaysWithdrawal[0]);
                        mATMOpeningWithdrawalTuesday.setText(openingHoursDaysWithdrawal[1]);
                        mATMOpeningWithdrawalWednesday.setText(openingHoursDaysWithdrawal[2]);
                        mATMOpeningWithdrawalThursday.setText(openingHoursDaysWithdrawal[3]);
                        mATMOpeningWithdrawalFriday.setText(openingHoursDaysWithdrawal[4]);
                        mATMOpeningWithdrawalSaturday.setText(openingHoursDaysWithdrawal[5]);
                        mATMOpeningWithdrawalSunday.setText(openingHoursDaysWithdrawal[6]);
                        mATMOpeningDepositMonday.setText(openingHoursDaysDeposit[0]);
                        mATMOpeningDepositTuesday.setText(openingHoursDaysDeposit[1]);
                        mATMOpeningDepositWednesday.setText(openingHoursDaysDeposit[2]);
                        mATMOpeningDepositThursday.setText(openingHoursDaysDeposit[3]);
                        mATMOpeningDepositFriday.setText(openingHoursDaysDeposit[4]);
                        mATMOpeningDepositSaturday.setText(openingHoursDaysDeposit[5]);
                        mATMOpeningDepositSunday.setText(openingHoursDaysDeposit[6]);
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

