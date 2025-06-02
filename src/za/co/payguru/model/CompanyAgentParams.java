package za.co.payguru.model;

import org.json.JSONObject;

public class CompanyAgentParams {

    private int compId = 0;
    private int agentId = 0;
    private String paramId = "";
    private String paramValue = "";

    public CompanyAgentParams() {
    	 this.compId = 0;
    	 this.agentId = 0;
    	 this.paramId = "";
    	 this.paramValue = "";
    }

    public int getCompId() {
        return compId;
    }

    public void setCompId(int compId) {
        this.compId = compId;
    }

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    public String getParamId() {
        return paramId;
    }

    public void setParamId(String paramId) {
        this.paramId = paramId;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    @Override
    public String toString() {
        return "CompanyAgentParams [compId=" + compId + ", agentId=" + agentId + ", paramId=" + paramId + ", paramValue=" + paramValue + "]";
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        try {
            json.put("compid", compId);
            json.put("agentid", agentId);
            json.put("paramid", paramId);
            json.put("paramvalue", paramValue);
        } catch (Exception e) {
            System.out.println("Error creating JSON: " + e.toString());
        }
        return json;
    }

}
