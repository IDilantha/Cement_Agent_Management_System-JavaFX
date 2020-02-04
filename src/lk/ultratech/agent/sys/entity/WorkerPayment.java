package lk.ultratech.agent.sys.entity;

import java.sql.Date;

public class WorkerPayment {
    private String workerPaymentId;
    private String workerId;
    private Date date;
    private double amount;

    public WorkerPayment() {
    }

    public WorkerPayment(String workerPaymentId, String workerId, Date date, double amount) {
        this.workerPaymentId = workerPaymentId;
        this.workerId = workerId;
        this.date = date;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "WorkerPayment{" +
                "workerPaymentId='" + workerPaymentId + '\'' +
                ", workerId='" + workerId + '\'' +
                ", date=" + date +
                ", amount=" + amount +
                '}';
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
