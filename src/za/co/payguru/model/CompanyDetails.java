package za.co.payguru.model;

public class CompanyDetails {
	private int compid = 0;
	private String compref1 = "";
	private String compref2 = "";	
	private String compref3 = "";
	private String compref4 = "";
	private String compref5 = "";
	public CompanyDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CompanyDetails(int compid, String compref1, String compref2, String compref3, String compref4,
			String compref5) {
		super();
		this.compid = compid;
		this.compref1 = compref1;
		this.compref2 = compref2;
		this.compref3 = compref3;
		this.compref4 = compref4;
		this.compref5 = compref5;
	}
	public int getCompid() {
		return compid;
	}
	public void setCompid(int compid) {
		this.compid = compid;
	}
	public String getCompref1() {
		return compref1;
	}
	public void setCompref1(String compref1) {
		this.compref1 = compref1;
	}
	public String getCompref2() {
		return compref2;
	}
	public void setCompref2(String compref2) {
		this.compref2 = compref2;
	}
	public String getCompref3() {
		return compref3;
	}
	public void setCompref3(String compref3) {
		this.compref3 = compref3;
	}
	public String getCompref4() {
		return compref4;
	}
	public void setCompref4(String compref4) {
		this.compref4 = compref4;
	}
	public String getCompref5() {
		return compref5;
	}
	public void setCompref5(String compref5) {
		this.compref5 = compref5;
	}
	@Override
	public String toString() {
		return "CompanyDetails [compid=" + compid + ", compref1=" + compref1 + ", compref2=" + compref2 + ", compref3="
				+ compref3 + ", compref4=" + compref4 + ", compref5=" + compref5 + "]";
	}
	public String toJsonString() {
		return "{\"compid\" : " + compid + ", \"compref1\" : \"" + compref1 + "\", \"compref2\" : \"" + compref2 + "\", \"compref3\" : \""
				+ compref3 + "\", \"compref4\" : \"" + compref4 + "\", \"compref5\" : \"" + compref5 + "\"}";
	}
	
}
