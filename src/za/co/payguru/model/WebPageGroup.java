package za.co.payguru.model;

public class WebPageGroup {
	private int groupId = 0;
	private String groupDesc = "";
	private int groupPriority = 0;
	public WebPageGroup() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public WebPageGroup(int groupId, String groupDesc, int groupPriority) {
		super();
		this.groupId = groupId;
		this.groupDesc = groupDesc;
		this.groupPriority = groupPriority;
	}

	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getGroupDesc() {
		return groupDesc;
	}
	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}
	public int getGroupPriority() {
		return groupPriority;
	}
	public void setGroupPriority(int groupPriority) {
		this.groupPriority = groupPriority;
	}
	@Override
	public String toString() {
		return "WebPageGroup [groupId=" + groupId + ", groupDesc=" + groupDesc + ", groupPriority=" + groupPriority + "]";
	}
	
	
}
