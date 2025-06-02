package za.co.payguru.model;

import java.sql.Date;
import java.sql.Time;

import org.json.JSONObject;

import za.co.payguru.util.DateUtil;

public class CompanyLoyaltyReward {
	
		public static final String ACTIVE = "1";
		public static final String INACTIVE = "0";
	
    private long rewardid;
    private int compid;
    private int comployaltyid;
    private String rewarddesc;
    private double rewardpointval;
    private String rewardstatus;
    private Date rewardstatusdate;
    private Time rewardstatustime;
    private String rewardactive;

    public CompanyLoyaltyReward() {
        this.rewardid = 0;
        this.compid = 0;
        this.comployaltyid = 0;
        this.rewarddesc = "";
        this.rewardpointval = 0.0;
        this.rewardstatus = ACTIVE;
        this.rewardstatusdate = DateUtil.getCurrentCCYYMMDDDate();
        this.rewardstatustime = DateUtil.getCurrentHHMMSSTime();
        this.rewardactive = ACTIVE;
    }

    public CompanyLoyaltyReward(long rewardid, int compid, int comployaltyid, String rewarddesc, double rewardpointval,
                                  String rewardstatus, Date rewardstatusdate, Time rewardstatustime, String rewardactive) {
        super();
        this.rewardid = rewardid;
        this.compid = compid;
        this.comployaltyid = comployaltyid;
        this.rewarddesc = rewarddesc;
        this.rewardpointval = rewardpointval;
        this.rewardstatus = rewardstatus;
        this.rewardstatusdate = rewardstatusdate;
        this.rewardstatustime = rewardstatustime;
        this.rewardactive = rewardactive;
    }

    public long getRewardid() {
        return rewardid;
    }

    public void setRewardid(long rewardid) {
        this.rewardid = rewardid;
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

    public String getRewarddesc() {
        return rewarddesc;
    }

    public void setRewarddesc(String rewarddesc) {
        this.rewarddesc = rewarddesc;
    }

    public double getRewardpointval() {
        return rewardpointval;
    }

    public void setRewardpointval(double rewardpointval) {
        this.rewardpointval = rewardpointval;
    }

    

    public String getRewardstatus() {
        return rewardstatus;
    }

    public void setRewardstatus(String rewardstatus) {
        this.rewardstatus = rewardstatus;
    }

    public Date getRewardstatusdate() {
        return rewardstatusdate;
    }

    public void setRewardstatusdate(Date rewardstatusdate) {
        this.rewardstatusdate = rewardstatusdate;
    }

    public Time getRewardstatustime() {
        return rewardstatustime;
    }

    public void setRewardstatustime(Time rewardstatustime) {
        this.rewardstatustime = rewardstatustime;
    }

    public String getRewardactive() {
        return rewardactive;
    }

    public void setRewardactive(String rewardactive) {
        this.rewardactive = rewardactive;
    }

    @Override
    public String toString() {
        return "CompanyLoyaltyRewards [rewardid=" + rewardid + ", compid=" + compid + ", comployaltyid=" + comployaltyid
                + ", rewarddesc=" + rewarddesc + ", rewardpointval=" + rewardpointval + ", rewardstatus=" + rewardstatus + ", rewardstatusdate=" + rewardstatusdate + ", rewardstatustime=" + rewardstatustime
                + ", rewardactive=" + rewardactive + "]";
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        try {
            json.put("rewardid", this.rewardid);
            json.put("compid", this.compid);
            json.put("comployaltyid", this.comployaltyid);
            json.put("rewarddesc", this.rewarddesc);
            json.put("rewardpointval", this.rewardpointval);
            json.put("rewardstatus", this.rewardstatus);
            json.put("rewardstatusdate", this.rewardstatusdate.toString());
            json.put("rewardstatustime", this.rewardstatustime.toString());
            json.put("rewardactive", this.rewardactive);
        } catch (Exception e) {
            // Handle exception
        }
        return json;
    }
}
