package cz.polreich.banks.model.airBank;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import cz.polreich.banks.model.UniBranch;

public class AirBankBranchesList {
    @SerializedName("data")
    private List<AirBankBranch> branches;

    public AirBankBranchesList() {
    }

    public AirBankBranchesList(List<AirBankBranch> branches) {
        this.branches = branches;
    }

    public List<AirBankBranch> getBranches() {
        return branches;
    }

    public void setBranches(List<AirBankBranch> branches) {
        this.branches = branches;
    }
}
