package za.co.payguru.model;

import org.json.JSONObject;

public class CompanyProduct {
	public static String PROD_ACTIVE = "1";
	public static String PROD_INACTIVE = "0";
	
	public static final int TYPE_CUSTOM  = 0;
	public static final int TYPE_DAILY   = 1;
	public static final int TYPE_WEEKLY  = 2;
	public static final int TYPE_MONTHLY = 3;
	public static final int TYPE_YEARLY  = 4;
	public static final int TYPE_HOURLY  = 5;
	public static final int TYPE_WALLET  = 99;
	public static final int TYPE_LOYALTY_WALLET  = 100;
	
	public static int PROD_REBILL_FREQ_CUSTOM = 0; 
	public static int PROD_REBILL_FREQ_DAILY = 1;
	public static int PROD_REBILL_FREQ_WEEKLY = 2;
	public static int PROD_REBILL_FREQ_MONTLY = 3;
	public static int PROD_REBILL_FREQ_ANNUAL = 4;
	
	public static int PROD_STRUCT_SUBSCRIP = 1;
	public static int PROD_STRUCT_EVENT = 2;
	
	public static int PROD_PAY_TYPE_FIXED = 1;
	public static int PROD_PAY_TYPE_VARIABLE = 2;
	
	public static int PROD_LIFECYCLE_CONTINUOUS = 1;
	public static int PROD_LIFECYCLE_LIMITED = 2;
	
	public static int PROD_REBILL_TYPE_AUTO = 1;
	public static int PROD_REBILL_TYPE_MANUAL = 2;
	
	private int prodId = 0;
	private int compId = 0;
	private String prodCode = "";
	private String prodName = "";
	private String prodShortName = "";
	private int prodType = 0;
	private double prodTypeAmt1 = 0;
	private double prodTypeAmt2 = 0;
	private double prodTypeRsp = 0;
	private double prodTypeCost = 0;
	private String prodJoinName = "";
	private String prodProRata = "";
	private int prodTypePay = 0;
	private int prodTypeRebill = 0;
	private int prodTypeCycle = 0;
	private String prodStartDate = "";
	private String prodEndDate = "";
	private String prodActive = PROD_ACTIVE;
	private String prodSmsMessage1 = "";
	private int prodSmsDays1 = 0;
	private int prodSmsDays2 = 0;
	private String prodExtRef1 = "";
	private String prodExtRef2 = "";
	private double prodExtAmt1 = 0;
	private String prodSmsMessage2 = "";
	private int prodStruct = 0;
	private double prodStructAmt1 = 0;
	private double prodStructAmt2 = 0;
	private String prodSmsMessage3 = "";
	private int compInternalId = 0;
	public CompanyProduct() {
		this.prodId = 0;
		this.compId = 0;
		this.prodCode = "";
		this.prodName = "";
		this.prodShortName = "";
		this.prodType = 0;
		this.prodTypeAmt1 = 0;
		this.prodTypeAmt2 = 0;
		this.prodTypeRsp = 0;
		this.prodTypeCost = 0;
		this.prodJoinName = "";
		this.prodProRata = "";
		this.prodTypePay = 0;
		this.prodTypeRebill = 0;
		this.prodTypeCycle = 0;
		this.prodStartDate = "";
		this.prodEndDate = "";
		this.prodActive = "";
		this.prodSmsMessage1 = "";
		this.prodSmsDays1 = 0;
		this.prodSmsDays2 = 0;
		this.prodExtRef1 = "";
		this.prodExtRef2 = "";
		this.prodExtAmt1 = 0;
		this.prodSmsMessage2 = "";
		this.prodStruct = 0;
		this.prodStructAmt1 = 0;
		this.prodStructAmt2 = 0;
		this.prodSmsMessage3 = "";
		this.compInternalId = 0;
	}
	public CompanyProduct(int prodId, int compId, String prodCode, String prodName, String prodShortName, int prodType,
			double prodTypeAmt1, double prodTypeAmt2, double prodTypeRsp, double prodTypeCost, String prodJoinName,
			String prodProRata, int prodTypePay, int prodTypeRebill, int prodTypeCycle, String prodStartDate,
			String prodEndDate, String prodActive, String prodSmsMessage1, int prodSmsDays1, int prodSmsDays2,
			String prodExtRef1, String prodExtRef2, double prodExtAmt1, String prodSmsMessage2, int prodStruct,
			double prodStructAmt1, double prodStructAmt2, String prodSmsMessage3, int compInternalId) {
		super();
		this.prodId = prodId;
		this.compId = compId;
		this.prodCode = prodCode;
		this.prodName = prodName;
		this.prodShortName = prodShortName;
		this.prodType = prodType;
		this.prodTypeAmt1 = prodTypeAmt1;
		this.prodTypeAmt2 = prodTypeAmt2;
		this.prodTypeRsp = prodTypeRsp;
		this.prodTypeCost = prodTypeCost;
		this.prodJoinName = prodJoinName;
		this.prodProRata = prodProRata;
		this.prodTypePay = prodTypePay;
		this.prodTypeRebill = prodTypeRebill;
		this.prodTypeCycle = prodTypeCycle;
		this.prodStartDate = prodStartDate;
		this.prodEndDate = prodEndDate;
		this.prodActive = prodActive;
		this.prodSmsMessage1 = prodSmsMessage1;
		this.prodSmsDays1 = prodSmsDays1;
		this.prodSmsDays2 = prodSmsDays2;
		this.prodExtRef1 = prodExtRef1;
		this.prodExtRef2 = prodExtRef2;
		this.prodExtAmt1 = prodExtAmt1;
		this.prodSmsMessage2 = prodSmsMessage2;
		this.prodStruct = prodStruct;
		this.prodStructAmt1 = prodStructAmt1;
		this.prodStructAmt2 = prodStructAmt2;
		this.prodSmsMessage3 = prodSmsMessage3;
		this.compInternalId = compInternalId;
	}
	public int getProdId() {
		return prodId;
	}
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
	public int getCompId() {
		return compId;
	}
	public void setCompId(int compId) {
		this.compId = compId;
	}
	public String getProdCode() {
		return prodCode;
	}
	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getProdShortName() {
		return prodShortName;
	}
	public void setProdShortName(String prodShortName) {
		this.prodShortName = prodShortName;
	}
	public int getProdType() {
		return prodType;
	}
	public void setProdType(int prodType) {
		this.prodType = prodType;
	}
	public double getProdTypeAmt1() {
		return prodTypeAmt1;
	}
	public void setProdTypeAmt1(double prodTypeAmt1) {
		this.prodTypeAmt1 = prodTypeAmt1;
	}
	public double getProdTypeAmt2() {
		return prodTypeAmt2;
	}
	public void setProdTypeAmt2(double prodTypeAmt2) {
		this.prodTypeAmt2 = prodTypeAmt2;
	}
	public double getProdTypeRsp() {
		return prodTypeRsp;
	}
	public void setProdTypeRsp(double prodTypeRsp) {
		this.prodTypeRsp = prodTypeRsp;
	}
	public double getProdTypeCost() {
		return prodTypeCost;
	}
	public void setProdTypeCost(double prodTypeCost) {
		this.prodTypeCost = prodTypeCost;
	}
	public String getProdJoinName() {
		return prodJoinName;
	}
	public void setProdJoinName(String prodJoinName) {
		this.prodJoinName = prodJoinName;
	}
	public String getProdProRata() {
		return prodProRata;
	}
	public void setProdProRata(String prodProRata) {
		this.prodProRata = prodProRata;
	}
	public int getProdTypePay() {
		return prodTypePay;
	}
	public void setProdTypePay(int prodTypePay) {
		this.prodTypePay = prodTypePay;
	}
	public int getProdTypeRebill() {
		return prodTypeRebill;
	}
	public void setProdTypeRebill(int prodTypeRebill) {
		this.prodTypeRebill = prodTypeRebill;
	}
	public int getProdTypeCycle() {
		return prodTypeCycle;
	}
	public void setProdTypeCycle(int prodTypeCycle) {
		this.prodTypeCycle = prodTypeCycle;
	}
	public String getProdStartDate() {
		return prodStartDate;
	}
	public void setProdStartDate(String prodStartDate) {
		this.prodStartDate = prodStartDate;
	}
	public String getProdEndDate() {
		return prodEndDate;
	}
	public void setProdEndDate(String prodEndDate) {
		this.prodEndDate = prodEndDate;
	}
	public String getProdActive() {
		return prodActive;
	}
	public void setProdActive(String prodActive) {
		this.prodActive = prodActive;
	}
	public String getProdSmsMessage1() {
		return prodSmsMessage1;
	}
	public void setProdSmsMessage1(String prodSmsMessage1) {
		this.prodSmsMessage1 = prodSmsMessage1;
	}
	public int getProdSmsDays1() {
		return prodSmsDays1;
	}
	public void setProdSmsDays1(int prodSmsDays1) {
		this.prodSmsDays1 = prodSmsDays1;
	}
	public int getProdSmsDays2() {
		return prodSmsDays2;
	}
	public void setProdSmsDays2(int prodSmsDays2) {
		this.prodSmsDays2 = prodSmsDays2;
	}
	public String getProdExtRef1() {
		return prodExtRef1;
	}
	public void setProdExtRef1(String prodExtRef1) {
		this.prodExtRef1 = prodExtRef1;
	}
	public String getProdExtRef2() {
		return prodExtRef2;
	}
	public void setProdExtRef2(String prodExtRef2) {
		this.prodExtRef2 = prodExtRef2;
	}
	public double getProdExtAmt1() {
		return prodExtAmt1;
	}
	public void setProdExtAmt1(double prodExtAmt1) {
		this.prodExtAmt1 = prodExtAmt1;
	}
	public String getProdSmsMessage2() {
		return prodSmsMessage2;
	}
	public void setProdSmsMessage2(String prodSmsMessage2) {
		this.prodSmsMessage2 = prodSmsMessage2;
	}
	public int getProdStruct() {
		return prodStruct;
	}
	public void setProdStruct(int prodStruct) {
		this.prodStruct = prodStruct;
	}
	public double getProdStructAmt1() {
		return prodStructAmt1;
	}
	public void setProdStructAmt1(double prodStructAmt1) {
		this.prodStructAmt1 = prodStructAmt1;
	}
	public double getProdStructAmt2() {
		return prodStructAmt2;
	}
	public void setProdStructAmt2(double prodStructAmt2) {
		this.prodStructAmt2 = prodStructAmt2;
	}
	public String getProdSmsMessage3() {
		return prodSmsMessage3;
	}
	public void setProdSmsMessage3(String prodSmsMessage3) {
		this.prodSmsMessage3 = prodSmsMessage3;
	}
	public int getCompInternalId() {
		return compInternalId;
	}
	public void setCompInternalId(int compInternalId) {
		this.compInternalId = compInternalId;
	}
	@Override
	public String toString() {
		return "CompanyProduct [prodId=" + prodId + ", compId=" + compId + ", prodCode=" + prodCode + ", prodName="
				+ prodName + ", prodShortName=" + prodShortName + ", prodType=" + prodType + ", prodTypeAmt1=" + prodTypeAmt1
				+ ", prodTypeAmt2=" + prodTypeAmt2 + ", prodTypeRsp=" + prodTypeRsp + ", prodTypeCost=" + prodTypeCost
				+ ", prodJoinName=" + prodJoinName + ", prodProRata=" + prodProRata + ", prodTypePay=" + prodTypePay
				+ ", prodTypeRebill=" + prodTypeRebill + ", prodTypeCycle=" + prodTypeCycle + ", prodStartDate=" + prodStartDate
				+ ", prodEndDate=" + prodEndDate + ", prodActive=" + prodActive + ", prodSmsMessage1=" + prodSmsMessage1
				+ ", prodSmsDays1=" + prodSmsDays1 + ", prodSmsDays2=" + prodSmsDays2 + ", prodExtRef1=" + prodExtRef1
				+ ", prodExtRef2=" + prodExtRef2 + ", prodExtAmt1=" + prodExtAmt1 + ", prodSmsMessage2=" + prodSmsMessage2
				+ ", prodStruct=" + prodStruct + ", prodStructAmt1=" + prodStructAmt1 + ", prodStructAmt2=" + prodStructAmt2
				+ ", prodSmsMessage3=" + prodSmsMessage3 + ", compInternalId=" + compInternalId + "]";
	}
	
	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		try {
			 json.put("compid",compId);
			 json.put("prodid",prodId);
			 json.put("prodcode", prodCode);
			 json.put("prodname", prodName);
			 json.put("prodshortname", prodShortName);
			 json.put("prodtype", prodType);
			 json.put("prodtypeamt1", prodTypeAmt1);
			 json.put("prodtypeamt2", prodTypeAmt2);
			 json.put("prodtypersp", prodTypeRsp);
			 json.put("prodtypecost", prodTypeCost);
			 json.put("prodjoinname", prodJoinName);
			 json.put("prodprorata", prodProRata);
			 json.put("prodtypepay", prodTypePay);
			 json.put("prodtyperebill", prodTypeRebill);
			 json.put("prodtypecycle", prodTypeCycle);
			 json.put("prodstartdate", prodStartDate);
			 json.put("prodenddate", prodEndDate);
			 json.put("prodactive", prodActive);
			 json.put("prodsmsmessage1", prodSmsMessage1);
			 json.put("prodsmsdays1", prodSmsMessage2);
			 json.put("prodsmsdays2", prodSmsDays2);
			 json.put("prodextref1", prodExtRef1);
			 json.put("prodextref2", prodExtRef2);
			 json.put("prodextamt1", prodExtAmt1);
			 json.put("prodsmsmessage2", prodSmsMessage2);
			 json.put("prodstruct", prodStruct);
			 json.put("prodstructamt1", prodStructAmt1);
			 json.put("prodstructamt2", prodStructAmt2);
			 json.put("prodsmsmessage3", prodSmsMessage3);
			 json.put("compinternalid", compInternalId);
		}catch (Exception e) {
			System.out.println("Error creating json: " + e.toString());
		}
		return json;
	}
	
	
	
}