package lk.ultratech.agent.sys.entity;

public class Cement {
    private String cementId;
    private String name;

    public Cement() {
    }

    public Cement(String cementId, String name) {
        this.cementId = cementId;
        this.name = name;
    }

    public String getCementId() {
        return cementId;
    }

    public void setCementId(String cementId) {
        this.cementId = cementId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Cement{" +
                "cementId='" + cementId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
