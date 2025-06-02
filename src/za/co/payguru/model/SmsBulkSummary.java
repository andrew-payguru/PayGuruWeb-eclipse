package za.co.payguru.model;

public class SmsBulkSummary {
	public static final String SUMMARY_TYPE_ALL = "1";
	public static final String SUMMARY_TYPE_PRODUCT = "2";
	public static final String SUMMARY_TYPE_FILE = "3";
	
	public static final String SUMMARY_STATUS_ACTIVE = "1";
	public static final String SUMMARY_STATUS_BUSY = "2";
	public static final String SUMMARY_STATUS_DONE = "3";
	public static final String SUMMARY_STATUS_ERROR = "4";
	public static final String SUMMARY_STATUS_INACTIVE = "0";
	
	private int summaryID;
	private int summaryCompId;
	private String summaryDesc;
	private String summaryType;
	private String summaryTypeRef1;
	private String summaryCreatedDate;
	private String summaryCreatedTime;
	private int summaryCreatedUserID;
	private String summarySmsMessage;
	private String summarySmsSendDate;
	private String summarySmsSendTime;
	private String summaryStatus;
	private String summaryStatusDate;
	private String summaryStatusTime;
	public SmsBulkSummary() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SmsBulkSummary(int summaryID, int summaryCompId, String summaryDesc, String summaryType,
			String summaryTypeRef1, String summaryCreatedDate, String summaryCreatedTime, int summaryCreatedUserID,
			String summarySmsMessage, String summarySmsSendDate, String summarySmsSendTime, String summaryStatus,
			String summaryStatusDate, String summaryStatusTime) {
		super();
		this.summaryID = summaryID;
		this.summaryCompId = summaryCompId;
		this.summaryDesc = summaryDesc;
		this.summaryType = summaryType;
		this.summaryTypeRef1 = summaryTypeRef1;
		this.summaryCreatedDate = summaryCreatedDate;
		this.summaryCreatedTime = summaryCreatedTime;
		this.summaryCreatedUserID = summaryCreatedUserID;
		this.summarySmsMessage = summarySmsMessage;
		this.summarySmsSendDate = summarySmsSendDate;
		this.summarySmsSendTime = summarySmsSendTime;
		this.summaryStatus = summaryStatus;
		this.summaryStatusDate = summaryStatusDate;
		this.summaryStatusTime = summaryStatusTime;
	}
	public int getSummaryID() {
		return summaryID;
	}
	public void setSummaryID(int summaryID) {
		this.summaryID = summaryID;
	}
	public int getSummaryCompId() {
		return summaryCompId;
	}
	public void setSummaryCompId(int summaryCompId) {
		this.summaryCompId = summaryCompId;
	}
	public String getSummaryDesc() {
		return summaryDesc;
	}
	public void setSummaryDesc(String summaryDesc) {
		this.summaryDesc = summaryDesc;
	}
	public String getSummaryType() {
		return summaryType;
	}
	public void setSummaryType(String summaryType) {
		this.summaryType = summaryType;
	}
	public String getSummaryTypeRef1() {
		return summaryTypeRef1;
	}
	public void setSummaryTypeRef1(String summaryTypeRef1) {
		this.summaryTypeRef1 = summaryTypeRef1;
	}
	public String getSummaryCreatedDate() {
		return summaryCreatedDate;
	}
	public void setSummaryCreatedDate(String summaryCreatedDate) {
		this.summaryCreatedDate = summaryCreatedDate;
	}
	public String getSummaryCreatedTime() {
		return summaryCreatedTime;
	}
	public void setSummaryCreatedTime(String summaryCreatedTime) {
		this.summaryCreatedTime = summaryCreatedTime;
	}
	public int getSummaryCreatedUserID() {
		return summaryCreatedUserID;
	}
	public void setSummaryCreatedUserID(int summaryCreatedUserID) {
		this.summaryCreatedUserID = summaryCreatedUserID;
	}
	public String getSummarySmsMessage() {
		return summarySmsMessage;
	}
	public void setSummarySmsMessage(String summarySmsMessage) {
		this.summarySmsMessage = summarySmsMessage;
	}
	public String getSummarySmsSendDate() {
		return summarySmsSendDate;
	}
	public void setSummarySmsSendDate(String summarySmsSendDate) {
		this.summarySmsSendDate = summarySmsSendDate;
	}
	public String getSummarySmsSendTime() {
		return summarySmsSendTime;
	}
	public void setSummarySmsSendTime(String summarySmsSendTime) {
		this.summarySmsSendTime = summarySmsSendTime;
	}
	public String getSummaryStatus() {
		return summaryStatus;
	}
	public void setSummaryStatus(String summaryStatus) {
		this.summaryStatus = summaryStatus;
	}
	public String getSummaryStatusDate() {
		return summaryStatusDate;
	}
	public void setSummaryStatusDate(String summaryStatusDate) {
		this.summaryStatusDate = summaryStatusDate;
	}
	public String getSummaryStatusTime() {
		return summaryStatusTime;
	}
	public void setSummaryStatusTime(String summaryStatusTime) {
		this.summaryStatusTime = summaryStatusTime;
	}
	@Override
	public String toString() {
		return "SmsBulkSummary [summaryID=" + summaryID + ", summaryCompId=" + summaryCompId + ", summaryDesc="
				+ summaryDesc + ", summaryType=" + summaryType + ", summaryTypeRef1=" + summaryTypeRef1
				+ ", summaryCreatedDate=" + summaryCreatedDate + ", summaryCreatedTime=" + summaryCreatedTime
				+ ", summaryCreatedUserID=" + summaryCreatedUserID + ", summarySmsMessage=" + summarySmsMessage
				+ ", summarySmsSendDate=" + summarySmsSendDate + ", summarySmsSendTime=" + summarySmsSendTime
				+ ", summaryStatus=" + summaryStatus + ", summaryStatusDate=" + summaryStatusDate + ", summaryStatusTime="
				+ summaryStatusTime + "]";
	}
	
	
	
}
