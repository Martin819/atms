package cz.polreich.banks.model;

import cz.polreich.banks.model.airBank.Address;
import cz.polreich.banks.model.erste.ErsteBranch;

public class UniAddress {
    private String street;
    private String city;
    private String region;
    private String country;
    private String zip;

    public UniAddress(Address address) {
        this.street = address.getStreetAddress();
        this.city = address.getCity();
        this.zip = address.getZip();
    }

    public UniAddress(ErsteBranch branch) {
        this.street = branch.getStreet();
        this.city = branch.getCity();
        this.region = branch.getRegion();
        this.country = branch.getCountry();
        this.zip = branch.getPostCode();
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}