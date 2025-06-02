package za.co.payguru.model;

import org.json.JSONObject;

public class ClientHistory {
	public static final String IN_ACTIVE = "0";
	public static final String ACTIVE = "1";

	public static final String TYPE_CREDIT_PAYMENT = "1";
	public static final String TYPE_DEBIT_PAYMENT  = "2";
	public static final String TYPE_CREDIT_PRODUCT = "3";
	public static final String TYPE_DEBIT_PRODUCT  = "4";
	public static final String TYPE_CREDIT_BALANCE = "5";
	public static final String TYPE_DEBIT_BALANCE  = "6";
	public static final String TYPE_CREDIT_WALLET_BALANCE = "7";
	public static final String TYPE_DEBIT_WALLET_BALANCE  = "8";
	
	private long tranno = 0;
	private String trantype = "";
	private String clientid = "";
	private String trandate = "";
	private String trantime = "";
	private double tranamt = 0;
	private String tranref1 = "";
	private String tranref2 = "";
	
	public ClientHistory() {
	}

	public long getTranno() {
		return tranno;
	}

	public void setTranno(long tranno) {
		this.tranno = tranno;
	}

	public String getTrantype() {
		return trantype;
	}

	public void setTrantype(String trantype) {
		this.trantype = trantype;
	}

	public String getClientid() {
		return clientid;
	}

	public void setClientid(String clientid) {
		this.clientid = clientid;
	}

	public String getTrandate() {
		return trandate;
	}

	public void setTrandate(String trandate) {
		this.trandate = trandate;
	}

	public String getTrantime() {
		return trantime;
	}

	public void setTrantime(String trantime) {
		this.trantime = trantime;
	}

	public double getTranamt() {
		return tranamt;
	}

	public void setTranamt(double tranamt) {
		this.tranamt = tranamt;
	}

	public String getTranref1() {
		return tranref1;
	}

	public void setTranref1(String tranref1) {
		this.tranref1 = tranref1;
	}

	public String getTranref2() {
		return tranref2;
	}

	public void setTranref2(String tranref2) {
		this.tranref2 = tranref2;
	}

	@Override
	public String toString() {
		return "ClientHistory [tranno=" + tranno + ", trantype=" + trantype + ", clientid=" + clientid + ", trandate="
				+ trandate + ", trantime=" + trantime + ", tranamt=" + tranamt + ", tranref1=" + tranref1
				+ ", tranref2=" + tranref2 + "]";
	}
	
	
	
	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		try {
			json.put("tranno", tranno);
			json.put("trantype", trantype);
			json.put("clientid", clientid);
			json.put("trandate", trandate);
			json.put("trantime", trantime);
			json.put("tranamt", tranamt);
			json.put("tranref1", tranref1);
			json.put("tranref2", tranref2);
		}catch (Exception e) {
			System.out.println("Error creating JSON: " + e.toString());
		}
		return json;
	}
	
}
