package cz.polreich.banks.model.erste;

import io.realm.RealmObject;

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
