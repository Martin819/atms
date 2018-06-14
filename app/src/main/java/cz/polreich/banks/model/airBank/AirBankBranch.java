package cz.polreich.banks.model.airBank;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity
public class AirBankBranch {

    @PrimaryKey
    @NotNull
    private String id;
    private String name;
    private AirBankAddress address;
    private String[] phones;
    private AirBankLocation location;
    private AirBankOpeningHours openingHours;
    private String[] services;
    private String[] pictures;
    @Ignore
    private float distance;

    public AirBankBranch() {
    }

    @Ignore
    public AirBankBranch(@NotNull String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Ignore
    public AirBankBranch(@NotNull String id, String name, AirBankAddress address, String[] phones, AirBankLocation location, AirBankOpeningHours openingHours, String[] services, String[] pictures, float distance) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phones = phones;
        this.location = location;
        this.openingHours = openingHours;
        this.services = services;
        this.pictures = pictures;
        this.distance = distance;
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

    public AirBankAddress getAddress() {
        return address;
    }

    public void setAddress(AirBankAddress address) {
        this.address = address;
    }

    public String[] getPhones() {
        return phones;
    }

    public void setPhones(String[] phones) {
        this.phones = phones;
    }

    public AirBankLocation getLocation() {
        return location;
    }

    public void setLocation(AirBankLocation location) {
        this.location = location;
    }

    public AirBankOpeningHours getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(AirBankOpeningHours openingHours) {
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

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }
}
