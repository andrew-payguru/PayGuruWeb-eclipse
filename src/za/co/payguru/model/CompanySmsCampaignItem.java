package za.co.payguru.model;

import java.sql.Date;
import java.sql.Time;

import za.co.payguru.util.DateUtil;

public class CompanySmsCampaignItem {
	public static final String CAMPAIGN_ITEM_ACTIVE = "1";
	public static final String CAMPAIGN_ITEM_INACTIVE = "0";
	private int compId = 0;
	private int campaignId = 0;
	private long campaignItemNo = 0;
	private String campaignItemMessage = "";
	private Date campaignItemDate = DateUtil.DEFAULT_DATE;
	private Time campaignItemTime = DateUtil.DEFAULT_TIME;
	private String campaignItemStatus = CAMPAIGN_ITEM_ACTIVE;
	private String campaignItemActive = CAMPAIGN_ITEM_ACTIVE;
	public CompanySmsCampaignItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CompanySmsCampaignItem(int compId, int campaignId, long campaignItemNo, String campaignItemMessage,
			Date campaignItemDate, Time campaignItemTime, String campaignItemStatus, String campaignItemActive) {
		super();
		this.compId = compId;
		this.campaignId = campaignId;
		this.campaignItemNo = campaignItemNo;
		this.campaignItemMessage = campaignItemMessage;
		this.campaignItemDate = campaignItemDate;
		this.campaignItemTime = campaignItemTime;
		this.campaignItemStatus = campaignItemStatus;
		this.campaignItemActive = campaignItemActive;
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
	public long getCampaignItemNo() {
		return campaignItemNo;
	}
	public void setCampaignItemNo(long campaignItemNo) {
		this.campaignItemNo = campaignItemNo;
	}
	public String getCampaignItemMessage() {
		return campaignItemMessage;
	}
	public void setCampaignItemMessage(String campaignItemMessage) {
		this.campaignItemMessage = campaignItemMessage;
	}
	public Date getCampaignItemDate() {
		return campaignItemDate;
	}
	public void setCampaignItemDate(Date campaignItemDate) {
		this.campaignItemDate = campaignItemDate;
	}
	public Time getCampaignItemTime() {
		return campaignItemTime;
	}
	public void setCampaignItemTime(Time campaignItemTime) {
		this.campaignItemTime = campaignItemTime;
	}
	public String getCampaignItemStatus() {
		return campaignItemStatus;
	}
	public void setCampaignItemStatus(String campaignItemStatus) {
		this.campaignItemStatus = campaignItemStatus;
	}
	public String getCampaignItemActive() {
		return campaignItemActive;
	}
	public void setCampaignItemActive(String campaignItemActive) {
		this.campaignItemActive = campaignItemActive;
	}
	@Override
	public String toString() {
		return "CompanySmsCampaignItem [compId=" + compId + ", campaignId=" + campaignId + ", campaignItemNo="
				+ campaignItemNo + ", campaignItemMessage=" + campaignItemMessage + ", campaignItemDate=" + campaignItemDate
				+ ", campaignItemTime=" + campaignItemTime + ", campaignItemStatus=" + campaignItemStatus
				+ ", campaignItemActive=" + campaignItemActive + "]";
	}
	
	
}
