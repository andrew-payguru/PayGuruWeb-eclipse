package za.co.payguru.model;

import java.sql.Date;
import java.sql.Time;

public class DonorClient {

    private int donorid;
    private int clientid;
    private int status;
    private Time statustime;
    private Date statusdate;

    public DonorClient() {
        this.donorid = 0;
        this.clientid = 0;
        this.status = 0;
        this.statustime = new Time(System.currentTimeMillis());
        this.statusdate = new Date(System.currentTimeMillis());
    }

    public int getDonorid() {
        return donorid;
    }

    public void setDonorid(int donorid) {
        this.donorid = donorid;
    }

    public int getClientid() {
        return clientid;
    }

    public void setClientid(int clientid) {
        this.clientid = clientid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Time getStatustime() {
        return statustime;
    }

    public void setStatustime(Time statustime) {
        this.statustime = statustime;
    }

    public Date getStatusdate() {
        return statusdate;
    }

    public void setStatusdate(Date statusdate) {
        this.statusdate = statusdate;
    }

    @Override
    public String toString() {
        return "DonorClients [donorid=" + donorid + ", clientid=" + clientid + ", status=" + status + ", statustime="
                + statustime + ", statusdate=" + statusdate + "]";
    }
}
