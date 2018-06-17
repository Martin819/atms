package cz.polreich.banks.model;

import java.util.ArrayList;
import java.util.List;

import cz.polreich.banks.model.airBank.AirBankATM;

public class UniATMsList {

    private List<UniATM> atms;

    public UniATMsList(List<AirBankATM> atms) {
        this.atms = new ArrayList<>();
        for (AirBankATM atm:atms) {
            this.atms.add(new UniATM(atm));
        }
    }

    public List<UniATM> getAtms() {
        return atms;
    }

    public void setAtms(List<UniATM> atms) {
        this.atms = atms;
    }
}
