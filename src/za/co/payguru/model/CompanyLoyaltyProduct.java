package za.co.payguru.model;

public class CompanyLoyaltyProduct {
	public static final String ACTIVE = "1";
	public static final String INACTIVE = "0";
	
	private int compid = 0;
	private int comployaltyid = 0;
	private long prodid = 0;
	private double prodpointperc = 0.0;
	private int prodpointlifespan = 0;
	private String prodactive = ACTIVE;
	public CompanyLoyaltyProduct() {
		this.compid = 0;
		this.comployaltyid = 0;
		this.prodid = 0;
		this.prodpointperc = 0.0;
		this.prodpointlifespan = 0;
		this.prodactive = ACTIVE;
	}
	public CompanyLoyaltyProduct(int compid, int comployaltyid, long prodid, double prodpointperc, int prodpointlifespan,
			String prodactive) {
		super();
		this.compid = compid;
		this.comployaltyid = comployaltyid;
		this.prodid = prodid;
		this.prodpointperc = prodpointperc;
		this.prodpointlifespan = prodpointlifespan;
		this.prodactive = prodactive;
	}
	public int getCompid() {
		return compid;
	}
	public void setCompid(int compid) {
		this.compid = compid;
	}
	public int getComployaltyid() {
		return comployaltyid;
	}
	public void setComployaltyid(int comployaltyid) {
		this.comployaltyid = comployaltyid;
	}
	public long getProdid() {
		return prodid;
	}
	public void setProdid(long prodid) {
		this.prodid = prodid;
	}
	public double getProdpointperc() {
		return prodpointperc;
	}
	public void setProdpointperc(double prodpointperc) {
		this.prodpointperc = prodpointperc;
	}
	public int getProdpointlifespan() {
		return prodpointlifespan;
	}
	public void setProdpointlifespan(int prodpointlifespan) {
		this.prodpointlifespan = prodpointlifespan;
	}
	public String getProdactive() {
		return prodactive;
	}
	public void setProdactive(String prodactive) {
		this.prodactive = prodactive;
	}
	@Override
	public String toString() {
		return "CompanyLoyaltyProduct [compid=" + compid + ", comployaltyid=" + comployaltyid + ", prodid=" + prodid
				+ ", prodpointperc=" + prodpointperc + ", prodpointlifespan=" + prodpointlifespan + ", prodactive=" + prodactive
				+ "]";
	}
	
	
}
