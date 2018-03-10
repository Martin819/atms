package cz.polreich.banks.model.airBank;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Address {
    @PrimaryKey(autoGenerate = true)
    private int addressId;
    private String streetAddress;
    private String city;
    private String zip;

    public Address() {
    }

    @Ignore
    public Address(String streetAddress, String city, String zip) {
        this.streetAddress = streetAddress;
        this.city = city;
        this.zip = zip;
    }

    @Ignore
    public Address(int addressId, String streetAddress, String city, String zip) {
        this.addressId = addressId;
        this.streetAddress = streetAddress;
        this.city = city;
        this.zip = zip;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getFullAddress(Address address) {
        String fullAddress = address.getStreetAddress() + ", " + address.getCity() + ", " + address.getZip();
        return fullAddress;
    }
}
