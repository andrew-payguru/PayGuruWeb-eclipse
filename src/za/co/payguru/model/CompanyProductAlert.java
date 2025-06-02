package za.co.payguru.model;

public class CompanyProductAlert {
	public static int ALERT_TYPE_PAYMENTS = 1;
	public static int ALERT_TYPE_REMINDER = 2;
	
	public static String ALERT_ACTIVE = "1";
	public static String ALERT_INACTIVE = "0";
	
	private int compId;
	private int alertId;
	private int prodId;
	private int alertType;
	private double alertVal1;
	private String alertSmsMessage;
	private String alertEmailMessage;
	private String alertActive;
	public CompanyProductAlert() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CompanyProductAlert(int compId, int alertId, int prodId, int alertType, double alertVal1, String alertSmsMessage,
			String alertEmailMessage, String alertActive) {
		super();
		this.compId = compId;
		this.alertId = alertId;
		this.prodId = prodId;
		this.alertType = alertType;
		this.alertVal1 = alertVal1;
		this.alertSmsMessage = alertSmsMessage;
		this.alertEmailMessage = alertEmailMessage;
		this.alertActive = alertActive;
	}
	public int getCompId() {
		return compId;
	}
	public void setCompId(int compId) {
		this.compId = compId;
	}
	public int getAlertId() {
		return alertId;
	}
	public void setAlertId(int alertId) {
		this.alertId = alertId;
	}
	public int getProdId() {
		return prodId;
	}
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
	public int getAlertType() {
		return alertType;
	}
	public void setAlertType(int alertType) {
		this.alertType = alertType;
	}
	public double getAlertVal1() {
		return alertVal1;
	}
	public void setAlertVal1(double alertVal1) {
		this.alertVal1 = alertVal1;
	}
	public String getAlertSmsMessage() {
		return alertSmsMessage;
	}
	public void setAlertSmsMessage(String alertSmsMessage) {
		this.alertSmsMessage = alertSmsMessage;
	}
	public String getAlertEmailMessage() {
		return alertEmailMessage;
	}
	public void setAlertEmailMessage(String alertEmailMessage) {
		this.alertEmailMessage = alertEmailMessage;
	}
	public String getAlertActive() {
		return alertActive;
	}
	public void setAlertActive(String alertActive) {
		this.alertActive = alertActive;
	}
	
	
}
