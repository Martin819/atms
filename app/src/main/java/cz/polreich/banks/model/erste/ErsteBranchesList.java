package cz.polreich.banks.model.erste;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Martin on 08.03.2018.
 */

public class ErsteBranchesList {
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
