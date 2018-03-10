package cz.polreich.banks;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cz.polreich.banks.model.airBank.OpeningHours;
import cz.polreich.banks.model.airBank.OpeningHoursDay;

public class Converters {

    private final String DEBUG_TAG_INFO = "[INFO     ] " + this.getClass().getSimpleName();
    private final String DEBUG_TAG_ERROR = "[    ERROR] " + this.getClass().getSimpleName();
    private final String DEBUG_TAG_WARNING = "[ WARNING ] " + this.getClass().getSimpleName();

/*    Gson gson = new Gson();

    @TypeConverter
    public String fromOpeningHoursDayArray(OpeningHoursDay[] ohda){
        List<OpeningHoursDay> ohdList = new ArrayList<>();
        ohdList.addAll(Arrays.asList(ohda));
        return gson.toJson(ohdList);
*//*        return ohdList;*//*
    }

    @TypeConverter
    public OpeningHoursDay[] toOpeningHoursDayArray(String ohdJson){
        List<OpeningHoursDay> ohdList = new ArrayList<>();
        ohdList = gson.fromJson(ohdJson, new TypeToken<List<OpeningHoursDay>>(){}.getType());
        OpeningHoursDay[] ohda = null;
        for (int i = 0; i < ohdList.size(); i++) {
            ohda[i] = ohdList.get(i);
        }
        return ohda;
    }*/

    Gson objGson = new GsonBuilder().setPrettyPrinting().create();

    @TypeConverter
    public String fromOpeningHoursDayArray (OpeningHoursDay[] ohda) {
        String mapToJson = objGson.toJson(ohda);
        return mapToJson;
    }

    @TypeConverter
    public OpeningHoursDay[] toOpeningHoursDayArray (String mapToJson) {
        OpeningHoursDay[] ohda = objGson.fromJson(mapToJson, OpeningHoursDay[].class);
        return ohda;
    }

    @TypeConverter
    public String fromOpeningHoursDay (OpeningHoursDay ohd) {
        String mapToJson = objGson.toJson(ohd);
        return mapToJson;
    }

    @TypeConverter
    public OpeningHoursDay toOpeningHoursDay (String mapToJson) {
        OpeningHoursDay ohd = objGson.fromJson(mapToJson, OpeningHoursDay.class);
        return ohd;
    }

    @TypeConverter
    public String fromOpeningHours (OpeningHours oh) {
        String mapToJson = objGson.toJson(oh);
        return mapToJson;
    }

    @TypeConverter
    public OpeningHours toOpeningHours (String mapToJson) {
        OpeningHours oh = objGson.fromJson(mapToJson, OpeningHours.class);
        return oh;
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
