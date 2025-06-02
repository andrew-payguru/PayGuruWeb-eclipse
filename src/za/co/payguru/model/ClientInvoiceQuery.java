package za.co.payguru.model;

import java.sql.Date;
import java.sql.Time;

public class ClientInvoiceQuery {
	public static final String INV_QUERY_TYPE_CLAIM = "1";
	
	public static final String INV_QUERY_FILTER_POLICY = "policyno";
	public static final String INV_QUERY_FILTER_CELL = "clientcell";
	public static final String INV_QUERY_FILTER_NAME = "clientname";
	public static final String INV_QUERY_FILTER_SURNAME = "clientsurname";
	public static final String INV_QUERY_FILTER_REGNO = "vehiclereg";
	public static final String INV_QUERY_FILTER_MAKE = "vehiclemake";
	public static final String INV_QUERY_FILTER_ENGNO = "vehicleengno";
	
	private int invQueryId = 0;
	private String invNo = "";
	private String invQueryNo = "";
	private int invQueryCompId = 0;
	private int invQueryUserId = 0;
	private Date invQueryCreateDate = Date.valueOf("2022-01-01");
	private Time invQueryCreateTime = Time.valueOf("10:00:00");
	private String invQueryRef1 = "";
	private String invQueryRef2 = "";
	private String invQueryRef3 = "";
	private String invQueryRef4 = "";
	private String invQueryComment = "";
	private double invQueryAmt1 = 0;
	private double invQueryAmt2 = 0;
	private String invQueryType = INV_QUERY_TYPE_CLAIM;
	private Date invQueryDate1 = Date.valueOf("2022-01-01");
	public ClientInvoiceQuery() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ClientInvoiceQuery(int invQueryId, String invNo, String invQueryNo, int invQueryCompId, int invQueryUserId,
			Date invQueryCreateDate, Time invQueryCreateTime, String invQueryRef1, String invQueryRef2, String invQueryRef3,
			String invQueryRef4, String invQueryComment, double invQueryAmt1, double invQueryAmt2, String invQueryType,
			Date invQueryDate1) {
		super();
		this.invQueryId = invQueryId;
		this.invNo = invNo;
		this.invQueryNo = invQueryNo;
		this.invQueryCompId = invQueryCompId;
		this.invQueryUserId = invQueryUserId;
		this.invQueryCreateDate = invQueryCreateDate;
		this.invQueryCreateTime = invQueryCreateTime;
		this.invQueryRef1 = invQueryRef1;
		this.invQueryRef2 = invQueryRef2;
		this.invQueryRef3 = invQueryRef3;
		this.invQueryRef4 = invQueryRef4;
		this.invQueryComment = invQueryComment;
		this.invQueryAmt1 = invQueryAmt1;
		this.invQueryAmt2 = invQueryAmt2;
		this.invQueryType = invQueryType;
		this.invQueryDate1 = invQueryDate1;
	}
	public int getInvQueryId() {
		return invQueryId;
	}
	public void setInvQueryId(int invQueryId) {
		this.invQueryId = invQueryId;
	}
	public String getInvNo() {
		return invNo;
	}
	public void setInvNo(String invNo) {
		this.invNo = invNo;
	}
	public String getInvQueryNo() {
		return invQueryNo;
	}
	public void setInvQueryNo(String invQueryNo) {
		this.invQueryNo = invQueryNo;
	}
	public int getInvQueryCompId() {
		return invQueryCompId;
	}
	public void setInvQueryCompId(int invQueryCompId) {
		this.invQueryCompId = invQueryCompId;
	}
	public int getInvQueryUserId() {
		return invQueryUserId;
	}
	public void setInvQueryUserId(int invQueryUserId) {
		this.invQueryUserId = invQueryUserId;
	}
	public Date getInvQueryCreateDate() {
		return invQueryCreateDate;
	}
	public void setInvQueryCreateDate(Date invQueryCreateDate) {
		this.invQueryCreateDate = invQueryCreateDate;
	}
	public Time getInvQueryCreateTime() {
		return invQueryCreateTime;
	}
	public void setInvQueryCreateTime(Time invQueryCreateTime) {
		this.invQueryCreateTime = invQueryCreateTime;
	}
	public String getInvQueryRef1() {
		return invQueryRef1;
	}
	public void setInvQueryRef1(String invQueryRef1) {
		this.invQueryRef1 = invQueryRef1;
	}
	public String getInvQueryRef2() {
		return invQueryRef2;
	}
	public void setInvQueryRef2(String invQueryRef2) {
		this.invQueryRef2 = invQueryRef2;
	}
	public String getInvQueryRef3() {
		return invQueryRef3;
	}
	public void setInvQueryRef3(String invQueryRef3) {
		this.invQueryRef3 = invQueryRef3;
	}
	public String getInvQueryRef4() {
		return invQueryRef4;
	}
	public void setInvQueryRef4(String invQueryRef4) {
		this.invQueryRef4 = invQueryRef4;
	}
	public String getInvQueryComment() {
		return invQueryComment;
	}
	public void setInvQueryComment(String invQueryComment) {
		this.invQueryComment = invQueryComment;
	}
	public double getInvQueryAmt1() {
		return invQueryAmt1;
	}
	public void setInvQueryAmt1(double invQueryAmt1) {
		this.invQueryAmt1 = invQueryAmt1;
	}
	public double getInvQueryAmt2() {
		return invQueryAmt2;
	}
	public void setInvQueryAmt2(double invQueryAmt2) {
		this.invQueryAmt2 = invQueryAmt2;
	}
	public String getInvQueryType() {
		return invQueryType;
	}
	public void setInvQueryType(String invQueryType) {
		this.invQueryType = invQueryType;
	}
	public Date getInvQueryDate1() {
		return invQueryDate1;
	}
	public void setInvQueryDate1(Date invQueryDate1) {
		this.invQueryDate1 = invQueryDate1;
	}
	@Override
	public String toString() {
		return "ClientInvoiceQuery [invQueryId=" + invQueryId + ", invNo=" + invNo + ", invQueryNo=" + invQueryNo
				+ ", invQueryCompId=" + invQueryCompId + ", invQueryUserId=" + invQueryUserId + ", invQueryCreateDate="
				+ invQueryCreateDate + ", invQueryCreateTime=" + invQueryCreateTime + ", invQueryRef1=" + invQueryRef1
				+ ", invQueryRef2=" + invQueryRef2 + ", invQueryRef3=" + invQueryRef3 + ", invQueryRef4=" + invQueryRef4
				+ ", invQueryComment=" + invQueryComment + ", invQueryAmt1=" + invQueryAmt1 + ", invQueryAmt2=" + invQueryAmt2
				+ ", invQueryType=" + invQueryType + ", invQueryDate1=" + invQueryDate1 + "]";
	}
	
	public StringBuilder toJsonString() {
		String json = "{"
				+ "\"invqueryid\" : " + invQueryId + ", \"invno\" : \"" + invNo + "\", \"invqueryno\" : \"" + invQueryNo
				+ "\", \"invquerycompid\" : " + invQueryCompId + ", \"invqueryuserid\" : " + invQueryUserId + ", \"invquerycreatedate\" : "
				+ "\"" + invQueryCreateDate + "\", \"invquerycreatetime\" : \"" + invQueryCreateTime + "\", \"invqueryref1\" : \"" + invQueryRef1
				+ "\", \"invqueryref2\" : \"" + invQueryRef2 + "\", \"invqueryref3\" : \"" + invQueryRef3 + "\", \"invqueryref4\" : \"" + invQueryRef4
				+ "\", \"invquerycomment\" : \"" + invQueryComment + "\", \"invqueryamt1\" : " + invQueryAmt1 + ", \"invqueryamt2\" : " + invQueryAmt2
				+ ", \"invquerytype\" : \"" + invQueryType + "\", \"invquerydate1\" : \"" + invQueryDate1 + "\"}";
		return new StringBuilder(json);
	}
	
	
	
}
