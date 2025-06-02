package za.co.payguru.model;

public class CompanyWebPage {
	public static final String ACTIVE = "1";
	public static final String INACTIVE = "0";
	
	private int compId = 0;
	private int pageId = 0;
	private String compPageActive = ACTIVE;
	public CompanyWebPage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CompanyWebPage(int compId, int pageId, String compPageActive) {
		super();
		this.compId = compId;
		this.pageId = pageId;
		this.compPageActive = compPageActive;
	}
	public int getCompId() {
		return compId;
	}
	public void setCompId(int compId) {
		this.compId = compId;
	}
	public int getPageId() {
		return pageId;
	}
	public void setPageId(int pageId) {
		this.pageId = pageId;
	}
	public String getCompPageActive() {
		return compPageActive;
	}
	public void setCompPageActive(String compPageActive) {
		this.compPageActive = compPageActive;
	}
	@Override
	public String toString() {
		return "CompanyWebPages [compId=" + compId + ", pageId=" + pageId + ", compPageActive=" + compPageActive + "]";
	}
	
	
}
