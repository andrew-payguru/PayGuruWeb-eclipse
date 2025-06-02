package za.co.payguru.model;

import java.sql.Date;
import java.sql.Time;

import za.co.payguru.util.DateUtil;


public class CompanySmsCustomer {
	public static final String CUST_ACTIVE = "1";
	public static final String CUST_INACTIVE = "0";
	
	public static final int GENDER_MALE = 1;
	public static final int GENDER_FEMALE = 2;
	public static final int GENDER_OTHER = 3;
	
	private int compId = 0;
	private String custCell = "";
	private long custId = 0;
	private String custName = "";
	private String custSurname = "";
	private String custEmail = "";
	private Date custDOB = DateUtil.DEFAULT_DATE;
	private int custGender = 0;
	private String custSuburb = "";
	private String custTown = "";
	private String custProv = "";
	private Date custCreatedDate = DateUtil.DEFAULT_DATE;
	private Time custCreatedTime = DateUtil.DEFAULT_TIME;
	private int custCreatedUserId = 0;
	private String custActive = CUST_ACTIVE;
	public CompanySmsCustomer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CompanySmsCustomer(int compId, String custCell, long custId, String custName, String custSurname,
			String custEmail, Date custDOB, int custGender, String custSuburb, String custTown, String custProv,
			Date custCreatedDate, Time custCreatedTime, int custCreatedUserId, String custActive) {
		super();
		this.compId = compId;
		this.custCell = custCell;
		this.custId = custId;
		this.custName = custName;
		this.custSurname = custSurname;
		this.custEmail = custEmail;
		this.custDOB = custDOB;
		this.custGender = custGender;
		this.custSuburb = custSuburb;
		this.custTown = custTown;
		this.custProv = custProv;
		this.custCreatedDate = custCreatedDate;
		this.custCreatedTime = custCreatedTime;
		this.custCreatedUserId = custCreatedUserId;
		this.custActive = custActive;
	}
	public int getCompId() {
		return compId;
	}
	public void setCompId(int compId) {
		this.compId = compId;
	}
	public String getCustCell() {
		return custCell;
	}
	public void setCustCell(String custCell) {
		this.custCell = custCell;
	}
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustSurname() {
		return custSurname;
	}
	public void setCustSurname(String custSurname) {
		this.custSurname = custSurname;
	}
	public String getCustEmail() {
		return custEmail;
	}
	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}
	public Date getCustDOB() {
		return custDOB;
	}
	public void setCustDOB(Date custDOB) {
		this.custDOB = custDOB;
	}
	public int getCustGender() {
		return custGender;
	}
	public void setCustGender(int custGender) {
		this.custGender = custGender;
	}
	public String getCustSuburb() {
		return custSuburb;
	}
	public void setCustSuburb(String custSuburb) {
		this.custSuburb = custSuburb;
	}
	public String getCustTown() {
		return custTown;
	}
	public void setCustTown(String custTown) {
		this.custTown = custTown;
	}
	public String getCustProv() {
		return custProv;
	}
	public void setCustProv(String custProv) {
		this.custProv = custProv;
	}
	public Date getCustCreatedDate() {
		return custCreatedDate;
	}
	public void setCustCreatedDate(Date custCreatedDate) {
		this.custCreatedDate = custCreatedDate;
	}
	public Time getCustCreatedTime() {
		return custCreatedTime;
	}
	public void setCustCreatedTime(Time custCreatedTime) {
		this.custCreatedTime = custCreatedTime;
	}
	public int getCustCreatedUserId() {
		return custCreatedUserId;
	}
	public void setCustCreatedUserId(int custCreatedUserId) {
		this.custCreatedUserId = custCreatedUserId;
	}
	public String getCustActive() {
		return custActive;
	}
	public void setCustActive(String custActive) {
		this.custActive = custActive;
	}
	@Override
	public String toString() {
		return "CompanySmsCustomer [compId=" + compId + ", custCell=" + custCell + ", custId=" + custId + ", custName="
				+ custName + ", custSurname=" + custSurname + ", custEmail=" + custEmail + ", custDOB=" + custDOB
				+ ", custGender=" + custGender + ", custSuburb=" + custSuburb + ", custTown=" + custTown + ", custProv="
				+ custProv + ", custCreatedDate=" + custCreatedDate + ", custCreatedTime=" + custCreatedTime
				+ ", custCreatedUserId=" + custCreatedUserId + ", custActive=" + custActive + "]";
	}
	
	
	
}
