package cz.polreich.banks.model.erste;

/**
 * Created by Martin on 08.03.2018.
 */

public class ErsteATM extends ErstePlace {

    private String bankCode;
    private String accessType;

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }
}
