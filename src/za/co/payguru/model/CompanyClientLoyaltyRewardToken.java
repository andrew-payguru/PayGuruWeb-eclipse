package za.co.payguru.model;

import java.sql.Date;
import java.sql.Time;

import org.json.JSONException;
import org.json.JSONObject;

import za.co.payguru.util.DateUtil;

public class CompanyClientLoyaltyRewardToken {

	public static final String TOKEN_STATUS_INACTIVE = "0";
	public static final String TOKEN_STATUS_ACTIVE = "1";
	public static final String TOKEN_STATUS_EXPIRED = "2";
	public static final String TOKEN_STATUS_REDEEMED = "3";

	private String tokenno = "";
	private String tokenuid = "";
	private int compid = 0;
	private int comployaltyid = 0;
	private long rewardid = 0;
	private int clientid = 0;
	private Date tokendate = DateUtil.getCurrentCCYYMMDDDate();
	private Time tokentime = DateUtil.getCurrentHHMMSSTime();
	private String tokenstatus = TOKEN_STATUS_ACTIVE;
	private Date tokenstatusdate = DateUtil.getCurrentCCYYMMDDDate();
	private Time tokenstatustime = DateUtil.getCurrentHHMMSSTime();
	private Date tokenexpiredate = DateUtil.DEFAULT_DATE;
	private Time tokenexpiretime = DateUtil.getCurrentHHMMSSTime();
	private double tokenpointsval = 0;
	public String tokenredeemref1 = "";
	public String tokenredeemref2 = "";

	public CompanyClientLoyaltyRewardToken() {
		this.tokenno = "";
		this.tokenuid = "";
		this.compid = 0;
		this.comployaltyid = 0;
		this.rewardid = 0;
		this.clientid = 0;
		this.tokendate = DateUtil.getCurrentCCYYMMDDDate();
		this.tokentime = DateUtil.getCurrentHHMMSSTime();
		this.tokenstatus = TOKEN_STATUS_ACTIVE;
		this.tokenstatusdate = DateUtil.getCurrentCCYYMMDDDate();
		this.tokenstatustime = DateUtil.getCurrentHHMMSSTime();
		this.tokenexpiredate = DateUtil.DEFAULT_DATE;
		this.tokenexpiretime = DateUtil.getCurrentHHMMSSTime();
		this.tokenpointsval = 0;
		this.tokenredeemref1 = "";
		this.tokenredeemref2 = "";
	}


	// Getters and Setters
	public String getTokenno() {
		return tokenno;
	}

	public void setTokenno(String tokenno) {
		this.tokenno = tokenno;
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

	public long getRewardid() {
		return rewardid;
	}

	public void setRewardid(long rewardid) {
		this.rewardid = rewardid;
	}

	public int getClientid() {
		return clientid;
	}

	public void setClientid(int clientid) {
		this.clientid = clientid;
	}

	public Date getTokendate() {
		return tokendate;
	}

	public void setTokendate(Date tokendate) {
		this.tokendate = tokendate;
	}

	public Time getTokentime() {
		return tokentime;
	}

	public void setTokentime(Time tokentime) {
		this.tokentime = tokentime;
	}

	public String getTokenstatus() {
		return tokenstatus;
	}

	public void setTokenstatus(String tokenstatus) {
		this.tokenstatus = tokenstatus;
	}

	public Date getTokenstatusdate() {
		return tokenstatusdate;
	}

	public void setTokenstatusdate(Date tokenstatusdate) {
		this.tokenstatusdate = tokenstatusdate;
	}

	public Time getTokenstatustime() {
		return tokenstatustime;
	}

	public void setTokenstatustime(Time tokenstatustime) {
		this.tokenstatustime = tokenstatustime;
	}

	public String getTokenuid() {
		return tokenuid;
	}

	public void setTokenuid(String tokenuid) {
		this.tokenuid = tokenuid;
	}

	public Date getTokenexpiredate() {
		return tokenexpiredate;
	}

	public void setTokenexpiredate(Date tokenexpiredate) {
		this.tokenexpiredate = tokenexpiredate;
	}


	public Time getTokenexpiretime() {
		return tokenexpiretime;
	}

	public void setTokenexpiretime(Time tokenexpiretime) {
		this.tokenexpiretime = tokenexpiretime;
	}

	public double getTokenpointsval() {
		return tokenpointsval;
	}

	public void setTokenpointsval(double tokenpointsval) {
		this.tokenpointsval = tokenpointsval;
	}
	
	public String getTokenredeemref1() {
		return tokenredeemref1;
	}

	public void setTokenredeemref1(String tokenredeemref1) {
		this.tokenredeemref1 = tokenredeemref1;
	}

	public String getTokenredeemref2() {
		return tokenredeemref2;
	}

	public void setTokenredeemref2(String tokenredeemref2) {
		this.tokenredeemref2 = tokenredeemref2;
	}


	public JSONObject toJson() {
		JSONObject json = new JSONObject();
		try {
			json.put("tokenno", tokenno);
			json.put("tokenuid", tokenuid);
			json.put("compid", compid);
			json.put("comployaltyid", comployaltyid);
			json.put("rewardid", rewardid);
			json.put("clientid", clientid);
			json.put("tokendate", tokendate.toString());
			json.put("tokentime", tokentime.toString());
			json.put("tokenstatus", tokenstatus);
			json.put("tokenstatusdate", tokenstatusdate.toString());
			json.put("tokenstatustime", tokenstatustime.toString());
			json.put("tokenexpiredate", tokenexpiredate.toString());
			json.put("tokenexpiretime", tokenexpiretime.toString());
			json.put("tokenpointsval", tokenpointsval);
			json.put("tokenredeemref1", tokenredeemref1);
			json.put("tokenredeemref2", tokenredeemref2);
		} catch (JSONException e) {
			System.out.println("Error creating json: " + e.toString());
		}

		return json;
	}
}
