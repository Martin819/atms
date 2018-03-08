package cz.polreich.banks.model.erste;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Martin on 08.03.2018.
 */

public class ErsteATMsList {
    @SerializedName("items")
    private List<ErsteATM> amts;

    public ErsteATMsList() {
    }

    public ErsteATMsList(List<ErsteATM> amts) {
        this.amts = amts;
    }

    public List<ErsteATM> getAmts() {
        return amts;
    }

    public void setAmts(List<ErsteATM> amts) {
        this.amts = amts;
    }
}
