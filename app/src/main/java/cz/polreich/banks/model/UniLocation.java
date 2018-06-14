package cz.polreich.banks.model;

import android.arch.persistence.room.Entity;

import cz.polreich.banks.model.airBank.AirBankLocation;
import cz.polreich.banks.model.erste.ErsteLocation;

@Entity
public class UniLocation {

    private float longitude;
    private float latitude;

    public UniLocation(AirBankLocation location) {
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
    }

    public UniLocation(ErsteLocation location) {
        this.latitude = location.getLat();
        this.longitude = location.getLng();
    }
}
