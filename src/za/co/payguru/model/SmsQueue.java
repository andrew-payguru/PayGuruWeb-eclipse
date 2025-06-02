package za.co.payguru.model;

import za.co.payguru.util.DateUtil;

public class SmsQueue {
	public static int BULK_SMS_TARGET_METHOD_ALL_CLIENTS = 1;
	public static int BULK_SMS_TARGET_METHOD_BY_PRODUCT = 2;
	public static int BULK_SMS_TARGET_METHOD_FILE_IMPORT = 3;
	
	public static String SMS_ACTIVE = "1";
	
	private long smsId = 0;
	private String smsCellNo = "";
	private String smsDate = DateUtil.getCurrentCCYYMMDDStr();
	private String smsTime = DateUtil.getCurrentHHMMSSStr();
	private String smsMessage = "";
	private String smsStatus = SMS_ACTIVE;
	private String smsStatusDate = DateUtil.getCurrentCCYYMMDDStr();
	private String smsStatusTime = DateUtil.getCurrentHHMMSSStr();
	private String smsSendRef = "";
	private String smsPayRef = "";
	private String smsActive = SMS_ACTIVE;
	public SmsQueue() {
		this.smsId = 0;
		this.smsCellNo = "";
		this.smsDate = DateUtil.getCurrentCCYYMMDDStr();
		this.smsTime = DateUtil.getCurrentHHMMSSStr();
		this.smsMessage = "";
		this.smsStatus = SMS_ACTIVE;
		this.smsStatusDate = DateUtil.getCurrentCCYYMMDDStr();
		this.smsStatusTime = DateUtil.getCurrentHHMMSSStr();
		this.smsSendRef = "";
		this.smsPayRef = "";
		this.smsActive = SMS_ACTIVE;
	}
	public SmsQueue(long smsId, String smsCellNo, String smsDate, String smsTime, String smsMessage, String smsStatus,
			String smsStatusDate, String smsStatusTime, String smsSendRef, String smsPayRef, String smsActive) {
		super();
		this.smsId = smsId;
		this.smsCellNo = smsCellNo;
		this.smsDate = smsDate;
		this.smsTime = smsTime;
		this.smsMessage = smsMessage;
		this.smsStatus = smsStatus;
		this.smsStatusDate = smsStatusDate;
		this.smsStatusTime = smsStatusTime;
		this.smsSendRef = smsSendRef;
		this.smsPayRef = smsPayRef;
		this.smsActive = smsActive;
	}
	public long getSmsId() {
		return smsId;
	}
	public void setSmsId(long smsId) {
		this.smsId = smsId;
	}
	public String getSmsCellNo() {
		return smsCellNo;
	}
	public void setSmsCellNo(String smsCellNo) {
		this.smsCellNo = smsCellNo;
	}
	public String getSmsDate() {
		return smsDate;
	}
	public void setSmsDate(String smsDate) {
		this.smsDate = smsDate;
	}
	public String getSmsTime() {
		return smsTime;
	}
	public void setSmsTime(String smsTime) {
		this.smsTime = smsTime;
	}
	public String getSmsMessage() {
		return smsMessage;
	}
	public void setSmsMessage(String smsMessage) {
		this.smsMessage = smsMessage;
	}
	public String getSmsStatus() {
		return smsStatus;
	}
	public void setSmsStatus(String smsStatus) {
		this.smsStatus = smsStatus;
	}
	public String getSmsStatusDate() {
		return smsStatusDate;
	}
	public void setSmsStatusDate(String smsStatusDate) {
		this.smsStatusDate = smsStatusDate;
	}
	public String getSmsStatusTime() {
		return smsStatusTime;
	}
	public void setSmsStatusTime(String smsStatusTime) {
		this.smsStatusTime = smsStatusTime;
	}
	public String getSmsSendRef() {
		return smsSendRef;
	}
	public void setSmsSendRef(String smsSendRef) {
		this.smsSendRef = smsSendRef;
	}
	public String getSmsPayRef() {
		return smsPayRef;
	}
	public void setSmsPayRef(String smsPayRef) {
		this.smsPayRef = smsPayRef;
	}
	public String getSmsActive() {
		return smsActive;
	}
	public void setSmsActive(String smsActive) {
		this.smsActive = smsActive;
	}
	@Override
	public String toString() {
		return "SmsQueue [smsId=" + smsId + ", smsCellNo=" + smsCellNo + ", smsDate=" + smsDate + ", smsTime=" + smsTime
				+ ", smsMessage=" + smsMessage + ", smsStatus=" + smsStatus + ", smsStatusDate=" + smsStatusDate
				+ ", smsStatusTime=" + smsStatusTime + ", smsSendRef=" + smsSendRef + ", smsPayRef=" + smsPayRef
				+ ", smsActive=" + smsActive + "]";
	}
	
	
	
}
