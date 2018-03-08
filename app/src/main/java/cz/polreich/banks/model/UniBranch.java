package cz.polreich.banks.model;

import cz.polreich.banks.model.airBank.Branch;
import cz.polreich.banks.utils;

/**
 * Created by Martin on 07.03.2018.
 */

public class UniBranch {

    private String name;
    private UniAddress address;
    private String[] phones;
    private String email;
    private String[] images;
    private UniLocation location;


    public UniBranch(Branch branch) {
        this.name = branch.getName();
        this.address = new UniAddress(branch.getAddress());
        this.phones = branch.getPhones();
        this.images = branch.getPictures();
        this.location = new UniLocation(branch.getLocation());
    }
}
