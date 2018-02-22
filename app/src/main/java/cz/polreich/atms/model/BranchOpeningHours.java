package cz.polreich.atms.model;

/**
 * Created by Martin on 21.02.2018.
 */

public class BranchOpeningHours {

    private boolean isNonstop;
    private OpeningHoursDay[] days;

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
