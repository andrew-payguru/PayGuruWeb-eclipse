package za.co.payguru.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import za.co.payguru.model.WebPage;

public class WebPageDao {
	public static ArrayList<WebPage> getCompWebPages(int compId, Connection connection){
		ArrayList<WebPage> compWebPages = new ArrayList<WebPage>();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM WEBPAGES WHERE pageid IN (SELECT pageid FROM COMPANYWEBPAGES WHERE compid = ?) ORDER BY groupid,pagepriority");
		){
			statement.setInt(1, compId);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				WebPage webPage = new WebPage();
				webPage.setPageId(rs.getInt("pageid"));
				webPage.setGroupId(rs.getInt("groupid"));
				webPage.setPageDesc(rs.getString("pagedesc"));
				webPage.setPagePriority(rs.getInt("pagepriority"));
				webPage.setPageName(rs.getString("pagename"));
				compWebPages.add(webPage);
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying WEBPAGES: " + e.toString());
		}
		return compWebPages;
	}
	
//JSON HELPER METHODS
	public static StringBuilder getJsonWebPages(ArrayList<WebPage> webPages) {
		StringBuilder sb = new StringBuilder();
		sb.append("[\n");
		for(int i=0;i<webPages.size();i++) {
			WebPage webPage = webPages.get(i);
			sb.append("{");
			sb.append("\"pageid\" : "+webPage.getPageId()+",\n");
			sb.append("\"groupid\" : "+webPage.getGroupId()+",\n");
			sb.append("\"pagedesc\" : \""+webPage.getPageDesc()+"\",\n");
			sb.append("\"pagepriority\" : "+webPage.getPagePriority()+",\n");
			sb.append("\"pagename\" : \""+webPage.getPageName()+"\"\n");
			sb.append("}" + (i == webPages.size()-1 ? "" : ",") + "\n");
		}
		sb.append("]");
		return sb;
	}
}
