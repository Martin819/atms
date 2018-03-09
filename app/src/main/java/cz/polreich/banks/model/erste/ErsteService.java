package cz.polreich.banks.model.erste;

import io.realm.RealmObject;

public class ErsteService extends RealmObject {

    private ErsteServiceType type;
    private String flag;
    private String name;
    private String desc;
    private int qmaticId;
}
