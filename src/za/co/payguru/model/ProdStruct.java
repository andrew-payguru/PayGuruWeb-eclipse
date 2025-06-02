package za.co.payguru.model;

public class ProdStruct {
	private String prodStructID;
	private String prodStructName;
	public ProdStruct(String prodStructID, String prodStructName) {
		super();
		this.prodStructID = prodStructID;
		this.prodStructName = prodStructName;
	}
	public ProdStruct() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getProdStructID() {
		return prodStructID;
	}
	public void setProdStructID(String prodStructID) {
		this.prodStructID = prodStructID;
	}
	public String getProdStructName() {
		return prodStructName;
	}
	public void setProdStructName(String prodStructName) {
		this.prodStructName = prodStructName;
	}
	
	
}
