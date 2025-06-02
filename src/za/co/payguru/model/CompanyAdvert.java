package za.co.payguru.model;

import java.sql.Date;
import java.sql.Time;

import za.co.payguru.util.DateUtil;

public class CompanyAdvert {
	public static final String ADVERT_ACTIVE = "1";
	public static final String ADVERT_INACTIVE = "0";
	
	public static final int ADVERT_TYPE_REDIRECT = 1;
	public static final int ADVERT_TYPE_DATACAPTURE = 2;

	public static final int ADLINK_TYPE_INTERNAL = 1;
	public static final int ADLINK_TYPE_EXTERNAL = 2;
	
	public static final String DOC_TYPE_HTML = ".html";
	public static final String DOC_TYPE_PDF = ".pdf";
	public static final String DOC_TYPE_JPG = ".jpg";
	public static final String DOC_TYPE_JPEG = ".jpeg";
	public static final String DOC_TYPE_PNG = ".png";
	
	public static final String FIELD_TYPE_VARCHAR = "VARCHAR";
	public static final String FIELD_TYPE_INTEGER = "INTEGER";
	public static final String FIELD_TYPE_BIGINT  = "BIGINT";
	public static final String FIELD_TYPE_NUMERIC = "NUMERIC(16,2)";
	
	private int compid = 0;
	private int compadid = 0;
	private String compaddesc = "";
	private int compadtype = ADVERT_TYPE_REDIRECT;
	private int compadlinktype = ADLINK_TYPE_INTERNAL;
	private String compadlink = "";
	private String compadredirectref = "";
	private String compaddata = "";
	private String compaddataref = "";
	private Date compadstartdate = DateUtil.DEFAULT_DATE;
	private Date compadenddate = DateUtil.DEFAULT_DATE;
	private String compadstatus = ADVERT_ACTIVE;
	private Date compadstatusdate = DateUtil.getCurrentCCYYMMDDDate();
	private Time compadstatustime = DateUtil.getCurrentHHMMSSTime();
	private String compadactive = ADVERT_ACTIVE;
	public CompanyAdvert() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CompanyAdvert(int compid, int compadid, String compaddesc, int compadtype, int compadlinktype,
			String compadlink, String compadredirectref, String compaddata, String compaddataref, Date compadstartdate,
			Date compadenddate, String compadstatus, Date compadstatusdate, Time compadstatustime, String compadactive) {
		super();
		this.compid = compid;
		this.compadid = compadid;
		this.compaddesc = compaddesc;
		this.compadtype = compadtype;
		this.compadlinktype = compadlinktype;
		this.compadlink = compadlink;
		this.compadredirectref = compadredirectref;
		this.compaddata = compaddata;
		this.compaddataref = compaddataref;
		this.compadstartdate = compadstartdate;
		this.compadenddate = compadenddate;
		this.compadstatus = compadstatus;
		this.compadstatusdate = compadstatusdate;
		this.compadstatustime = compadstatustime;
		this.compadactive = compadactive;
	}
	public int getCompid() {
		return compid;
	}
	public void setCompid(int compid) {
		this.compid = compid;
	}
	public int getCompadid() {
		return compadid;
	}
	public void setCompadid(int compadid) {
		this.compadid = compadid;
	}
	public String getCompaddesc() {
		return compaddesc;
	}
	public void setCompaddesc(String compaddesc) {
		this.compaddesc = compaddesc;
	}
	public int getCompadtype() {
		return compadtype;
	}
	public void setCompadtype(int compadtype) {
		this.compadtype = compadtype;
	}
	public int getCompadlinktype() {
		return compadlinktype;
	}
	public void setCompadlinktype(int compadlinktype) {
		this.compadlinktype = compadlinktype;
	}
	public String getCompadlink() {
		return compadlink;
	}
	public void setCompadlink(String compadlink) {
		this.compadlink = compadlink;
	}
	public String getCompadredirectref() {
		return compadredirectref;
	}
	public void setCompadredirectref(String compadredirectref) {
		this.compadredirectref = compadredirectref;
	}
	public String getCompaddata() {
		return compaddata;
	}
	public void setCompaddata(String compaddata) {
		this.compaddata = compaddata;
	}
	public String getCompaddataref() {
		return compaddataref;
	}
	public void setCompaddataref(String compaddataref) {
		this.compaddataref = compaddataref;
	}
	public Date getCompadstartdate() {
		return compadstartdate;
	}
	public void setCompadstartdate(Date compadstartdate) {
		this.compadstartdate = compadstartdate;
	}
	public Date getCompadenddate() {
		return compadenddate;
	}
	public void setCompadenddate(Date compadenddate) {
		this.compadenddate = compadenddate;
	}
	public String getCompadstatus() {
		return compadstatus;
	}
	public void setCompadstatus(String compadstatus) {
		this.compadstatus = compadstatus;
	}
	public Date getCompadstatusdate() {
		return compadstatusdate;
	}
	public void setCompadstatusdate(Date compadstatusdate) {
		this.compadstatusdate = compadstatusdate;
	}
	public Time getCompadstatustime() {
		return compadstatustime;
	}
	public void setCompadstatustime(Time compadstatustime) {
		this.compadstatustime = compadstatustime;
	}
	public String getCompadactive() {
		return compadactive;
	}
	public void setCompadactive(String compadactive) {
		this.compadactive = compadactive;
	}
	@Override
	public String toString() {
		return "CompanyAdvert [compid=" + compid + ", compadid=" + compadid + ", compaddesc=" + compaddesc + ", compadtype="
				+ compadtype + ", compadlinktype=" + compadlinktype + ", compadlink=" + compadlink + ", compadredirectref="
				+ compadredirectref + ", compaddata=" + compaddata + ", compaddataref=" + compaddataref + ", compadstartdate="
				+ compadstartdate + ", compadenddate=" + compadenddate + ", compadstatus=" + compadstatus
				+ ", compadstatusdate=" + compadstatusdate + ", compadstatustime=" + compadstatustime + ", compadactive="
				+ compadactive + "]";
	}
	
	
	public static String getInputTypeFromDBType(String dbType) {
		if(dbType==null)
			dbType = "";
		String inputType = "text";
		if(dbType.equals(FIELD_TYPE_VARCHAR))
			inputType = "text";
		if(dbType.equals(FIELD_TYPE_BIGINT)||dbType.equals(FIELD_TYPE_INTEGER)||dbType.equals(FIELD_TYPE_NUMERIC))
			inputType = "number";
		return inputType;
	}
	
}
