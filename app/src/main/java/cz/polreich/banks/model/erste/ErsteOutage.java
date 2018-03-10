package cz.polreich.banks.model.erste;

import java.util.Date;



public class ErsteOutage  {

    private String branchId;
    private Date from;
    private Date to;
    private String note;
    private boolean inWorkingHours;
    private boolean in30Days;
    private boolean active;

    public ErsteOutage() {
    }

    public ErsteOutage(String branchId, Date from, Date to, String note, boolean inWorkingHours, boolean in30Days, boolean active) {
        this.branchId = branchId;
        this.from = from;
        this.to = to;
        this.note = note;
        this.inWorkingHours = inWorkingHours;
        this.in30Days = in30Days;
        this.active = active;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isInWorkingHours() {
        return inWorkingHours;
    }

    public void setInWorkingHours(boolean inWorkingHours) {
        this.inWorkingHours = inWorkingHours;
    }

    public boolean isIn30Days() {
        return in30Days;
    }

    public void setIn30Days(boolean in30Days) {
        this.in30Days = in30Days;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
