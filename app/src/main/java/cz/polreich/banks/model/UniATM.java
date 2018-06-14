package cz.polreich.banks.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import cz.polreich.banks.model.airBank.AirBankATM;
import cz.polreich.banks.model.airBank.AirBankOpeningHoursDay;

@Entity
public class UniATM {

    @PrimaryKey
    @NotNull
    private String id;
    private UniAddress address;
    private UniLocation location;
    private UniOpeningHours[] openingHoursWithdrawal;
    private UniOpeningHours[] openingHoursDeposit;
    @Ignore
    private float distance;

    public UniATM() {
    }

    @Ignore
    public UniATM(AirBankATM atm) {
        this.id = atm.getId();
        this.address = new UniAddress(atm.getAddress());
        this.location = new UniLocation(atm.getLocation());
        this.openingHoursWithdrawal = new UniOpeningHours[0];
        for (int i=0; i < atm.getOpeningHoursWithdrawal().getDays().length; i++) {
            this.openingHoursWithdrawal[i] = new UniOpeningHours(atm.getOpeningHoursWithdrawal().getDays()[i]);
        }
        this.openingHoursDeposit = new UniOpeningHours[0];
        for (int i=0; i < atm.getOpeningHoursDeposit().getDays().length; i++) {
            this.openingHoursDeposit[i] = new UniOpeningHours(atm.getOpeningHoursDeposit().getDays()[i]);
        }
    }

    // TODO: Erste Constructor

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UniAddress getAddress() {
        return address;
    }

    public void setAddress(UniAddress address) {
        this.address = address;
    }

    public UniLocation getLocation() {
        return location;
    }

    public void setLocation(UniLocation location) {
        this.location = location;
    }

    public UniOpeningHours[] getOpeningHoursWithdrawal() {
        return openingHoursWithdrawal;
    }

    public void setOpeningHoursWithdrawal(UniOpeningHours[] openingHoursWithdrawal) {
        this.openingHoursWithdrawal = openingHoursWithdrawal;
    }

    public UniOpeningHours[] getOpeningHoursDeposit() {
        return openingHoursDeposit;
    }

    public void setOpeningHoursDeposit(UniOpeningHours[] openingHoursDeposit) {
        this.openingHoursDeposit = openingHoursDeposit;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }
}
