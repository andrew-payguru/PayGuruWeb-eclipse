package za.co.payguru.model;

import org.json.JSONObject;

public class ClientCompanyBankRef {

	private int clientid = 0;
	private int compid = 0;
	private int bankid = 0;
	private String bankref = "";
	private int subacc = 0;
	
	public ClientCompanyBankRef() {
		this.clientid = 0;
		this.compid = 0;
		this.bankid = 0;
		this.bankref = "";
		this.subacc = 0;
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

	public int getBankid() {
		return bankid;
	}

	public void setBankid(int bankid) {
		this.bankid = bankid;
	}

	public String getBankref() {
		return bankref;
	}

	public void setBankref(String bankref) {
		this.bankref = bankref;
	}

	public int getSubacc() {
		return subacc;
	}

	public void setSubacc(int subacc) {
		this.subacc = subacc;
	}

	@Override
	public String toString() {
		return "ClientCompanyBankRef [clientid=" + clientid + ", compid=" + compid + ", bankid=" + bankid + ", bankref="
				+ bankref + ", subacc=" + subacc + "]";
	}
	
	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		try {
			json.put("clientid", clientid);
			json.put("compid", compid);
			json.put("bankid", bankid);
			json.put("bankref", bankref);
			json.put("subacc", subacc);
		}catch (Exception e) {
			System.out.println("Error creating json: " + e.toString());
		}
		return json;
	}
	
	
}
