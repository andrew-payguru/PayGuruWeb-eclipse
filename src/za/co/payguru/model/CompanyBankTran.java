package za.co.payguru.model;

import org.json.JSONObject;

public class CompanyBankTran {
	public static String COMPBANKTRAN_ACTIVE = "1";
	public static String COMPBANKTRAN_INACTIVE = "0";
	public static final String FAKE_TRAN = "FAKE"; 

	public static final String STATUS_INIT           = "I";
	public static final String STATUS_ACTIVE         = "1";
	public static final String STATUS_BUSY           = "2";
	public static final String STATUS_DONE           = "3";
	public static final String STATUS_ERROR          = "4";
	public static final String STATUS_INVALIDREF     = "5";
	public static final String STATUS_INVALIDDETAILS = "6";
	public static final String STATUS_SAMEDAY        = "7";
	public static final String STATUS_REVERSE        = "8";
	public static final String STATUS_CANCELLED      = "9";
	public static final String STATUS_DUPLICATE      = "D";
	public static final String STATUS_EXISTS         = "E";
		
	public static final int TYPE_FILE  = 1;
	public static final int TYPE_EMAIL = 2;
	public static final int TYPE_XML   = 3;
	
	private long bankTranId = 0;
	private int bankCompId = 0;
	private int bankId = 0;
	private int bankTranType = 0;
	private String bankTranRef = "";
	private String bankTranRef1 = "";
	private String bankTranRef2 = "";
	private String bankTranData = "";
	private String bankTranStatus = COMPBANKTRAN_ACTIVE;
	private String bankTranStatusDate = "";
	private String bankTranStatusTime = "";
	private double bankTranAmt1 = 0;
	private double bankTranAmt2 = 0;
	private double bankTranPayNo = 0;
	private String bankTranDate = "";
	private String bankTranCreatedDate = "";
	private String bankTranCreatedTime = "";
	private boolean bankTranCredit = false;
	private String bankTranActive = "";
	private long bankTranTransferTranId = 0;
	private String bankTranTransferRef = "";
	private String bankTranTransferDesc = "";
	private int bankTranStatusUser = 0;
	
	public CompanyBankTran() {
		this.bankTranId = 0;
		this.bankCompId = 0;
		this.bankId = 0;
		this.bankTranType = 0;
		this.bankTranRef = "";
		this.bankTranRef1 = "";
		this.bankTranRef2 = "";
		this.bankTranData = "";
		this.bankTranStatus = COMPBANKTRAN_ACTIVE;
		this.bankTranStatusDate = "";
		this.bankTranStatusTime = "";
		this.bankTranAmt1 = 0;
		this.bankTranAmt2 = 0;
		this.bankTranPayNo = 0;
		this.bankTranDate = "";
		this.bankTranCreatedDate = "";
		this.bankTranCreatedTime = "";
		this.bankTranCredit = false;
		this.bankTranActive = "";
		this.bankTranTransferTranId = 0;
		this.bankTranTransferRef = "";
		this.bankTranTransferDesc = "";
		this.bankTranStatusUser = 0;
	}

	

	public CompanyBankTran(long bankTranId, int bankCompId, int bankId, int bankTranType, String bankTranRef,
			String bankTranRef1, String bankTranRef2, String bankTranData, String bankTranStatus, String bankTranStatusDate,
			String bankTranStatusTime, double bankTranAmt1, double bankTranAmt2, double bankTranPayNo, String bankTranDate,
			String bankTranCreatedDate, String bankTranCreatedTime, boolean bankTranCredit, String bankTranActive,
			long bankTranTransferTranId, String bankTranTransferRef, String bankTranTransferDesc, int bankTranStatusUser) {
		super();
		this.bankTranId = bankTranId;
		this.bankCompId = bankCompId;
		this.bankId = bankId;
		this.bankTranType = bankTranType;
		this.bankTranRef = bankTranRef;
		this.bankTranRef1 = bankTranRef1;
		this.bankTranRef2 = bankTranRef2;
		this.bankTranData = bankTranData;
		this.bankTranStatus = bankTranStatus;
		this.bankTranStatusDate = bankTranStatusDate;
		this.bankTranStatusTime = bankTranStatusTime;
		this.bankTranAmt1 = bankTranAmt1;
		this.bankTranAmt2 = bankTranAmt2;
		this.bankTranPayNo = bankTranPayNo;
		this.bankTranDate = bankTranDate;
		this.bankTranCreatedDate = bankTranCreatedDate;
		this.bankTranCreatedTime = bankTranCreatedTime;
		this.bankTranCredit = bankTranCredit;
		this.bankTranActive = bankTranActive;
		this.bankTranTransferTranId = bankTranTransferTranId;
		this.bankTranTransferRef = bankTranTransferRef;
		this.bankTranTransferDesc = bankTranTransferDesc;
		this.bankTranStatusUser = bankTranStatusUser;
	}



	public long getBankTranId() {
		return bankTranId;
	}

	public void setBankTranId(long bankTranId) {
		this.bankTranId = bankTranId;
	}

	public int getBankCompId() {
		return bankCompId;
	}

	public void setBankCompId(int bankCompId) {
		this.bankCompId = bankCompId;
	}

	public int getBankId() {
		return bankId;
	}

	public void setBankId(int bankId) {
		this.bankId = bankId;
	}

	public int getBankTranType() {
		return bankTranType;
	}

	public void setBankTranType(int bankTranType) {
		this.bankTranType = bankTranType;
	}

	public String getBankTranRef() {
		return bankTranRef;
	}

	public void setBankTranRef(String bankTranRef) {
		this.bankTranRef = bankTranRef;
	}

	public String getBankTranRef1() {
		return bankTranRef1;
	}

	public void setBankTranRef1(String bankTranRef1) {
		this.bankTranRef1 = bankTranRef1;
	}

	public String getBankTranRef2() {
		return bankTranRef2;
	}

	public void setBankTranRef2(String bankTranRef2) {
		this.bankTranRef2 = bankTranRef2;
	}

	public String getBankTranData() {
		return bankTranData;
	}

	public void setBankTranData(String bankTranData) {
		this.bankTranData = bankTranData;
	}

	public String getBankTranStatus() {
		return bankTranStatus;
	}

	public void setBankTranStatus(String bankTranStatus) {
		this.bankTranStatus = bankTranStatus;
	}

	public String getBankTranStatusDate() {
		return bankTranStatusDate;
	}

	public void setBankTranStatusDate(String bankTranStatusDate) {
		this.bankTranStatusDate = bankTranStatusDate;
	}

	public String getBankTranStatusTime() {
		return bankTranStatusTime;
	}

	public void setBankTranStatusTime(String bankTranStatusTime) {
		this.bankTranStatusTime = bankTranStatusTime;
	}

	public double getBankTranAmt1() {
		return bankTranAmt1;
	}

	public void setBankTranAmt1(double bankTranAmt1) {
		this.bankTranAmt1 = bankTranAmt1;
	}

	public double getBankTranAmt2() {
		return bankTranAmt2;
	}

	public void setBankTranAmt2(double bankTranAmt2) {
		this.bankTranAmt2 = bankTranAmt2;
	}

	public double getBankTranPayNo() {
		return bankTranPayNo;
	}

	public void setBankTranPayNo(double bankTranPayNo) {
		this.bankTranPayNo = bankTranPayNo;
	}

	public String getBankTranDate() {
		return bankTranDate;
	}

	public void setBankTranDate(String bankTranDate) {
		this.bankTranDate = bankTranDate;
	}

	public String getBankTranCreatedDate() {
		return bankTranCreatedDate;
	}

	public void setBankTranCreatedDate(String bankTranCreatedDate) {
		this.bankTranCreatedDate = bankTranCreatedDate;
	}

	public String getBankTranCreatedTime() {
		return bankTranCreatedTime;
	}

	public void setBankTranCreatedTime(String bankTranCreatedTime) {
		this.bankTranCreatedTime = bankTranCreatedTime;
	}

	public boolean isBankTranCredit() {
		return bankTranCredit;
	}

	public void setBankTranCredit(boolean bankTranCredit) {
		this.bankTranCredit = bankTranCredit;
	}

	public String getBankTranActive() {
		return bankTranActive;
	}

	public void setBankTranActive(String bankTranActive) {
		this.bankTranActive = bankTranActive;
	}



	public long getBankTranTransferTranId() {
		return bankTranTransferTranId;
	}



	public void setBankTranTransferTranId(long bankTranTransferTranId) {
		this.bankTranTransferTranId = bankTranTransferTranId;
	}



	public String getBankTranTransferRef() {
		return bankTranTransferRef;
	}



	public void setBankTranTransferRef(String bankTranTransferRef) {
		this.bankTranTransferRef = bankTranTransferRef;
	}



	public String getBankTranTransferDesc() {
		return bankTranTransferDesc;
	}



	public void setBankTranTransferDesc(String bankTranTransferDesc) {
		this.bankTranTransferDesc = bankTranTransferDesc;
	}



	public int getBankTranStatusUser() {
		return bankTranStatusUser;
	}



	public void setBankTranStatusUser(int bankTranStatusUser) {
		this.bankTranStatusUser = bankTranStatusUser;
	}



	@Override
	public String toString() {
		return "CompanyBankTran [bankTranId=" + bankTranId + ", bankCompId=" + bankCompId + ", bankId=" + bankId
				+ ", bankTranType=" + bankTranType + ", bankTranRef=" + bankTranRef + ", bankTranRef1=" + bankTranRef1
				+ ", bankTranRef2=" + bankTranRef2 + ", bankTranData=" + bankTranData + ", bankTranStatus="
				+ bankTranStatus + ", bankTranStatusDate=" + bankTranStatusDate + ", bankTranStatusTime="
				+ bankTranStatusTime + ", bankTranAmt1=" + bankTranAmt1 + ", bankTranAmt2=" + bankTranAmt2
				+ ", bankTranPayNo=" + bankTranPayNo + ", bankTranDate=" + bankTranDate + ", bankTranCreatedDate="
				+ bankTranCreatedDate + ", bankTranCreatedTime=" + bankTranCreatedTime + ", bankTranCredit="
				+ bankTranCredit + ", bankTranActive=" + bankTranActive + ", bankTranTransferTranId="
				+ bankTranTransferTranId + ", bankTranTransferRef=" + bankTranTransferRef + ", bankTranTransferDesc="
				+ bankTranTransferDesc + ", bankTranStatusUser=" + bankTranStatusUser + "]";
	}
	
	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		try {
			json.put("banktranid", bankTranId);
			json.put("bankcompid", bankTranId);
			json.put("bankid", bankTranId);
			json.put("banktrantype", bankTranId);
			json.put("banktranref", bankTranId);
			json.put("banktranref1", bankTranId);
			json.put("banktranref2", bankTranId);
			json.put("banktrandata", bankTranId);
			json.put("banktranstatus", bankTranId);
			json.put("banktranstatusdate", bankTranId);
			json.put("banktranstatustime", bankTranId);
			json.put("banktranamt1", bankTranId);
			json.put("banktranamt2", bankTranId);
			json.put("banktranpayno", bankTranId);
			json.put("banktrandate", bankTranId);
			json.put("banktrancreateddate", bankTranId);
			json.put("banktrancreatedtime", bankTranId);
			json.put("banktrancredit", bankTranId);
			json.put("banktranactive", bankTranId);
			json.put("banktrantransfertranid", bankTranId);
			json.put("banktrantransferref", bankTranId);
			json.put("banktrantransferdesc", bankTranId);
			json.put("banktranstatususer", bankTranId);
		}catch (Exception e) {
			System.out.println("Error creating JSON: " + e.toString());
		}
		return json;
	}
		
}
