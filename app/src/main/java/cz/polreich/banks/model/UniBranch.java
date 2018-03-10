package cz.polreich.banks.model;

import cz.polreich.banks.model.airBank.Branch;
import cz.polreich.banks.model.airBank.OpeningHoursDay;
import cz.polreich.banks.model.erste.ErsteBranch;
import cz.polreich.banks.utils;

public class UniBranch {

    private String id;
    private String name;
    private UniAddress address;
    private String[] phones;
    private String email;
    private UniOpeningHours[] openingHours;
    private String[] images;
    private UniLocation location;


    public UniBranch(Branch branch) {
        this.id = branch.getId();
        this.name = branch.getName();
        this.address = new UniAddress(branch.getAddress());
        this.phones = branch.getPhones();
        this.images = branch.getPictures();
        this.location = new UniLocation(branch.getLocation());
    }

    public UniBranch(ErsteBranch branch) {
        this.id = Integer.toString(branch.getId());
        this.name = branch.getName();
        this.address = new UniAddress(branch);
        this.phones = branch.getPhones();
        this.location = new UniLocation(branch.getLocation());
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
}