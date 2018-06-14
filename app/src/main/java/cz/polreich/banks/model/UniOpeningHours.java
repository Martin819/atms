package cz.polreich.banks.model;


import cz.polreich.banks.model.airBank.AirBankOpeningHoursDay;
import cz.polreich.banks.model.erste.ErsteInterval;

public class UniOpeningHours {
    private int weekday;
    private String from;
    private String to;

    public UniOpeningHours(AirBankOpeningHoursDay openingHoursDay) {
        this.weekday = openingHoursDay.getDayOfWeek();
        this.from = openingHoursDay.getOpening();
        this.to = openingHoursDay.getClosing();
    }

    public UniOpeningHours(int weekday, ErsteInterval ersteInterval) {
        this.weekday = weekday;
        this.from = ersteInterval.getFrom();
        this.to = ersteInterval.getTo();
    }
}
