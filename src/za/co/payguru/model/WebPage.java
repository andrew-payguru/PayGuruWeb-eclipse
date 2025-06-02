package za.co.payguru.model;

public class WebPage {
	private int pageId = 0;
	private int groupId = 0;
	private String pageDesc = "";
	private int pagePriority = 0;
	private String pageName = "";
	public WebPage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public WebPage(int pageId, int groupId, String pageDesc, int pagePriority, String pageName) {
		super();
		this.pageId = pageId;
		this.groupId = groupId;
		this.pageDesc = pageDesc;
		this.pagePriority = pagePriority;
		this.pageName = pageName;
	}
	public int getPageId() {
		return pageId;
	}
	public void setPageId(int pageId) {
		this.pageId = pageId;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getPageDesc() {
		return pageDesc;
	}
	public void setPageDesc(String pageDesc) {
		this.pageDesc = pageDesc;
	}
	public int getPagePriority() {
		return pagePriority;
	}
	public void setPagePriority(int pagePriority) {
		this.pagePriority = pagePriority;
	}
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	@Override
	public String toString() {
		return "WebPage [pageId=" + pageId + ", groupId=" + groupId + ", pageDesc=" + pageDesc + ", pagePriority="
				+ pagePriority + ", pageName=" + pageName + "]";
	}
		
	
}
