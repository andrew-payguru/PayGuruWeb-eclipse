package za.co.payguru.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;

import javax.naming.spi.DirStateFactory.Result;

import za.co.payguru.model.CompanySmsGroup;
import za.co.payguru.util.DateUtil;

public class CompanySmsGroupDao {
	public static long getNextGroupId(Connection connection) {
		long groupId = 0;
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT NEXTVAL('companysmsgroups_groupid_seq') AS groupid");
				){
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				groupId = rs.getLong("groupid");
			}rs.close();
		}catch (Exception e) {
			System.out.println("ERROR UPDATING COMPANYSMSGROUPS: " + e.toString());
		}
		return groupId;
	}

	public static CompanySmsGroup loadCompanySmsGroup(int compid, long groupid, Connection connection) {
		CompanySmsGroup companySmsGroup = new CompanySmsGroup();
		String query = "SELECT * FROM COMPANYSMSGROUPS WHERE compid = ? AND smsgroupid = ?";
		try(
				PreparedStatement statement = connection.prepareStatement(query);
		){
			statement.setInt(1, compid);
			statement.setLong(2, groupid);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				companySmsGroup.setCompId(rs.getInt("compid"));
				companySmsGroup.setSmsGroupId(rs.getLong("smsgroupid"));
				companySmsGroup.setSmsGroupName(rs.getString("smsgroupname"));
				companySmsGroup.setSmsGroupCreatedDate(rs.getDate("smsgroupcreateddate"));
				companySmsGroup.setSmsGroupCreatedTime(rs.getTime("smsgroupcreatedtime"));
				companySmsGroup.setSmsGroupActive(rs.getString("smsgroupactive"));
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error Loading CompanySmsGroups: " + e.toString());
		}
		return companySmsGroup;
	}
	
	public static boolean createSmsGroup(CompanySmsGroup companySmsGroup, Connection connection) {
		boolean done = false;
		String query = "INSERT INTO COMPANYSMSGROUPS VALUES (?,?,?,?,?,?)";
		try(
				PreparedStatement statement = connection.prepareStatement(query);
		){
			companySmsGroup.setSmsGroupId(getNextGroupId(connection));
			companySmsGroup.setSmsGroupCreatedDate(DateUtil.getCurrentCCYYMMDDDate());
			companySmsGroup.setSmsGroupCreatedTime(DateUtil.getCurrentHHMMSSTime());
			companySmsGroup.setSmsGroupActive(CompanySmsGroup.GROUP_ACTIVE);

			statement.setInt(1, companySmsGroup.getCompId());
			statement.setLong(2, companySmsGroup.getSmsGroupId());
			statement.setString(3, companySmsGroup.getSmsGroupName());
			statement.setDate(4, companySmsGroup.getSmsGroupCreatedDate());
			statement.setTime(5, companySmsGroup.getSmsGroupCreatedTime());
			statement.setString(6, companySmsGroup.getSmsGroupActive());
			if(statement.executeUpdate()>0)
				done = true;
			else 
				companySmsGroup = new CompanySmsGroup();
		}catch (Exception e) {
			System.out.println("Error creating COMPANYSMSGROUPS: " + e.toString());
		}
		return done;
	}

	public static ArrayList<CompanySmsGroup> getCompanySmsGroups(int compId, boolean inactive, Connection connection){
		ArrayList<CompanySmsGroup> groups = new ArrayList<CompanySmsGroup>();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM COMPANYSMSGROUPS WHERE compid = ? and smsgroupactive = ? ORDER BY smsgroupname");
				){
			statement.setInt(1, compId);
			statement.setString(2, (inactive ? CompanySmsGroup.GROUP_INACTIVE : CompanySmsGroup.GROUP_ACTIVE));
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				CompanySmsGroup group = new CompanySmsGroup();
				group.setCompId(rs.getInt("compid"));
				group.setSmsGroupId(rs.getLong("smsgroupid"));
				group.setSmsGroupName(rs.getString("smsgroupname"));
				group.setSmsGroupCreatedDate(rs.getDate("smsgroupcreateddate"));
				group.setSmsGroupCreatedTime(rs.getTime("smsgroupcreatedtime"));
				group.setSmsGroupActive(rs.getString("smsgroupactive"));
				groups.add(group);
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying COMPANYSMSGROUPS: " + e.toString());
		}
		return groups;
	}
	public static boolean updateSmsGroup(CompanySmsGroup smsGroup, Connection connection) {
		boolean updated = false;
		if(smsGroup==null)
			return updated;
		String sql = "UPDATE COMPANYSMSGROUPS SET smsgroupname = ?, smsgroupactive = ? WHERE compid = ? AND smsgroupid = ?";
		try(
				PreparedStatement statement = connection.prepareStatement(sql)
		){
			statement.setString(1, smsGroup.getSmsGroupName().toUpperCase());
			statement.setString(2, smsGroup.getSmsGroupActive());
			statement.setInt(3, smsGroup.getCompId());
			statement.setLong(4, smsGroup.getSmsGroupId());
			if(statement.executeUpdate()>0)
				updated = true;
		}catch(Exception e) {
			System.out.println("Error updating table COMPANYSMSGROUPS: " + e.toString());
		}
		return updated;
	}

	public static StringBuilder getCompanySmsGroupsJSON(ArrayList<CompanySmsGroup> companySmsGroups) {
		StringBuilder sb = new StringBuilder();
		sb.append("[\n");
		for(int i=0;i<companySmsGroups.size();i++) {
			CompanySmsGroup companySmsGroup = companySmsGroups.get(i);
			sb.append("{\n");
			sb.append("\"compid\" : "+companySmsGroup.getCompId()+",\n");
			sb.append("\"smsgroupid\" : "+companySmsGroup.getSmsGroupId()+",\n");
			sb.append("\"smsgroupname\" : \""+companySmsGroup.getSmsGroupName()+"\",\n");
			sb.append("\"smsgroupcreateddate\" : \""+companySmsGroup.getSmsGroupCreatedDate()+"\",\n");
			sb.append("\"smsgroupcreatedtime\" : \""+companySmsGroup.getSmsGroupCreatedTime()+"\",\n");
			sb.append("\"smsgroupactive\" : \""+companySmsGroup.getSmsGroupActive()+"\"\n");
			sb.append("}"+(i==companySmsGroups.size()-1 ? "" : ",")+"\n");
		}
		sb.append("]");
		return sb;
	}

	public static StringBuilder getCompanySmsGroupJSON(CompanySmsGroup companySmsGroup) {
		StringBuilder sb = new StringBuilder();
		sb.append("{\n");
		sb.append("\"compid\" : "+companySmsGroup.getCompId()+",\n");
		sb.append("\"smsgroupid\" : "+companySmsGroup.getSmsGroupId()+",\n");
		sb.append("\"smsgroupname\" : \""+companySmsGroup.getSmsGroupName()+"\",\n");
		sb.append("\"smsgroupcreateddate\" : \""+companySmsGroup.getSmsGroupCreatedDate()+"\",\n");
		sb.append("\"smsgroupcreatedtime\" : \""+companySmsGroup.getSmsGroupCreatedTime()+"\",\n");
		sb.append("\"smsgroupactive\" : \""+companySmsGroup.getSmsGroupActive()+"\"\n");
		sb.append("}");
		return sb;
	}



}
