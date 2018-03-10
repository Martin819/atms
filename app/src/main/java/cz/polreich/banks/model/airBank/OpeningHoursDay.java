package cz.polreich.banks.model.airBank;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class OpeningHoursDay  {

    @PrimaryKey(autoGenerate = true)
    private int ohdId;
    private int dayOfWeek;
    private String opening;
    private String closing;

    public OpeningHoursDay() {
    }

    @Ignore
    public OpeningHoursDay(int dayOfWeek, String opening, String closing) {
        this.dayOfWeek = dayOfWeek;
        this.opening = opening;
        this.closing = closing;
    }

    @Ignore
    public OpeningHoursDay(int ohdId, int dayOfWeek, String opening, String closing) {
        this.ohdId = ohdId;
        this.dayOfWeek = dayOfWeek;
        this.opening = opening;
        this.closing = closing;
    }

    public int getOhdId() {
        return ohdId;
    }

    public void setOhdId(int ohdId) {
        this.ohdId = ohdId;
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
