package lk.ultratech.agent.sys.dto;

public class CementAgentDTO {
    private String cementId;
    private String agentId;
    private double unitPrice;

    public CementAgentDTO() {
    }

    public CementAgentDTO(String cementId, String agentId, double unitPrice) {
        this.cementId = cementId;
        this.agentId = agentId;
        this.unitPrice = unitPrice;
    }

    public String getCementId() {
        return cementId;
    }

    public void setCementId(String cementId) {
        this.cementId = cementId;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
