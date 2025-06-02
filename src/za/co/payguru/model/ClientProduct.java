package za.co.payguru.model;

import org.json.JSONObject;

public class ClientProduct {
	public static final int IN_ACTIVE = 0;
	public static final int ACTIVE = 1;
	
	public static final int PAYGURU_WALLET = -1;
	
	public static final String WALLET_TYPE_NORMAL = "1";
	public static final String WALLET_TYPE_EXTERNAL = "2";
	
	private int clientId = 0;
	private int compId = 0;
	private int prodId = 0;
	private String createDate = "";
	private String createTime = "";
	private int status = 0;
	private String statusDate = "";
	private String statusTime = "";
	private double prodDisc = 0;
	private double prodRsp = 0;
	private double prodCycle = 0;
	private String prodRef = "";
	private String prodData = "";
	private int donorid = -1;
	public ClientProduct() {
		this.clientId = 0;
		this.compId = 0;
		this.prodId = 0;
		this.createDate = "";
		this.createTime = "";
		this.status = 0;
		this.statusDate = "";
		this.statusTime = "";
		this.prodDisc = 0;
		this.prodRsp = 0;
		this.prodCycle = 0;
		this.prodRef = "";
		this.prodData = "";
		this.donorid = -1;
	}
	
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public int getCompId() {
		return compId;
	}
	public void setCompId(int compId) {
		this.compId = compId;
	}
	public int getProdId() {
		return prodId;
	}
	public void setProdId(int prodId) {
		this.prodId = prodId;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
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
	public double getProdDisc() {
		return prodDisc;
	}
	public void setProdDisc(double prodDisc) {
		this.prodDisc = prodDisc;
	}
	public double getProdRsp() {
		return prodRsp;
	}
	public void setProdRsp(double prodRsp) {
		this.prodRsp = prodRsp;
	}
	public double getProdCycle() {
		return prodCycle;
	}
	public void setProdCycle(double prodCycle) {
		this.prodCycle = prodCycle;
	}
	public String getProdRef() {
		return prodRef;
	}
	public void setProdRef(String prodRef) {
		this.prodRef = prodRef;
	}
	public String getProdData() {
		return prodData;
	}
	public void setProdData(String prodData) {
		this.prodData = prodData;
	}
	public int getDonorid() {
		return donorid;
	}
	public void setDonorid(int donorid) {
		this.donorid = donorid;
	}	
	@Override
	public String toString() {
		return "ClientProduct [clientId=" + clientId + ", compId=" + compId + ", prodId=" + prodId + ", createDate="
				+ createDate + ", createTime=" + createTime + ", status=" + status + ", statusDate=" + statusDate
				+ ", statusTime=" + statusTime + ", prodDisc=" + prodDisc + ", prodRsp=" + prodRsp + ", prodCycle="
				+ prodCycle + ", prodRef=" + prodRef + ", prodData=" + prodData + ", donorid=" + donorid + "]";
	}

	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		try {
			json.put("clientid", clientId);
			json.put("compid", compId);
			json.put("prodid", prodId);
			json.put("createdate", createDate);
			json.put("createtime", createTime);
			json.put("status", status);
			json.put("statusdate", statusDate);
			json.put("statustime", statusTime);
			json.put("proddisc", prodDisc);
			json.put("prodrsp", prodRsp);
			json.put("prodcycle", prodCycle);
			json.put("prodref", prodRef);
			json.put("proddata", prodData);
			json.put("donorid", donorid);
		}catch (Exception e) {
			System.out.println("Error creating json: " + e.toString());
		}
		return json;
	}
		
}
