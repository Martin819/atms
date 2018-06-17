package cz.polreich.banks.model.erste;



public class ErsteService  {

    private ErsteServiceType type;
    private String flag;
    private String name;
    private String desc;
    private int qmaticId;

    public ErsteService() {
    }

    public ErsteService(ErsteServiceType type, String flag, String name, String desc, int qmaticId) {
        this.type = type;
        this.flag = flag;
        this.name = name;
        this.desc = desc;
        this.qmaticId = qmaticId;
    }

    public ErsteServiceType getType() {
        return type;
    }

    public void setType(ErsteServiceType type) {
        this.type = type;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getQmaticId() {
        return qmaticId;
    }

    public void setQmaticId(int qmaticId) {
        this.qmaticId = qmaticId;
    }
}
