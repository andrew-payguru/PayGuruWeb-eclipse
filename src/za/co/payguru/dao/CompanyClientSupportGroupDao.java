package za.co.payguru.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import za.co.payguru.model.CompanyClientSupportGroup;
import za.co.payguru.util.DateUtil;

public class CompanyClientSupportGroupDao {

	public static int getNextGroupId(Connection connection) {
		int groupid = 0;
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT NEXTVAL('companyclientsupportgroup_supportgroupid_seq') AS supportgroupid");
		){
			ResultSet rs = statement.executeQuery();
			if(rs.next())
				groupid = rs.getInt("supportgroupid");
			rs.close();
		}catch (Exception e) {
			System.out.println("Error getting next supportgroupid: " +e.toString());
		}
		return groupid;
	}
	public static CompanyClientSupportGroup createSupportGroup(Connection connection, CompanyClientSupportGroup supportGroup) {
		supportGroup.setSupportgroupid(getNextGroupId(connection));
		supportGroup.setSupportgroupcreatedate(DateUtil.getCurrentCCYYMMDDDate());
		supportGroup.setSupportgroupstatus(CompanyClientSupportGroup.SUPPORT_GROUP_READ);
		supportGroup.setSupportgroupstatusdate(DateUtil.getCurrentCCYYMMDDDate());
		supportGroup.setSupportgroupstatustime(DateUtil.getCurrentHHMMSSTime());
		supportGroup.setSupportgroupactive(CompanyClientSupportGroup.SUPPORT_GROUP_ACTIVE);
		try(
				PreparedStatement statement = connection.prepareStatement("INSERT INTO COMPANYCLIENTSUPPORTGROUP VALUES (?,?,?,?,?,?,?,?,?,?)");
		){
			statement.setInt(1, supportGroup.getSupportgroupid());
			statement.setInt(2, supportGroup.getSupportgroupcompid());
			statement.setInt(3, supportGroup.getSupportgroupclientid());
			statement.setInt(4, supportGroup.getSupportgroupuserid());
			statement.setDate(5, supportGroup.getSupportgroupcreatedate());
			statement.setString(6, supportGroup.getSupportgroupdesc());
			statement.setString(7, supportGroup.getSupportgroupstatus());
			statement.setDate(8, supportGroup.getSupportgroupstatusdate());
			statement.setTime(9, supportGroup.getSupportgroupstatustime());
			statement.setString(10, supportGroup.getSupportgroupactive());
			if(statement.executeUpdate()<=0)
				supportGroup = new CompanyClientSupportGroup();
		}catch (Exception e) {
			System.out.println("Error creating COMPANYCLIENTSUPPORTGROUP: " + e.toString());
		}
		return supportGroup;
	}
	public static boolean updateSupportGroup(Connection connection, CompanyClientSupportGroup clientSupportGroup) {
		boolean updated = false;
		try(
				PreparedStatement statement = connection.prepareStatement("UPDATE COMPANYCLIENTSUPPORTGROUP SET supportgroupuserid = ?, supportgroupdesc = ?, supportgroupstatus = ?, supportgroupstatusdate = ?, supportgroupstatustime = ?, supportgroupactive = ? WHERE supportgroupid = ?");
		){
			statement.setString(1, clientSupportGroup.getSupportgroupdesc());
			statement.setInt(2, clientSupportGroup.getSupportgroupuserid());
			statement.setString(3, clientSupportGroup.getSupportgroupstatus());
			statement.setDate(4, DateUtil.getCurrentCCYYMMDDDate());
			statement.setTime(5, DateUtil.getCurrentHHMMSSTime());
			statement.setString(6, clientSupportGroup.getSupportgroupactive());
			statement.setInt(7, clientSupportGroup.getSupportgroupid());
			if(statement.executeUpdate()>0)
				updated = true;
		}catch (Exception e) {
			System.out.println("Error updating COMPANYCLIENTSUPPORTGROUP: " + e.toString());
		}
		return updated;
	}
	public static CompanyClientSupportGroup loadSupportGroup(Connection connection, int supportgroupid) {
		CompanyClientSupportGroup supportGroup = new CompanyClientSupportGroup();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM COMPANYCLIENTSUPPORTGROUP WHERE supportgroupid = ?");
		){
			statement.setInt(1, supportgroupid);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				supportGroup.setSupportgroupid(rs.getInt("supportgroupid"));
				supportGroup.setSupportgroupcompid(rs.getInt("supportgroupcompid"));
				supportGroup.setSupportgroupclientid(rs.getInt("supportgroupclientid"));
				supportGroup.setSupportgroupuserid(rs.getInt("supportgroupuserid"));
				supportGroup.setSupportgroupcreatedate(rs.getDate("supportgroupcreatedate"));
				supportGroup.setSupportgroupdesc(rs.getString("supportgroupdesc"));
				supportGroup.setSupportgroupstatus(rs.getString("supportgroupstatus"));
				supportGroup.setSupportgroupstatusdate(rs.getDate("supportgroupstatusdate"));
				supportGroup.setSupportgroupstatustime(rs.getTime("supportgroupstatustime"));
				supportGroup.setSupportgroupactive(rs.getString("supportgroupactive"));
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying table COMPANYCLIENTSUPPORTGROUP: " + e.toString());
		}
		return supportGroup;
	}
	
	public static ArrayList<CompanyClientSupportGroup> loadCompanyClientSupportGroups(Connection connection, int compid, int clientid) {
		ArrayList<CompanyClientSupportGroup> groups = new ArrayList<CompanyClientSupportGroup>();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM COMPANYCLIENTSUPPORTGROUP WHERE supportgroupcompid = ? AND supportgroupclientid = ? and supportgroupactive = ? ORDER BY supportgroupstatus DESC, supportgroupstatusdate DESC, supportgroupstatustime DESC, supportgroupcreatedate DESC");
		){
			statement.setInt(1, compid);
			statement.setInt(2, clientid);
			statement.setString(3, CompanyClientSupportGroup.SUPPORT_GROUP_ACTIVE);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				CompanyClientSupportGroup supportGroup = new CompanyClientSupportGroup();
				supportGroup.setSupportgroupid(rs.getInt("supportgroupid"));
				supportGroup.setSupportgroupcompid(rs.getInt("supportgroupcompid"));
				supportGroup.setSupportgroupclientid(rs.getInt("supportgroupclientid"));
				supportGroup.setSupportgroupuserid(rs.getInt("supportgroupuserid"));
				supportGroup.setSupportgroupcreatedate(rs.getDate("supportgroupcreatedate"));
				supportGroup.setSupportgroupdesc(rs.getString("supportgroupdesc"));
				supportGroup.setSupportgroupstatus(rs.getString("supportgroupstatus"));
				supportGroup.setSupportgroupstatusdate(rs.getDate("supportgroupstatusdate"));
				supportGroup.setSupportgroupstatustime(rs.getTime("supportgroupstatustime"));
				supportGroup.setSupportgroupactive(rs.getString("supportgroupactive"));
				groups.add(supportGroup);
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying table COMPANYCLIENTSUPPORTGROUP: " + e.toString());
		}
		return groups;
	}
	
	public static StringBuilder getCompClientSupportGroupsJson(ArrayList<CompanyClientSupportGroup> groups) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(int i=0;i<groups.size();i++) {
			CompanyClientSupportGroup group = groups.get(i);
			sb.append((i==0?"":",")+group.toJsonString());
		}
		sb.append("]");
		return sb;
	}
}
