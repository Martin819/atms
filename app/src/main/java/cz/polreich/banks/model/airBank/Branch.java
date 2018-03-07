package cz.polreich.banks.model.airBank;

/**
 * Created by Martin on 21.02.2018.
 */

public class Branch {

    private String id;
    private String name;
    private Address address;
    private String[] phones;
    private Location location;
    private OpeningHours openingHours;
    private String[] services;
    private String[] pictures;

    public Branch() {
    }

    public Branch(String id, String name, Address address, String[] phones, Location location, OpeningHours openingHours, String[] services, String[] pictures) {
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String[] getPhones() {
        return phones;
    }

    public void setPhones(String[] phones) {
        this.phones = phones;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public OpeningHours getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(OpeningHours openingHours) {
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
