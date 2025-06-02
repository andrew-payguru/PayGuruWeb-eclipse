package za.co.payguru.model;

import java.sql.Date;
import java.sql.Time;

import org.json.JSONObject;

import za.co.payguru.util.DateUtil;

public class AppClient {
	
	public final static String STATUS_INACTIVE = "0";
	public final static String STATUS_ACTIVE = "1";
	public final static String STATUS_IDLE = "2";
	
	private int clientId = 0;
	private String clientCellNo = "";
	private String clientEmail= "";
	private String clientPassword = "";
	private Time clientLastLogTime = DateUtil.DEFAULT_TIME;
	private Date clientLastLogDate = DateUtil.DEFAULT_DATE;
	private String clientStatus = STATUS_ACTIVE;
	private Time clientStatusTime = DateUtil.DEFAULT_TIME;
	private Date clientStatusDate = DateUtil.DEFAULT_DATE;
	private String clientActive = STATUS_ACTIVE;
	private String appRef1 = "";
	private String appRef2 = "";
	private String appRef3 = "";
	private Time createdTime = DateUtil.DEFAULT_TIME;
	private Date createdDate = DateUtil.DEFAULT_DATE;
	public AppClient() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AppClient(int clientId, String clientCellNo, String clientEmail, String clientPassword, Time clientLastLogTime,
			Date clientLastLogDate, String clientStatus, Time clientStatusTime, Date clientStatusDate, String clientActive,
			String appRef1, String appRef2, String appRef3, Time createdTime, Date createdDate) {
		super();
		this.clientId = clientId;
		this.clientCellNo = clientCellNo;
		this.clientEmail = clientEmail;
		this.clientPassword = clientPassword;
		this.clientLastLogTime = clientLastLogTime;
		this.clientLastLogDate = clientLastLogDate;
		this.clientStatus = clientStatus;
		this.clientStatusTime = clientStatusTime;
		this.clientStatusDate = clientStatusDate;
		this.clientActive = clientActive;
		this.appRef1 = appRef1;
		this.appRef2 = appRef2;
		this.appRef3 = appRef3;
		this.createdTime = createdTime;
		this.createdDate = createdDate;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public String getClientCellNo() {
		return clientCellNo;
	}
	public void setClientCellNo(String clientCellNo) {
		this.clientCellNo = clientCellNo;
	}
	public String getClientEmail() {
		return clientEmail;
	}
	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}
	public String getClientPassword() {
		return clientPassword;
	}
	public void setClientPassword(String clientPassword) {
		this.clientPassword = clientPassword;
	}
	public Time getClientLastLogTime() {
		return clientLastLogTime;
	}
	public void setClientLastLogTime(Time clientLastLogTime) {
		this.clientLastLogTime = clientLastLogTime;
	}
	public Date getClientLastLogDate() {
		return clientLastLogDate;
	}
	public void setClientLastLogDate(Date clientLastLogDate) {
		this.clientLastLogDate = clientLastLogDate;
	}
	public String getClientStatus() {
		return clientStatus;
	}
	public void setClientStatus(String clientStatus) {
		this.clientStatus = clientStatus;
	}
	public Time getClientStatusTime() {
		return clientStatusTime;
	}
	public void setClientStatusTime(Time clientStatusTime) {
		this.clientStatusTime = clientStatusTime;
	}
	public Date getClientStatusDate() {
		return clientStatusDate;
	}
	public void setClientStatusDate(Date clientStatusDate) {
		this.clientStatusDate = clientStatusDate;
	}
	public String getClientActive() {
		return clientActive;
	}
	public void setClientActive(String clientActive) {
		this.clientActive = clientActive;
	}
	public String getAppRef1() {
		return appRef1;
	}
	public void setAppRef1(String appRef1) {
		this.appRef1 = appRef1;
	}
	public String getAppRef2() {
		return appRef2;
	}
	public void setAppRef2(String appRef2) {
		this.appRef2 = appRef2;
	}
	public String getAppRef3() {
		return appRef3;
	}
	public void setAppRef3(String appRef3) {
		this.appRef3 = appRef3;
	}
	public Time getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Time createdTime) {
		this.createdTime = createdTime;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	@Override
	public String toString() {
		return "AppClient [clientId=" + clientId + ", clientCellNo=" + clientCellNo + ", clientEmail=" + clientEmail
				+ ", clientPassword=" + clientPassword + ", clientLastLogTime=" + clientLastLogTime + ", clientLastLogDate="
				+ clientLastLogDate + ", clientStatus=" + clientStatus + ", clientStatusTime=" + clientStatusTime
				+ ", clientStatusDate=" + clientStatusDate + ", clientActive=" + clientActive + ", appRef1=" + appRef1
				+ ", appRef2=" + appRef2 + ", appRef3=" + appRef3 + ", createdTime=" + createdTime + ", createdDate="
				+ createdDate + "]";
	}
	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		try {
			json.put("clientid", clientId);
			json.put("clientcellno", clientCellNo);
			json.put("clientemail", clientEmail);
			json.put("clientpassword", clientPassword);
			json.put("clientlastlogtime", clientLastLogTime);
			json.put("clientlastlogdate", clientLastLogDate);
			json.put("clientstatus", clientStatus);
			json.put("clientstatustime", clientStatusTime);
			json.put("clientstatusdate", clientStatusDate);
			json.put("clientactive", clientActive);
			json.put("appref1", appRef1);
			json.put("appref2", appRef2);
			json.put("appref3", appRef3);
			json.put("createdtime", createdTime);
			json.put("createddate", createdDate);
		}catch (Exception e) {
			System.out.println("Error creating json: " + e.toString());
		}
		return json;
	}
	public String toJsonString() {
		return "{\"clientid\" : " + clientId + ", \"clientcellno\" : \"" + clientCellNo + "\", \"clientemail\" : \"" + clientEmail
				+ "\", \"clientpassword\" : \"" + clientPassword + "\", \"clientlastlogtime\" : \"" + clientLastLogTime 
				+ "\", \"clientlastlogdate\" : \""+ clientLastLogDate + "\", \"clientstatus\" : \"" + clientStatus 
				+ "\", \"clientstatustime\" : \"" + clientStatusTime + "\", \"clientstatusdate\" : \"" + clientStatusDate 
				+ "\", \"clientactive\" : \"" + clientActive + "\", \"appref1\" : \"" + appRef1	+ "\", \"appref2\" : \"" + appRef2
				+ "\", \"appref3\" : \"" + appRef3 + "\", \"createdtime\" : \"" + createdTime + "\", \"createddate\" : \"" + createdDate + "\"}";
	}
	
}
