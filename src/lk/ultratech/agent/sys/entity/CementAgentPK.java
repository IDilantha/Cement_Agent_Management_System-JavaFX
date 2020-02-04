package lk.ultratech.agent.sys.entity;

public class CementAgentPK {
    private String cementId;
    private String agentId;

    public CementAgentPK(String cementId, String agentId) {
        this.cementId = cementId;
        this.agentId = agentId;
    }

    public CementAgentPK() {
    }

    @Override
    public String toString() {
        return "CementAgentPK{" +
                "cementId='" + cementId + '\'' +
                ", agentId='" + agentId + '\'' +
                '}';
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
}
