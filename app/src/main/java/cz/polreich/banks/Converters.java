package cz.polreich.banks;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cz.polreich.banks.model.airBank.AirBankAddress;
import cz.polreich.banks.model.airBank.AirBankLocation;
import cz.polreich.banks.model.airBank.AirBankOpeningHours;
import cz.polreich.banks.model.airBank.AirBankOpeningHoursDay;

public class Converters {

    private final String DEBUG_TAG_INFO = "[INFO     ] " + this.getClass().getSimpleName();
    private final String DEBUG_TAG_ERROR = "[    ERROR] " + this.getClass().getSimpleName();
    private final String DEBUG_TAG_WARNING = "[ WARNING ] " + this.getClass().getSimpleName();

/*    Gson gson = new Gson();

    @TypeConverter
    public String fromOpeningHoursDayArray(AirBankOpeningHoursDay[] ohda){
        List<AirBankOpeningHoursDay> ohdList = new ArrayList<>();
        ohdList.addAll(Arrays.asList(ohda));
        return gson.toJson(ohdList);
*//*        return ohdList;*//*
    }

    @TypeConverter
    public AirBankOpeningHoursDay[] toOpeningHoursDayArray(String ohdJson){
        List<AirBankOpeningHoursDay> ohdList = new ArrayList<>();
        ohdList = gson.fromJson(ohdJson, new TypeToken<List<AirBankOpeningHoursDay>>(){}.getType());
        AirBankOpeningHoursDay[] ohda = null;
        for (int i = 0; i < ohdList.size(); i++) {
            ohda[i] = ohdList.get(i);
        }
        return ohda;
    }*/

    Gson objGson = new GsonBuilder().setPrettyPrinting().create();

    @TypeConverter
    public String fromOpeningHoursDayArray (AirBankOpeningHoursDay[] ohda) {
        String mapToJson = objGson.toJson(ohda);
        return mapToJson;
    }

    @TypeConverter
    public AirBankOpeningHoursDay[] toOpeningHoursDayArray (String mapToJson) {
        AirBankOpeningHoursDay[] ohda = objGson.fromJson(mapToJson, AirBankOpeningHoursDay[].class);
        return ohda;
    }

    @TypeConverter
    public String fromOpeningHoursDay (AirBankOpeningHoursDay ohd) {
        String mapToJson = objGson.toJson(ohd);
        return mapToJson;
    }

    @TypeConverter
    public AirBankOpeningHoursDay toOpeningHoursDay (String mapToJson) {
        AirBankOpeningHoursDay ohd = objGson.fromJson(mapToJson, AirBankOpeningHoursDay.class);
        return ohd;
    }

    @TypeConverter
    public String fromOpeningHours (AirBankOpeningHours oh) {
        String mapToJson = objGson.toJson(oh);
        return mapToJson;
    }

    @TypeConverter
    public AirBankOpeningHours toOpeningHours (String mapToJson) {
        AirBankOpeningHours oh = objGson.fromJson(mapToJson, AirBankOpeningHours.class);
        return oh;
    }

    @TypeConverter
    public String fromLocation (AirBankLocation loc) {
        String mapToJson = objGson.toJson(loc);
        return mapToJson;
    }

    @TypeConverter
    public AirBankLocation toLocation (String mapToJson) {
        AirBankLocation loc = objGson.fromJson(mapToJson, AirBankLocation.class);
        return loc;
    }

    @TypeConverter
    public String fromAddress (AirBankAddress address) {
        String mapToJson = objGson.toJson(address);
        return mapToJson;
    }

    @TypeConverter
    public AirBankAddress toAddress (String mapToJson) {
        AirBankAddress address = objGson.fromJson(mapToJson, AirBankAddress.class);
        return address;
    }

    @TypeConverter
    public String fromStringArray (String[] sa) {
        String mapToJson = objGson.toJson(sa);
        return mapToJson;
    }

    @TypeConverter
    public String[] toStringArray (String mapToJson) {
        String[] sa = objGson.fromJson(mapToJson, String[].class);
        return sa;
    }
}
