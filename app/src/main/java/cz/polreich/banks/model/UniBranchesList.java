package cz.polreich.banks.model;

import java.util.List;

public class UniBranchesList {

    private List<UniBranch> branches;

    public UniBranchesList() {
    }

    public UniBranchesList(List<UniBranch> branches) {
        this.branches = branches;
    }

    public List<UniBranch> getBranches() {
        return branches;
    }

    public void setBranches(List<UniBranch> branches) {
        this.branches = branches;
    }
}
