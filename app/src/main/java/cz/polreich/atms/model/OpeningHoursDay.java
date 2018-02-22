package cz.polreich.atms.model;

/**
 * Created by Martin on 21.02.2018.
 */

public class OpeningHoursDay {

    private int dayOfWeek;
    private String opening;
    private String closing;

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
