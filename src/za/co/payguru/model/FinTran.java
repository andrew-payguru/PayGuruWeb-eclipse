package za.co.payguru.model;

import java.sql.Date;
import java.sql.Time;
import org.json.JSONObject;

import za.co.payguru.util.DateUtil;

public class FinTran {
	public static final String INACTIVE = "0";
	public static final String ACTIVE = "1";
	
	public static final String INIT = "1";
    public static final String PENDING = "2";
    public static final String RESPONDED = "3";
    public static final String DONE = "4";
    public static final String ERROR = "5";
    
    public static final int FIN_TYPE_EFT = 1;
    public static final int FIN_TYPE_MPESA = 2;
    public static final int FIN_TYPE_CARD = 3;
    public static final int FIN_TYPE_MKESH = 4;
    public static final int FIN_TYPE_EMOLA = 5;
    
    private String fintranno = "";
    private Date fintrancreateddate = DateUtil.getCurrentCCYYMMDDDate();
    private Time fintrancreatedtime = DateUtil.getCurrentHHMMSSTime();
    private int compid = 0;
    private String fintranref1 = "";
    private String fintranref2 = "";
    private String fintranref3 = "";
    private String fintranextref1 = "";
    private String fintranexttranno = "";
    private double fintranamt = 0.0;
    private double fintranfee = 0.0;
    private String fintranstatus = "";
    private Time fintranstatustime = DateUtil.getCurrentHHMMSSTime();
    private Date fintranstatusdate = DateUtil.getCurrentCCYYMMDDDate();
    private String fintranstatusdesc = "";
    private int fintrantype = 0;
    private Time fintranresponsetime = DateUtil.getCurrentHHMMSSTime();
    private Date fintranresponsedate = DateUtil.getCurrentCCYYMMDDDate();
    private String fintranactiontranno = "";
    private Time fintranactiontrantime = DateUtil.getCurrentHHMMSSTime();
    private Date fintranactiontrandate = DateUtil.getCurrentCCYYMMDDDate();
    private String fintranactive = "";

    public FinTran() {
    	this.fintranno = "";
    	this.fintrancreateddate = DateUtil.getCurrentCCYYMMDDDate();
    	this.fintrancreatedtime = DateUtil.getCurrentHHMMSSTime();
    	this.compid = 0;
    	this.fintranref1 = "";
    	this.fintranref2 = "";
    	this.fintranref3 = "";
    	this.fintranextref1 = "";
    	this.fintranexttranno = "";
    	this.fintranamt = 0.0;
    	this.fintranfee = 0.0;
    	this.fintranstatus = "";
    	this.fintranstatustime = DateUtil.getCurrentHHMMSSTime();
    	this.fintranstatusdate = DateUtil.getCurrentCCYYMMDDDate();
    	this.fintranstatusdesc = "";
    	this.fintrantype = 0;
    	this.fintranresponsetime = DateUtil.getCurrentHHMMSSTime();
    	this.fintranresponsedate = DateUtil.getCurrentCCYYMMDDDate();
    	this.fintranactiontranno = "";
    	this.fintranactiontrantime = DateUtil.getCurrentHHMMSSTime();
    	this.fintranactiontrandate = DateUtil.getCurrentCCYYMMDDDate();
    	this.fintranactive = "";
    }



    // Getters and Setters
    public String getFintranno() { return fintranno; }
    public void setFintranno(String fintranno) { this.fintranno = fintranno; }
    public Date getFintrancreateddate() { return fintrancreateddate; }
    public void setFintrancreateddate(Date fintrancreateddate) { this.fintrancreateddate = fintrancreateddate; }
    public Time getFintrancreatedtime() { return fintrancreatedtime; }
    public void setFintrancreatedtime(Time fintrancreatedtime) { this.fintrancreatedtime = fintrancreatedtime; }
    public int getCompid() { return compid; }
    public void setCompid(int compid) { this.compid = compid; }
    public String getFintranref1() { return fintranref1; }
    public void setFintranref1(String fintranref1) { this.fintranref1 = fintranref1; }
    public String getFintranref2() { return fintranref2; }
    public void setFintranref2(String fintranref2) { this.fintranref2 = fintranref2; }
    public String getFintranref3() { return fintranref3; }
    public void setFintranref3(String fintranref3) { this.fintranref3 = fintranref3; }
    public String getFintranextref1() { return fintranextref1; }
    public void setFintranextref1(String fintranextref1) { this.fintranextref1 = fintranextref1; }
    public String getFintranexttranno() { return fintranexttranno; }
    public void setFintranexttranno(String fintranexttranno) { this.fintranexttranno = fintranexttranno; }
    public double getFintranamt() { return fintranamt; }
    public void setFintranamt(double fintranamt) { this.fintranamt = fintranamt; }
    public double getFintranfee() { return fintranfee; }
    public void setFintranfee(double fintranfee) { this.fintranfee = fintranfee; }
    public String getFintranstatus() { return fintranstatus; }
    public void setFintranstatus(String fintranstatus) { this.fintranstatus = fintranstatus; }
    public Time getFintranstatustime() { return fintranstatustime; }
    public void setFintranstatustime(Time fintranstatustime) { this.fintranstatustime = fintranstatustime; }
    public Date getFintranstatusdate() { return fintranstatusdate; }
    public void setFintranstatusdate(Date fintranstatusdate) { this.fintranstatusdate = fintranstatusdate; }
    public String getFintranstatusdesc() { return fintranstatusdesc; }
    public void setFintranstatusdesc(String fintranstatusdesc) { this.fintranstatusdesc = fintranstatusdesc; }
    public int getFintrantype() { return fintrantype; }
    public void setFintrantype(int fintrantype) { this.fintrantype = fintrantype; }
    public Time getFintranresponsetime() { return fintranresponsetime; }
    public void setFintranresponsetime(Time fintranresponsetime) { this.fintranresponsetime = fintranresponsetime; }
    public Date getFintranresponsedate() { return fintranresponsedate; }
    public void setFintranresponsedate(Date fintranresponsedate) { this.fintranresponsedate = fintranresponsedate; }
    public String getFintranactiontranno() { return fintranactiontranno; }
    public void setFintranactiontranno(String fintranactiontranno) { this.fintranactiontranno = fintranactiontranno; }
    public Time getFintranactiontrantime() { return fintranactiontrantime; }
    public void setFintranactiontrantime(Time fintranactiontrantime) { this.fintranactiontrantime = fintranactiontrantime; }
    public Date getFintranactiontrandate() { return fintranactiontrandate; }
    public void setFintranactiontrandate(Date fintranactiontrandate) { this.fintranactiontrandate = fintranactiontrandate; }
    public String getFintranactive() { return fintranactive; }
    public void setFintranactive(String fintranactive) { this.fintranactive = fintranactive; }

    @Override
    public String toString() {
        return "FinTrans [fintranno=" + fintranno + ", fintrancreateddate=" + fintrancreateddate + ", fintrancreatedtime=" 
            + fintrancreatedtime + ", compid=" + compid + ", fintranref1=" + fintranref1 + ", fintranref2=" + fintranref2 
            + ", fintranref3=" + fintranref3 + ", fintranextref1=" + fintranextref1 + ", fintranexttranno=" + fintranexttranno 
            + ", fintranamt=" + fintranamt + ", fintranfee=" + fintranfee + ", fintranstatus=" + fintranstatus 
            + ", fintranstatustime=" + fintranstatustime + ", fintranstatusdate=" + fintranstatusdate 
            + ", fintranstatusdesc=" + fintranstatusdesc + ", fintrantype=" + fintrantype + ", fintranresponsetime=" 
            + fintranresponsetime + ", fintranresponsedate=" + fintranresponsedate + ", fintranactiontranno=" 
            + fintranactiontranno + ", fintranactiontrantime=" + fintranactiontrantime + ", fintranactiontrandate=" 
            + fintranactiontrandate + ", fintranactive=" + fintranactive + "]";
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        try {
        json.put("fintranno", fintranno);
        json.put("fintrancreateddate", fintrancreateddate.toString());
        json.put("fintrancreatedtime", fintrancreatedtime.toString());
        json.put("compid", compid);
        json.put("fintranref1", fintranref1);
        json.put("fintranref2", fintranref2);
        json.put("fintranref3", fintranref3);
        json.put("fintranextref1", fintranextref1);
        json.put("fintranexttranno", fintranexttranno);
        json.put("fintranamt", fintranamt);
        json.put("fintranfee", fintranfee);
        json.put("fintranstatus", fintranstatus);
        json.put("fintranstatustime", fintranstatustime.toString());
        json.put("fintranstatusdate", fintranstatusdate.toString());
        json.put("fintranstatusdesc", fintranstatusdesc);
        json.put("fintrantype", fintrantype);
        json.put("fintranresponsetime", fintranresponsetime.toString());
        json.put("fintranresponsedate", fintranresponsedate.toString());
        json.put("fintranactiontranno", fintranactiontranno);
        json.put("fintranactiontrantime", fintranactiontrantime.toString());
        json.put("fintranactiontrandate", fintranactiontrandate.toString());
        json.put("fintranactive", fintranactive);
        }catch (Exception e) {
        	System.out.println("Error creating json: " + e.toString());
		}
        return json;
    }
}
