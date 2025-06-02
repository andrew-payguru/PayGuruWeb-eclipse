package za.co.payguru.model;

import java.sql.Connection;

public class CompanyInternal {
	public static final String ACTIVE = "1";
	
	private int compid = 0;
	private int compinternalid = 0;
	private String compinternalname = new String();
	private String compinternalref1 = new String();
	private String compinternalref2 = new String();
	private String compinternalref3 = new String();
	private String compinternalstatus = ACTIVE;
	public CompanyInternal() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CompanyInternal(int compid, int compinternalid, String compinternalname, String compinternalref1,
			String compinternalref2, String compinternalref3, String compinternalstatus) {
		super();
		this.compid = compid;
		this.compinternalid = compinternalid;
		this.compinternalname = compinternalname;
		this.compinternalref1 = compinternalref1;
		this.compinternalref2 = compinternalref2;
		this.compinternalref3 = compinternalref3;
		this.compinternalstatus = compinternalstatus;
	}
	public int getCompid() {
		return compid;
	}
	public void setCompid(int compid) {
		this.compid = compid;
	}
	public int getCompinternalid() {
		return compinternalid;
	}
	public void setCompinternalid(int compinternalid) {
		this.compinternalid = compinternalid;
	}
	public String getCompinternalname() {
		return compinternalname;
	}
	public void setCompinternalname(String compinternalname) {
		this.compinternalname = compinternalname;
	}
	public String getCompinternalref1() {
		return compinternalref1;
	}
	public void setCompinternalref1(String compinternalref1) {
		this.compinternalref1 = compinternalref1;
	}
	public String getCompinternalref2() {
		return compinternalref2;
	}
	public void setCompinternalref2(String compinternalref2) {
		this.compinternalref2 = compinternalref2;
	}
	public String getCompinternalref3() {
		return compinternalref3;
	}
	public void setCompinternalref3(String compinternalref3) {
		this.compinternalref3 = compinternalref3;
	}
	public String getCompinternalstatus() {
		return compinternalstatus;
	}
	public void setCompinternalstatus(String compinternalstatus) {
		this.compinternalstatus = compinternalstatus;
	}
	@Override
	public String toString() {
		return "CompanyInternal [compid=" + compid + ", compinternalid=" + compinternalid + ", compinternalname="
				+ compinternalname + ", compinternalref1=" + compinternalref1 + ", compinternalref2=" + compinternalref2
				+ ", compinternalref3=" + compinternalref3 + ", compinternalstatus=" + compinternalstatus + "]";
	}
}
