package cz.polreich.banks.model;

import java.util.ArrayList;
import java.util.List;

import cz.polreich.banks.model.airBank.AirBankBranch;
import cz.polreich.banks.model.erste.ErsteBranch;
import cz.polreich.banks.model.erste.ErsteBranchesList;

public class UniBranchesList {

    private List<UniBranch> branches;

    public UniBranchesList(List<AirBankBranch> branches) {
        this.branches = new ArrayList<>();
        for (AirBankBranch branch : branches) {
            this.branches.add(new UniBranch(branch));
        }
    }

    public UniBranchesList(ErsteBranchesList branchesList) {
        this.branches = new ArrayList<>();
        for (ErsteBranch branch : branchesList.getBranches()) {
            this.branches.add(new UniBranch(branch));
        }
    }

    public List<UniBranch> getBranches() {
        return branches;
    }

    public void setBranches(List<UniBranch> branches) {
        this.branches = branches;
    }
}
