package za.co.payguru.model;

import org.json.JSONObject;

public class Bank {
	
	public static String ACTIVE = "1";
	public static String INACTIVE = "0";
	
    private int bankid;
    private String bankdesc;
    private String bankaccno;
    private String bankactive;
    private String bankref1;
    private String bankref2;
    private String bankref3;
    private String bankref4;
    private String bankref5;
    private String bankref6;

    public Bank() {
        this.bankid = 0;
        this.bankdesc = "";
        this.bankaccno = "";
        this.bankactive = "1";
        this.bankref1 = "";
        this.bankref2 = "";
        this.bankref3 = "";
        this.bankref4 = "";
        this.bankref5 = "";
        this.bankref6 = "";
    }

    public int getBankid() {
        return bankid;
    }

    public void setBankid(int bankid) {
        this.bankid = bankid;
    }

    public String getBankdesc() {
        return bankdesc;
    }

    public void setBankdesc(String bankdesc) {
        this.bankdesc = bankdesc;
    }

    public String getBankaccno() {
        return bankaccno;
    }

    public void setBankaccno(String bankaccno) {
        this.bankaccno = bankaccno;
    }

    public String getBankactive() {
        return bankactive;
    }

    public void setBankactive(String bankactive) {
        this.bankactive = bankactive;
    }

    public String getBankref1() {
        return bankref1;
    }

    public void setBankref1(String bankref1) {
        this.bankref1 = bankref1;
    }

    public String getBankref2() {
        return bankref2;
    }

    public void setBankref2(String bankref2) {
        this.bankref2 = bankref2;
    }

    public String getBankref3() {
        return bankref3;
    }

    public void setBankref3(String bankref3) {
        this.bankref3 = bankref3;
    }

    public String getBankref4() {
        return bankref4;
    }

    public void setBankref4(String bankref4) {
        this.bankref4 = bankref4;
    }

    public String getBankref5() {
        return bankref5;
    }

    public void setBankref5(String bankref5) {
        this.bankref5 = bankref5;
    }

    public String getBankref6() {
        return bankref6;
    }

    public void setBankref6(String bankref6) {
        this.bankref6 = bankref6;
    }

    @Override
    public String toString() {
        return "Banks [bankid=" + bankid + ", bankdesc=" + bankdesc + ", bankaccno=" + bankaccno + ", bankactive=" + bankactive
                + ", bankref1=" + bankref1 + ", bankref2=" + bankref2 + ", bankref3=" + bankref3 + ", bankref4=" + bankref4
                + ", bankref5=" + bankref5 + ", bankref6=" + bankref6 + "]";
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        try {
            json.put("bankid", bankid);
            json.put("bankdesc", bankdesc);
            json.put("bankaccno", bankaccno);
            json.put("bankactive", bankactive);
            json.put("bankref1", bankref1);
            json.put("bankref2", bankref2);
            json.put("bankref3", bankref3);
            json.put("bankref4", bankref4);
            json.put("bankref5", bankref5);
            json.put("bankref6", bankref6);
        } catch (Exception e) {
            System.out.println("Error creating JSON for Banks: " + e.toString());
        }
        return json;
    }
}