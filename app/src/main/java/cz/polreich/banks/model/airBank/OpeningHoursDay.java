package cz.polreich.banks.model.airBank;

import io.realm.RealmObject;

public class OpeningHoursDay extends RealmObject {

    private int dayOfWeek;
    private String opening;
    private String closing;

    public OpeningHoursDay() {
    }

    public OpeningHoursDay(int dayOfWeek, String opening, String closing) {
        this.dayOfWeek = dayOfWeek;
        this.opening = opening;
        this.closing = closing;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getOpening() {
        return opening;
    }

    public void setOpening(String opening) {
        this.opening = opening;
    }

    public String getClosing() {
        return closing;
    }

    public void setClosing(String closing) {
        this.closing = closing;
    }
}
