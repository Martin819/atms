package cz.polreich.banks.model.erste;



public class ErsteLocation  {
    private float lat;
    private float lng;

    public ErsteLocation() {
    }

    public ErsteLocation(float lat, float lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }
}
