package za.co.payguru.model;

import org.json.JSONObject;

public class ClientCompanyBalance {
	private int clientid = 0;
	private int compid = 0;
	private double balance = 0;
	private int subacc = 0;
	private int prodid = 0;
	private int donorid = 0;
	
	public ClientCompanyBalance() {
		this.clientid = 0;
		this.compid = 0;
		this.balance = 0;
		this.subacc = 0;
		this.prodid = 0;
		this.donorid = -1;
	}

	public int getClientid() {
		return clientid;
	}

	public void setClientid(int clientid) {
		this.clientid = clientid;
	}

	public int getCompid() {
		return compid;
	}

	public void setCompid(int compid) {
		this.compid = compid;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getSubacc() {
		return subacc;
	}

	public void setSubacc(int subacc) {
		this.subacc = subacc;
	}

	
	public int getProdid() {
		return prodid;
	}

	public void setProdid(int prodid) {
		this.prodid = prodid;
	}

	public int getDonorid() {
		return donorid;
	}

	public void setDonorid(int donorid) {
		this.donorid = donorid;
	}

	
	
	@Override
	public String toString() {
		return "ClientCompanyBalance [clientid=" + clientid + ", compid=" + compid + ", balance=" + balance
				+ ", subacc=" + subacc + ", prodid=" + prodid + ", donorid=" + donorid + "]";
	}

	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		try {
			json.put("clientid", clientid);
			json.put("compid", compid);
			json.put("balance", balance);
			json.put("subacc", subacc);
			json.put("prodid", prodid);
			json.put("donorid", donorid);

		}catch (Exception e) {
			System.out.println("Error creating json: " + e.toString());
		}
		return json;
	}
	
}
