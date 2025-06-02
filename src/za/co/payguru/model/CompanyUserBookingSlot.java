package za.co.payguru.model;

public class CompanyUserBookingSlot {
	private int userID;
	private String bookingDay;
	private String bookingSlots;
	private String bookingDate;
	public CompanyUserBookingSlot() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CompanyUserBookingSlot(int userID, String bookingDay, String bookingSlots, String bookingDate) {
		super();
		this.userID = userID;
		this.bookingDay = bookingDay;
		this.bookingSlots = bookingSlots;
		this.bookingDate = bookingDate;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getBookingDay() {
		return bookingDay;
	}
	public void setBookingDay(String bookingDay) {
		this.bookingDay = bookingDay;
	}
	public String getBookingSlots() {
		return bookingSlots;
	}
	public void setBookingSlots(String bookingSlots) {
		this.bookingSlots = bookingSlots;
	}
	public String getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}
	
	
}
