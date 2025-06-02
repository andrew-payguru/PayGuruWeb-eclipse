package za.co.payguru.model;

import org.json.JSONObject;

public class ClientProductRef {
	public static final String ACTIVE = "1";
	public static final String INACTIVE = "0";
	
    public static final int REF_TYPE_QR = 1;
    public static final int REF_TYPE_NFC = 2;
    public static final int REF_TYPE_MCR = 3;
    public static final int REF_TYPE_CUST_ID = 4;
    public static final int REF_TYPE_MSISDN = 5;
    public static final int REF_TYPE_ACC_NO = 6;
	
	private int clientid = 0;
	private int compid = 0;
	private int prodid = 0;
	private String prodref = "";
	private String prodrefno = "";
	private int prodreftype = 0;
	private String prodrefactive = ACTIVE;
	private int donorid = -1;
    private String extref1 = "";

	
	public ClientProductRef() {
		this.clientid = 0;
		this.compid = 0;
		this.prodid = 0;
		this.prodref = "";
		this.prodrefno = "";
		this.prodreftype = 0;
		this.prodrefactive = ACTIVE;
		this.donorid = -1;
		this.extref1 = "";
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

	public int getProdid() {
		return prodid;
	}

	public void setProdid(int prodid) {
		this.prodid = prodid;
	}

	public String getProdref() {
		return prodref;
	}

	public void setProdref(String prodref) {
		this.prodref = prodref;
	}

	public String getProdrefno() {
		return prodrefno;
	}

	public void setProdrefno(String prodrefno) {
		this.prodrefno = prodrefno;
	}

	public int getProdreftype() {
		return prodreftype;
	}

	public void setProdreftype(int prodreftype) {
		this.prodreftype = prodreftype;
	}

	public String getProdrefactive() {
		return prodrefactive;
	}

	public void setProdrefactive(String prodrefactive) {
		this.prodrefactive = prodrefactive;
	}

	
	public int getDonorid() {
		return donorid;
	}

	public void setDonorid(int donorid) {
		this.donorid = donorid;
	}
	
	public String getExtref1() {
		return extref1;
	}

	public void setExtref1(String extref1) {
		this.extref1 = extref1;
	}

	
	@Override
	public String toString() {
		return "ClientProductRef [clientid=" + clientid + ", compid=" + compid + ", prodid=" + prodid + ", prodref="
				+ prodref + ", prodrefno=" + prodrefno + ", prodreftype=" + prodreftype + ", prodrefactive="
				+ prodrefactive + ", donorid=" + donorid + ", extref1=" + extref1 + "]";
	}

	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		try {
			json.put("clientid", clientid);
			json.put("compid", compid);
			json.put("prodid", prodid);
			json.put("prodref", prodref);
			json.put("prodrefno", prodrefno);
			json.put("prodreftype", prodreftype);
			json.put("prodrefactive", prodrefactive);
			json.put("donorid", donorid);
			json.put("extref1", extref1);
		}catch (Exception e) {
			System.out.println("Error creating json: " + e.toString());
		}
		return json;
	}
	
	
}
