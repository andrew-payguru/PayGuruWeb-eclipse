package za.co.payguru.model;

import java.sql.Date;
import java.sql.Time;

import org.json.JSONObject;
import za.co.payguru.util.DateUtil;

public class CompanyUserSession {
	public static final long EXPIRE_DURATION = 43200000; //12 hours

    public static final int STATUS_ACTIVE = 1;
    public static final int STATUS_INACTIVE = 0;

    private int compid = 0;
    private long sessionid = 0;
    private int userid = 0;
    private String token = "";
    private Date createdate = DateUtil.getCurrentCCYYMMDDDate();
    private Time createtime = DateUtil.getCurrentHHMMSSTime();
    private int status = STATUS_ACTIVE;
    private Date statusdate = DateUtil.getCurrentCCYYMMDDDate();
    private Time statustime = DateUtil.getCurrentHHMMSSTime();
    private String ipaddress = "";
    private long expiretimemillis = 0;
    
    public CompanyUserSession() {
    	this.compid = 0;
        this.sessionid = 0;
        this.userid = 0;
        this.token = "";
        this.createdate = DateUtil.getCurrentCCYYMMDDDate();
        this.createtime = DateUtil.getCurrentHHMMSSTime();
        this.status = STATUS_ACTIVE;
        this.statusdate = DateUtil.getCurrentCCYYMMDDDate();
        this.statustime = DateUtil.getCurrentHHMMSSTime();
        this.ipaddress = "";
        this.expiretimemillis = 0;
    }

    // Getters and Setters
    
    
    public final int getCompid() {
		return compid;
	}

	public final void setCompid(int compid) {
		this.compid = compid;
	}
	
    public long getSessionid() { return sessionid; }
	public void setSessionid(long sessionid) { this.sessionid = sessionid; }

    public int getUserid() { return userid; }
    public void setUserid(int userid) { this.userid = userid; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public Date getCreatedate() { return createdate; }
    public void setCreatedate(Date createdate) { this.createdate = createdate; }

    public Time getCreatetime() { return createtime; }
    public void setCreatetime(Time createtime) { this.createtime = createtime; }

    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }

    public Date getStatusdate() { return statusdate; }
    public void setStatusdate(Date statusdate) { this.statusdate = statusdate; }

    public Time getStatustime() { return statustime; }
    public void setStatustime(Time statustime) { this.statustime = statustime; }

    public String getIpaddress() { return ipaddress; }
    public void setIpaddress(String ipaddress) { this.ipaddress = ipaddress; }

    
    public final long getExpiretimemillis() {
		return expiretimemillis;
	}

	public final void setExpiretimemillis(long expiretimemillis) {
		this.expiretimemillis = expiretimemillis;
	}

	@Override
    public String toString() {
        return "CompanyUserSession [compid = " + compid + ", sessionid=" + sessionid + ", userid=" + userid + ", token=" + token 
                + ", createdate=" + createdate + ", createtime=" + createtime 
                + ", status=" + status + ", statusdate=" + statusdate 
                + ", statustime=" + statustime + ", ipaddress=" + ipaddress + ", expiretimemillis = " + expiretimemillis + "]";
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        try {
        	json.put("compid", compid);
            json.put("sessionid", sessionid);
            json.put("userid", userid);
            json.put("token", token);
            json.put("createdate", createdate.toString());
            json.put("createtime", createtime.toString());
            json.put("status", status);
            json.put("statusdate", statusdate.toString());
            json.put("statustime", statustime.toString());
            json.put("ipaddress", ipaddress);
            json.put("expiretimemillis", expiretimemillis);
        } catch (Exception e) {
            System.out.println("Error creating JSON: " + e.toString());
        }
        return json;
    }
}
