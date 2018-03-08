package cz.polreich.banks.model.erste;

/**
 * Created by Martin on 08.03.2018.
 */

public enum ErsteServiceType {

    BRANCH_TYPE(1),
    BRANCH_SERVICE(2),
    SPECIALIST(3),
    QUEUE(4),
    ATM_TYPE(5),
    ATM_SERVICE(6);

    private int value;

    ErsteServiceType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
