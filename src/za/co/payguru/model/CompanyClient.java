package za.co.payguru.model;

public class CompanyClient {
	public static String COMP_CLIENT_ACTIVE = "1";
	
	private int compId = 0;
	private int clientId = 0;
	private String createDate = "";
	private String createTime = "";
	private String status = "";
	private String statusDate = "";
	private String statusTime = "";
	private String clientExtRef = "";
	public CompanyClient() {
		this.compId = 0;
		this.clientId = 0;
		this.createDate = "";
		this.createTime = "";
		this.status = "";
		this.statusDate = "";
		this.statusTime = "";
		this.clientExtRef = "";
	}
	public CompanyClient(int compId, int clientId, String createDate, String createTime, String status,
			String statusDate, String statusTime, String clientExtRef) {
		super();
		this.compId = compId;
		this.clientId = clientId;
		this.createDate = createDate;
		this.createTime = createTime;
		this.status = status;
		this.statusDate = statusDate;
		this.statusTime = statusTime;
		this.clientExtRef = clientExtRef;
	}
	public int getCompId() {
		return compId;
	}
	public void setCompId(int compId) {
		this.compId = compId;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatusDate() {
		return statusDate;
	}
	public void setStatusDate(String statusDate) {
		this.statusDate = statusDate;
	}
	public String getStatusTime() {
		return statusTime;
	}
	public void setStatusTime(String statusTime) {
		this.statusTime = statusTime;
	}
	public String getClientExtRef() {
		return clientExtRef;
	}
	public void setClientExtRef(String clientExtRef) {
		this.clientExtRef = clientExtRef;
	}
	@Override
	public String toString() {
		return "CompanyClients [compId=" + compId + ", clientId=" + clientId + ", createDate=" + createDate
				+ ", createTime=" + createTime + ", status=" + status + ", statusDate=" + statusDate + ", statusTime="
				+ statusTime + ", clientExtRef=" + clientExtRef + "]";
	}
	
	public String toJsonString() {
		return "{\"compid\" : " + compId + ", \"clientid\" : " + clientId + ", \"createdate\" : \"" + createDate
				+ "\", \"createtime\" : \"" + createTime + "\", \"status\" : \"" + status + "\", \"statusdate\" : \"" + statusDate + "\", \"statustime\" : \""
				+ statusTime + "\", \"clientextref\" : \"" + clientExtRef + "\"}";
	}
	
}
