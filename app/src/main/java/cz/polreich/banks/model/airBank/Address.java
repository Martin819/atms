package cz.polreich.banks.model.airBank;

import io.realm.RealmObject;

public class Address extends RealmObject {

    private String streetAddress;
    private String city;
    private String zip;

    public Address() {
    }

    public Address(String streetAddress, String city, String zip) {
        this.streetAddress = streetAddress;
        this.city = city;
        this.zip = zip;
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
