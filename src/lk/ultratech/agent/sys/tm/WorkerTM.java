package lk.ultratech.agent.sys.tm;

public class WorkerTM {
    private String workerId;
    private String name;
    private int telephone;
    private String type;

    public WorkerTM() {
    }

    public WorkerTM(String workerId, String name, int telephone, String type) {
        this.setWorkerId(workerId);
        this.setName(name);
        this.setTelephone(telephone);
        this.setType(type);
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
}
