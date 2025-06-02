package za.co.payguru.model;

import java.sql.Date;
import java.sql.Time;
import org.json.JSONObject;

import za.co.payguru.util.DateUtil;

public class ClientLoyaltyHistory {
		public static final int LOYALTY_TRAN_TYPE_DEBIT = 1;
		public static final int LOYALTY_TRAN_TYPE_CREDIT = 2;
	
    private int tranno = 0;
    private int clientid = 0;
    private int compid = 0;
    private int loyaltyid = 0;
    private double tranamt = 0;
    private double tranpoints = 0;
    private Date trandate = DateUtil.getCurrentCCYYMMDDDate();
    private Time trantime = DateUtil.getCurrentHHMMSSTime();
    private int trantype = LOYALTY_TRAN_TYPE_DEBIT;

    public ClientLoyaltyHistory() {
    	this.tranno = 0;
    	this.clientid = 0;
    	this.compid = 0;
    	this.loyaltyid = 0;
    	this.tranamt = 0;
    	this.tranpoints = 0;
    	this.trandate = DateUtil.getCurrentCCYYMMDDDate();
    	this.trantime = DateUtil.getCurrentHHMMSSTime();
    	this.trantype = LOYALTY_TRAN_TYPE_DEBIT;
    }

    public ClientLoyaltyHistory(int tranno, int clientid, int compid, int loyaltyid, double tranamt, double tranpoints, Date trandate, Time trantime, int trantype) {
        this.tranno = tranno;
        this.clientid = clientid;
        this.compid = compid;
        this.loyaltyid = loyaltyid;
        this.tranamt = tranamt;
        this.tranpoints = tranpoints;
        this.trandate = trandate;
        this.trantime = trantime;
        this.trantype = trantype;
    }

    public int getTranno() {
        return tranno;
    }

    public void setTranno(int tranno) {
        this.tranno = tranno;
    }

    public int getClientid() {
        return clientid;
    }

    public void setClientid(int clientid) {
        this.clientid = clientid;
    }

    public int getCompid() {
        return compid;
    }

    public void setCompid(int compid) {
        this.compid = compid;
    }

    public int getLoyaltyid() {
        return loyaltyid;
    }

    public void setLoyaltyid(int loyaltyid) {
        this.loyaltyid = loyaltyid;
    }

    public double getTranamt() {
        return tranamt;
    }

    public void setTranamt(double tranamt) {
        this.tranamt = tranamt;
    }

    public double getTranpoints() {
        return tranpoints;
    }

    public void setTranpoints(double tranpoints) {
        this.tranpoints = tranpoints;
    }

    public Date getTrandate() {
        return trandate;
    }

    public void setTrandate(Date trandate) {
        this.trandate = trandate;
    }

    public Time getTrantime() {
        return trantime;
    }

    public void setTrantime(Time trantime) {
        this.trantime = trantime;
    }

    public int getTrantype() {
        return trantype;
    }

    public void setTrantype(int trantype) {
        this.trantype = trantype;
    }

    @Override
    public String toString() {
        return "ClientLoyaltyHistory [tranno=" + tranno + ", clientid=" + clientid + ", compid=" + compid
                + ", loyaltyid=" + loyaltyid + ", tranamt=" + tranamt + ", tranpoints=" + tranpoints + ", trandate="
                + trandate + ", trantime=" + trantime + ", trantype=" + trantype + "]";
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        try {
            json.put("tranno", tranno);
            json.put("clientid", clientid);
            json.put("compid", compid);
            json.put("loyaltyid", loyaltyid);
            json.put("tranamt", tranamt);
            json.put("tranpoints", tranpoints);
            json.put("trandate", trandate);
            json.put("trantime", trantime);
            json.put("trantype", trantype);
        } catch (Exception e) {
            System.out.println("Error creating JSON for ClientLoyaltyHistory: " + e.toString());
        }
        return json;
    }
}
