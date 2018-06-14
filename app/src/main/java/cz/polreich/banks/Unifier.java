package cz.polreich.banks;

import cz.polreich.banks.model.airBank.AirBankBranch;
import cz.polreich.banks.model.erste.ErsteBranch;

public interface Unifier {

    public static AirBankBranch unifyBranch (ErsteBranch ersteBranch) {
        return new AirBankBranch();
    }

}
