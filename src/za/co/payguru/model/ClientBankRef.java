package za.co.payguru.model;

import org.json.JSONObject;

public class ClientBankRef {

    private int clientid = 0;
    private int bankid = 0;
    private String bankref = "";
    private int subacc = -1;

    public ClientBankRef() {
        this.clientid = 0;
        this.bankid = 0;
        this.bankref = "";
        this.subacc = -1;
    }

    public int getClientid() {
        return clientid;
    }

    public void setClientid(int clientid) {
        this.clientid = clientid;
    }

    public int getBankid() {
        return bankid;
    }

    public void setBankid(int bankid) {
        this.bankid = bankid;
    }

    public String getBankref() {
        return bankref;
    }

    public void setBankref(String bankref) {
        this.bankref = bankref;
    }

    public int getSubacc() {
        return subacc;
    }

    public void setSubacc(int subacc) {
        this.subacc = subacc;
    }

    @Override
    public String toString() {
        return "ClientBankRef [clientid=" + clientid + ", bankid=" + bankid + ", bankref=" + bankref + ", subacc=" + subacc + "]";
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        try {
            json.put("clientid", clientid);
            json.put("bankid", bankid);
            json.put("bankref", bankref);
            json.put("subacc", subacc);
        } catch (Exception e) {
            System.out.println("Error creating JSON for ClientBankRef: " + e.toString());
        }
        return json;
    }
}
