package cz.polreich.banks.model.airBank;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmObject;

public class ATMsList extends RealmObject {
    @SerializedName("data")
    private List<ATM> atms;

    public ATMsList() {
    }

    public ATMsList(List<ATM> atms) {
        this.atms = atms;
    }

    public List<ATM> getAtms() {
        return atms;
    }

    public void setAtms(List<ATM> atms) {
        this.atms = atms;
    }
}
