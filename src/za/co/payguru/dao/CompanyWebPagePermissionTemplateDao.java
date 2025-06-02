package za.co.payguru.dao;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.json.JSONArray;

import za.co.payguru.model.CompanyWebPagePermissionTemplate;

public class CompanyWebPagePermissionTemplateDao {

	public static ArrayList<CompanyWebPagePermissionTemplate> getCompanyPermissionTemplates(Connection connection, int compid){
		ArrayList<CompanyWebPagePermissionTemplate> templates = new ArrayList<CompanyWebPagePermissionTemplate>();
		try(
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM COMPANYWEBPAGEPERMISSIONTEMPLATES WHERE compid = ?");
		){
			statement.setInt(1, compid);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				CompanyWebPagePermissionTemplate template = new CompanyWebPagePermissionTemplate();
				template.setTemplateid(rs.getInt("templateid"));
				template.setCompid(rs.getInt("compid"));
				template.setTemplatedesc(rs.getString("templatedesc"));
				template.setTemplatepermissions(rs.getString("templatepermissions"));
				template.setTemplateref1(rs.getString("templateref1"));
				templates.add(template);
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying table COMPANYWEBPAGEPERMISSIONTEMPLATES: " + e.toString());
		}
		return templates;
	}
	
	public static JSONArray getJSONArray(ArrayList<CompanyWebPagePermissionTemplate> templates) {
		JSONArray jsonAr = new JSONArray();
		try {
			for(int i=0;i<templates.size();i++) {
				CompanyWebPagePermissionTemplate template = templates.get(i);
				jsonAr.put(template.toJSON());
			}
		}catch (Exception e) {
			System.out.println("Error making json array: " + e.toString());
		}
		return jsonAr;
	}
}
