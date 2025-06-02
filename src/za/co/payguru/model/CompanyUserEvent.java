package za.co.payguru.model;

public class CompanyUserEvent {
	public static final int EVENT_TYPE_RESENDLINK = 1;
	public static final int EVENT_TYPE_CANCELPOLICY = 2;
	public static final int EVENT_TYPE_UPDATE = 3;
	
	private int compId;
	private int eventId;
	private int userId;
	private String eventDate;
	private String eventTime;
	private int eventType;
	private String eventRef1;
	private String eventRef2;
	public CompanyUserEvent(int compId, int eventId, int userId, String eventDate, String eventTime, int eventType,
			String eventRef1, String eventRef2) {
		super();
		this.compId = compId;
		this.eventId = eventId;
		this.userId = userId;
		this.eventDate = eventDate;
		this.eventTime = eventTime;
		this.eventType = eventType;
		this.eventRef1 = eventRef1;
		this.eventRef2 = eventRef2;
	}
	public CompanyUserEvent() {
		 	this.compId = 0;
		 	this.eventId = 0;
		 	this.userId = 0;
		 	this.eventDate = "";
		 	this.eventTime = "";
		 	this.eventType = 0;
		 	this.eventRef1 = "";
		 	this.eventRef2 = "";
	}
	public int getCompId() {
		return compId;
	}
	public void setCompId(int compId) {
		this.compId = compId;
	}
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getEventDate() {
		return eventDate;
	}
	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}
	public String getEventTime() {
		return eventTime;
	}
	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}
	public int getEventType() {
		return eventType;
	}
	public void setEventType(int eventType) {
		this.eventType = eventType;
	}
	public String getEventRef1() {
		return eventRef1;
	}
	public void setEventRef1(String eventRef1) {
		this.eventRef1 = eventRef1;
	}
	public String getEventRef2() {
		return eventRef2;
	}
	public void setEventRef2(String eventRef2) {
		this.eventRef2 = eventRef2;
	}
	@Override
	public String toString() {
		return "CompanyUserEvent [compId=" + compId + ", eventId=" + eventId + ", userId=" + userId + ", eventDate="
				+ eventDate + ", eventTime=" + eventTime + ", eventType=" + eventType + ", eventRef1=" + eventRef1
				+ ", eventRef2=" + eventRef2 + "]";
	}
	
	
}
