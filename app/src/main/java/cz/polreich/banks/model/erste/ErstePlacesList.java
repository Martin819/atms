package cz.polreich.banks.model.erste;

import com.google.gson.annotations.SerializedName;

import java.util.List;



public class ErstePlacesList  {
    @SerializedName("items")
    private List<ErstePlace> places;

    public ErstePlacesList() {
    }

    public ErstePlacesList(List<ErstePlace> places) {
        this.places = places;
    }

    public List<ErstePlace> getPlaces() {
        return places;
    }

    public void setPlaces(List<ErstePlace> places) {
        this.places = places;
    }
}
