package cz.polreich.banks.model.airBank;

import com.google.gson.annotations.SerializedName;

import java.util.List;



public class AirBankATMsList {
    @SerializedName("data")
    private List<AirBankATM> atms;

    public AirBankATMsList() {
    }

    public AirBankATMsList(List<AirBankATM> atms) {
        this.atms = atms;
    }

    public List<AirBankATM> getAtms() {
        return atms;
    }

    public void setAtms(List<AirBankATM> atms) {
        this.atms = atms;
    }
}
