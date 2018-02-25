package cz.polreich.atms;

import android.text.TextUtils;

import cz.polreich.atms.model.airBank.BranchAddress;

/**
 * Created by Martin on 25.02.2018.
 */

public class utils {

    public static String getFullAddress(BranchAddress address) {
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
}
