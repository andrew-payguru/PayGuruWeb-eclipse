package za.co.payguru.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.json.JSONArray;

import za.co.payguru.model.WebPagePermission;
import za.co.payguru.model.WebPagePermissionGroup;

public class WebPagePermissionGroupDao {

	public static ArrayList<WebPagePermissionGroup> getPagePermissionGroups(Connection connection){
		ArrayList<WebPagePermissionGroup> permissionGroups = new ArrayList<WebPagePermissionGroup>();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM WEBPAGEPERMISSIONGROUPS");
		){
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				WebPagePermissionGroup group = new WebPagePermissionGroup();
				group.setPermissiongroupid(rs.getInt("permissiongroupid"));
				group.setPermissiongroupdesc(rs.getString("permissiongroupdesc"));
				group.setPermissiongroupref1(rs.getString("permissiongroupref1"));
				permissionGroups.add(group);
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying table WEBPAGEPERMISSIONGROUPS: " + e.toString());
		}
		return permissionGroups;
	}
	
	public static JSONArray getJSONArray(ArrayList<WebPagePermissionGroup> groups) {
		JSONArray json = new JSONArray();
		try {
			for(WebPagePermissionGroup group : groups) {
				json.put(group.toJSON());
			}
		}catch (Exception e) {
			System.out.println("Error creating json: " + e.toString());
		}
		return json;
	}
}
