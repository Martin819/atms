package cz.polreich.atms.model.airBank;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Martin on 22.02.2018.
 */

public class BranchesList {
    @SerializedName("data")
    private List<Branch> branches;

    public BranchesList() {
    }

    public BranchesList(List<Branch> branches) {
        this.branches = branches;
    }

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }
}
