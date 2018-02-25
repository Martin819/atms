package cz.polreich.atms.model.airBank;

/**
 * Created by Martin on 21.02.2018.
 */

public class BranchOpeningHours {

    private boolean isNonstop;
    private OpeningHoursDay[] days;

    public BranchOpeningHours() {
    }

    public BranchOpeningHours(boolean isNonstop, OpeningHoursDay[] days) {
        this.isNonstop = isNonstop;
        this.days = days;
    }

    public boolean isNonstop() {
        return isNonstop;
    }

    public void setNonstop(boolean nonstop) {
        isNonstop = nonstop;
    }

    public OpeningHoursDay[] getDays() {
        return days;
    }

    public void setDays(OpeningHoursDay[] days) {
        this.days = days;
    }
}
