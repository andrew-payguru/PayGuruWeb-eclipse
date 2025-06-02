package za.co.payguru.model;

import org.json.JSONObject;

public class ClientInvoiceStatus {

	public static final String ACTIVE = "1";
	public static final String PENDING = "2";
	public static final String DONE = "3";
	public static final String ERROR = "4";
	public static final String RETRY = "5";

	private String invNo;
	private String extRefNo;
	private int compId;
	private String status;

	public ClientInvoiceStatus() {
		super();
	}

	public ClientInvoiceStatus(String invNo, String extRefNo, int compId, String status) {
		this.invNo = invNo;
		this.extRefNo = extRefNo;
		this.compId = compId;
		this.status = status;
	}

	public String getInvNo() {
		return invNo;
	}

	public void setInvNo(String invNo) {
		this.invNo = invNo;
	}

	public String getExtRefNo() {
		return extRefNo;
	}

	public void setExtRefNo(String extRefNo) {
		this.extRefNo = extRefNo;
	}

	public int getCompId() {
		return compId;
	}

	public void setCompId(int compId) {
		this.compId = compId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ClientInvoiceStatus [invno=" + invNo + ", extrefno=" + extRefNo + ", compid=" + compId + ", status=" + status + "]";
	}

	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		try {
			json.put("invno", invNo);
			json.put("extrefno", extRefNo);
			json.put("compid", compId);
			json.put("status", status);
		} catch (Exception e) {
			System.out.println("Error creating JSON: " + e.toString());
		}
		return json;
	}

	
}
