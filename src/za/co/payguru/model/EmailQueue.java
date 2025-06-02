package za.co.payguru.model;

import java.sql.Date;
import java.sql.Time;

import org.json.JSONObject;

import za.co.payguru.util.DateUtil;

public class EmailQueue {
		public static final String ACTIVE = "1";
		public static final String INACTIVE = "0";
		
		
		public static final String STATUS_ACTIVE  = "1";
		public static final String STATUS_PENDING = "2";
		public static final String STATUS_DONE    = "3";
		public static final String STATUS_ERROR   = "4";
	
    private long emailid = 0;
    private String emailaddress = "";
    private Date emaildate = DateUtil.getCurrentCCYYMMDDDate();
    private Time emailtime = DateUtil.getCurrentHHMMSSTime();
    private String emailmessage = "";
    private String emailstatus = STATUS_ACTIVE;
    private Date emailstatusdate = DateUtil.getCurrentCCYYMMDDDate();
    private Time emailstatustime = DateUtil.getCurrentHHMMSSTime();
    private String emailsendref = "";
    private String emailpayref = "";
    private String emailactive = ACTIVE;;

    public EmailQueue() {
        this.emailid = 0;
        this.emailaddress = "";
        this.emaildate = DateUtil.getCurrentCCYYMMDDDate();
        this.emailtime = DateUtil.getCurrentHHMMSSTime();
        this.emailmessage = "";
        this.emailstatus = STATUS_ACTIVE;
        this.emailstatusdate = DateUtil.getCurrentCCYYMMDDDate();
        this.emailstatustime = DateUtil.getCurrentHHMMSSTime();
        this.emailsendref = "";
        this.emailpayref = "";
        this.emailactive = ACTIVE;  
    }

    public EmailQueue(long emailid, String emailaddress, Date emaildate, Time emailtime, String emailmessage, String emailstatus, Date emailstatusdate, Time emailstatustime, String emailsendref, String emailpayref, String emailactive) {
        this.emailid = emailid;
        this.emailaddress = emailaddress;
        this.emaildate = emaildate;
        this.emailtime = emailtime;
        this.emailmessage = emailmessage;
        this.emailstatus = emailstatus;
        this.emailstatusdate = emailstatusdate;
        this.emailstatustime = emailstatustime;
        this.emailsendref = emailsendref;
        this.emailpayref = emailpayref;
        this.emailactive = emailactive;
    }


    public long getEmailid() {
        return emailid;
    }

    public void setEmailid(long emailid) {
        this.emailid = emailid;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    public Date getEmaildate() {
        return emaildate;
    }

    public void setEmaildate(Date emaildate) {
        this.emaildate = emaildate;
    }

    public Time getEmailtime() {
        return emailtime;
    }

    public void setEmailtime(Time emailtime) {
        this.emailtime = emailtime;
    }

    public String getEmailmessage() {
        return emailmessage;
    }

    public void setEmailmessage(String emailmessage) {
        this.emailmessage = emailmessage;
    }

    public String getEmailstatus() {
        return emailstatus;
    }

    public void setEmailstatus(String emailstatus) {
        this.emailstatus = emailstatus;
    }

    public Date getEmailstatusdate() {
        return emailstatusdate;
    }

    public void setEmailstatusdate(Date emailstatusdate) {
        this.emailstatusdate = emailstatusdate;
    }

    public Time getEmailstatustime() {
        return emailstatustime;
    }

    public void setEmailstatustime(Time emailstatustime) {
        this.emailstatustime = emailstatustime;
    }

    public String getEmailsendref() {
        return emailsendref;
    }

    public void setEmailsendref(String emailsendref) {
        this.emailsendref = emailsendref;
    }

    public String getEmailpayref() {
        return emailpayref;
    }

    public void setEmailpayref(String emailpayref) {
        this.emailpayref = emailpayref;
    }

    public String getEmailactive() {
        return emailactive;
    }

    public void setEmailactive(String emailactive) {
        this.emailactive = emailactive;
    }

    @Override
    public String toString() {
        return "EmailQueue [emailid=" + emailid + ", emailaddress=" + emailaddress + ", emaildate=" + emaildate + ", emailtime=" + emailtime + ", emailmessage=" + emailmessage + ", emailstatus=" + emailstatus + ", emailstatusdate=" + emailstatusdate + ", emailstatustime=" + emailstatustime + ", emailsendref=" + emailsendref + ", emailpayref=" + emailpayref + ", emailactive=" + emailactive + "]";
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        try {
            json.put("emailid", emailid);
            json.put("emailaddress", emailaddress);
            json.put("emaildate", emaildate);
            json.put("emailtime", emailtime);
            json.put("emailmessage", emailmessage);
            json.put("emailstatus", emailstatus);
            json.put("emailstatusdate", emailstatusdate);
            json.put("emailstatustime", emailstatustime);
            json.put("emailsendref", emailsendref);
            json.put("emailpayref", emailpayref);
            json.put("emailactive", emailactive);
        } catch (Exception e) {
            System.out.println("Error creating json EMAILQUEUE: " + e.toString());
        }
        return json;
    }
}
