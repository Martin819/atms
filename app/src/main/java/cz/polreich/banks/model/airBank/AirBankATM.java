package cz.polreich.banks.model.airBank;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity
public class AirBankATM {

    @PrimaryKey
    @NotNull
    private String id;
    private AirBankAddress address;
    private AirBankLocation location;
    private AirBankOpeningHours openingHoursWithdrawal;
    private AirBankOpeningHours openingHoursDeposit;
    private String bank = "Air Bank";
    private float distance;

    public AirBankATM() {
    }

    @Ignore
    public AirBankATM(@NotNull String id, AirBankAddress address, AirBankLocation location, AirBankOpeningHours openingHoursWithdrawal, AirBankOpeningHours openingHoursDeposit, float distance) {
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

    public AirBankAddress getAddress() {
        return address;
    }

    public void setAddress(AirBankAddress address) {
        this.address = address;
    }

    public AirBankLocation getLocation() {
        return location;
    }

    public void setLocation(AirBankLocation location) {
        this.location = location;
    }

    public AirBankOpeningHours getOpeningHoursWithdrawal() {
        return openingHoursWithdrawal;
    }

    public void setOpeningHoursWithdrawal(AirBankOpeningHours openingHoursWithdrawal) {
        this.openingHoursWithdrawal = openingHoursWithdrawal;
    }

    public AirBankOpeningHours getOpeningHoursDeposit() {
        return openingHoursDeposit;
    }

    public void setOpeningHoursDeposit(AirBankOpeningHours openingHoursDeposit) {
        this.openingHoursDeposit = openingHoursDeposit;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }
}
