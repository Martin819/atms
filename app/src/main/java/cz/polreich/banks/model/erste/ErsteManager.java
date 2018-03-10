package cz.polreich.banks.model.erste;



public class ErsteManager  {

    private String name;
    private String[] phones;
    private String email;

    public ErsteManager() {
    }

    public ErsteManager(String name, String[] phones, String email) {
        this.name = name;
        this.phones = phones;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getPhones() {
        return phones;
    }

    public void setPhones(String[] phones) {
        this.phones = phones;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
