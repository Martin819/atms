package cz.polreich.banks;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;

import cz.polreich.banks.model.UniAddress;
import cz.polreich.banks.model.airBank.AirBankAddress;
import cz.polreich.banks.model.airBank.AirBankOpeningHours;
import cz.polreich.banks.model.airBank.AirBankOpeningHoursDay;

public class utils {

    private static final String DEBUG_TAG_INFO = "[INFO     ] Utils";
    private static final String DEBUG_TAG_ERROR = "[    ERROR] Utils";
    private static final String DEBUG_TAG_WARNING = "[ WARNING ] Utils";

    public static String getFullAddress(AirBankAddress address) {
        return address.getStreetAddress() + ", " + address.getCity() + ", " + address.getZip();
    }

    public static String getFullAddress(UniAddress address) {
        return address.getStreet() + ", " + address.getCity() + ", " + address.getZip();
    }

    public static String getAllPhones(String[] phonesArray) {
        if (phonesArray.length > 0) {
            return TextUtils.join(",", phonesArray);
        } else {
            return "No phone provided.";
        }
    }

    public static void getBranchOpeningHours(AirBankOpeningHours openingHours, Activity activity) {
        TextView mBranchOpeningMonday = (TextView) activity.findViewById(R.id.branch_opening_mondayValue);
        TextView mBranchOpeningTuesday = (TextView) activity.findViewById(R.id.branch_opening_tuesdayValue);
        TextView mBranchOpeningWednesday = (TextView) activity.findViewById(R.id.branch_opening_wednesdayValue);
        TextView mBranchOpeningThursday = (TextView) activity.findViewById(R.id.branch_opening_thursdayValue);
        TextView mBranchOpeningFriday = (TextView) activity.findViewById(R.id.branch_opening_fridayValue);
        TextView mBranchOpeningSaturday = (TextView) activity.findViewById(R.id.branch_opening_saturdayValue);
        TextView mBranchOpeningSunday = (TextView) activity.findViewById(R.id.branch_opening_sundayValue);
        String bct = activity.getResources().getString(R.string.branch_closedTitle);
        String branchNonstopTitle = activity.getResources().getString(R.string.branch_nonstopTitle);
        String retDays[] = {bct, bct, bct, bct, bct, bct, bct};
        if (openingHours.isNonstop()){
            Arrays.fill(retDays, branchNonstopTitle);
        } else {
            retDays = formatOpeningHours(openingHours);
        }
        mBranchOpeningMonday.setText(retDays[0]);
        mBranchOpeningTuesday.setText(retDays[1]);
        mBranchOpeningWednesday.setText(retDays[2]);
        mBranchOpeningThursday.setText(retDays[3]);
        mBranchOpeningFriday.setText(retDays[4]);
        mBranchOpeningSaturday.setText(retDays[5]);
        mBranchOpeningSunday.setText(retDays[6]);
    }
    
    public static void getATMWithdrawalOpeningHours(AirBankOpeningHours openingHours, Activity activity) {
        TextView mATMOpeningWithdrawalMonday = (TextView) activity.findViewById(R.id.atm_openingWithdrawal_mondayValue);
        TextView mATMOpeningWithdrawalTuesday = (TextView) activity.findViewById(R.id.atm_openingWithdrawal_tuesdayValue);
        TextView mATMOpeningWithdrawalWednesday = (TextView) activity.findViewById(R.id.atm_openingWithdrawal_wednesdayValue);
        TextView mATMOpeningWithdrawalThursday = (TextView) activity.findViewById(R.id.atm_openingWithdrawal_thursdayValue);
        TextView mATMOpeningWithdrawalFriday = (TextView) activity.findViewById(R.id.atm_openingWithdrawal_fridayValue);
        TextView mATMOpeningWithdrawalSaturday = (TextView) activity.findViewById(R.id.atm_openingWithdrawal_saturdayValue);
        TextView mATMOpeningWithdrawalSunday = (TextView) activity.findViewById(R.id.atm_openingWithdrawal_sundayValue);
        String bct = activity.getResources().getString(R.string.branch_closedTitle);
        String atmNonstopTitle = activity.getResources().getString(R.string.atm_nonstopTitle);
        String retDays[] = {bct, bct, bct, bct, bct, bct, bct};
        if (openingHours.isNonstop()){
            Arrays.fill(retDays, atmNonstopTitle);
        } else {
            retDays = formatOpeningHours(openingHours);
        }
        mATMOpeningWithdrawalMonday.setText(retDays[0]);
        mATMOpeningWithdrawalTuesday.setText(retDays[1]);
        mATMOpeningWithdrawalWednesday.setText(retDays[2]);
        mATMOpeningWithdrawalThursday.setText(retDays[3]);
        mATMOpeningWithdrawalFriday.setText(retDays[4]);
        mATMOpeningWithdrawalSaturday.setText(retDays[5]);
        mATMOpeningWithdrawalSunday.setText(retDays[6]);
    }

    public static void getATMDepositOpeningHours(AirBankOpeningHours openingHours, Activity activity) {
        TextView mATMOpeningDepositMonday = (TextView) activity.findViewById(R.id.atm_openingDeposit_mondayValue);
        TextView mATMOpeningDepositTuesday = (TextView) activity.findViewById(R.id.atm_openingDeposit_tuesdayValue);
        TextView mATMOpeningDepositWednesday = (TextView) activity.findViewById(R.id.atm_openingDeposit_wednesdayValue);
        TextView mATMOpeningDepositThursday = (TextView) activity.findViewById(R.id.atm_openingDeposit_thursdayValue);
        TextView mATMOpeningDepositFriday = (TextView) activity.findViewById(R.id.atm_openingDeposit_fridayValue);
        TextView mATMOpeningDepositSaturday = (TextView) activity.findViewById(R.id.atm_openingDeposit_saturdayValue);
        TextView mATMOpeningDepositSunday = (TextView) activity.findViewById(R.id.atm_openingDeposit_sundayValue);
        String bct = activity.getResources().getString(R.string.branch_closedTitle);
        String atmNonstopTitle = activity.getResources().getString(R.string.atm_nonstopTitle);
        String retDays[] = {bct, bct, bct, bct, bct, bct, bct};
        if (openingHours.isNonstop()){
            Arrays.fill(retDays, atmNonstopTitle);
        } else {
            retDays = formatOpeningHours(openingHours);
        }
        mATMOpeningDepositMonday.setText(retDays[0]);
        mATMOpeningDepositTuesday.setText(retDays[1]);
        mATMOpeningDepositWednesday.setText(retDays[2]);
        mATMOpeningDepositThursday.setText(retDays[3]);
        mATMOpeningDepositFriday.setText(retDays[4]);
        mATMOpeningDepositSaturday.setText(retDays[5]);
        mATMOpeningDepositSunday.setText(retDays[6]);
    }

    private static String[] formatOpeningHours(AirBankOpeningHours openingHours) {
        String retDays[] = new String[7];
        AirBankOpeningHoursDay openingHoursDays[] = openingHours.getDays();
        for (AirBankOpeningHoursDay day : openingHoursDays) {
            switch (day.getDayOfWeek()) {
                case 1: {
                    retDays[0] = day.getOpening().substring(1, 6) + " - " + day.getClosing().substring(1, 6);
                    break;
                }
                case 2: {
                    retDays[1] = day.getOpening().substring(1, 6) + " - " + day.getClosing().substring(1, 6);
                    break;
                }
                case 3: {
                    retDays[2] = day.getOpening().substring(1, 6) + " - " + day.getClosing().substring(1, 6);
                    break;
                }
                case 4: {
                    retDays[3] = day.getOpening().substring(1, 6) + " - " + day.getClosing().substring(1, 6);
                    break;
                }
                case 5: {
                    retDays[4] = day.getOpening().substring(1, 6) + " - " + day.getClosing().substring(1, 6);
                    break;
                }
                case 6: {
                    retDays[5] = day.getOpening().substring(1, 6) + " - " + day.getClosing().substring(1, 6);
                    break;
                }
                case 7: {
                    retDays[6] = day.getOpening().substring(1, 6) + " - " + day.getClosing().substring(1, 6);
                    break;
                }
                default: {
                    Log.w(DEBUG_TAG_WARNING, "Default switch-case triggered.");
                    break;
                }
            }
        }
        return retDays;
    }

    public static void setServices(String services[], Activity activity) {
        ImageView mAnimalsImageView = (ImageView) activity.findViewById(R.id.branch_services_animalImageView);
        ImageView mChildImageView = (ImageView) activity.findViewById(R.id.branch_services_childImageView);
        ImageView mATMImageView = (ImageView) activity.findViewById(R.id.branch_services_atmImageView);
        ImageView mFoodImageView = (ImageView) activity.findViewById(R.id.branch_services_foodImageView);
        ImageView mDrinkImageView = (ImageView) activity.findViewById(R.id.branch_services_drinkImageView);

        if (Arrays.asList(services).contains("ANIMALS")) {
            mAnimalsImageView.setImageResource(R.drawable.ic_animal_green_24dp);
        }
        if (Arrays.asList(services).contains("CHILDREN")) {
            mChildImageView.setImageResource(R.drawable.ic_child_green_24dp);
        }
        if (Arrays.asList(services).contains("AirBankATM")) {
            mATMImageView.setImageResource(R.drawable.ic_atm_green_24dp);
        }
        if (Arrays.asList(services).contains("DRINK")) {
            mFoodImageView.setImageResource(R.drawable.ic_food_green_24dp);
        }
        if (Arrays.asList(services).contains("FOOD")) {
            mDrinkImageView.setImageResource(R.drawable.ic_drink_green_24dp);
        }
    }
}