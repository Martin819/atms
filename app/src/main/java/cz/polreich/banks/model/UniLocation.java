package cz.polreich.banks.model;

import cz.polreich.banks.model.airBank.Location;
import cz.polreich.banks.model.erste.ErsteLocation;

public class UniLocation {

    private float longitude;
    private float latitude;

    public UniLocation(Location location) {
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
    }

    public UniLocation(ErsteLocation location) {
        this.latitude = location.getLat();
        this.longitude = location.getLng();
    }
}
