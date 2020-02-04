package lk.ultratech.agent.sys.dto;

public class WorkerDTO {
    private String workerId;
    private String name;
    private int telephone;
    private String type;

    public WorkerDTO() {
    }

    public WorkerDTO(String workerId, String name, int telephone, String type) {
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
}
