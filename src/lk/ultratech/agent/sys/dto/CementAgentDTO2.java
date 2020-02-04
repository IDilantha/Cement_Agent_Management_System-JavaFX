package lk.ultratech.agent.sys.dto;

public class CementAgentDTO2 {
    private String cementId;
    private String agentId;

    public CementAgentDTO2() {
    }

    public CementAgentDTO2(String cementId, String agentId) {
        this.cementId = cementId;
        this.agentId = agentId;
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
