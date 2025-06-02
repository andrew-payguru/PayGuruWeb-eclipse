package za.co.payguru.model;

import org.json.JSONObject;

public class DonorCompanyProduct {
	public static final String ACTIVE = "1";
	public static final String INACTIVE = "2";

    private int donorid = 0;
    private int compid = 0;
    private int prodid = 0;
    private String donorprodref1 = "";
    private String donorprodref2 = "";
    private String prodactive = "1";

    public DonorCompanyProduct() {
        this.donorid = 0;
        this.compid = 0;
        this.prodid = 0;
        this.donorprodref1 = "";
        this.donorprodref2 = "";
        this.prodactive = "1";
    }

  

    public int getDonorid() {
        return donorid;
    }

    public void setDonorid(int donorid) {
        this.donorid = donorid;
    }

    public int getCompid() {
        return compid;
    }

    public void setCompid(int compid) {
        this.compid = compid;
    }

    public int getProdid() {
        return prodid;
    }

    public void setProdid(int prodid) {
        this.prodid = prodid;
    }

    public String getDonorprodref1() {
        return donorprodref1;
    }

    public void setDonorprodref1(String donorprodref1) {
        this.donorprodref1 = donorprodref1;
    }

    public String getDonorprodref2() {
        return donorprodref2;
    }

    public void setDonorprodref2(String donorprodref2) {
        this.donorprodref2 = donorprodref2;
    }

    public String getProdactive() {
        return prodactive;
    }

    public void setProdactive(String prodactive) {
        this.prodactive = prodactive;
    }

    @Override
    public String toString() {
        return "DonorCompanyProducts [donorid=" + donorid + ", compid=" + compid + ", prodid=" + prodid + ", donorprodref1=" + donorprodref1 + ", donorprodref2=" + donorprodref2 + ", prodactive=" + prodactive + "]";
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        try {
            json.put("donorid", donorid);
            json.put("compid", compid);
            json.put("prodid", prodid);
            json.put("donorprodref1", donorprodref1);
            json.put("donorprodref2", donorprodref2);
            json.put("prodactive", prodactive);
        } catch (Exception e) {
            System.out.println("Error creating json DonorCompanyProducts: " + e.toString());
        }
        return json;
    }
}
