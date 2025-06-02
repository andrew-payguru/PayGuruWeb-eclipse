package za.co.payguru.model;

import java.sql.Date;
import java.sql.Time;

import org.json.JSONObject;

import za.co.payguru.util.DateUtil;

public class CompanyClientLoyalty {
    public static final String LOYALTY_ACTIVE = "1";
    public static final String LOYALTY_INACTIVE = "0";

    private int compid = 0;
    private int comployaltyid = 0;
    private int clientid = 0;
    private double loyaltypointsbalance = 0.0;
    private double loyaltypointsredeemed = 0.0;
    private double loyaltypointsexpired = 0.0;
    private String loyaltystatus = LOYALTY_ACTIVE;
    private Date loyaltystatusdate = DateUtil.getCurrentCCYYMMDDDate();
    private Time loyaltystatustime = DateUtil.getCurrentHHMMSSTime();
    private int loyaltytier = 0;
    private String compcustomloyaltyno = "";
    private String active = LOYALTY_ACTIVE;

    public CompanyClientLoyalty() {
        this.compid = 0;
        this.comployaltyid = 0;
        this.clientid = 0;
        this.loyaltypointsbalance = 0.0;
        this.loyaltypointsredeemed = 0.0;
        this.loyaltypointsexpired = 0.0;
        this.loyaltystatus = LOYALTY_ACTIVE;
        this.loyaltystatusdate = DateUtil.getCurrentCCYYMMDDDate();
        this.loyaltystatustime = DateUtil.getCurrentHHMMSSTime();
        this.loyaltytier = 0;
        this.compcustomloyaltyno = "";
        this.active = LOYALTY_ACTIVE;
    }

    public CompanyClientLoyalty(int compid, int comployaltyid, int clientid, double loyaltypointsbalance,
                                double loyaltypointsredeemed, double loyaltypointsexpired, String loyaltystatus,
                                Date loyaltystatusdate, Time loyaltystatustime, int loyaltytier, 
                                String compcustomloyaltyno, String active) {
        this.compid = compid;
        this.comployaltyid = comployaltyid;
        this.clientid = clientid;
        this.loyaltypointsbalance = loyaltypointsbalance;
        this.loyaltypointsredeemed = loyaltypointsredeemed;
        this.loyaltypointsexpired = loyaltypointsexpired;
        this.loyaltystatus = loyaltystatus;
        this.loyaltystatusdate = loyaltystatusdate;
        this.loyaltystatustime = loyaltystatustime;
        this.loyaltytier = loyaltytier;
        this.compcustomloyaltyno = compcustomloyaltyno;
        this.active = active;
    }

    public int getCompid() {
        return compid;
    }

    public void setCompid(int compid) {
        this.compid = compid;
    }

    public int getComployaltyid() {
        return comployaltyid;
    }

    public void setComployaltyid(int comployaltyid) {
        this.comployaltyid = comployaltyid;
    }

    public int getClientid() {
        return clientid;
    }

    public void setClientid(int clientid) {
        this.clientid = clientid;
    }

    public double getLoyaltypointsbalance() {
        return loyaltypointsbalance;
    }

    public void setLoyaltypointsbalance(double loyaltypointsbalance) {
        this.loyaltypointsbalance = loyaltypointsbalance;
    }

    public double getLoyaltypointsredeemed() {
        return loyaltypointsredeemed;
    }

    public void setLoyaltypointsredeemed(double loyaltypointsredeemed) {
        this.loyaltypointsredeemed = loyaltypointsredeemed;
    }

    public double getLoyaltypointsexpired() {
        return loyaltypointsexpired;
    }

    public void setLoyaltypointsexpired(double loyaltypointsexpired) {
        this.loyaltypointsexpired = loyaltypointsexpired;
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

    public int getLoyaltytier() {
        return loyaltytier;
    }

    public void setLoyaltytier(int loyaltytier) {
        this.loyaltytier = loyaltytier;
    }

    public String getCompcustomloyaltyno() {
        return compcustomloyaltyno;
    }

    public void setCompcustomloyaltyno(String compcustomloyaltyno) {
        this.compcustomloyaltyno = compcustomloyaltyno;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "CompanyClientLoyalty [compid=" + compid + ", comployaltyid=" + comployaltyid + ", clientid=" + clientid
                + ", loyaltypointsbalance=" + loyaltypointsbalance + ", loyaltypointsredeemed=" + loyaltypointsredeemed
                + ", loyaltypointsexpired=" + loyaltypointsexpired + ", loyaltystatus=" + loyaltystatus
                + ", loyaltystatusdate=" + loyaltystatusdate + ", loyaltystatustime=" + loyaltystatustime
                + ", loyaltytier=" + loyaltytier + ", compcustomloyaltyno=" + compcustomloyaltyno + ", active=" + active
                + "]";
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        try {
            json.put("compid", compid);
            json.put("comployaltyid", comployaltyid);
            json.put("clientid", clientid);
            json.put("loyaltypointsbalance", loyaltypointsbalance);
            json.put("loyaltypointsredeemed", loyaltypointsredeemed);
            json.put("loyaltypointsexpired", loyaltypointsexpired);
            json.put("loyaltystatus", loyaltystatus);
            json.put("loyaltystatusdate", loyaltystatusdate);
            json.put("loyaltystatustime", loyaltystatustime);
            json.put("loyaltytier", loyaltytier);
            json.put("compcustomloyaltyno", compcustomloyaltyno);
            json.put("active", active);
        } catch (Exception e) {
            System.out.println("Error creating json COMPANYCLIENTLOYALTY: " + e.toString());
        }
        return json;
    }
}
