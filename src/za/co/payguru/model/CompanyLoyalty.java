package za.co.payguru.model;

import java.sql.Date;
import java.sql.Time;

import org.json.JSONObject;

import za.co.payguru.util.DateUtil;

public class CompanyLoyalty {
	public static final String LOYALTY_ACTVIE = "1";
	public static final String LOYALTY_INACTVIE = "0";

	public static final int LOYALTY_TYPE_SIMPLE = 1;
	public static final int LOYALTY_TYPE_PROD_BASED = 2;

	public static final int CAPTURE_TYPE_POS_API = 1;
	public static final int CAPTURE_TYPE_DAILY_FILE = 2;
	public static final int CAPTURE_TYPE_POS_API_DAILY_FILE_HYBRID = 3;

	public static final int LOYALTY_APP_TYPE_GENERIC = 1;
	public static final int LOYALTY_APP_TYPE_COMPANY = 2;
	public static final int LOYALTY_APP_TYPE_COMPANY_LOYALTY = 3;

	private int compid = 0;
	private int comployaltyid = 0;
	private String comployaltydesc = "";
	private int comployaltytype = LOYALTY_TYPE_SIMPLE;
	private int comployaltycapturetype = CAPTURE_TYPE_POS_API_DAILY_FILE_HYBRID;
	private Date comployaltystart = DateUtil.getCurrentCCYYMMDDDate();
	private Date comployaltyexpire = null;
	private double comployaltypointsperc = 0;
	private double comployaltypointsvalperc = 0;
	private int comployaltypointslifespan = 365;
	private String comployaltystatus = LOYALTY_ACTVIE;
	private Date comployaltystatusdate = DateUtil.getCurrentCCYYMMDDDate();
	private Time comployaltystatustime = DateUtil.getCurrentHHMMSSTime();
	private String active = LOYALTY_ACTVIE;
	private int companyloyaltyrewardtokenlifespan = 0;
	public CompanyLoyalty() {
		this.compid = 0;
		this.comployaltyid = 0;
		this.comployaltydesc = "";
		this.comployaltytype = LOYALTY_TYPE_SIMPLE;
		this.comployaltycapturetype = CAPTURE_TYPE_POS_API_DAILY_FILE_HYBRID;
		this.comployaltystart = DateUtil.getCurrentCCYYMMDDDate();
		this.comployaltyexpire = null;
		this.comployaltypointsperc = 0;
		this.comployaltypointsvalperc = 0;
		this.comployaltypointslifespan = 365;
		this.comployaltystatus = LOYALTY_ACTVIE;
		this.comployaltystatusdate = DateUtil.getCurrentCCYYMMDDDate();
		this.comployaltystatustime = DateUtil.getCurrentHHMMSSTime();
		this.active = LOYALTY_ACTVIE;
		this.companyloyaltyrewardtokenlifespan = 0;
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
	public String getComployaltydesc() {
		return comployaltydesc;
	}
	public void setComployaltydesc(String comployaltydesc) {
		this.comployaltydesc = comployaltydesc;
	}
	public int getComployaltytype() {
		return comployaltytype;
	}
	public void setComployaltytype(int comployaltytype) {
		this.comployaltytype = comployaltytype;
	}
	public int getComployaltycapturetype() {
		return comployaltycapturetype;
	}
	public void setComployaltycapturetype(int comployaltycapturetype) {
		this.comployaltycapturetype = comployaltycapturetype;
	}
	public Date getComployaltystart() {
		return comployaltystart;
	}
	public void setComployaltystart(Date comployaltystart) {
		this.comployaltystart = comployaltystart;
	}
	public Date getComployaltyexpire() {
		return comployaltyexpire;
	}
	public void setComployaltyexpire(Date comployaltyexpire) {
		this.comployaltyexpire = comployaltyexpire;
	}
	public int getComployaltypointslifespan() {
		return comployaltypointslifespan;
	}
	public void setComployaltypointslifespan(int comployaltypointslifespan) {
		this.comployaltypointslifespan = comployaltypointslifespan;
	}
	public double getComployaltypointsperc() {
		return comployaltypointsperc;
	}
	public void setComployaltypointsperc(double comployaltypointsperc) {
		this.comployaltypointsperc = comployaltypointsperc;
	}
	public double getComployaltypointsvalperc() {
		return comployaltypointsvalperc;
	}
	public void setComployaltypointsvalperc(double comployaltypointsvalperc) {
		this.comployaltypointsvalperc = comployaltypointsvalperc;
	}
	public String getComployaltystatus() {
		return comployaltystatus;
	}
	public void setComployaltystatus(String comployaltystatus) {
		this.comployaltystatus = comployaltystatus;
	}
	public Date getComployaltystatusdate() {
		return comployaltystatusdate;
	}
	public void setComployaltystatusdate(Date comployaltystatusdate) {
		this.comployaltystatusdate = comployaltystatusdate;
	}
	public Time getComployaltystatustime() {
		return comployaltystatustime;
	}
	public void setComployaltystatustime(Time comployaltystatustime) {
		this.comployaltystatustime = comployaltystatustime;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public int getCompanyloyaltyrewardtokenlifespan() {
		return companyloyaltyrewardtokenlifespan;
	}
	public void setCompanyloyaltyrewardtokenlifespan(int companyloyaltyrewardtokenlifespan) {
		this.companyloyaltyrewardtokenlifespan = companyloyaltyrewardtokenlifespan;
	}



	@Override
	public String toString() {
		return "CompanyLoyalty [compid=" + compid + ", comployaltyid=" + comployaltyid + ", comployaltydesc="
				+ comployaltydesc + ", comployaltytype=" + comployaltytype + ", comployaltycapturetype="
				+ comployaltycapturetype + ", comployaltystart=" + comployaltystart + ", comployaltyexpire=" + comployaltyexpire
				+ ", comployaltypointsperc=" + comployaltypointsperc + ", comployaltypointsvalperc=" + comployaltypointsvalperc
				+ ", comployaltypointslifespan=" + comployaltypointslifespan + ", comployaltystatus=" + comployaltystatus
				+ ", comployaltystatusdate=" + comployaltystatusdate + ", comployaltystatustime=" + comployaltystatustime
				+ ", active=" + active + ", companyloyaltyrewardtokenlifespan=" + companyloyaltyrewardtokenlifespan + "]";
	}



	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		try {
			json.put("compid", compid);
			json.put("comployaltyid", comployaltyid);
			json.put("comployaltydesc", comployaltydesc);
			json.put("comployaltytype", comployaltytype);
			json.put("comployaltycapturetype", comployaltycapturetype);
			json.put("comployaltystart", comployaltystart);
			json.put("comployaltyexpire", comployaltyexpire);
			json.put("comployaltypointslifespan", comployaltypointslifespan);
			json.put("comployaltypointsperc", comployaltypointsperc);
			json.put("comployaltypointsvalperc", comployaltypointsvalperc);
			json.put("comployaltystatus", comployaltystatus);
			json.put("comployaltystatusdate", comployaltystatusdate);
			json.put("comployaltystatustime", comployaltystatustime);
			json.put("active", active);
			json.put("companyloyaltyrewardtokenlifespan", companyloyaltyrewardtokenlifespan);
		}catch (Exception e) {
			System.out.println("Error creating json COMPANYLOYALTY: " + e.toString());
		}
		return json;
	}


}
