package cz.polreich.banks.model.erste;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmObject;

public class ErsteBranchesList extends RealmObject {
    @SerializedName("items")
    private List<ErsteBranch> branches;

    public ErsteBranchesList() {
    }

    public ErsteBranchesList(List<ErsteBranch> branches) {
        this.branches = branches;
    }

    public List<ErsteBranch> getBranches() {
        return branches;
    }

    public void setBranches(List<ErsteBranch> branches) {
        this.branches = branches;
    }
}
