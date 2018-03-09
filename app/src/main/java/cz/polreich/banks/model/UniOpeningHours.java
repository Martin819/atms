package cz.polreich.banks.model;


import cz.polreich.banks.model.airBank.OpeningHours;
import cz.polreich.banks.model.airBank.OpeningHoursDay;
import cz.polreich.banks.model.erste.ErsteInterval;
import cz.polreich.banks.model.erste.ErsteOpeningHours;

public class UniOpeningHours {
    private int weekday;
    private String from;
    private String to;

    public UniOpeningHours(OpeningHoursDay openingHoursDay) {
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
