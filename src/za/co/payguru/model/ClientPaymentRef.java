package za.co.payguru.model;

import java.sql.Connection;

public class ClientPaymentRef {

	public static final String IN_ACTIVE = "0";
	public static final String ACTIVE = "1";
	
	public static final String STATUS_ACTIVE    = "1";
	public static final String STATUS_PENDING   = "2";
	public static final String STATUS_CANCELLED = "3";
	public static final String STATUS_DONE      = "4";
	public static final String STATUS_RETRY     = "5";
	//manually activated, but payment not reflected in our system yet
	public static final String STATUS_WAITING   = "6";
	
	private int compid = 0;
	private String payref = new String();
	private int clientid = 0;
	private int prodid = 0;
	private String prodref = new String();
	private double payamt = 0;
	private String invno = new String();
	private String createdate = new String ();
	private String createtime = new String ();
	private String status = new String ();
	private String statusdate = new String ();
	private String statustime = new String ();
	public ClientPaymentRef() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ClientPaymentRef(int compid, String payref, int clientid, int prodid, String prodref, double payamt,
			String invno, String createdate, String createtime, String status, String statusdate, String statustime) {
		super();
		this.compid = compid;
		this.payref = payref;
		this.clientid = clientid;
		this.prodid = prodid;
		this.prodref = prodref;
		this.payamt = payamt;
		this.invno = invno;
		this.createdate = createdate;
		this.createtime = createtime;
		this.status = status;
		this.statusdate = statusdate;
		this.statustime = statustime;
	}
	public int getCompid() {
		return compid;
	}
	public void setCompid(int compid) {
		this.compid = compid;
	}
	public String getPayref() {
		return payref;
	}
	public void setPayref(String payref) {
		this.payref = payref;
	}
	public int getClientid() {
		return clientid;
	}
	public void setClientid(int clientid) {
		this.clientid = clientid;
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
	public double getPayamt() {
		return payamt;
	}
	public void setPayamt(double payamt) {
		this.payamt = payamt;
	}
	public String getInvno() {
		return invno;
	}
	public void setInvno(String invno) {
		this.invno = invno;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatusdate() {
		return statusdate;
	}
	public void setStatusdate(String statusdate) {
		this.statusdate = statusdate;
	}
	public String getStatustime() {
		return statustime;
	}
	public void setStatustime(String statustime) {
		this.statustime = statustime;
	}
	@Override
	public String toString() {
		return "ClientPaymentRef [compid=" + compid + ", payref=" + payref + ", clientid=" + clientid + ", prodid=" + prodid
				+ ", prodref=" + prodref + ", payamt=" + payamt + ", invno=" + invno + ", createdate=" + createdate
				+ ", createtime=" + createtime + ", status=" + status + ", statusdate=" + statusdate + ", statustime="
				+ statustime + "]";
	}
	
	public String toJsonString() {
		return "{\"compid\" : " + compid + ", \"payref\" : \"" + payref + "\", \"clientid\" : " + clientid + ", \"prodid\" : " + prodid
				+ ", \"prodref\" : \"" + prodref + "\", \"payamt\" : " + payamt + ", \"invno\" : \"" + invno + "\", \"createdate\" : \"" + createdate
				+ "\", \"createtime\" : \"" + createtime + "\", \"status\" : " + status + ", \"statusdate\" : \"" + statusdate + "\", \"statustime\" : \""
				+ statustime + "\"}";
	}
}
