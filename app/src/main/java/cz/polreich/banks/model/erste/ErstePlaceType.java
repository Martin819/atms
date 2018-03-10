package cz.polreich.banks.model.erste;



public enum ErstePlaceType {
    BRANCH(1),
    ATM(2);

    private int value;

    private ErstePlaceType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
