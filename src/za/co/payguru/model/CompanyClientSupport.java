package za.co.payguru.model;

import java.sql.Date;
import java.sql.Time;

import org.json.JSONObject;

import za.co.payguru.util.DateUtil;

public class CompanyClientSupport {
	public static final String COMP_CLI_SUPPORT_ACTIVE = "1";
	public static final String COMP_CLI_SUPPORT_READ = "2";
	public static final String COMP_CLI_SUPPORT_DELETED = "3";
	
	public static final int SUPPORT_TYPE_CLIENT_MESSAGE = 1;
	public static final int SUPPORT_TYPE_RESPONSE = 2;
	
	private long supportid = 0;
	private int supportgroupid = 0;
	private Date createdate = DateUtil.DEFAULT_DATE;
	private Time createtime = DateUtil.getCurrentHHMMSSTime();
	private String supportmessage = "";
	private String supportstatus = COMP_CLI_SUPPORT_ACTIVE;
	private Date supportstatusdate = DateUtil.DEFAULT_DATE;
	private Time supportstatustime = DateUtil.DEFAULT_TIME;
	private int supporttype = SUPPORT_TYPE_CLIENT_MESSAGE;
	private String supportactive = COMP_CLI_SUPPORT_ACTIVE;
	public CompanyClientSupport() {
		supportid = 0;
		supportgroupid = 0;
		createdate = DateUtil.DEFAULT_DATE;
		createtime = DateUtil.getCurrentHHMMSSTime();
		supportmessage = "";
		supportstatus = COMP_CLI_SUPPORT_ACTIVE;
		supportstatusdate = DateUtil.DEFAULT_DATE;
		supportstatustime = DateUtil.DEFAULT_TIME;
		supporttype = SUPPORT_TYPE_CLIENT_MESSAGE;
		supportactive = COMP_CLI_SUPPORT_ACTIVE;
	}
	
	public long getSupportid() {
		return supportid;
	}
	public void setSupportid(long supportid) {
		this.supportid = supportid;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public Time getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Time createtime) {
		this.createtime = createtime;
	}
	public String getSupportmessage() {
		return supportmessage;
	}
	public void setSupportmessage(String supportmessage) {
		this.supportmessage = supportmessage;
	}
	public String getSupportstatus() {
		return supportstatus;
	}
	public void setSupportstatus(String supportstatus) {
		this.supportstatus = supportstatus;
	}
	public Date getSupportstatusdate() {
		return supportstatusdate;
	}
	public void setSupportstatusdate(Date supportstatusdate) {
		this.supportstatusdate = supportstatusdate;
	}
	public Time getSupportstatustime() {
		return supportstatustime;
	}
	public void setSupportstatustime(Time supportstatustime) {
		this.supportstatustime = supportstatustime;
	}
	public String getSupportactive() {
		return supportactive;
	}
	public void setSupportactive(String supportactive) {
		this.supportactive = supportactive;
	}
	public int getSupportgroupid() {
		return supportgroupid;
	}
	public void setSupportgroupid(int supportgroupid) {
		this.supportgroupid = supportgroupid;
	}
	public int getSupporttype() {
		return supporttype;
	}

	public void setSupporttype(int supporttype) {
		this.supporttype = supporttype;
	}
	
	@Override
	public String toString() {
		return "CompanyClientSupport [supportid=" + supportid + ", supportgroupid=" + supportgroupid + ", createdate="
				+ createdate + ", createtime=" + createtime + ", supportmessage=" + supportmessage + ", supportstatus="
				+ supportstatus + ", supportstatusdate=" + supportstatusdate + ", supportstatustime=" + supportstatustime
				+ ", supporttype=" + supporttype + ", supportactive=" + supportactive + "]";
	}

	public String toJsonString() {
		return "{\"supportid\" : " + supportid + ", \"supportgroupid\" : " + supportgroupid + ", \"createdate\" : \"" + createdate + "\", \"createtime\" : \"" + createtime
				+ "\", \"supportmessage\": \"" + supportmessage + "\", \"supportstatus\" : \"" + supportstatus 
				+ "\", \"supportstatusdate\" : \"" + supportstatusdate + "\", \"supportstatustime\" : \"" + supportstatustime
				+ "\", \"supporttype\" : " + supporttype + ", \"supportactive\" : \"" + supportactive + "\"}";
	}
	
	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		try {
			json.put("supportid", supportid);
			json.put("supportgroupid", supportgroupid);
			json.put("createdate", createdate);
			json.put("createtime", createtime);
			json.put("supportmessage", supportmessage);
			json.put("supportstatus", supportstatus);
			json.put("supportstatusdate", supportstatusdate);
			json.put("supportstatustime", supportstatustime);
			json.put("supporttype", supporttype);
			json.put("supportactive", supportactive);
		}catch (Exception e) {
			System.out.println("Error creating json: " + e.toString());
		}
		return json;
	}
	
	
}
