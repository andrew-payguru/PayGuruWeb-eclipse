package za.co.payguru.model;

public class ClientInvoiceItem {
	private String invNo = "";
	private int compId = 0;
	private int prodId = 0;
	private int prodQty = 0;
	private double prodRsp = 0;
	private String prodRef = "";
	private String prodLink = "";
	public ClientInvoiceItem() {
		this.invNo = "";
		this.compId = 0;
		this.prodId = 0;
		this.prodQty = 0;
		this.prodRsp = 0;
		this.prodRef = "";
		this.prodLink = "";
	}
	public ClientInvoiceItem(String invNo, int compId, int prodId, int prodQty, double prodRsp, String prodRef,
			String prodLink) {
		super();
		this.invNo = invNo;
		this.compId = compId;
		this.prodId = prodId;
		this.prodQty = prodQty;
		this.prodRsp = prodRsp;
		this.prodRef = prodRef;
		this.prodLink = prodLink;
	}
	public String getInvNo() {
		return invNo;
	}
	public void setInvNo(String invNo) {
		this.invNo = invNo;
	}
	public int getCompId() {
		return compId;
	}
	public void setCompId(int compId) {
		this.compId = compId;
	}
	public int getProdId() {
		return prodId;
	}
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
	public int getProdQty() {
		return prodQty;
	}
	public void setProdQty(int prodQty) {
		this.prodQty = prodQty;
	}
	public double getProdRsp() {
		return prodRsp;
	}
	public void setProdRsp(double prodRsp) {
		this.prodRsp = prodRsp;
	}
	public String getProdRef() {
		return prodRef;
	}
	public void setProdRef(String prodRef) {
		this.prodRef = prodRef;
	}
	public String getProdLink() {
		return prodLink;
	}
	public void setProdLink(String prodLink) {
		this.prodLink = prodLink;
	}
	@Override
	public String toString() {
		return "ClientInvoiceItems [invNo=" + invNo + ", compId=" + compId + ", prodId=" + prodId + ", prodQty=" + prodQty
				+ ", prodRsp=" + prodRsp + ", prodRef=" + prodRef + ", prodLink=" + prodLink + "]";
	}
	
	
}
