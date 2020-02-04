package lk.ultratech.agent.sys.tm;

import java.sql.Date;

public class WorkerPaymentTM {
    private Date date;
    private String workerPayId;
    private String workerId;
    private String workerName;
    private String type;
    private double amount;

    public WorkerPaymentTM() {
    }

    public WorkerPaymentTM(Date date, String workerPayId, String workerId, String workerName, String type, double amount) {
        this.date = date;
        this.workerPayId = workerPayId;
        this.workerId = workerId;
        this.workerName = workerName;
        this.type = type;
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getWorkerPayId() {
        return workerPayId;
    }

    public void setWorkerPayId(String workerPayId) {
        this.workerPayId = workerPayId;
    }

    public String getWorkerId() {
        return workerId;
    }

    public void setWorkerId(String workerId) {
        this.workerId = workerId;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
