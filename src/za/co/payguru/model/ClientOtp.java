package za.co.payguru.model;

import java.sql.Date;
import java.sql.Time;
import org.json.JSONObject;

import za.co.payguru.dao.ClientOtpDao;
import za.co.payguru.util.DateUtil;
import za.co.payguru.util.Util;

public class ClientOtp {
	public static final String OTP_INACTIVE = "0";
	public static final String OTP_ACTIVE = "1";
	public static final String OTP_EXPIRED = "2";
	public static final String OTP_DONE = "3";

	public static final int OTP_TYPE_SMS = 1;
	public static final int OTP_TYPE_EMAIL = 2;

	private long otpid = 0;
	private int clientid = 0;
	private Date otpdate = DateUtil.getCurrentCCYYMMDDDate();
	private Time otptime = DateUtil.getCurrentHHMMSSTime();
	private String otpno = "";
	private int otptype = OTP_TYPE_SMS;
	private String otpsendref = "";
	private String otpstatus = OTP_ACTIVE;
	private Date otpstatusdate = DateUtil.getCurrentCCYYMMDDDate();
	private Time otpstatustime = DateUtil.getCurrentHHMMSSTime();

	public ClientOtp() {
		this.otpid = 0;
		this.clientid = 0;
		this.otpdate = DateUtil.getCurrentCCYYMMDDDate();
		this.otptime = DateUtil.getCurrentHHMMSSTime();
		this.otpno = "";
		this.otptype = OTP_TYPE_EMAIL;
		this.otpsendref = "";
		this.otpstatus = OTP_ACTIVE;
		this.otpstatusdate = DateUtil.getCurrentCCYYMMDDDate();
		this.otpstatustime = DateUtil.getCurrentHHMMSSTime();
	}

	public ClientOtp(long otpid, int clientid, Date otpdate, Time otptime, String otpno, int otptype, String otpsendref, String otpstatus, Date otpstatusdate, Time otpstatustime) {
		this.otpid = otpid;
		this.clientid = clientid;
		this.otpdate = otpdate;
		this.otptime = otptime;
		this.otpno = otpno;
		this.otptype = otptype;
		this.otpsendref = otpsendref;
		this.otpstatus = otpstatus;
		this.otpstatusdate = otpstatusdate;
		this.otpstatustime = otpstatustime;
	}


	public long getOtpid() {
		return otpid;
	}

	public void setOtpid(long otpid) {
		this.otpid = otpid;
	}

	public int getClientid() {
		return clientid;
	}

	public void setClientid(int clientid) {
		this.clientid = clientid;
	}

	public Date getOtpdate() {
		return otpdate;
	}

	public void setOtpdate(Date otpdate) {
		this.otpdate = otpdate;
	}

	public Time getOtptime() {
		return otptime;
	}

	public void setOtptime(Time otptime) {
		this.otptime = otptime;
	}

	public String getOtpno() {
		return otpno;
	}

	public void setOtpno(String otpno) {
		this.otpno = otpno;
	}

	public int getOtptype() {
		return otptype;
	}

	public void setOtptype(int otptype) {
		this.otptype = otptype;
	}

	public String getOtpsendref() {
		return otpsendref;
	}

	public void setOtpsendref(String otpsendref) {
		this.otpsendref = otpsendref;
	}

	public String getOtpstatus() {
		return otpstatus;
	}

	public void setOtpstatus(String otpstatus) {
		this.otpstatus = otpstatus;
	}

	public Date getOtpstatusdate() {
		return otpstatusdate;
	}

	public void setOtpstatusdate(Date otpstatusdate) {
		this.otpstatusdate = otpstatusdate;
	}

	public Time getOtpstatustime() {
		return otpstatustime;
	}

	public void setOtpstatustime(Time otpstatustime) {
		this.otpstatustime = otpstatustime;
	}

	@Override
	public String toString() {
		return "ClientOtp{" +
				"otpid=" + otpid +
				", clientid=" + clientid +
				", otpdate=" + otpdate +
				", otptime=" + otptime +
				", otpno='" + otpno + '\'' +
				", otptype=" + otptype +
				", otpsendref='" + otpsendref + '\'' +
				", otpstatus='" + otpstatus + '\'' +
				", otpstatusdate=" + otpstatusdate +
				", otpstatustime=" + otpstatustime +
				'}';
	}

	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		try {
			json.put("otpid", otpid);
			json.put("clientid", clientid);
			json.put("otpdate", otpdate);
			json.put("otptime", otptime);
			json.put("otpno", otpno);
			json.put("otptype", otptype);
			json.put("otpsendref", otpsendref);
			json.put("otpstatus", otpstatus);
			json.put("otpstatusdate", otpstatusdate);
			json.put("otpstatustime", otpstatustime);
		} catch (Exception e) {
			System.out.println("Error creating JSON for ClientOtp: " + e.toString());
		}
		return json;
	}
}