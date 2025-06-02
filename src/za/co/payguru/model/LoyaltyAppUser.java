package za.co.payguru.model;

import java.sql.Date;
import java.sql.Time;

import org.json.JSONObject;

import za.co.payguru.util.DateUtil;

public class LoyaltyAppUser {
	public static final String LOGGED_IN = "1";
	public static final String LOGGED_OUT = "2";
	public static final String DORMANT = "3";

	public static final String ACTIVE = "1";
	public static final String INACTIVE = "0";

	private long userid = 0;
	private int clientid = 0;
	private String loyaltyno = "";
	private int compid = 0;
	private int comployaltyid = 0;
	private String password = "";
	private String status = LOGGED_OUT;
	private Date statusdate = DateUtil.getCurrentCCYYMMDDDate();
	private Time statustime = DateUtil.getCurrentHHMMSSTime();
	private String active = ACTIVE;

	public LoyaltyAppUser() {
		this.userid = 0;
		this.clientid = 0;
		this.loyaltyno = "";
		this.compid = 0;
		this.comployaltyid = 0;
		this.password = "";
		this.status = LOGGED_OUT;
		this.statusdate = DateUtil.getCurrentCCYYMMDDDate();
		this.statustime = DateUtil.getCurrentHHMMSSTime();
		this.active = ACTIVE;
	}

	public LoyaltyAppUser(long userid, int clientid, String loyaltyno, int compid, int comployaltyid, String password,
			String status, Date statusdate, Time statustime, String active) {
		super();
		this.userid = userid;
		this.clientid = clientid;
		this.loyaltyno = loyaltyno;
		this.compid = compid;
		this.comployaltyid = comployaltyid;
		this.password = password;
		this.status = status;
		this.statusdate = statusdate;
		this.statustime = statustime;
		this.active = active;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public int getClientid() {
		return clientid;
	}

	public void setClientid(int clientid) {
		this.clientid = clientid;
	}

	public String getLoyaltyno() {
		return loyaltyno;
	}

	public void setLoyaltyno(String loyaltyno) {
		this.loyaltyno = loyaltyno;
	}

	public int getCompid() {
		return compid;
	}

	public void setCompid(int compid) {
		this.compid = compid;
	}

	public int getComployaltyid() {
		return comployaltyid;
	}

	public void setComployaltyid(int comployaltyid) {
		this.comployaltyid = comployaltyid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStatusdate() {
		return statusdate;
	}

	public void setStatusdate(Date statusdate) {
		this.statusdate = statusdate;
	}

	public Time getStatustime() {
		return statustime;
	}

	public void setStatustime(Time statustime) {
		this.statustime = statustime;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "LoyaltyAppUserDao [userid=" + userid + ", clientid=" + clientid + ", loyaltyno=" + loyaltyno + ", compid="
				+ compid + ", comployaltyid=" + comployaltyid + ", password=" + password + ", status=" + status
				+ ", statusdate=" + statusdate + ", statustime=" + statustime + ", active=" + active + "]";
	}

	public JSONObject toJson() {
		JSONObject json = new JSONObject();
		try {
			json.put("userid", this.userid);
			json.put("clientid", this.clientid);
			json.put("loyaltyno", this.loyaltyno);
			json.put("compid", this.compid);
			json.put("comployaltyid", this.comployaltyid);
			json.put("password", this.password);
			json.put("status", this.status);
			json.put("statusdate", this.statusdate.toString());
			json.put("statustime", this.statustime.toString());
			json.put("active", this.active);
		}catch (Exception e) {}
		return json;
	}


}
