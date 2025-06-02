package za.co.payguru.model;

import java.sql.Date;
import java.sql.Time;

import za.co.payguru.util.DateUtil;

public class CompanySmsCampaign {
	public static final String CAMPAIGN_ACTIVE = "1";
	public static final String CAMPAIGN_INACTIVE = "0";
	public static final int ONCE_OFF = 1;
	public static final int DAILY = 2;
	public static final int WEEKLY = 3;
	public static final int MONTHLY = 4;
	
	private int compId = 0;
	private int campaignId = 0;
	private String campaignSendGroups = "";
	private String campaignName = "";
	private int campaignRecurrence = ONCE_OFF;
	private int campaignFrequency = 1;
	private Date campaignStart = DateUtil.DEFAULT_DATE;
	private Date campaignEnd = DateUtil.DEFAULT_DATE;
	private String campaignStatus = CAMPAIGN_ACTIVE;
	private int campaignStatusUser = 0;
	private Time campaignStatusTime = DateUtil.DEFAULT_TIME;
	private Date campaignStatusDate = DateUtil.DEFAULT_DATE;
	public CompanySmsCampaign() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CompanySmsCampaign(int compId, int campaignId, String campaignSendGroups, String campaignName,
			int campaignRecurrence, int campaignFrequency, Date campaignStart, Date campaignEnd, String campaignStatus,
			int campaignStatusUser, Time campaignStatusTime, Date campaignStatusDate) {
		super();
		this.compId = compId;
		this.campaignId = campaignId;
		this.campaignSendGroups = campaignSendGroups;
		this.campaignName = campaignName;
		this.campaignRecurrence = campaignRecurrence;
		this.campaignFrequency = campaignFrequency;
		this.campaignStart = campaignStart;
		this.campaignEnd = campaignEnd;
		this.campaignStatus = campaignStatus;
		this.campaignStatusUser = campaignStatusUser;
		this.campaignStatusTime = campaignStatusTime;
		this.campaignStatusDate = campaignStatusDate;
	}
	public int getCompId() {
		return compId;
	}
	public void setCompId(int compId) {
		this.compId = compId;
	}
	public int getCampaignId() {
		return campaignId;
	}
	public void setCampaignId(int campaignId) {
		this.campaignId = campaignId;
	}
	public String getCampaignSendGroups() {
		return campaignSendGroups;
	}
	public void setCampaignSendGroups(String campaignSendGroups) {
		this.campaignSendGroups = campaignSendGroups;
	}
	public String getCampaignName() {
		return campaignName;
	}
	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}
	public int getCampaignRecurrence() {
		return campaignRecurrence;
	}
	public void setCampaignRecurrence(int campaignRecurrence) {
		this.campaignRecurrence = campaignRecurrence;
	}
	public int getCampaignFrequency() {
		return campaignFrequency;
	}
	public void setCampaignFrequency(int campaignFrequency) {
		this.campaignFrequency = campaignFrequency;
	}
	public Date getCampaignStart() {
		return campaignStart;
	}
	public void setCampaignStart(Date campaignStart) {
		this.campaignStart = campaignStart;
	}
	public Date getCampaignEnd() {
		return campaignEnd;
	}
	public void setCampaignEnd(Date campaignEnd) {
		this.campaignEnd = campaignEnd;
	}
	public String getCampaignStatus() {
		return campaignStatus;
	}
	public void setCampaignStatus(String campaignStatus) {
		this.campaignStatus = campaignStatus;
	}
	public int getCampaignStatusUser() {
		return campaignStatusUser;
	}
	public void setCampaignStatusUser(int campaignStatusUser) {
		this.campaignStatusUser = campaignStatusUser;
	}
	public Time getCampaignStatusTime() {
		return campaignStatusTime;
	}
	public void setCampaignStatusTime(Time campaignStatusTime) {
		this.campaignStatusTime = campaignStatusTime;
	}
	public Date getCampaignStatusDate() {
		return campaignStatusDate;
	}
	public void setCampaignStatusDate(Date campaignStatusDate) {
		this.campaignStatusDate = campaignStatusDate;
	}
	@Override
	public String toString() {
		return "CompanySmsCampaign [compId=" + compId + ", campaignId=" + campaignId + ", campaignSendGroups="
				+ campaignSendGroups + ", campaignName=" + campaignName + ", campaignRecurrence=" + campaignRecurrence
				+ ", campaignFrequency=" + campaignFrequency + ", campaignStart=" + campaignStart + ", campaignEnd="
				+ campaignEnd + ", campaignStatus=" + campaignStatus + ", campaignStatusUser=" + campaignStatusUser
				+ ", campaignStatusTime=" + campaignStatusTime + ", campaignStatusDate=" + campaignStatusDate + "]";
	}
	
	
	
	
}
