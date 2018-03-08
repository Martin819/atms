package cz.polreich.banks.model;

import cz.polreich.banks.model.airBank.Address;
import cz.polreich.banks.model.erste.ErsteBranch;

/**
 * Created by Martin on 07.03.2018.
 */

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
}
