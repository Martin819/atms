package cz.polreich.banks.model.erste;



public class ErsteInterval  {

    private String from;
    private String to;

    public ErsteInterval() {
    }

    public ErsteInterval(String from, String to) {
        this.from = from;
        this.to = to;
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
