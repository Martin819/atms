package cz.polreich.banks.model.airBank;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Location  {

    @PrimaryKey(autoGenerate = true)
    private int locationId;
    private float longitude;
    private float latitude;

    public Location() {
    }

    @Ignore
    public Location(int locationId, float longitude, float latitude) {
        this.locationId = locationId;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    @Ignore
    public Location(float longitude, float latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
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
