package cz.polreich.atms.model.airBank;

/**
 * Created by Martin on 27.02.2018.
 */

public class ATM {
    public String id;
    public Address address;
    public Location location;
    public OpeningHours openingHoursWithdrawal;
    public OpeningHours openingHoursDeposit;

    public ATM() {
    }

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
