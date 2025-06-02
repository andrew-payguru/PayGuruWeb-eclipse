package za.co.payguru.model;

import java.sql.Date;
import java.sql.Time;

import za.co.payguru.util.DateUtil;

public class CompanySmsGroup {
	public static String GROUP_ACTIVE = "1";
	public static String GROUP_INACTIVE = "0";
	
	private int compId = 0;
	private long smsGroupId = 0;
	private String smsGroupName = "";
	private Date smsGroupCreatedDate = DateUtil.DEFAULT_DATE;
	private Time smsGroupCreatedTime = DateUtil.DEFAULT_TIME;
	private String smsGroupActive = GROUP_ACTIVE;
	public CompanySmsGroup() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CompanySmsGroup(int compId, long smsGroupId, String smsGroupName, Date smsGroupCreatedDate,
			Time smsGroupCreatedTime, String smsGroupActive) {
		super();
		this.compId = compId;
		this.smsGroupId = smsGroupId;
		this.smsGroupName = smsGroupName;
		this.smsGroupCreatedDate = smsGroupCreatedDate;
		this.smsGroupCreatedTime = smsGroupCreatedTime;
		this.smsGroupActive = smsGroupActive;
	}
	public int getCompId() {
		return compId;
	}
	public void setCompId(int compId) {
		this.compId = compId;
	}
	public long getSmsGroupId() {
		return smsGroupId;
	}
	public void setSmsGroupId(long smsGroupId) {
		this.smsGroupId = smsGroupId;
	}
	public String getSmsGroupName() {
		return smsGroupName;
	}
	public void setSmsGroupName(String smsGroupName) {
		this.smsGroupName = smsGroupName;
	}
	public Date getSmsGroupCreatedDate() {
		return smsGroupCreatedDate;
	}
	public void setSmsGroupCreatedDate(Date smsGroupCreatedDate) {
		this.smsGroupCreatedDate = smsGroupCreatedDate;
	}
	public Time getSmsGroupCreatedTime() {
		return smsGroupCreatedTime;
	}
	public void setSmsGroupCreatedTime(Time smsGroupCreatedTime) {
		this.smsGroupCreatedTime = smsGroupCreatedTime;
	}
	public String getSmsGroupActive() {
		return smsGroupActive;
	}
	public void setSmsGroupActive(String smsGroupActive) {
		this.smsGroupActive = smsGroupActive;
	}
	@Override
	public String toString() {
		return "CompanySmsGroup [compId=" + compId + ", smsGroupId=" + smsGroupId + ", smsGroupName=" + smsGroupName
				+ ", smsGroupCreatedDate=" + smsGroupCreatedDate + ", smsGroupCreatedTime=" + smsGroupCreatedTime
				+ ", smsGroupActive=" + smsGroupActive + "]";
	}
	
	
}
