package za.co.payguru.model;

import org.json.JSONObject;

public class ClientInvoiceExtDocItem {
	
	public static final String RETRIEVED = "2";
	public static final String ACTIVE = "1";
	public static final String INACTIVE = "0";
	
	public static final int DOCTYPE_RECEIPT = 1;

    private String invNo = "";
    private int compId = 0;
    private int extDocType = 0;
    private String extDocData = "";
    private String extDocRef1 = "";
    private String extDocRef2 = "";
    private String extDocRef3 = "";
    private String extDocStatus = ACTIVE;

    public ClientInvoiceExtDocItem() {
        super();
    }

    public ClientInvoiceExtDocItem(String invNo, int compId, int extDocType, String extDocData, String extDocRef1,
            String extDocRef2, String extDocRef3, String extDocStatus) {
        super();
        this.invNo = "";
        this.compId = 0;
        this.extDocType = 0;
        this.extDocData = "";
        this.extDocRef1 = "";
        this.extDocRef2 = "";
        this.extDocRef3 = "";
        this.extDocStatus = ACTIVE;
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

    public int getExtDocType() {
        return extDocType;
    }

    public void setExtDocType(int extDocType) {
        this.extDocType = extDocType;
    }

    public String getExtDocData() {
        return extDocData;
    }

    public void setExtDocData(String extDocData) {
        this.extDocData = extDocData;
    }

    public String getExtDocRef1() {
        return extDocRef1;
    }

    public void setExtDocRef1(String extDocRef1) {
        this.extDocRef1 = extDocRef1;
    }

    public String getExtDocRef2() {
        return extDocRef2;
    }

    public void setExtDocRef2(String extDocRef2) {
        this.extDocRef2 = extDocRef2;
    }

    public String getExtDocRef3() {
        return extDocRef3;
    }

    public void setExtDocRef3(String extDocRef3) {
        this.extDocRef3 = extDocRef3;
    }

    public String getExtDocStatus() {
        return extDocStatus;
    }

    public void setExtDocStatus(String extDocStatus) {
        this.extDocStatus = extDocStatus;
    }

    @Override
    public String toString() {
        return "ClientInvoiceExtDocItems [invno=" + invNo + ", compid=" + compId + ", extdoctype=" + extDocType
                + ", extdocdata=" + extDocData + ", extdocref1=" + extDocRef1 + ", extdocref2=" + extDocRef2
                + ", extdocref3=" + extDocRef3 + ", extdocstatus=" + extDocStatus + "]";
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        try {
            json.put("invno", invNo);
            json.put("compid", compId);
            json.put("extdoctype", extDocType);
            json.put("extdocdata", extDocData);
            json.put("extdocref1", extDocRef1);
            json.put("extdocref2", extDocRef2);
            json.put("extdocref3", extDocRef3);
            json.put("extdocstatus", extDocStatus);
        } catch (Exception e) {
            System.out.println("Error creating json: " + e.toString());
        }
        return json;
    }
}
