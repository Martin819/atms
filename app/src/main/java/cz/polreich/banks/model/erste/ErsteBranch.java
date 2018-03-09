package cz.polreich.banks.model.erste;

import com.google.gson.annotations.SerializedName;

public class ErsteBranch extends ErstePlace {

    private ErsteCashWithdrawal cashWithdrawal;
    private String email;
    private String[] phones;
    private boolean moreBuildings;

    public ErsteBranch() {
    }

    public ErsteBranch(ErsteCashWithdrawal cashWithdrawal, String email, String[] phones, boolean moreBuildings) {
        this.cashWithdrawal = cashWithdrawal;
        this.email = email;
        this.phones = phones;
        this.moreBuildings = moreBuildings;
    }

    public ErsteCashWithdrawal getCashWithdrawal() {
        return cashWithdrawal;
    }

    public void setCashWithdrawal(ErsteCashWithdrawal cashWithdrawal) {
        this.cashWithdrawal = cashWithdrawal;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String[] getPhones() {
        return phones;
    }

    public void setPhones(String[] phones) {
        this.phones = phones;
    }

    public boolean isMoreBuildings() {
        return moreBuildings;
    }

    public void setMoreBuildings(boolean moreBuildings) {
        this.moreBuildings = moreBuildings;
    }
}
