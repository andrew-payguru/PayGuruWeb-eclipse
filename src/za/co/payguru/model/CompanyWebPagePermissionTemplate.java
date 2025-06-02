package za.co.payguru.model;

import org.json.JSONObject;

public class CompanyWebPagePermissionTemplate {
	public static int TEMPLATE_TYPE_CUSTOM = 0;
	public static int TEMPLATE_TYPE_ALL = -1;
	
	private int templateid = 0;
	private int compid = 0;
	private String templatedesc = "";
	private String templatepermissions = "";
	private String templateref1 = "";
	public CompanyWebPagePermissionTemplate() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getTemplateid() {
		return templateid;
	}
	public void setTemplateid(int templateid) {
		this.templateid = templateid;
	}
	public int getCompid() {
		return compid;
	}
	public void setCompid(int compid) {
		this.compid = compid;
	}
	public String getTemplatedesc() {
		return templatedesc;
	}
	public void setTemplatedesc(String templatedesc) {
		this.templatedesc = templatedesc;
	}
	public String getTemplatepermissions() {
		return templatepermissions;
	}
	public void setTemplatepermissions(String templatepermissions) {
		this.templatepermissions = templatepermissions;
	}
	public String getTemplateref1() {
		return templateref1;
	}
	public void setTemplateref1(String templateref1) {
		this.templateref1 = templateref1;
	}
	@Override
	public String toString() {
		return "CompanyWebPagePermissionTemplate [templateid=" + templateid + ", compid=" + compid + ", templatedesc="
				+ templatedesc + ", templatepermissions=" + templatepermissions + ", templateref1=" + templateref1
				+ "]";
	}
	
	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		try {
			json.put("templateid", templateid);
			json.put("compid", compid);
			json.put("templatedesc", templatedesc);
			json.put("templatepermissions", templatepermissions);
			json.put("templateref1", templateref1);
		}catch (Exception e) {
			System.out.println("Error creating json: " + e.toString());
		}
		return json;
	}
}
