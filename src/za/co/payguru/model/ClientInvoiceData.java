package za.co.payguru.model;

public class ClientInvoiceData {
	private String invNo;
	private int compId;
	private String invData;
	private String invDataType;
	private String invDataRef1;
	private String invDataRef2;
	private String invDataRef3;
	private String invDataRef4;
	private String invDataRef5;
	private String invDataRef6;
	private String invDataRef7;
	private String invDataRef8;
	private double invDataAmt1;
	private double invDataAmt2;
	private double invDataAmt3;
	private double invDataAmt4;
	private double invDataAmt5;
	private String invLink = new String();
	private int clientId = 0;
	public ClientInvoiceData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ClientInvoiceData(String invNo, int compId, String invData, String invDataType, String invDataRef1,
			String invDataRef2, String invDataRef3, String invDataRef4, String invDataRef5, String invDataRef6,
			String invDataRef7, String invDataRef8, double invDataAmt1, double invDataAmt2, double invDataAmt3,
			double invDataAmt4, double invDataAmt5, String invLink, int clientId) {
		super();
		this.invNo = invNo;
		this.compId = compId;
		this.invData = invData;
		this.invDataType = invDataType;
		this.invDataRef1 = invDataRef1;
		this.invDataRef2 = invDataRef2;
		this.invDataRef3 = invDataRef3;
		this.invDataRef4 = invDataRef4;
		this.invDataRef5 = invDataRef5;
		this.invDataRef6 = invDataRef6;
		this.invDataRef7 = invDataRef7;
		this.invDataRef8 = invDataRef8;
		this.invDataAmt1 = invDataAmt1;
		this.invDataAmt2 = invDataAmt2;
		this.invDataAmt3 = invDataAmt3;
		this.invDataAmt4 = invDataAmt4;
		this.invDataAmt5 = invDataAmt5;
		this.invLink = invLink;
		this.clientId = clientId;
	}
	public String getInvNo() {
		return invNo;
	}
	public void setInvNo(String invNo) {
		this.invNo = invNo;
	}
	public int getCompId() {
		return compId;
	}
	public void setCompId(int compId) {
		this.compId = compId;
	}
	public String getInvData() {
		return invData;
	}
	public void setInvData(String invData) {
		this.invData = invData;
	}
	public String getInvDataType() {
		return invDataType;
	}
	public void setInvDataType(String invDataType) {
		this.invDataType = invDataType;
	}
	public String getInvDataRef1() {
		return invDataRef1;
	}
	public void setInvDataRef1(String invDataRef1) {
		this.invDataRef1 = invDataRef1;
	}
	public String getInvDataRef2() {
		return invDataRef2;
	}
	public void setInvDataRef2(String invDataRef2) {
		this.invDataRef2 = invDataRef2;
	}
	public String getInvDataRef3() {
		return invDataRef3;
	}
	public void setInvDataRef3(String invDataRef3) {
		this.invDataRef3 = invDataRef3;
	}
	public String getInvDataRef4() {
		return invDataRef4;
	}
	public void setInvDataRef4(String invDataRef4) {
		this.invDataRef4 = invDataRef4;
	}
	public String getInvDataRef5() {
		return invDataRef5;
	}
	public void setInvDataRef5(String invDataRef5) {
		this.invDataRef5 = invDataRef5;
	}
	public String getInvDataRef6() {
		return invDataRef6;
	}
	public void setInvDataRef6(String invDataRef6) {
		this.invDataRef6 = invDataRef6;
	}
	public String getInvDataRef7() {
		return invDataRef7;
	}
	public void setInvDataRef7(String invDataRef7) {
		this.invDataRef7 = invDataRef7;
	}
	public String getInvDataRef8() {
		return invDataRef8;
	}
	public void setInvDataRef8(String invDataRef8) {
		this.invDataRef8 = invDataRef8;
	}
	public double getInvDataAmt1() {
		return invDataAmt1;
	}
	public void setInvDataAmt1(double invDataAmt1) {
		this.invDataAmt1 = invDataAmt1;
	}
	public double getInvDataAmt2() {
		return invDataAmt2;
	}
	public void setInvDataAmt2(double invDataAmt2) {
		this.invDataAmt2 = invDataAmt2;
	}
	public double getInvDataAmt3() {
		return invDataAmt3;
	}
	public void setInvDataAmt3(double invDataAmt3) {
		this.invDataAmt3 = invDataAmt3;
	}
	public double getInvDataAmt4() {
		return invDataAmt4;
	}
	public void setInvDataAmt4(double invDataAmt4) {
		this.invDataAmt4 = invDataAmt4;
	}
	public double getInvDataAmt5() {
		return invDataAmt5;
	}
	public void setInvDataAmt5(double invDataAmt5) {
		this.invDataAmt5 = invDataAmt5;
	}
	public String getInvLink() {
		return invLink;
	}
	public void setInvLink(String invLink) {
		this.invLink = invLink;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	@Override
	public String toString() {
		return "ClientInvoiceData [invNo=" + invNo + ", compId=" + compId + ", invData=" + invData + ", invDataType="
				+ invDataType + ", invDataRef1=" + invDataRef1 + ", invDataRef2=" + invDataRef2 + ", invDataRef3=" + invDataRef3
				+ ", invDataRef4=" + invDataRef4 + ", invDataRef5=" + invDataRef5 + ", invDataRef6=" + invDataRef6
				+ ", invDataRef7=" + invDataRef7 + ", invDataRef8=" + invDataRef8 + ", invDataAmt1=" + invDataAmt1
				+ ", invDataAmt2=" + invDataAmt2 + ", invDataAmt3=" + invDataAmt3 + ", invDataAmt4=" + invDataAmt4
				+ ", invDataAmt5=" + invDataAmt5 + ", invLink=" + invLink + ", clientId=" + clientId + "]";
	}
	
	public String toJsonString() {
		return "{\"invno\" : \"" + invNo + "\", \"compid\" : " + compId + ", \"invdata\" : \"" + invData + "\", \"invdatatype\" : \""
				+ invDataType + "\", \"invdataref1\" : \"" + invDataRef1 + "\", \"invdataref2\" : \"" + invDataRef2 + "\", \"invdataref3\" : \""
				+ invDataRef3 + "\", \"invdataref4\" : \"" + invDataRef4 + "\", \"invdataref5\" : \"" + invDataRef5 + "\", \"invdataref6\" : \"" 
				+ invDataRef6 + "\", \"invdataref7\" : \"" + invDataRef7 + "\", \"invdataref8\" : \"" + invDataRef8 + "\", \"invdataamt1\" : "
				+ invDataAmt1 + ", \"invdataamt2\" : " + invDataAmt2 + ", \"invdataamt3\" : " + invDataAmt3 + ", \"invdataamt4\" : " 
				+ invDataAmt4 + ", \"invdataamt5\" : " + invDataAmt5 + ", \"invlink\" : \"" + invLink + "\", \"clientid\" : " + clientId + "}";
	}
	
	public String toJsonStringCamelCase() {
		return "{\"invNo\" : \"" + invNo + "\", \"compId\" : " + compId + ", \"invData\" : \"" + invData + "\", \"invDataType\" : \""
				+ invDataType + "\", \"invDataRef1\" : \"" + invDataRef1 + "\", \"invDataRef2\" : \"" + invDataRef2 + "\", \"invDataRef3\" : \""
				+ invDataRef3 + "\", \"invDataRef4\" : \"" + invDataRef4 + "\", \"invDataRef5\" : \"" + invDataRef5 + "\", \"invDataRef6\" : \"" 
				+ invDataRef6 + "\", \"invDataRef7\" : \"" + invDataRef7 + "\", \"invDataRef8\" : \"" + invDataRef8 + "\", \"invDataAmt1\" : "
				+ invDataAmt1 + ", \"invDataAmt2\" : " + invDataAmt2 + ", \"invDataAmt3\" : " + invDataAmt3 + ", \"invDataAmt4\" : " 
				+ invDataAmt4 + ", \"invDataAmt5\" : " + invDataAmt5 + ", \"invLink\" : \"" + invLink + "\", \"clientId\" : " + clientId + "}";
	}
	
}
