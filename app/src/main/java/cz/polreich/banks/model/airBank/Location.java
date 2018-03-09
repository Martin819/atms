package cz.polreich.banks.model.airBank;

import io.realm.RealmObject;

public class Location extends RealmObject {

    private float longitude;
    private float latitude;

    public Location() {
    }

    public Location(float longitude, float latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }
}
