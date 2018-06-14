package cz.polreich.banks.model;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import cz.polreich.banks.model.airBank.AirBankOpeningHoursDay;
import cz.polreich.banks.model.erste.ErsteInterval;

@Entity
public class UniOpeningHours {

    @PrimaryKey(autoGenerate = true)
    private int ohId;
    private int weekday;
    private String from;
    private String to;

    public UniOpeningHours() {
    }

    @Ignore
    public UniOpeningHours(AirBankOpeningHoursDay openingHoursDay) {
        this.weekday = openingHoursDay.getDayOfWeek();
        this.from = openingHoursDay.getOpening();
        this.to = openingHoursDay.getClosing();
    }

    @Ignore
    public UniOpeningHours(int weekday, ErsteInterval ersteInterval) {
        this.weekday = weekday;
        this.from = ersteInterval.getFrom();
        this.to = ersteInterval.getTo();
    }

    public int getOhId() {
        return ohId;
    }

    public void setOhId(int ohId) {
        this.ohId = ohId;
    }

    public int getWeekday() {
        return weekday;
    }

    public void setWeekday(int weekday) {
        this.weekday = weekday;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
