package lk.ultratech.agent.sys.dto;

import java.sql.Date;

public class WorkerPaymentDTO{
    private String workerPaymentId;
    private String workerId;
    private Date date;
    private double amount;

    public WorkerPaymentDTO() {
    }

    public WorkerPaymentDTO(String workerPaymentId, String workerId, Date date, double amount) {
        this.workerPaymentId = workerPaymentId;
        this.workerId = workerId;
        this.date = date;
        this.amount = amount;
    }

    public String getWorkerPaymentId() {
        return workerPaymentId;
    }

    public void setWorkerPaymentId(String workerPaymentId) {
        this.workerPaymentId = workerPaymentId;
    }

    public String getWorkerId() {
        return workerId;
    }

    public void setWorkerId(String workerId) {
        this.workerId = workerId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
