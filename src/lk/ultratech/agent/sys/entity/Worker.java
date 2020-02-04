package lk.ultratech.agent.sys.entity;

public class Worker {
    private String workerId;
    private String name;
    private int telephone;
    private String type;

    public Worker() {
    }

    public Worker(String workerId, String name, int telephone, String type) {
        this.workerId = workerId;
        this.name = name;
        this.telephone = telephone;
        this.type = type;
    }

    public String getWorkerId() {
        return workerId;
    }

    public void setWorkerId(String workerId) {
        this.workerId = workerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "workerId='" + workerId + '\'' +
                ", name='" + name + '\'' +
                ", telephone=" + telephone +
                ", type='" + type + '\'' +
                '}';
    }
}
