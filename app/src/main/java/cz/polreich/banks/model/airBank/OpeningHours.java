package cz.polreich.banks.model.airBank;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class OpeningHours  {

    @PrimaryKey(autoGenerate = true)
    private int ohId;
    private boolean isNonstop;
    private OpeningHoursDay[] days;

    public OpeningHours() {
    }

    @Ignore
    public OpeningHours(boolean isNonstop, OpeningHoursDay[] days) {
        this.isNonstop = isNonstop;
        this.days = days;
    }

    @Ignore
    public OpeningHours(int ohId, boolean isNonstop, OpeningHoursDay[] days) {
        this.ohId = ohId;
        this.isNonstop = isNonstop;
        this.days = days;
    }

    public int getOhId() {
        return ohId;
    }

    public void setOhId(int ohId) {
        this.ohId = ohId;
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
