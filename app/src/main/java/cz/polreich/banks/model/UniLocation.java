package cz.polreich.banks.model;

import cz.polreich.banks.model.airBank.Location;

/**
 * Created by Martin on 08.03.2018.
 */

public class UniLocation {

    private float longitude;
    private float latitude;

    public UniLocation(Location location) {
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
    }
}
