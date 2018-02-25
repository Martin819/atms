package cz.polreich.atms.model.airBank;

/**
 * Created by Martin on 21.02.2018.
 */

public class BranchAddress {

    private String streetAddress;
    private String city;
    private String zip;

    public BranchAddress() {
    }

    public BranchAddress(String streetAddress, String city, String zip) {
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

    public String getFullAddress(BranchAddress address) {
        String fullAddress = address.getStreetAddress() + ", " + address.getCity() + ", " + address.getZip();
        return fullAddress;
    }
}
