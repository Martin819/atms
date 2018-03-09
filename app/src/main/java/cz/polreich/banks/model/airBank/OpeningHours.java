package cz.polreich.banks.model.airBank;

import io.realm.RealmObject;

public class OpeningHours extends RealmObject {

    private boolean isNonstop;
    private OpeningHoursDay[] days;

    public OpeningHours() {
    }

    public OpeningHours(boolean isNonstop, OpeningHoursDay[] days) {
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
