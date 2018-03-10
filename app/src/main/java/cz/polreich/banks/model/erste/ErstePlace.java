package cz.polreich.banks.model.erste;

import com.google.gson.annotations.SerializedName;



public class ErstePlace  {
    private int id;
    private ErsteLocation location;
    private ErstePlaceType type;
    private ErstePlaceState state;
    private String stateNote;
    private int distance;
    private String name;
    @SerializedName("address")
    private String street;
    private String city;
    private String postCode;
    private String country;
    private String region;
    private String district;
    private ErsteService[] services;
    private ErsteOpeningHours[] openingHours;
    private String managerName;
    private String courierCode;
    private String branchNum;
    private ErsteEquipment[] equipment;
    private ErsteManager manager;
    private ErsteOutage[] outages;

    public ErstePlace() {
    }

    public ErstePlace(int id, ErsteLocation location, ErstePlaceType type, ErstePlaceState state, String stateNote, int distance, String name, String street, String city, String postCode, String country, String region, String district, ErsteService[] services, ErsteOpeningHours[] openingHours, String managerName, String courierCode, String branchNum, ErsteEquipment[] equipment, ErsteManager manager, ErsteOutage[] outages) {
        this.id = id;
        this.location = location;
        this.type = type;
        this.state = state;
        this.stateNote = stateNote;
        this.distance = distance;
        this.name = name;
        this.street = street;
        this.city = city;
        this.postCode = postCode;
        this.country = country;
        this.region = region;
        this.district = district;
        this.services = services;
        this.openingHours = openingHours;
        this.managerName = managerName;
        this.courierCode = courierCode;
        this.branchNum = branchNum;
        this.equipment = equipment;
        this.manager = manager;
        this.outages = outages;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ErsteLocation getLocation() {
        return location;
    }

    public void setLocation(ErsteLocation location) {
        this.location = location;
    }

    public ErstePlaceType getType() {
        return type;
    }

    public void setType(ErstePlaceType type) {
        this.type = type;
    }

    public ErstePlaceState getState() {
        return state;
    }

    public void setState(ErstePlaceState state) {
        this.state = state;
    }

    public String getStateNote() {
        return stateNote;
    }

    public void setStateNote(String stateNote) {
        this.stateNote = stateNote;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public ErsteService[] getServices() {
        return services;
    }

    public void setServices(ErsteService[] services) {
        this.services = services;
    }

    public ErsteOpeningHours[] getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(ErsteOpeningHours[] openingHours) {
        this.openingHours = openingHours;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getCourierCode() {
        return courierCode;
    }

    public void setCourierCode(String courierCode) {
        this.courierCode = courierCode;
    }

    public String getBranchNum() {
        return branchNum;
    }

    public void setBranchNum(String branchNum) {
        this.branchNum = branchNum;
    }

    public ErsteEquipment[] getEquipment() {
        return equipment;
    }

    public void setEquipment(ErsteEquipment[] equipment) {
        this.equipment = equipment;
    }

    public ErsteManager getManager() {
        return manager;
    }

    public void setManager(ErsteManager manager) {
        this.manager = manager;
    }

    public ErsteOutage[] getOutages() {
        return outages;
    }

    public void setOutages(ErsteOutage[] outages) {
        this.outages = outages;
    }
}
