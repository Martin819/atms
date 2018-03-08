package cz.polreich.banks.model.erste;

/**
 * Created by Martin on 08.03.2018.
 */

public class ErsteOpeningHours {
    private ErsteDay weekday;
    private ErsteInterval[] intervals;

    public ErsteOpeningHours() {
    }

    public ErsteOpeningHours(ErsteDay weekday, ErsteInterval[] intervals) {
        this.weekday = weekday;
        this.intervals = intervals;
    }

    public ErsteDay getWeekday() {
        return weekday;
    }

    public void setWeekday(ErsteDay weekday) {
        this.weekday = weekday;
    }

    public ErsteInterval[] getIntervals() {
        return intervals;
    }

    public void setIntervals(ErsteInterval[] intervals) {
        this.intervals = intervals;
    }
}
