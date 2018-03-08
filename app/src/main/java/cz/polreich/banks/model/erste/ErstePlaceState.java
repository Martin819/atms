package cz.polreich.banks.model.erste;

/**
 * Created by Martin on 08.03.2018.
 */

public enum ErstePlaceState {

    OPEN(1),
    CLOSED(2),
    OUT_OF_ORDER(3);

    private int value;

    ErstePlaceState(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
