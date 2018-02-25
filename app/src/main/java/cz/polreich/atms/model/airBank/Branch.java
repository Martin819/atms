package cz.polreich.atms.model.airBank;

/**
 * Created by Martin on 21.02.2018.
 */

public class Branch {

    private String id;
    private String name;
    private BranchAddress address;
    private String[] phones;
    private BranchLocation location;
    private BranchOpeningHours openingHours;
    private String[] services;
    private String[] pictures;

    public Branch() {
    }

    public Branch(String id, String name, BranchAddress address, String[] phones, BranchLocation location, BranchOpeningHours openingHours, String[] services, String[] pictures) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phones = phones;
        this.location = location;
        this.openingHours = openingHours;
        this.services = services;
        this.pictures = pictures;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BranchAddress getAddress() {
        return address;
    }

    public void setAddress(BranchAddress address) {
        this.address = address;
    }

    public String[] getPhones() {
        return phones;
    }

    public void setPhones(String[] phones) {
        this.phones = phones;
    }

    public BranchLocation getLocation() {
        return location;
    }

    public void setLocation(BranchLocation location) {
        this.location = location;
    }

    public BranchOpeningHours getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(BranchOpeningHours openingHours) {
        this.openingHours = openingHours;
    }

    public String[] getServices() {
        return services;
    }

    public void setServices(String[] services) {
        this.services = services;
    }

    public String[] getPictures() {
        return pictures;
    }

    public void setPictures(String[] pictures) {
        this.pictures = pictures;
    }
}
