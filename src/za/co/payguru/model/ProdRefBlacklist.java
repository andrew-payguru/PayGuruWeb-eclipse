package za.co.payguru.model;

import za.co.payguru.util.DateUtil;

public class ProdRefBlacklist {
	public static final String ACTIVE = "1";
	public static final String INACTIVE = "0";
	
	public static final int PRODREFBLACKLIST_ALL = -1; 
	
	private int compid = 0;
	private String prodref = "";
	private int prodid = 0;
	private String active = "1";
	private String reason = "";
	private String blacklistdate = DateUtil.getCurrentCCYYMMDDStr();
	private String blacklisttime = DateUtil.getCurrentHHMMSSStr();
	public ProdRefBlacklist() {
		this.compid = 0;
		this.prodref = "";
		this.prodid = 0;
		this.active = "1";
		this.reason = "";
		this.blacklistdate = DateUtil.getCurrentCCYYMMDDStr();
		this.blacklisttime = DateUtil.getCurrentHHMMSSStr();
	}

	public int getCompid() {
		return compid;
	}
	public void setCompid(int compid) {
		this.compid = compid;
	}
	public String getProdref() {
		return prodref;
	}
	public void setProdref(String prodref) {
		this.prodref = prodref;
	}
	public int getProdid() {
		return prodid;
	}
	public void setProdid(int prodid) {
		this.prodid = prodid;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getBlacklistdate() {
		return blacklistdate;
	}

	public void setBlacklistdate(String blacklistdate) {
		this.blacklistdate = blacklistdate;
	}

	public String getBlacklisttime() {
		return blacklisttime;
	}

	public void setBlacklisttime(String blacklisttime) {
		this.blacklisttime = blacklisttime;
	}

	@Override
	public String toString() {
		return "ProdRefBlacklist [compid=" + compid + ", prodref=" + prodref + ", prodid=" + prodid + ", active=" + active
				+ "]";
	}
	public String toJsonString() {
		return "{\"compid\" : " + compid + ", \"prodref\" : \"" + prodref + "\", \"prodid\" : " + prodid + ", \"active\" : \"" + active + "\", \"reason\" : \"" + reason + "\", \"blacklistdate\" : \"" + blacklistdate + "\", \"blacklisttime\" : \"" + blacklisttime + "\"}";
	}
	
}
