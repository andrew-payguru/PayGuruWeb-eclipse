package za.co.payguru.model;

import org.json.JSONObject;

public class WebPagePermissionGroup {
	private int permissiongroupid = 0;
	private String permissiongroupdesc = "";
	private String permissiongroupref1 = "";
	public WebPagePermissionGroup() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public int getPermissiongroupid() {
		return permissiongroupid;
	}


	public void setPermissiongroupid(int permissiongroupid) {
		this.permissiongroupid = permissiongroupid;
	}


	public String getPermissiongroupdesc() {
		return permissiongroupdesc;
	}


	public void setPermissiongroupdesc(String permissiongroupdesc) {
		this.permissiongroupdesc = permissiongroupdesc;
	}


	public String getPermissiongroupref1() {
		return permissiongroupref1;
	}


	public void setPermissiongroupref1(String permissiongroupref1) {
		this.permissiongroupref1 = permissiongroupref1;
	}

	@Override
	public String toString() {
		return "WebPagePermissionGroup [permissiongroupid=" + permissiongroupid + ", permissiongroupdesc="
				+ permissiongroupdesc + ", permissiongroupref1=" + permissiongroupref1 + "]";
	}


	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		try {
			json.put("permissiongroupid", permissiongroupid);
			json.put("permissiongroupdesc", permissiongroupdesc);
			json.put("permissiongroupref1", permissiongroupref1);
		}catch (Exception e) {
			System.out.println("Error creating json: " + e.toString());
		}
		return json;
	}
}
