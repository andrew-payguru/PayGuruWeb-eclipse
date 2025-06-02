package za.co.payguru.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import za.co.payguru.model.CompanySmsCampaignItem;
import za.co.payguru.model.CompanySmsGroup;
import za.co.payguru.model.WebPage;

public class CompanySmsCampaignItemDao {

	public static long getNextItemNo(Connection connection) {
		long itemNo = 0;
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT nextval('companysmscampaignitem_campaignitemno_seq') AS itemno");
				){
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				itemNo = rs.getLong("itemno");
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error getting next itemno in COMPANYSMSCAMPAIGNITEM: " + e.toString());
		}
		return itemNo;
	}

	public static CompanySmsCampaignItem createSmsCampaignItem(Connection connection, CompanySmsCampaignItem campaignItem) {
		campaignItem.setCampaignItemNo(getNextItemNo(connection));
		campaignItem.setCampaignItemStatus(CompanySmsCampaignItem.CAMPAIGN_ITEM_ACTIVE);
		campaignItem.setCampaignItemActive(CompanySmsCampaignItem.CAMPAIGN_ITEM_ACTIVE);
		try(
				PreparedStatement statement = connection.prepareStatement("INSERT INTO COMPANYSMSCAMPAIGNITEM VALUES (?,?,?,?,?,?,?,?)");
				){
			statement.setInt(1, campaignItem.getCompId());
			statement.setInt(2, campaignItem.getCampaignId());
			statement.setLong(3, campaignItem.getCampaignItemNo());
			statement.setString(4, campaignItem.getCampaignItemMessage());
			statement.setDate(5, campaignItem.getCampaignItemDate());
			statement.setTime(6, campaignItem.getCampaignItemTime());
			statement.setString(7, campaignItem.getCampaignItemStatus());
			statement.setString(8, campaignItem.getCampaignItemActive());
			if(statement.executeUpdate()<=0)
				campaignItem = new CompanySmsCampaignItem();
		}catch (Exception e) {
			System.out.println("Error updating COMPANYSMSCAMPAIGNITEM: " + e.toString());
		}
		return campaignItem;
	}

	public static boolean updateSmsCampaignItem(Connection connection, CompanySmsCampaignItem campaignItem) {
		boolean updated = false;

		if (campaignItem==null) 
			return updated;
		campaignItem.setCampaignItemStatus(CompanySmsCampaignItem.CAMPAIGN_ITEM_ACTIVE);
		campaignItem.setCampaignItemActive(CompanySmsCampaignItem.CAMPAIGN_ITEM_ACTIVE);
		try(
				PreparedStatement statement = connection.prepareStatement("UPDATE COMPANYSMSCAMPAIGNITEM SET COMPID = ?, CAMPAIGNID = ?, CAMPAIGNITEMNO = ?, CAMPAIGNITEMMESSAGE = ?, CAMPAIGNITEMDATE = ?, CAMPAIGNITEMTIME = ?, CAMPAIGNITEMSTATUS = ?, CAMPAIGNITEMACTIVE = ? WHERE COMPID = ? AND CAMPAIGNID = ? AND CAMPAIGNITEMNO = ?");
				){
			statement.setInt(1, campaignItem.getCompId());
			statement.setInt(2, campaignItem.getCampaignId());
			statement.setLong(3, campaignItem.getCampaignItemNo());
			statement.setString(4, campaignItem.getCampaignItemMessage());
			statement.setDate(5, campaignItem.getCampaignItemDate());
			statement.setTime(6, campaignItem.getCampaignItemTime());
			statement.setString(7, campaignItem.getCampaignItemStatus());
			statement.setString(8, campaignItem.getCampaignItemActive());
			statement.setInt(9, campaignItem.getCompId());
			statement.setInt(10, campaignItem.getCampaignId());
			statement.setLong(11, campaignItem.getCampaignItemNo());
			if(statement.executeUpdate()>0)
				updated = true;
		}catch (Exception e) {
			System.out.println("Error updating COMPANYSMSCAMPAIGNITEM: " + e.toString());
		}
		return updated;
	}

	public static ArrayList<CompanySmsCampaignItem> getCampaignItems(Connection connection, int compId, int campaignId){
		ArrayList<CompanySmsCampaignItem> items = new ArrayList<CompanySmsCampaignItem>();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM COMPANYSMSCAMPAIGNITEM WHERE compid = ? AND campaignid = ?");
				){
			statement.setInt(1, compId);
			statement.setInt(2, campaignId);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				CompanySmsCampaignItem item = new CompanySmsCampaignItem();
				item.setCompId(rs.getInt("compid"));
				item.setCampaignId(rs.getInt("campaignid"));
				item.setCampaignItemNo(rs.getLong("campaignitemno"));
				item.setCampaignItemMessage(rs.getString("campaignitemmessage"));
				item.setCampaignItemDate(rs.getDate("campaignitemdate"));
				item.setCampaignItemTime(rs.getTime("campaignitemtime"));
				item.setCampaignItemStatus(rs.getString("campaignitemstatus"));
				item.setCampaignItemActive(rs.getString("campaignitemactive"));
				items.add(item);
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying table: " + e.toString());
		}
		return items;
	}

	//JSON HELPER METHODS
	public static StringBuilder getJsonSmsCampaignItems(ArrayList<CompanySmsCampaignItem> smsItems) {
		StringBuilder sb = new StringBuilder();
		sb.append("[\n");
		for(int i=0;i<smsItems.size();i++) {
			CompanySmsCampaignItem campaignItem = smsItems.get(i);
			sb.append("{");
			sb.append("\"compid\" : "+campaignItem.getCompId()+",\n");
			sb.append("\"campaignid\" : "+campaignItem.getCampaignId()+",\n");
			sb.append("\"campaignitemno\" : "+campaignItem.getCampaignItemNo()+",\n");
			sb.append("\"campaignitemmessage\" : \""+campaignItem.getCampaignItemMessage()+"\",\n");
			sb.append("\"campaignitemdate\" : \""+campaignItem.getCampaignItemDate()+"\",\n");
			sb.append("\"campaignitemtime\" : \""+campaignItem.getCampaignItemTime()+"\",\n");
			sb.append("\"campaignitemstatus\" : \""+campaignItem.getCampaignItemStatus()+"\",\n");
			sb.append("\"campaignitemactive\" : \""+campaignItem.getCampaignItemActive()+"\"\n");
			sb.append("}" + (i == smsItems.size()-1 ? "" : ",") + "\n");
		}
		sb.append("]");
		return sb;
	}

	public static CompanySmsCampaignItem getCampaignItem(Connection connection, int compId, int campaignId, long campaignItemNo){
		CompanySmsCampaignItem item = new CompanySmsCampaignItem();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM COMPANYSMSCAMPAIGNITEM WHERE compid = ? AND campaignid = ? AND campaignitemno = ?");
				){
			statement.setInt(1, compId);
			statement.setInt(2, campaignId);
			statement.setLong(3, campaignItemNo);
			ResultSet rs = statement.executeQuery();

			while(rs.next()) {
				item.setCompId(rs.getInt("compid"));
				item.setCampaignId(rs.getInt("campaignid"));
				item.setCampaignItemNo(rs.getLong("campaignitemno"));
				item.setCampaignItemMessage(rs.getString("campaignitemmessage"));
				item.setCampaignItemDate(rs.getDate("campaignitemdate"));
				item.setCampaignItemTime(rs.getTime("campaignitemtime"));
				item.setCampaignItemStatus(rs.getString("campaignitemstatus"));
				item.setCampaignItemActive(rs.getString("campaignitemactive"));
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying table: " + e.toString());
		}
		return item;
	}
	
	public static CompanySmsCampaignItem getCampaignItem(Connection connection, long campaignItemNo){
		CompanySmsCampaignItem item = new CompanySmsCampaignItem();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM COMPANYSMSCAMPAIGNITEM WHERE campaignitemno = ?");
				){
			statement.setLong(1, campaignItemNo);
			ResultSet rs = statement.executeQuery();

			while(rs.next()) {
				item.setCompId(rs.getInt("compid"));
				item.setCampaignId(rs.getInt("campaignid"));
				item.setCampaignItemNo(rs.getLong("campaignitemno"));
				item.setCampaignItemMessage(rs.getString("campaignitemmessage"));
				item.setCampaignItemDate(rs.getDate("campaignitemdate"));
				item.setCampaignItemTime(rs.getTime("campaignitemtime"));
				item.setCampaignItemStatus(rs.getString("campaignitemstatus"));
				item.setCampaignItemActive(rs.getString("campaignitemactive"));
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying table: " + e.toString());
		}
		return item;
	}

	
	public static StringBuilder getCampaignItemJSON(CompanySmsCampaignItem companySmsCampaignItem) {
		StringBuilder sb = new StringBuilder();
		sb.append("{\n");
		sb.append("\"compid\" : "+companySmsCampaignItem.getCompId()+",\n");
		sb.append("\"campaignid\" : "+companySmsCampaignItem.getCampaignId()+",\n");
		sb.append("\"campaignitemno\" : \""+companySmsCampaignItem.getCampaignItemNo()+"\",\n");
		sb.append("\"campaignitemmessage\" : \""+companySmsCampaignItem.getCampaignItemMessage()+"\",\n");
		sb.append("\"campaignitemdate\" : \""+companySmsCampaignItem.getCampaignItemDate()+"\",\n");
		sb.append("\"campaignitemtime\" : \""+companySmsCampaignItem.getCampaignItemTime()+"\",\n");
		sb.append("\"campaignitemstatus\" : \""+companySmsCampaignItem.getCampaignItemStatus()+"\",\n");
		sb.append("\"campaignitemactive\" : \""+companySmsCampaignItem.getCampaignItemActive()+"\"\n");
		sb.append("}");
		return sb;
	}
}
