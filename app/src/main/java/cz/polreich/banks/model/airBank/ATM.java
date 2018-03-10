package cz.polreich.banks.model.airBank;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "branches")
public class ATM   {

    @PrimaryKey
    @NotNull
    public String id;
    @Embedded
    public Address address;
    @Embedded
    public Location location;
    public OpeningHours openingHoursWithdrawal;
    public OpeningHours openingHoursDeposit;

    public ATM() {
    }

    @Ignore
    public ATM(String id, Address address, Location location, OpeningHours openingHoursWithdrawal, OpeningHours openingHoursDeposit) {
        this.id = id;
        this.address = address;
        this.location = location;
        this.openingHoursWithdrawal = openingHoursWithdrawal;
        this.openingHoursDeposit = openingHoursDeposit;
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
