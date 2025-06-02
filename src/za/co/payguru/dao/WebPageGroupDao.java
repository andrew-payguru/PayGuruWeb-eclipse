package za.co.payguru.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import za.co.payguru.model.WebPage;
import za.co.payguru.model.WebPageGroup;

public class WebPageGroupDao {

	public static ArrayList<WebPageGroup> getCompWebPageGroups(int compId, Connection connection){
		ArrayList<WebPageGroup> webPageGroups = new ArrayList<WebPageGroup>();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM WEBPAGEGROUPS WHERE groupid IN (SELECT groupid FROM WEBPAGES WHERE pageid IN (SELECT pageid FROM COMPANYWEBPAGES WHERE compid = ?)) ORDER BY grouppriority");
		){
			statement.setInt(1, compId);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				WebPageGroup webPageGroup = new WebPageGroup();
				webPageGroup.setGroupId(rs.getInt("groupid"));
				webPageGroup.setGroupDesc(rs.getString("groupdesc"));
				webPageGroup.setGroupPriority(rs.getInt("grouppriority"));
				webPageGroups.add(webPageGroup);
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying WEBPAGES: " + e.toString());
		}
		return webPageGroups;
	}
	
//JSON HELPER METHODS
	public static StringBuilder getJsonWebPageGroups(ArrayList<WebPageGroup> webPageGroups) {
		StringBuilder sb = new StringBuilder();
		sb.append("[\n");
		for(int i=0;i<webPageGroups.size();i++) {
			WebPageGroup webPageGroup = webPageGroups.get(i);
			sb.append("{");
			sb.append("\"groupid\" : "+webPageGroup.getGroupId()+",\n");
			sb.append("\"groupdesc\" : \""+webPageGroup.getGroupDesc()+"\",\n");
			sb.append("\"grouppriority\" : "+webPageGroup.getGroupPriority()+"\n");
			sb.append("}" + (i == webPageGroups.size()-1 ? "" : ",") + "\n");
		}
		sb.append("]");
		return sb;
	}
}
