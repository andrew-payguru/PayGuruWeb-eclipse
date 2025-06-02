package za.co.payguru.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import za.co.payguru.model.CompanySmsCampaign;
import za.co.payguru.util.DateUtil;

public class CompanySmsCampaignDao {

	public static int getNextCampaignId(Connection connection) {
		int campaignId = 0;
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT nextval('companysmscampaign_campaignid_seq') AS smsid");
		){
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				campaignId = rs.getInt("smsid");
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying next campaignid : " + e.toString());
		}
		return campaignId;
	}
	
	public static CompanySmsCampaign createSmsCampaign(CompanySmsCampaign companySmsCampaign, Connection connection) {
		companySmsCampaign.setCampaignId(getNextCampaignId(connection));
		companySmsCampaign.setCampaignStatusDate(DateUtil.getCurrentCCYYMMDDDate());
		companySmsCampaign.setCampaignStatusTime(DateUtil.getCurrentHHMMSSTime());
		companySmsCampaign.setCampaignStatus(CompanySmsCampaign.CAMPAIGN_ACTIVE);
		try(
				PreparedStatement statement = connection.prepareStatement("INSERT INTO COMPANYSMSCAMPAIGN VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
		){
			statement.setInt(1, companySmsCampaign.getCompId());
			statement.setInt(2, companySmsCampaign.getCampaignId());
			statement.setString(3, companySmsCampaign.getCampaignSendGroups());
			statement.setString(4, companySmsCampaign.getCampaignName());
			statement.setInt(5, companySmsCampaign.getCampaignRecurrence());
			statement.setInt(6, companySmsCampaign.getCampaignFrequency());
			statement.setDate(7, companySmsCampaign.getCampaignStart());
			statement.setDate(8, companySmsCampaign.getCampaignEnd());
			statement.setString(9, companySmsCampaign.getCampaignStatus());
			statement.setInt(10, companySmsCampaign.getCampaignStatusUser());
			statement.setTime(11, companySmsCampaign.getCampaignStatusTime());
			statement.setDate(12, companySmsCampaign.getCampaignStatusDate());
			if(statement.executeUpdate()<=0)
				companySmsCampaign = new CompanySmsCampaign();
			
		}catch (Exception e) {
			System.out.println("Error updating table COMPANYSMSCAMPAIGN: " + e.toString());
			companySmsCampaign = new CompanySmsCampaign();
		}
		
		return companySmsCampaign;
	}
	
	public static ArrayList<CompanySmsCampaign> getSmsCampaigns(Connection connection, int compId, String campaignName, Date fromDate, Date toDate){
		ArrayList<CompanySmsCampaign> smsCampaigns = new ArrayList<CompanySmsCampaign>();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM COMPANYSMSCAMPAIGN WHERE compid = ? AND campaignstart >= ? AND campaignstart <= ? AND upper(campaignname) like ? ORDER BY campaignstart DESC, campaignid DESC, campaignname ASC, campaignstatus DESC");
		){
			statement.setInt(1, compId);
			statement.setDate(2, fromDate);
			statement.setDate(3, toDate);
			statement.setString(4, "%"+campaignName.toUpperCase()+"%");
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				CompanySmsCampaign smsCampaign = new CompanySmsCampaign();
				smsCampaign.setCompId(rs.getInt("compid"));
				smsCampaign.setCampaignId(rs.getInt("campaignid"));
				smsCampaign.setCampaignSendGroups(rs.getString("campaignsendgroups"));
				smsCampaign.setCampaignName(rs.getString("campaignname"));
				smsCampaign.setCampaignRecurrence(rs.getInt("campaignrecurrence"));
				smsCampaign.setCampaignFrequency(rs.getInt("campaignfrequency"));
				smsCampaign.setCampaignStart(rs.getDate("campaignstart"));
				smsCampaign.setCampaignEnd(rs.getDate("campaignend"));
				smsCampaign.setCampaignStatus(rs.getString("campaignstatus"));
				smsCampaign.setCampaignStatusUser(rs.getInt("campaignstatususer"));
				smsCampaign.setCampaignStatusTime(rs.getTime("campaignstatustime"));
				smsCampaign.setCampaignStatusDate(rs.getDate("campaignstatusdate"));
				smsCampaigns.add(smsCampaign);
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying table COMPANYSMSCAMPAIGN: " + e.toString());
		}
		return smsCampaigns;
	}
	
	public static ArrayList<CompanySmsCampaign> getSmsCampaignsByCompID(Connection connection, int compId){
		ArrayList<CompanySmsCampaign> smsCampaigns = new ArrayList<CompanySmsCampaign>();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM COMPANYSMSCAMPAIGN WHERE compid = ?");
		){
			statement.setInt(1, compId);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				CompanySmsCampaign smsCampaign = new CompanySmsCampaign();
				smsCampaign.setCompId(rs.getInt("compid"));
				smsCampaign.setCampaignId(rs.getInt("campaignid"));
				smsCampaign.setCampaignSendGroups(rs.getString("campaignsendgroups"));
				smsCampaign.setCampaignName(rs.getString("campaignname"));
				smsCampaign.setCampaignRecurrence(rs.getInt("campaignrecurrence"));
				smsCampaign.setCampaignFrequency(rs.getInt("campaignfrequency"));
				smsCampaign.setCampaignStart(rs.getDate("campaignstart"));
				smsCampaign.setCampaignEnd(rs.getDate("campaignend"));
				smsCampaign.setCampaignStatus(rs.getString("campaignstatus"));
				smsCampaign.setCampaignStatusUser(rs.getInt("campaignstatususer"));
				smsCampaign.setCampaignStatusTime(rs.getTime("campaignstatustime"));
				smsCampaign.setCampaignStatusDate(rs.getDate("campaignstatusdate"));
				smsCampaigns.add(smsCampaign);
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying table COMPANYSMSCAMPAIGN: " + e.toString());
		}
		return smsCampaigns;
	}
	
	public static ArrayList<CompanySmsCampaign> getAllCompSmsCampaigns(Connection connection, int compId){
		ArrayList<CompanySmsCampaign> smsCampaigns = new ArrayList<CompanySmsCampaign>();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM COMPANYSMSCAMPAIGN WHERE compid = ? ORDER BY campaignstart DESC, campaignid DESC, campaignname ASC, campaignstatus DESC");
		){
			statement.setInt(1, compId);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				CompanySmsCampaign smsCampaign = new CompanySmsCampaign();
				smsCampaign.setCompId(rs.getInt("compid"));
				smsCampaign.setCampaignId(rs.getInt("campaignid"));
				smsCampaign.setCampaignSendGroups(rs.getString("campaignsendgroups"));
				smsCampaign.setCampaignName(rs.getString("campaignname"));
				smsCampaign.setCampaignRecurrence(rs.getInt("campaignrecurrence"));
				smsCampaign.setCampaignFrequency(rs.getInt("campaignfrequency"));
				smsCampaign.setCampaignStart(rs.getDate("campaignstart"));
				smsCampaign.setCampaignEnd(rs.getDate("campaignend"));
				smsCampaign.setCampaignStatus(rs.getString("campaignstatus"));
				smsCampaign.setCampaignStatusUser(rs.getInt("campaignstatususer"));
				smsCampaign.setCampaignStatusTime(rs.getTime("campaignstatustime"));
				smsCampaign.setCampaignStatusDate(rs.getDate("campaignstatusdate"));
				smsCampaigns.add(smsCampaign);
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying table COMPANYSMSCAMPAIGN: " + e.toString());
		}
		return smsCampaigns;
	}
	
	//JSON HELPER METHODS
	public static StringBuilder getSmsCampaignJSON(CompanySmsCampaign smsCampaign) {
		StringBuilder sb = new StringBuilder();
		sb.append("{\n");
		sb.append("\"compid\" : "+smsCampaign.getCompId()+",\n");
		sb.append("\"campaignid\" : "+smsCampaign.getCampaignId()+",\n");
		sb.append("\"campaignsendgroups\" : \""+smsCampaign.getCampaignSendGroups()+"\",\n");
		sb.append("\"campaignname\" : \""+smsCampaign.getCampaignName()+"\",\n");
		sb.append("\"campaignrecurrence\" : "+smsCampaign.getCampaignRecurrence()+",\n");
		sb.append("\"campaignfrequency\" : "+smsCampaign.getCampaignFrequency()+",\n");
		sb.append("\"campaignstart\" : \""+smsCampaign.getCampaignStart()+"\",\n");
		sb.append("\"campaignend\" : \""+smsCampaign.getCampaignEnd()+"\",\n");
		sb.append("\"campaignstatus\" : \""+smsCampaign.getCampaignStatus()+"\",\n");
		sb.append("\"campaignstatususer\" : "+smsCampaign.getCampaignStatusUser()+",\n");
		sb.append("\"campaignstatustime\" : \""+smsCampaign.getCampaignStatusTime()+"\",\n");
		sb.append("\"campaignstatusdate\" : \""+smsCampaign.getCampaignStatusDate()+"\"\n");
		sb.append("}");
		return sb;
	}
	public static StringBuilder getSmsCampaignsJSON(ArrayList<CompanySmsCampaign> smsCampaigns) {
		StringBuilder sb = new StringBuilder();
		sb.append("[\n");
		for(int i=0;i<smsCampaigns.size();i++) {
			CompanySmsCampaign smsCampaign = smsCampaigns.get(i);
			sb.append("{\n");
			sb.append("\"compid\" : "+smsCampaign.getCompId()+",\n");
			sb.append("\"campaignid\" : "+smsCampaign.getCampaignId()+",\n");
			sb.append("\"campaignsendgroups\" : \""+smsCampaign.getCampaignSendGroups()+"\",\n");
			sb.append("\"campaignname\" : \""+smsCampaign.getCampaignName()+"\",\n");
			sb.append("\"campaignrecurrence\" : "+smsCampaign.getCampaignRecurrence()+",\n");
			sb.append("\"campaignfrequency\" : "+smsCampaign.getCampaignFrequency()+",\n");
			sb.append("\"campaignstart\" : \""+smsCampaign.getCampaignStart()+"\",\n");
			sb.append("\"campaignend\" : \""+smsCampaign.getCampaignEnd()+"\",\n");
			sb.append("\"campaignstatus\" : \""+smsCampaign.getCampaignStatus()+"\",\n");
			sb.append("\"campaignstatususer\" : "+smsCampaign.getCampaignStatusUser()+",\n");
			sb.append("\"campaignstatustime\" : \""+smsCampaign.getCampaignStatusTime()+"\",\n");
			sb.append("\"campaignstatusdate\" : \""+smsCampaign.getCampaignStatusDate()+"\"\n");
			sb.append("}" + (i == smsCampaigns.size()-1 ? "" : ",") + "\n");
		}
		sb.append("]");
		return sb;
	}
	
	public static CompanySmsCampaign getCompSmsCampaign(Connection connection, int campaigId, String campaignName){
		CompanySmsCampaign smsCampaign = new CompanySmsCampaign();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM COMPANYSMSCAMPAIGN WHERE campaignid = ? AND campaignname = ?");
		){
			statement.setInt(1, campaigId);
			statement.setString(2, campaignName);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				smsCampaign.setCompId(rs.getInt("compid"));
				smsCampaign.setCampaignId(rs.getInt("campaignid"));
				smsCampaign.setCampaignSendGroups(rs.getString("campaignsendgroups"));
				smsCampaign.setCampaignName(rs.getString("campaignname"));
				smsCampaign.setCampaignRecurrence(rs.getInt("campaignrecurrence"));
				smsCampaign.setCampaignFrequency(rs.getInt("campaignfrequency"));
				smsCampaign.setCampaignStart(rs.getDate("campaignstart"));
				smsCampaign.setCampaignEnd(rs.getDate("campaignend"));
				smsCampaign.setCampaignStatus(rs.getString("campaignstatus"));
				smsCampaign.setCampaignStatusUser(rs.getInt("campaignstatususer"));
				smsCampaign.setCampaignStatusTime(rs.getTime("campaignstatustime"));
				smsCampaign.setCampaignStatusDate(rs.getDate("campaignstatusdate"));
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying table COMPANYSMSCAMPAIGN: " + e.toString());
		}
		return smsCampaign;
	}
	public static StringBuilder getSmsCampaignGraphDataJSON(CompanySmsCampaign smsCampaign, int sent, StringBuilder delivered, int notdelivered, StringBuilder times) {
		StringBuilder sb = new StringBuilder();
			sb.append("{\n");
			sb.append("\"compid\" : "+smsCampaign.getCompId()+",\n");
			sb.append("\"campaignid\" : "+smsCampaign.getCampaignId()+",\n");
			sb.append("\"campaignname\" : \""+smsCampaign.getCampaignName()+"\",\n");
			sb.append("\"sentcampaigns\" : \""+sent+"\",\n");
			sb.append("\"deliveredcampaigns\" : \""+delivered+"\",\n");
			sb.append("\"deliveredtimes\" : \""+times+"\"\n");
			sb.append("}");
		return sb;
	}
}
