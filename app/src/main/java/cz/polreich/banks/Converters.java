package cz.polreich.banks;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cz.polreich.banks.model.UniAddress;
import cz.polreich.banks.model.UniLocation;
import cz.polreich.banks.model.UniOpeningHours;
import cz.polreich.banks.model.airBank.AirBankAddress;
import cz.polreich.banks.model.airBank.AirBankLocation;
import cz.polreich.banks.model.airBank.AirBankOpeningHours;
import cz.polreich.banks.model.airBank.AirBankOpeningHoursDay;
import cz.polreich.banks.model.erste.ErsteEquipment;
import cz.polreich.banks.model.erste.ErsteLocation;
import cz.polreich.banks.model.erste.ErsteManager;
import cz.polreich.banks.model.erste.ErsteOpeningHours;
import cz.polreich.banks.model.erste.ErsteOutage;
import cz.polreich.banks.model.erste.ErstePlaceState;
import cz.polreich.banks.model.erste.ErstePlaceType;
import cz.polreich.banks.model.erste.ErsteService;

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
    public String fromOpeningHoursDayArray(AirBankOpeningHoursDay[] ohda) {
        String mapToJson = objGson.toJson(ohda);
        return mapToJson;
    }

    @TypeConverter
    public AirBankOpeningHoursDay[] toOpeningHoursDayArray(String mapToJson) {
        AirBankOpeningHoursDay[] ohda = objGson.fromJson(mapToJson, AirBankOpeningHoursDay[].class);
        return ohda;
    }

    @TypeConverter
    public String fromOpeningHoursDay(AirBankOpeningHoursDay ohd) {
        String mapToJson = objGson.toJson(ohd);
        return mapToJson;
    }

    @TypeConverter
    public AirBankOpeningHoursDay toOpeningHoursDay(String mapToJson) {
        AirBankOpeningHoursDay ohd = objGson.fromJson(mapToJson, AirBankOpeningHoursDay.class);
        return ohd;
    }

    @TypeConverter
    public String fromOpeningHours(AirBankOpeningHours oh) {
        String mapToJson = objGson.toJson(oh);
        return mapToJson;
    }

    @TypeConverter
    public AirBankOpeningHours toOpeningHours(String mapToJson) {
        AirBankOpeningHours oh = objGson.fromJson(mapToJson, AirBankOpeningHours.class);
        return oh;
    }

    @TypeConverter
    public String fromLocation(AirBankLocation loc) {
        String mapToJson = objGson.toJson(loc);
        return mapToJson;
    }

    @TypeConverter
    public AirBankLocation toLocation(String mapToJson) {
        AirBankLocation loc = objGson.fromJson(mapToJson, AirBankLocation.class);
        return loc;
    }

    @TypeConverter
    public String fromAddress(AirBankAddress address) {
        String mapToJson = objGson.toJson(address);
        return mapToJson;
    }

    @TypeConverter
    public AirBankAddress toAddress(String mapToJson) {
        AirBankAddress address = objGson.fromJson(mapToJson, AirBankAddress.class);
        return address;
    }

    @TypeConverter
    public String fromStringArray(String[] sa) {
        String mapToJson = objGson.toJson(sa);
        return mapToJson;
    }

    @TypeConverter
    public String[] toStringArray(String mapToJson) {
        String[] sa = objGson.fromJson(mapToJson, String[].class);
        return sa;
    }

    @TypeConverter
    public String fromUniAddress(UniAddress address) {
        String mapToJson = objGson.toJson(address);
        return mapToJson;
    }

    @TypeConverter
    public UniAddress toUniAddress(String mapToJson) {
        UniAddress address = objGson.fromJson(mapToJson, UniAddress.class);
        return address;
    }

    @TypeConverter
    public String fromUniLocation(UniLocation loc) {
        String mapToJson = objGson.toJson(loc);
        return mapToJson;
    }

    @TypeConverter
    public UniLocation toUniLocation(String mapToJson) {
        UniLocation loc = objGson.fromJson(mapToJson, UniLocation.class);
        return loc;
    }

    @TypeConverter
    public String fromUniOpeningHours(UniOpeningHours[] oh) {
        String mapToJson = objGson.toJson(oh);
        return mapToJson;
    }

    @TypeConverter
    public UniOpeningHours[] toUniOpeningHours(String mapToJson) {
        UniOpeningHours[] oh = objGson.fromJson(mapToJson, UniOpeningHours[].class);
        return oh;
    }

    @TypeConverter
    public String fromErsteLocation(ErsteLocation loc) {
        String mapToJson = objGson.toJson(loc);
        return mapToJson;
    }

    @TypeConverter
    public ErsteLocation toErsteLocation(String mapToJson) {
        ErsteLocation loc = objGson.fromJson(mapToJson, ErsteLocation.class);
        return loc;
    }

    @TypeConverter
    public String fromErstePlaceType(ErstePlaceType ept) {
        String mapToJson = objGson.toJson(ept);
        return mapToJson;
    }

    @TypeConverter
    public ErstePlaceType toErstePlaceType(String mapToJson) {
        ErstePlaceType ept = objGson.fromJson(mapToJson, ErstePlaceType.class);
        return ept;
    }

    @TypeConverter
    public String fromErstePlaceState(ErstePlaceState eps) {
        String mapToJson = objGson.toJson(eps);
        return mapToJson;
    }

    @TypeConverter
    public ErstePlaceState toErstePlaceState(String mapToJson) {
        ErstePlaceState eps = objGson.fromJson(mapToJson, ErstePlaceState.class);
        return eps;
    }

    @TypeConverter
    public String fromErsteService(ErsteService[] es) {
        String mapToJson = objGson.toJson(es);
        return mapToJson;
    }

    @TypeConverter
    public ErsteService[] toErsteService(String mapToJson) {
        ErsteService[] es = objGson.fromJson(mapToJson, ErsteService[].class);
        return es;
    }

    @TypeConverter
    public String fromErsteOpeningHours(ErsteOpeningHours oh) {
        String mapToJson = objGson.toJson(oh);
        return mapToJson;
    }

    @TypeConverter
    public ErsteOpeningHours toErsteOpeningHours(String mapToJson) {
        ErsteOpeningHours oh = objGson.fromJson(mapToJson, ErsteOpeningHours.class);
        return oh;
    }

    @TypeConverter
    public String fromErsteOpeningHoursArray(ErsteOpeningHours[] oh) {
        String mapToJson = objGson.toJson(oh);
        return mapToJson;
    }

    @TypeConverter
    public ErsteOpeningHours[] toErsteOpeningHoursArray(String mapToJson) {
        ErsteOpeningHours[] oh = objGson.fromJson(mapToJson, ErsteOpeningHours[].class);
        return oh;
    }

    @TypeConverter
    public String fromErsteEquipment(ErsteEquipment[] ee) {
        String mapToJson = objGson.toJson(ee);
        return mapToJson;
    }

    @TypeConverter
    public ErsteEquipment[] toErsteEquipment(String mapToJson) {
        ErsteEquipment[] ee = objGson.fromJson(mapToJson, ErsteEquipment[].class);
        return ee;
    }

    @TypeConverter
    public String fromErsteManager(ErsteManager em) {
        String mapToJson = objGson.toJson(em);
        return mapToJson;
    }

    @TypeConverter
    public ErsteManager toErsteManager(String mapToJson) {
        ErsteManager em = objGson.fromJson(mapToJson, ErsteManager.class);
        return em;
    }

    @TypeConverter
    public String fromErsteOutage(ErsteOutage[] eo) {
        String mapToJson = objGson.toJson(eo);
        return mapToJson;
    }

    @TypeConverter
    public ErsteOutage[] toErsteOutage(String mapToJson) {
        ErsteOutage[] eo = objGson.fromJson(mapToJson, ErsteOutage[].class);
        return eo;
    }

}