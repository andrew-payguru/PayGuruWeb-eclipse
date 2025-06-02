package za.co.payguru.model;

public class CompanyUserSchedule {
	private int userID;
	private int clientID;
	private int prodID;
	private String bookingDay;
	private String bookingTime;
	private String bookingDesc1;
	private String bookingDesc2;
	private String bookingActive;
	public CompanyUserSchedule() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CompanyUserSchedule(int userID, int clientID, int prodID, String bookingDay, String bookingTime,
			String bookingDesc1, String bookingDesc2, String bookingActive) {
		super();
		this.userID = userID;
		this.clientID = clientID;
		this.prodID = prodID;
		this.bookingDay = bookingDay;
		this.bookingTime = bookingTime;
		this.bookingDesc1 = bookingDesc1;
		this.bookingDesc2 = bookingDesc2;
		this.bookingActive = bookingActive;
	}
	public int getClientID() {
		return clientID;
	}
	public void setClientID(int clientID) {
		this.clientID = clientID;
	}
	public int getProdID() {
		return prodID;
	}
	public void setProdID(int prodID) {
		this.prodID = prodID;
	}
	public String getBookingDay() {
		return bookingDay;
	}
	public void setBookingDay(String bookingDay) {
		this.bookingDay = bookingDay;
	}
	public String getBookingTime() {
		return bookingTime;
	}
	public void setBookingTime(String bookingTime) {
		this.bookingTime = bookingTime;
	}
	public String getBookingDesc1() {
		return bookingDesc1;
	}
	public void setBookingDesc1(String bookingDesc1) {
		this.bookingDesc1 = bookingDesc1;
	}
	public String getBookingDesc2() {
		return bookingDesc2;
	}
	public void setBookingDesc2(String bookingDesc2) {
		this.bookingDesc2 = bookingDesc2;
	}
	public String getBookingActive() {
		return bookingActive;
	}
	public void setBookingActive(String bookingActive) {
		this.bookingActive = bookingActive;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
		
}
