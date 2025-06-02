package za.co.payguru.model;

import org.json.JSONObject;

public class ClientBalance {
	private int clientId = 0;
	private double clientBal = 0;
	private int subacc = 0;
	public ClientBalance() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public double getClientBal() {
		return clientBal;
	}
	public void setClientBal(double clientBal) {
		this.clientBal = clientBal;
	}
	public int getSubacc() {
		return subacc;
	}

	public void setSubacc(int subacc) {
		this.subacc = subacc;
	}

	
	@Override
	public String toString() {
		return "ClientBalance [clientId=" + clientId + ", clientBal=" + clientBal + ", subacc=" + subacc + "]";
	}

	public String toJsonString() {
		return "{\"clientid\" : " + clientId + ", \"clientbal\" : " + clientBal + ", \"subacc\" : " + subacc + "}";
	}
	
	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		try {
			json.put("clientid", clientId);
			json.put("clientbal", clientBal);
			json.put("subacc", subacc);
		}catch (Exception e) {
			System.out.println("Error making json: " + e.toString());
		}
		return json;
	}
}
