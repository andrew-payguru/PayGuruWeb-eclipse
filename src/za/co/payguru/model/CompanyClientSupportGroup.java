package za.co.payguru.model;

import java.sql.Date;
import java.sql.Time;

import za.co.payguru.util.DateUtil;

public class CompanyClientSupportGroup {
	public static final String SUPPORT_GROUP_ACTIVE = "1";
	public static final String SUPPORT_GROUP_INACTIVE = "0";

	public static final String SUPPORT_GROUP_UNREAD = "1";
	public static final String SUPPORT_GROUP_READ = "2";

	
	private int supportgroupid = 0;
	private int supportgroupclientid = 0;
	private int supportgroupcompid = 0;
	private int supportgroupuserid = 0;
	private Date supportgroupcreatedate = DateUtil.getCurrentCCYYMMDDDate();
	private String supportgroupdesc = "";
	private String supportgroupstatus = SUPPORT_GROUP_READ;
	private Date supportgroupstatusdate = DateUtil.getCurrentCCYYMMDDDate();
	private Time supportgroupstatustime = DateUtil.getCurrentHHMMSSTime();
	private String supportgroupactive = SUPPORT_GROUP_ACTIVE;

	public CompanyClientSupportGroup() {
		super();
		// TODO Auto-generated constructor stub
	}


	public CompanyClientSupportGroup(int supportgroupid, int supportgroupclientid, int supportgroupcompid,
			int supportgroupuserid, Date supportgroupcreatedate, String supportgroupdesc, String supportgroupstatus,
			Date supportgroupstatusdate, Time supportgroupstatustime, String supportgroupactive) {
		super();
		this.supportgroupid = supportgroupid;
		this.supportgroupclientid = supportgroupclientid;
		this.supportgroupcompid = supportgroupcompid;
		this.supportgroupuserid = supportgroupuserid;
		this.supportgroupcreatedate = supportgroupcreatedate;
		this.supportgroupdesc = supportgroupdesc;
		this.supportgroupstatus = supportgroupstatus;
		this.supportgroupstatusdate = supportgroupstatusdate;
		this.supportgroupstatustime = supportgroupstatustime;
		this.supportgroupactive = supportgroupactive;
	}

	public int getSupportgroupid() {
		return supportgroupid;
	}


	public void setSupportgroupid(int supportgroupid) {
		this.supportgroupid = supportgroupid;
	}


	public int getSupportgroupclientid() {
		return supportgroupclientid;
	}


	public void setSupportgroupclientid(int supportgroupclientid) {
		this.supportgroupclientid = supportgroupclientid;
	}


	public int getSupportgroupcompid() {
		return supportgroupcompid;
	}


	public void setSupportgroupcompid(int supportgroupcompid) {
		this.supportgroupcompid = supportgroupcompid;
	}


	public int getSupportgroupuserid() {
		return supportgroupuserid;
	}


	public void setSupportgroupuserid(int supportgroupuserid) {
		this.supportgroupuserid = supportgroupuserid;
	}


	public Date getSupportgroupcreatedate() {
		return supportgroupcreatedate;
	}


	public void setSupportgroupcreatedate(Date supportgroupcreatedate) {
		this.supportgroupcreatedate = supportgroupcreatedate;
	}


	public String getSupportgroupdesc() {
		return supportgroupdesc;
	}


	public void setSupportgroupdesc(String supportgroupdesc) {
		this.supportgroupdesc = supportgroupdesc;
	}


	public String getSupportgroupstatus() {
		return supportgroupstatus;
	}


	public void setSupportgroupstatus(String supportgroupstatus) {
		this.supportgroupstatus = supportgroupstatus;
	}


	public Date getSupportgroupstatusdate() {
		return supportgroupstatusdate;
	}


	public void setSupportgroupstatusdate(Date supportgroupstatusdate) {
		this.supportgroupstatusdate = supportgroupstatusdate;
	}


	public Time getSupportgroupstatustime() {
		return supportgroupstatustime;
	}


	public void setSupportgroupstatustime(Time supportgroupstatustime) {
		this.supportgroupstatustime = supportgroupstatustime;
	}


	public String getSupportgroupactive() {
		return supportgroupactive;
	}


	public void setSupportgroupactive(String supportgroupactive) {
		this.supportgroupactive = supportgroupactive;
	}




	@Override
	public String toString() {
		return "CompanyClientSupportGroup [supportgroupid=" + supportgroupid + ", supportgroupclientid="
				+ supportgroupclientid + ", supportgroupcompid=" + supportgroupcompid + ", supportgroupuserid="
				+ supportgroupuserid + ", supportgroupcreatedate=" + supportgroupcreatedate + ", supportgroupdesc="
				+ supportgroupdesc + ", supportgroupstatus=" + supportgroupstatus + ", supportgroupstatusdate="
				+ supportgroupstatusdate + ", supportgroupstatustime=" + supportgroupstatustime + ", supportgroupactive="
				+ supportgroupactive + "]";
	}


	public String toJsonString() {
		return "{\"supportgroupid\" : " + supportgroupid + ", \"supportgroupcompid\" : " + supportgroupcompid + ", \"supportgroupuserid\" : " + supportgroupuserid
				+ ", \"supportgroupclientid\" : " + supportgroupclientid + ", \"supportgroupcreatedate\" : \"" + supportgroupcreatedate + "\", \"supportgroupdesc\" : \"" + supportgroupdesc
				+ "\", \"supportgroupstatus\" : \"" + supportgroupstatus + "\", \"supportgroupstatusdate\" : \"" + supportgroupstatusdate
				+ "\", \"supportgroupstatustime\" : \"" + supportgroupstatustime + "\", \"supportgroupactive\" : \"" + supportgroupactive + "\"}";
	}
	
}
