package za.co.payguru.model;

import org.json.JSONObject;

public class CompanyUserProduct {
	public static final String PROD_ACTIVE = "1";
	public static final String PROD_INACTIVE = "0";
	
	private int compId;
	private int userId;
	private int prodId;
	private String prodActive;
	public CompanyUserProduct() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CompanyUserProduct(int compId, int userId, int prodId, String prodActive) {
		super();
		this.compId = compId;
		this.userId = userId;
		this.prodId = prodId;
		this.prodActive = prodActive;
	}
	public int getCompId() {
		return compId;
	}
	public void setCompId(int compId) {
		this.compId = compId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getProdId() {
		return prodId;
	}
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
	public String getProdActive() {
		return prodActive;
	}
	public void setProdActive(String prodActive) {
		this.prodActive = prodActive;
	}
	
	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		try {
			json.put("compid", compId);
			json.put("userid", userId);
			json.put("prodid", prodId);
			json.put("prodactive", prodActive);
		}catch (Exception e) {
			System.out.println("Error creating json: " + e.toString());
		}
		return json;
	}
}
