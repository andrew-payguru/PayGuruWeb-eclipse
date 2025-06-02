package za.co.payguru.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import za.co.payguru.model.WebPagePermission;

public class WebPagePermissionDao {

	public static ArrayList<WebPagePermission> getWebPagePermissions(Connection connection){
		ArrayList<WebPagePermission> permissions = new ArrayList<WebPagePermission>();
		try(
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM WEBPAGEPERMISSIONS ORDER BY pageid, permissiontype");
		){
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				WebPagePermission permission = new WebPagePermission();
				permission.setPermissionid(rs.getInt("permissionid"));
				permission.setPageid(rs.getInt("pageid"));
				permission.setPermissiontype(rs.getInt("permissiontype"));
				permission.setPermissiondesc(rs.getString("permissiondesc"));
				permission.setPermissionref1(rs.getString("permissionref1"));
				permission.setPermissiongroup(rs.getInt("permissiongroup"));
				permissions.add(permission);
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying table: " + e.toString());
		}
		
		return permissions;
	}
	
	public static JSONArray getJSONArray(ArrayList<WebPagePermission> permissions) {
		JSONArray jsonAr = new JSONArray();
		try {
			for(int i=0;i<permissions.size();i++) {
				WebPagePermission permission = permissions.get(i);
				jsonAr.put(permission.toJSON());
			}
		}catch (Exception e) {
			System.out.println("Error creating json array: " + e.toString());
		}
		return jsonAr;
	}
}
