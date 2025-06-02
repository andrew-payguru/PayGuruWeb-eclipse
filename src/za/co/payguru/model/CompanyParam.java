package za.co.payguru.model;

public class CompanyParam {

	private int compid = 0;
	private String paramid = new String();
	private String paramvalue = new String();
	public CompanyParam() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CompanyParam(int compid, String paramid, String paramvalue) {
		super();
		this.compid = compid;
		this.paramid = paramid;
		this.paramvalue = paramvalue;
	}
	public int getCompid() {
		return compid;
	}
	public void setCompid(int compid) {
		this.compid = compid;
	}
	public String getParamid() {
		return paramid;
	}
	public void setParamid(String paramid) {
		this.paramid = paramid;
	}
	public String getParamvalue() {
		return paramvalue;
	}
	public void setParamvalue(String paramvalue) {
		this.paramvalue = paramvalue;
	}
	@Override
	public String toString() {
		return "CompanyParam [compid=" + compid + ", paramid=" + paramid + ", paramvalue=" + paramvalue + "]";
	}
	
	
}
