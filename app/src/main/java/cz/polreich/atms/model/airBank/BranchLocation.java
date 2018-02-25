package cz.polreich.atms.model.airBank;

/**
 * Created by Martin on 21.02.2018.
 */

public class BranchLocation {

    private float longitude;
    private float latitude;

    public BranchLocation() {
    }

    public BranchLocation(float longitude, float latitude) {
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
