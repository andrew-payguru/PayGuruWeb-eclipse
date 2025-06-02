package za.co.payguru.model;

import org.json.JSONObject;

import za.co.payguru.util.DateUtil;

import java.sql.Date;
import java.sql.Time;

public class Agent {

    private int agentId =0 ;
    private String webUserId = "";
    private String webUserPass = "";
    private String apiRef = "";
    private String apiUserId = "";
    private String apiUserPass = "";
    private double dailyLimit = 0;
    private String srcIp = "";
    private int apiAuthType = 0;
    private long apiAuthTokenExpireTime = 0;
    private String apiAuthToken = "";
    private Date apiAuthTokenDate = DateUtil.DEFAULT_DATE;
    private Time apiAuthTokenTime = DateUtil.DEFAULT_TIME;
    private long apiAuthTokenExpireMillis = 0;
    private String apiAuthTokenRefresh = "";
    private long apiAuthTokenRefreshExpireMillis = 0;
    private int apiAuthTokenType = 0;
    private int apiMaxType1 = 0;
    private int apiMaxType2 = 0;
    private int apiMaxType3 = 0;
    private int compId = 0;

    public Agent() {
        super();
    }

    

    public int getAgentId() {
		return agentId;
	}



	public void setAgentId(int agentId) {
		this.agentId = agentId;
	}



	public String getWebUserId() {
		return webUserId;
	}



	public void setWebUserId(String webUserId) {
		this.webUserId = webUserId;
	}



	public String getWebUserPass() {
		return webUserPass;
	}



	public void setWebUserPass(String webUserPass) {
		this.webUserPass = webUserPass;
	}



	public String getApiRef() {
		return apiRef;
	}



	public void setApiRef(String apiRef) {
		this.apiRef = apiRef;
	}



	public String getApiUserId() {
		return apiUserId;
	}



	public void setApiUserId(String apiUserId) {
		this.apiUserId = apiUserId;
	}



	public String getApiUserPass() {
		return apiUserPass;
	}



	public void setApiUserPass(String apiUserPass) {
		this.apiUserPass = apiUserPass;
	}



	public double getDailyLimit() {
		return dailyLimit;
	}



	public void setDailyLimit(double dailyLimit) {
		this.dailyLimit = dailyLimit;
	}



	public String getSrcIp() {
		return srcIp;
	}



	public void setSrcIp(String srcIp) {
		this.srcIp = srcIp;
	}



	public int getApiAuthType() {
		return apiAuthType;
	}



	public void setApiAuthType(int apiAuthType) {
		this.apiAuthType = apiAuthType;
	}



	public long getApiAuthTokenExpireTime() {
		return apiAuthTokenExpireTime;
	}



	public void setApiAuthTokenExpireTime(long apiAuthTokenExpireTime) {
		this.apiAuthTokenExpireTime = apiAuthTokenExpireTime;
	}



	public String getApiAuthToken() {
		return apiAuthToken;
	}



	public void setApiAuthToken(String apiAuthToken) {
		this.apiAuthToken = apiAuthToken;
	}



	public Date getApiAuthTokenDate() {
		return apiAuthTokenDate;
	}



	public void setApiAuthTokenDate(Date apiAuthTokenDate) {
		this.apiAuthTokenDate = apiAuthTokenDate;
	}



	public Time getApiAuthTokenTime() {
		return apiAuthTokenTime;
	}



	public void setApiAuthTokenTime(Time apiAuthTokenTime) {
		this.apiAuthTokenTime = apiAuthTokenTime;
	}



	public long getApiAuthTokenExpireMillis() {
		return apiAuthTokenExpireMillis;
	}



	public void setApiAuthTokenExpireMillis(long apiAuthTokenExpireMillis) {
		this.apiAuthTokenExpireMillis = apiAuthTokenExpireMillis;
	}



	public String getApiAuthTokenRefresh() {
		return apiAuthTokenRefresh;
	}



	public void setApiAuthTokenRefresh(String apiAuthTokenRefresh) {
		this.apiAuthTokenRefresh = apiAuthTokenRefresh;
	}



	public long getApiAuthTokenRefreshExpireMillis() {
		return apiAuthTokenRefreshExpireMillis;
	}



	public void setApiAuthTokenRefreshExpireMillis(long apiAuthTokenRefreshExpireMillis) {
		this.apiAuthTokenRefreshExpireMillis = apiAuthTokenRefreshExpireMillis;
	}



	public int getApiAuthTokenType() {
		return apiAuthTokenType;
	}



	public void setApiAuthTokenType(int apiAuthTokenType) {
		this.apiAuthTokenType = apiAuthTokenType;
	}



	public int getApiMaxType1() {
		return apiMaxType1;
	}



	public void setApiMaxType1(int apiMaxType1) {
		this.apiMaxType1 = apiMaxType1;
	}



	public int getApiMaxType2() {
		return apiMaxType2;
	}



	public void setApiMaxType2(int apiMaxType2) {
		this.apiMaxType2 = apiMaxType2;
	}



	public int getApiMaxType3() {
		return apiMaxType3;
	}



	public void setApiMaxType3(int apiMaxType3) {
		this.apiMaxType3 = apiMaxType3;
	}



	public int getCompId() {
		return compId;
	}



	public void setCompId(int compId) {
		this.compId = compId;
	}



	public JSONObject toJSON() {
	    JSONObject json = new JSONObject();
	    try {
	        json.put("agentid", agentId);
	        json.put("webuserid", webUserId);
	        json.put("webuserpass", webUserPass);
	        json.put("apiref", apiRef);
	        json.put("apiuserid", apiUserId);
	        json.put("apiuserpass", apiUserPass);
	        json.put("dailylimit", dailyLimit);
	        json.put("srcip", srcIp);
	        json.put("apiauthtype", apiAuthType);
	        json.put("apiauthtokenexpiretime", apiAuthTokenExpireTime);
	        json.put("apiauthtoken", apiAuthToken);
	        json.put("apiauthtokendate", apiAuthTokenDate.toString());
	        json.put("apiauthtokentime", apiAuthTokenTime.toString());
	        json.put("apiauthtokenexpiremillis", apiAuthTokenExpireMillis);
	        json.put("apiauthtokenrefresh", apiAuthTokenRefresh);
	        json.put("apiauthtokenrefreshexpiremillis", apiAuthTokenRefreshExpireMillis);
	        json.put("apiauthtokentype", apiAuthTokenType);
	        json.put("apimaxtype1", apiMaxType1);
	        json.put("apimaxtype2", apiMaxType2);
	        json.put("apimaxtype3", apiMaxType3);
	        json.put("compid", compId);
	    } catch (Exception e) {
	        System.out.println("Error creating JSON: " + e.toString());
	    }
	    return json;
	}

}
