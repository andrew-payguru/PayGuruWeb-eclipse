package za.co.payguru.model;

import java.sql.Date;
import java.sql.Time;

import org.json.JSONObject;

import za.co.payguru.util.DateUtil;

public class ClientLoyaltyPointsEvents {
  public static final int EVENT_TYPE_POINTS_ACCRUAL 							= 1; //+
  public static final int EVENT_TYPE_POINTS_CONFIRM 							= 2; //+
  public static final int EVENT_TYPE_POINTS_EXPIRE                				= 3; //-
  public static final int EVENT_TYPE_POINTS_REDEEM_REWARD_CREATED				= 4; //-
  public static final int EVENT_TYPE_REWARD_REDEEMED							= 5; //0
  public static final int EVENT_TYPE_REWARD_REVERSED							= 6; //+
  public static final int EVENT_TYPE_REWARD_EXPIRED 							= 7; //0
  
  private long eventid = 0;
  private int clientid = 0;
  private int compid = 0;
  private int loyaltyid = 0;
  private int loyaltyeventtype = 0;
  private double loyaltypoints = 0;
  private Date eventdate = DateUtil.getCurrentCCYYMMDDDate();
  private Time eventtime = DateUtil.getCurrentHHMMSSTime();
  private String eventref1 = "";
  private String eventref2 = "";
  
	public ClientLoyaltyPointsEvents() {
		this.eventid = 0;
		this.clientid = 0;
		this.compid = 0;
		this.loyaltyid = 0;
		this.loyaltyeventtype = 0;
		this.loyaltypoints = 0;
		this.eventdate = DateUtil.getCurrentCCYYMMDDDate();
		this.eventtime = DateUtil.getCurrentHHMMSSTime();
		this.eventref1 = "";
	  this.eventref2 = "";
	}

	
	public long getEventid() {
		return eventid;
	}

	public int getClientid() {
		return clientid;
	}

	public int getCompid() {
		return compid;
	}

	public int getLoyaltyid() {
		return loyaltyid;
	}

	public int getLoyaltyeventtype() {
		return loyaltyeventtype;
	}

	public double getLoyaltypoints() {
		return loyaltypoints;
	}

	public Date getEventdate() {
		return eventdate;
	}

	public Time getEventtime() {
		return eventtime;
	}

	public void setEventid(long eventid) {
		this.eventid = eventid;
	}

	public void setClientid(int clientid) {
		this.clientid = clientid;
	}

	public void setCompid(int compid) {
		this.compid = compid;
	}

	public void setLoyaltyid(int loyaltyid) {
		this.loyaltyid = loyaltyid;
	}

	public void setLoyaltyeventtype(int loyaltyeventtype) {
		this.loyaltyeventtype = loyaltyeventtype;
	}

	public void setLoyaltypoints(double loyaltypoints) {
		this.loyaltypoints = loyaltypoints;
	}

	public void setEventdate(Date eventdate) {
		this.eventdate = eventdate;
	}

	public void setEventtime(Time eventtime) {
		this.eventtime = eventtime;
	}

	
	public String getEventref1() {
		return eventref1;
	}


	public String getEventref2() {
		return eventref2;
	}


	public void setEventref1(String eventref1) {
		this.eventref1 = eventref1;
	}


	public void setEventref2(String eventref2) {
		this.eventref2 = eventref2;
	}


	
	
	@Override
	public String toString() {
		return "ClientLoyaltyPointsEvents [eventid=" + eventid + ", clientid=" + clientid + ", compid=" + compid
				+ ", loyaltyid=" + loyaltyid + ", loyaltyeventtype=" + loyaltyeventtype + ", loyaltypoints=" + loyaltypoints
				+ ", eventdate=" + eventdate + ", eventtime=" + eventtime + ", eventref1=" + eventref1 + ", eventref2="
				+ eventref2 + "]";
	}


	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		try {
			json.put("eventid", eventid);
			json.put("clientid", clientid);
			json.put("compid", compid);
			json.put("loyaltyid", loyaltyid);
			json.put("loyaltyeventtype", loyaltyeventtype);
			json.put("loyaltypoints", loyaltypoints);
			json.put("eventdate", eventdate);
			json.put("eventtime", eventtime);
			json.put("eventref1", eventref1);
			json.put("eventref2", eventref2);
		}catch (Exception e) {
			System.out.println("Error creating json COMPANYLOYALTYPOINTSEVENTS: " + e.toString());
		}
		return json;
	}
  

}
