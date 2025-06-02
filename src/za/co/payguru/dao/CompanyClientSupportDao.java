package za.co.payguru.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import za.co.payguru.model.CompanyClientSupport;
import za.co.payguru.util.DateUtil;

public class CompanyClientSupportDao {

	public static long getNextSupportId(Connection connection) {
		long id = 0;
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT NEXTVAL('companyclientsupport_supportid_seq') AS supportid");
		){
			ResultSet rs = statement.executeQuery();
			if(rs.next()) 
				id = rs.getLong("supportid");
			rs.close();
		}catch (Exception e) {
			System.out.println("Error Loading Next Sequence [companyclientsupport_supportid_seq]: " + e.toString());
		}
		return id;
	}
	
	public static CompanyClientSupport createCompanyClientSupport(Connection connection, CompanyClientSupport compClientSupport) {
		compClientSupport.setSupportid(getNextSupportId(connection));
		compClientSupport.setCreatedate(DateUtil.getCurrentCCYYMMDDDate());
		compClientSupport.setCreatetime(DateUtil.getCurrentHHMMSSTime());
		compClientSupport.setSupportstatus(CompanyClientSupport.COMP_CLI_SUPPORT_ACTIVE);
		compClientSupport.setSupportstatusdate(DateUtil.getCurrentCCYYMMDDDate());
		compClientSupport.setSupportstatustime(DateUtil.getCurrentHHMMSSTime());
		compClientSupport.setSupportactive(CompanyClientSupport.COMP_CLI_SUPPORT_ACTIVE);
		try(
				PreparedStatement statement = connection.prepareStatement("INSERT INTO COMPANYCLIENTSUPPORT VALUES (?,?,?,?,?,?,?,?,?,?)");
		){
			statement.setLong(1, compClientSupport.getSupportid());
			statement.setInt(2, compClientSupport.getSupportgroupid());
			statement.setDate(3, compClientSupport.getCreatedate());
			statement.setTime(4, compClientSupport.getCreatetime());
			statement.setString(5, compClientSupport.getSupportmessage());
			statement.setString(6, compClientSupport.getSupportstatus());
			statement.setDate(7, compClientSupport.getSupportstatusdate());
			statement.setTime(8, compClientSupport.getSupportstatustime());
			statement.setInt(9, compClientSupport.getSupporttype());
			statement.setString(10, compClientSupport.getSupportactive());
			if(statement.executeUpdate()<=0)
				compClientSupport = new CompanyClientSupport();
		}catch (Exception e) {
			System.out.println("Error creating record in COMPANYCLIENTSUPPORT: " + e.toString());
		}
		return compClientSupport;
	}
	public static boolean updateCompClientSupport(Connection connection, CompanyClientSupport clientSupport) {
		boolean updated = false;
		try(
				PreparedStatement statement = connection.prepareStatement("UPDATE COMPANYCLIENTSUPPORT SET supportmessage = ?, supportstatus = ?, supportstatusdate = ?, supportstatustime = ? WHERE supportid = ?");
		){
			statement.setString(1, clientSupport.getSupportmessage());
			statement.setString(2, clientSupport.getSupportstatus());
			statement.setDate(3, DateUtil.getCurrentCCYYMMDDDate());
			statement.setTime(4, DateUtil.getCurrentHHMMSSTime());
			statement.setLong(5, clientSupport.getSupportid());
			if(statement.executeUpdate()>0)
				updated = true;
		}catch (Exception e) {
			System.out.println("Error updating COMPANYCLIENTSUPPORT: " + e.toString());
		}
		return updated;
	}
	public static CompanyClientSupport loadCompClientSupport(Connection connection, int supportid){
		CompanyClientSupport clientSupport = new CompanyClientSupport();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM COMPANYCLIENTSUPPORT WHERE supportid = ?");
		){
			statement.setInt(1, supportid);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				clientSupport.setSupportid(rs.getLong("supportid"));
				clientSupport.setSupportgroupid(rs.getInt("supportgroupid"));
				clientSupport.setCreatedate(rs.getDate("createdate"));
				clientSupport.setCreatetime(rs.getTime("createtime"));
				clientSupport.setSupportmessage(rs.getString("supportmessage"));
				clientSupport.setSupportstatus(rs.getString("supportstatus"));
				clientSupport.setSupportstatusdate(rs.getDate("supportstatusdate"));
				clientSupport.setSupportstatustime(rs.getTime("supportstatustime"));
				clientSupport.setSupporttype(rs.getInt("supporttype"));
				clientSupport.setSupportactive(rs.getString("supportactive"));
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying table COMPANYCLIENTSUPPORT: " + e.toString());
		}
		return clientSupport;
	}
	public static ArrayList<CompanyClientSupport> getGroupCompanyClientSupport(Connection connection, int groupid){
		ArrayList<CompanyClientSupport> clientsSupport = new ArrayList<CompanyClientSupport>();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM COMPANYCLIENTSUPPORT WHERE supportgroupid = ? ORDER BY supportid asc");
		){

			statement.setInt(1, groupid);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				CompanyClientSupport compCliSupport = new CompanyClientSupport();
				compCliSupport.setSupportid(rs.getLong("supportid"));
				compCliSupport.setSupportgroupid(rs.getInt("supportgroupid"));
				compCliSupport.setCreatedate(rs.getDate("createdate"));
				compCliSupport.setCreatetime(rs.getTime("createtime"));
				compCliSupport.setSupportmessage(rs.getString("supportmessage"));
				compCliSupport.setSupportstatus(rs.getString("supportstatus"));
				compCliSupport.setSupportstatusdate(rs.getDate("supportstatusdate"));
				compCliSupport.setSupportstatustime(rs.getTime("supportstatustime"));
				compCliSupport.setSupporttype(rs.getInt("supporttype"));
				compCliSupport.setSupportactive(rs.getString("supportactive"));
				clientsSupport.add(compCliSupport);
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying table COMPANYCLIENTSUPPORT: " + e.toString());
		}
		return clientsSupport;
	}
	
	public static StringBuilder getCompanyClientsSupportJson(ArrayList<CompanyClientSupport> clientsSupport) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(int i=0;i<clientsSupport.size();i++) {
			CompanyClientSupport support = clientsSupport.get(i);
			sb.append((i==0?"":",")+support.toJsonString());
		}
		sb.append("]");
		return sb;
	}
}
