package za.co.payguru.model;

public class ClientBlacklist {
	public static final String BLACKLIST_ACTIVE = "1";
	public static final String BLACKLIST_INACTIVE = "0";
	
	private int compId = 0;
	private int clientId = 0;
	private int prodId = 0;
	private String active = "1";
	public ClientBlacklist() {
	}
	public ClientBlacklist(int compId, int clientId, int prodId, String active) {
		super();
		this.compId = compId;
		this.clientId = clientId;
		this.prodId = prodId;
		this.active = active;
	}
	public int getCompId() {
		return compId;
	}
	public void setCompId(int compId) {
		this.compId = compId;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public int getProdId() {
		return prodId;
	}
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	@Override
	public String toString() {
		return "ClientBlacklist [compId=" + compId + ", clientId=" + clientId + ", prodId=" + prodId + ", active=" + active
				+ "]";
	}
	public String toJsonString() {
		return "{\"compid\" : " + compId + ", \"clientid\" : " + clientId + ", \"prodid\" : " + prodId + ", \"active\" : \"" + active + "\""
				+ "}";
	}
	
	
}
