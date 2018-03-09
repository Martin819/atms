package cz.polreich.banks.model.airBank;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmObject;

public class BranchesList extends RealmObject {
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
