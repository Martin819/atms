package cz.polreich.banks.model.erste;

/**
 * Created by Martin on 08.03.2018.
 */

public class ErsteCashWithdrawal {
    private int limit;
    private String excessDeadline;

    public ErsteCashWithdrawal() {
    }

    public ErsteCashWithdrawal(int limit, String excessDeadline) {
        this.limit = limit;
        this.excessDeadline = excessDeadline;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getExcessDeadline() {
        return excessDeadline;
    }

    public void setExcessDeadline(String excessDeadline) {
        this.excessDeadline = excessDeadline;
    }
}
