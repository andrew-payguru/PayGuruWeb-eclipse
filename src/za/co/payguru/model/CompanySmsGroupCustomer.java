package za.co.payguru.model;

import java.sql.Date;
import java.sql.Time;

import za.co.payguru.util.DateUtil;

public class CompanySmsGroupCustomer {
	public static final String ACTIVE = "1";
	public static final String INACTIVE = "0";
	
	private long custId = 0;
	private int smsGroupId = 0;
	private Date groupCustCreatedDate = DateUtil.getCurrentCCYYMMDDDate();
	private Time groupCustCreatedTime = DateUtil.getCurrentHHMMSSTime();
	private Date groupCustLastSendDate = DateUtil.getCurrentCCYYMMDDDate();
	private Time groupCustLastSendTime = DateUtil.getCurrentHHMMSSTime();
	private String groupCustActive = ACTIVE;
	public CompanySmsGroupCustomer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CompanySmsGroupCustomer(long custId, int smsGroupId, Date groupCustCreatedDate, Time groupCustCreatedTime,
			Date groupCustLastSendDate, Time groupCustLastSendTime, String groupCustActive) {
		super();
		this.custId = custId;
		this.smsGroupId = smsGroupId;
		this.groupCustCreatedDate = groupCustCreatedDate;
		this.groupCustCreatedTime = groupCustCreatedTime;
		this.groupCustLastSendDate = groupCustLastSendDate;
		this.groupCustLastSendTime = groupCustLastSendTime;
		this.groupCustActive = groupCustActive;
	}
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	public int getSmsGroupId() {
		return smsGroupId;
	}
	public void setSmsGroupId(int smsGroupId) {
		this.smsGroupId = smsGroupId;
	}
	public Date getGroupCustCreatedDate() {
		return groupCustCreatedDate;
	}
	public void setGroupCustCreatedDate(Date groupCustCreatedDate) {
		this.groupCustCreatedDate = groupCustCreatedDate;
	}
	public Time getGroupCustCreatedTime() {
		return groupCustCreatedTime;
	}
	public void setGroupCustCreatedTime(Time groupCustCreatedTime) {
		this.groupCustCreatedTime = groupCustCreatedTime;
	}
	public Date getGroupCustLastSendDate() {
		return groupCustLastSendDate;
	}
	public void setGroupCustLastSendDate(Date groupCustLastSendDate) {
		this.groupCustLastSendDate = groupCustLastSendDate;
	}
	public Time getGroupCustLastSendTime() {
		return groupCustLastSendTime;
	}
	public void setGroupCustLastSendTime(Time groupCustLastSendTime) {
		this.groupCustLastSendTime = groupCustLastSendTime;
	}
	public String getGroupCustActive() {
		return groupCustActive;
	}
	public void setGroupCustActive(String groupCustActive) {
		this.groupCustActive = groupCustActive;
	}
	@Override
	public String toString() {
		return "CompanySmsGroupCustomer [custId=" + custId + ", smsGroupId=" + smsGroupId + ", groupCustCreatedDate="
				+ groupCustCreatedDate + ", groupCustCreatedTime=" + groupCustCreatedTime + ", groupCustLastSendDate="
				+ groupCustLastSendDate + ", groupCustLastSendTime=" + groupCustLastSendTime + ", groupCustActive="
				+ groupCustActive + "]";
	}
	
}
