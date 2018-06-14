package cz.polreich.banks.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import cz.polreich.banks.model.airBank.AirBankLocation;
import cz.polreich.banks.model.erste.ErsteLocation;

@Entity
public class UniLocation {

    @PrimaryKey(autoGenerate = true)
    private int locationId;
    private float longitude;
    private float latitude;

    public UniLocation() {
    }

    @Ignore
    public UniLocation(AirBankLocation location) {
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
    }

    @Ignore
    public UniLocation(ErsteLocation location) {
        this.latitude = location.getLat();
        this.longitude = location.getLng();
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
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
