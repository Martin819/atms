package cz.polreich.atms;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;

import cz.polreich.atms.model.airBank.Address;
import cz.polreich.atms.model.airBank.OpeningHoursDay;

/**
 * Created by Martin on 25.02.2018.
 */

public class utils {

    private static final String DEBUG_TAG_INFO = "[INFO     ] Utils";
    private static final String DEBUG_TAG_ERROR = "[    ERROR] Utils";
    private static final String DEBUG_TAG_WARNING = "[ WARNING ] Utils";

    public static String getFullAddress(Address address) {
        String fullAddress = address.getStreetAddress() + ", " + address.getCity() + ", " + address.getZip();
        return fullAddress;
    }

    public static String getAllPhones(String[] phonesArray) {
/*        if (phonesArray.length > 0) {
            StringBuilder phonesBuilder = new StringBuilder();

            for (String phone : phonesArray) {
                phonesBuilder.append("'").append(phone.replace("'", "\\'")).append("',");
            }

            phonesBuilder.deleteCharAt(phonesBuilder.length() - 1);

            return phonesBuilder.toString();
        } else {
            return "No phone number provided.";
        }*/

        if (phonesArray.length > 0) {
            return TextUtils.join(",", phonesArray);
        } else {
            return "No phone provided.";
        }

    }

    public static String[] getOpeningHours(OpeningHoursDay[] openingHoursDays, Activity activity) {
        String bct = activity.getResources().getString(R.string.branch_closedTitle);
        String retDays[] = {bct, bct, bct, bct, bct, bct, bct};
        for (OpeningHoursDay day : openingHoursDays) {
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
}
