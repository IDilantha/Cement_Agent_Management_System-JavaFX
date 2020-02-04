package lk.ultratech.agent.sys.entity;

public class Agent {
    private String agentId;
    private String agentCode;
    private String agentName;
    private String region;
    private String territory;

    public Agent() {
    }

    public Agent(String agentId, String agentCode, String agentName, String region, String territory) {
        this.agentId = agentId;
        this.agentCode = agentCode;
        this.agentName = agentName;
        this.region = region;
        this.territory = territory;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getTerritory() {
        return territory;
    }

    public void setTerritory(String territory) {
        this.territory = territory;
    }

    @Override
    public String toString() {
        return "Agent{" +
                "agentId='" + agentId + '\'' +
                ", agentCode='" + agentCode + '\'' +
                ", agentName='" + agentName + '\'' +
                ", region='" + region + '\'' +
                ", territory='" + territory + '\'' +
                '}';
    }
}
