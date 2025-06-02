package za.co.payguru.model;

import org.json.JSONObject;

public class Donor {
    private int donorid;
    private String donorname;
    private String donoremail;
    private String donorcell;
    private String donorvatno;
    private String donorregno;
    private String donortype;
    private String donorref1;
    private String donorref2;
    private String donorref;
    private String donoractive;

    public Donor() {
        this.donorid = 0;
        this.donorname = "";
        this.donoremail = "";
        this.donorcell = "";
        this.donorvatno = "";
        this.donorregno = "";
        this.donortype = "";
        this.donorref1 = "";
        this.donorref2 = "";
        this.donorref = "";
        this.donoractive = "";
    }

    public Donor(int donorid, String donorname, String donoremail, String donorcell, String donorvatno, String donorregno, String donortype, String donorref1, String donorref2, String donorref, String donoractive) {
        this.donorid = donorid;
        this.donorname = donorname;
        this.donoremail = donoremail;
        this.donorcell = donorcell;
        this.donorvatno = donorvatno;
        this.donorregno = donorregno;
        this.donortype = donortype;
        this.donorref1 = donorref1;
        this.donorref2 = donorref2;
        this.donorref = donorref;
        this.donoractive = donoractive;
    }

    public int getDonorid() {
        return donorid;
    }

    public void setDonorid(int donorid) {
        this.donorid = donorid;
    }

    public String getDonorname() {
        return donorname;
    }

    public void setDonorname(String donorname) {
        this.donorname = donorname;
    }

    public String getDonoremail() {
        return donoremail;
    }

    public void setDonoremail(String donoremail) {
        this.donoremail = donoremail;
    }

    public String getDonorcell() {
        return donorcell;
    }

    public void setDonorcell(String donorcell) {
        this.donorcell = donorcell;
    }

    public String getDonorvatno() {
        return donorvatno;
    }

    public void setDonorvatno(String donorvatno) {
        this.donorvatno = donorvatno;
    }

    public String getDonorregno() {
        return donorregno;
    }

    public void setDonorregno(String donorregno) {
        this.donorregno = donorregno;
    }

    public String getDonortype() {
        return donortype;
    }

    public void setDonortype(String donortype) {
        this.donortype = donortype;
    }

    public String getDonorref1() {
        return donorref1;
    }

    public void setDonorref1(String donorref1) {
        this.donorref1 = donorref1;
    }

    public String getDonorref2() {
        return donorref2;
    }

    public void setDonorref2(String donorref2) {
        this.donorref2 = donorref2;
    }

    public String getDonorref() {
        return donorref;
    }

    public void setDonorref(String donorref) {
        this.donorref = donorref;
    }

    public String getDonoractive() {
        return donoractive;
    }

    public void setDonoractive(String donoractive) {
        this.donoractive = donoractive;
    }

    @Override
    public String toString() {
        return "Donor [donorid=" + donorid + ", donorname=" + donorname + ", donoremail=" + donoremail + ", donorcell=" + donorcell + ", donorvatno=" + donorvatno + ", donorregno=" + donorregno + ", donortype=" + donortype + ", donorref1=" + donorref1 + ", donorref2=" + donorref2 + ", donorref=" + donorref + ", donoractive=" + donoractive + "]";
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        try {
            json.put("donorid", donorid);
            json.put("donorname", donorname);
            json.put("donoremail", donoremail);
            json.put("donorcell", donorcell);
            json.put("donorvatno", donorvatno);
            json.put("donorregno", donorregno);
            json.put("donortype", donortype);
            json.put("donorref1", donorref1);
            json.put("donorref2", donorref2);
            json.put("donorref", donorref);
            json.put("donoractive", donoractive);
        } catch (Exception e) {
            System.out.println("Error creating JSON for Donor: " + e.toString());
        }
        return json;
    }
}
