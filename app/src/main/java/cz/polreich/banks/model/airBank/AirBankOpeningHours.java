package cz.polreich.banks.model.airBank;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class AirBankOpeningHours {

    @PrimaryKey(autoGenerate = true)
    private int ohId;
    private boolean isNonstop;
    private AirBankOpeningHoursDay[] days;

    public AirBankOpeningHours() {
    }

    @Ignore
    public AirBankOpeningHours(boolean isNonstop, AirBankOpeningHoursDay[] days) {
        this.isNonstop = isNonstop;
        this.days = days;
    }

    @Ignore
    public AirBankOpeningHours(int ohId, boolean isNonstop, AirBankOpeningHoursDay[] days) {
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

    public AirBankOpeningHoursDay[] getDays() {
        return days;
    }

    public void setDays(AirBankOpeningHoursDay[] days) {
        this.days = days;
    }
}
