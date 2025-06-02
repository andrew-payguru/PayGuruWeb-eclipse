package za.co.payguru.model;

import org.json.JSONObject;

public class WebPagePermission {

	public static final int PERMISSION_TYPE_DISPLAY = 1;
	public static final int PERMISSION_TYPE_EDIT    = 2;
	public static final int PERMISSION_TYPE_PAGE    = 3;
	
	private int permissionid = 0;
	private int pageid  = 0;
	private int permissiontype = PERMISSION_TYPE_DISPLAY;
	private String permissiondesc = "";
	private String permissionref1 = "";
	private int permissiongroup = 0;
	
	public WebPagePermission() {
		
	}

	public int getPermissionid() {
		return permissionid;
	}

	public void setPermissionid(int permissionid) {
		this.permissionid = permissionid;
	}

	public int getPageid() {
		return pageid;
	}

	public void setPageid(int pageid) {
		this.pageid = pageid;
	}

	public int getPermissiontype() {
		return permissiontype;
	}

	public void setPermissiontype(int permissiontype) {
		this.permissiontype = permissiontype;
	}

	public String getPermissiondesc() {
		return permissiondesc;
	}

	public void setPermissiondesc(String permissiondesc) {
		this.permissiondesc = permissiondesc;
	}

	public String getPermissionref1() {
		return permissionref1;
	}

	public void setPermissionref1(String permissionref1) {
		this.permissionref1 = permissionref1;
	}

	
	public int getPermissiongroup() {
		return permissiongroup;
	}

	public void setPermissiongroup(int permissiongroup) {
		this.permissiongroup = permissiongroup;
	}

	
	
	@Override
	public String toString() {
		return "WebPagePermission [permissionid=" + permissionid + ", pageid=" + pageid + ", permissiontype="
				+ permissiontype + ", permissiondesc=" + permissiondesc + ", permissionref1=" + permissionref1
				+ ", permissiongroup=" + permissiongroup + "]";
	}

	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		try {
			json.put("permissionid", permissionid);
			json.put("pageid", pageid);
			json.put("permissiontype", permissiontype);
			json.put("permissiondesc", permissiondesc);
			json.put("permissionref1", permissionref1);
			json.put("permissiongroup", permissiongroup);
		}catch (Exception e) {
			System.out.println("Error Creating JSON: " + e.toString());
		}
		return json;
	}
	
}
