package cz.polreich.banks.model.airBank;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity
public class ATM   {

    @PrimaryKey
    @NotNull
    private String id;
    private Address address;
    private Location location;
    private OpeningHours openingHoursWithdrawal;
    private OpeningHours openingHoursDeposit;
    @Ignore
    private float distance;

    public ATM() {
    }

    @Ignore
    public ATM(@NotNull String id, Address address, Location location, OpeningHours openingHoursWithdrawal, OpeningHours openingHoursDeposit, float distance) {
        this.id = id;
        this.address = address;
        this.location = location;
        this.openingHoursWithdrawal = openingHoursWithdrawal;
        this.openingHoursDeposit = openingHoursDeposit;
        this.distance = distance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public OpeningHours getOpeningHoursWithdrawal() {
        return openingHoursWithdrawal;
    }

    public void setOpeningHoursWithdrawal(OpeningHours openingHoursWithdrawal) {
        this.openingHoursWithdrawal = openingHoursWithdrawal;
    }

    public OpeningHours getOpeningHoursDeposit() {
        return openingHoursDeposit;
    }

    public void setOpeningHoursDeposit(OpeningHours openingHoursDeposit) {
        this.openingHoursDeposit = openingHoursDeposit;
    }
}
