package cz.polreich.banks.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;

import cz.polreich.banks.model.airBank.AirBankBranch;
import cz.polreich.banks.model.erste.ErsteBranch;

@Entity
public class UniBranch {

    @PrimaryKey
    @NotNull
    private String id;
    private String name;
    private UniAddress address;
    private String[] phones;
    private String email;
    private UniOpeningHours[] openingHours;
    private String[] images;
    private UniLocation location;
    private String bank;
    @Ignore
    private float distance;

    public UniBranch() {
    }

    @Ignore
    public UniBranch(AirBankBranch branch) {
        this.id = branch.getId();
        this.name = branch.getName();
        this.address = new UniAddress(branch.getAddress());
        this.phones = branch.getPhones();
        this.images = branch.getPictures();
        this.location = new UniLocation(branch.getLocation());
        this.openingHours = new UniOpeningHours[7];
        for (int i=0; i < branch.getOpeningHours().getDays().length; i++) {
            this.openingHours[i] = new UniOpeningHours(branch.getOpeningHours().getDays()[i]);
        }
        this.bank = "Air Bank";
    }

    @Ignore
    public UniBranch(ErsteBranch branch) {
        this.id = Integer.toString(branch.getId());
        this.name = branch.getName();
        this.address = new UniAddress(branch);
        this.phones = branch.getPhones();
        this.location = new UniLocation(branch.getLocation());
        this.bank = "Erste";
    }

    //TODO: Tune Erste Constructor - Opening Hours

    @NonNull
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

    public UniAddress getAddress() {
        return address;
    }

    public void setAddress(UniAddress address) {
        this.address = address;
    }

    public String[] getPhones() {
        return phones;
    }

    public void setPhones(String[] phones) {
        this.phones = phones;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public UniLocation getLocation() {
        return location;
    }

    public void setLocation(UniLocation location) {
        this.location = location;
    }

    public UniOpeningHours[] getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(UniOpeningHours[] openingHours) {
        this.openingHours = openingHours;
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
