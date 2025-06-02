package za.co.payguru.model;

import java.sql.Date;
import java.sql.Time;

import org.json.JSONObject;

import za.co.payguru.util.DateUtil;

public class ClientLoyalty {
    public static final String LOYALTY_ACTIVE = "1";
    public static final String LOYALTY_INACTIVE = "0";

    private int clientid = 0;
    private String loyaltyno = "";
    private Date loyaltyjoindate = DateUtil.getCurrentCCYYMMDDDate();
    private String loyaltystatus = LOYALTY_ACTIVE;
    private Date loyaltystatusdate = DateUtil.getCurrentCCYYMMDDDate();
    private Time loyaltystatustime = DateUtil.getCurrentHHMMSSTime();
    private String loyaltyref1 = "";
    private String loyaltyref2 = "";
    private String active = LOYALTY_ACTIVE;

    public ClientLoyalty() {
        this.clientid = 0;
        this.loyaltyno = "";
        this.loyaltyjoindate = DateUtil.getCurrentCCYYMMDDDate();
        this.loyaltystatus = LOYALTY_ACTIVE;
        this.loyaltystatusdate = DateUtil.getCurrentCCYYMMDDDate();
        this.loyaltystatustime = DateUtil.getCurrentHHMMSSTime();
        this.loyaltyref1 = "";
        this.loyaltyref2 = "";
        this.active = LOYALTY_ACTIVE;
    }

    public ClientLoyalty(int clientid, String loyaltyno, Date loyaltyjoindate, String loyaltystatus,
                         Date loyaltystatusdate, Time loyaltystatustime, String loyaltyref1, String loyaltyref2, String active) {
        this.clientid = clientid;
        this.loyaltyno = loyaltyno;
        this.loyaltyjoindate = loyaltyjoindate;
        this.loyaltystatus = loyaltystatus;
        this.loyaltystatusdate = loyaltystatusdate;
        this.loyaltystatustime = loyaltystatustime;
        this.loyaltyref1 = loyaltyref1;
        this.loyaltyref2 = loyaltyref2;
        this.active = active;
    }

    public int getClientid() {
        return clientid;
    }

    public void setClientid(int clientid) {
        this.clientid = clientid;
    }

    public String getLoyaltyno() {
        return loyaltyno;
    }

    public void setLoyaltyno(String loyaltyno) {
        this.loyaltyno = loyaltyno;
    }

    public Date getLoyaltyjoindate() {
        return loyaltyjoindate;
    }

    public void setLoyaltyjoindate(Date loyaltyjoindate) {
        this.loyaltyjoindate = loyaltyjoindate;
    }

    public String getLoyaltystatus() {
        return loyaltystatus;
    }

    public void setLoyaltystatus(String loyaltystatus) {
        this.loyaltystatus = loyaltystatus;
    }

    public Date getLoyaltystatusdate() {
        return loyaltystatusdate;
    }

    public void setLoyaltystatusdate(Date loyaltystatusdate) {
        this.loyaltystatusdate = loyaltystatusdate;
    }

    public Time getLoyaltystatustime() {
        return loyaltystatustime;
    }

    public void setLoyaltystatustime(Time loyaltystatustime) {
        this.loyaltystatustime = loyaltystatustime;
    }

    public String getLoyaltyref1() {
        return loyaltyref1;
    }

    public void setLoyaltyref1(String loyaltyref1) {
        this.loyaltyref1 = loyaltyref1;
    }

    public String getLoyaltyref2() {
        return loyaltyref2;
    }

    public void setLoyaltyref2(String loyaltyref2) {
        this.loyaltyref2 = loyaltyref2;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "ClientLoyalty [clientid=" + clientid + ", loyaltyno=" + loyaltyno + ", loyaltyjoindate="
                + loyaltyjoindate + ", loyaltystatus=" + loyaltystatus + ", loyaltystatusdate=" + loyaltystatusdate
                + ", loyaltystatustime=" + loyaltystatustime + ", loyaltyref1=" + loyaltyref1 + ", loyaltyref2="
                + loyaltyref2 + ", active=" + active + "]";
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        try {
            json.put("clientid", clientid);
            json.put("loyaltyno", loyaltyno);
            json.put("loyaltyjoindate", loyaltyjoindate);
            json.put("loyaltystatus", loyaltystatus);
            json.put("loyaltystatusdate", loyaltystatusdate);
            json.put("loyaltystatustime", loyaltystatustime);
            json.put("loyaltyref1", loyaltyref1);
            json.put("loyaltyref2", loyaltyref2);
            json.put("active", active);
        } catch (Exception e) {
            System.out.println("Error creating json CLIENTLOYALTY: " + e.toString());
        }
        return json;
    }
}
