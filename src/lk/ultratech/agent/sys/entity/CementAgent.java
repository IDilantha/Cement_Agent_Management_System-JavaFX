package lk.ultratech.agent.sys.entity;

public class CementAgent implements SuperEntity{

    private CementAgentPK cementAgentPK;
    private double unitPrice;

    public CementAgent(CementAgentPK cementAgentPK, double unitPrice) {
        this.cementAgentPK = cementAgentPK;
        this.unitPrice = unitPrice;
    }

    public CementAgent() {
    }

    public CementAgent(String cementId, String agentId, double unitPrice) {
        this.cementAgentPK = new CementAgentPK(cementId,agentId);
        this.unitPrice = unitPrice;
    }


    public CementAgentPK getCementAgentPK() {
        return cementAgentPK;
    }

    public void setCementAgentPK(CementAgentPK cementAgentPK) {
        this.cementAgentPK = cementAgentPK;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "CementAgent{" +
                "cementAgentPK=" + cementAgentPK +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
