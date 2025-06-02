package za.co.payguru.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import za.co.payguru.model.Client;
import za.co.payguru.model.CompanyClient;
import za.co.payguru.util.DateUtil;

public class CompanyClientDao {
	
	
	
	public static boolean createCompanyClient(Connection connection, int compid, Client client) {
		boolean created = false;
		try(
				PreparedStatement statement = connection.prepareStatement("INSERT INTO COMPANYCLIENTS VALUES (?,?,?,?,?,?,?,?)");
		){
			statement.setInt(1, compid);
			statement.setInt(2, client.getClientid());
			statement.setString(3, DateUtil.getCurrentCCYYMMDDStr());
			statement.setString(4, DateUtil.getCurrentHHMMSSStr());
			statement.setString(5, CompanyClient.COMP_CLIENT_ACTIVE);
			statement.setString(6, DateUtil.getCurrentCCYYMMDDStr());
			statement.setString(7, DateUtil.getCurrentHHMMSSStr());
			statement.setString(8, (client.getClientname()+"_"+client.getClientsurname()+"_"+client.getClientid()).toUpperCase());
			if(statement.executeUpdate()>0)
				created = true;
		}catch (Exception e) {
			System.out.println("Error creating COMPANYCLIENT: " + e.toString());
		}
		return created;
	}
	public static boolean createCompanyClient(Connection connection, CompanyClient companyClient) {
		boolean created = false;
		try(
				PreparedStatement statement = connection.prepareStatement("INSERT INTO COMPANYCLIENTS VALUES (?,?,?,?,?,?,?,?)");
		){
			statement.setInt(1, companyClient.getCompId());
			statement.setInt(2, companyClient.getClientId());
			statement.setString(3, DateUtil.getCurrentCCYYMMDDStr());
			statement.setString(4, DateUtil.getCurrentHHMMSSStr());
			statement.setString(5, CompanyClient.COMP_CLIENT_ACTIVE);
			statement.setString(6, DateUtil.getCurrentCCYYMMDDStr());
			statement.setString(7, DateUtil.getCurrentHHMMSSStr());
			statement.setString(8, companyClient.getClientExtRef());
			if(statement.executeUpdate()>0)
				created = true;
		}catch (Exception e) {
			System.out.println("Error creating COMPANYCLIENT: " + e.toString());
		}
		return created;
	}
	public static CompanyClient loadCompanyClient(Connection connection, int compid, int clientid) {
		CompanyClient companyClient = new CompanyClient();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM COMPANYCLIENTS WHERE compid = ? AND clientid = ?");
		){
			statement.setInt(1, compid);
			statement.setInt(2, clientid);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				companyClient.setCompId(rs.getInt("compid"));
				companyClient.setClientId(rs.getInt("clientid"));
				companyClient.setCreateDate(rs.getString("createdate"));
				companyClient.setCreateTime(rs.getString("createtime"));
				companyClient.setStatus(rs.getString("status"));
				companyClient.setStatusDate(rs.getString("statusdate"));
				companyClient.setStatusTime(rs.getString("statustime"));
				companyClient.setClientExtRef(rs.getString("clientextref"));
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying table COMPANYCLIENTS: " + e.toString());
		}
		return companyClient;
	}
	
	public static ArrayList<Integer> loadClientCompIds(Connection connection, int clientid) {
		ArrayList<Integer> compIds = new ArrayList<Integer>();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT compid FROM COMPANYCLIENTS WHERE clientid = ?");
		){
			statement.setInt(1, clientid);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("compid");
				compIds.add(id);
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying table COMPANYCLIENTS: " + e.toString());
		}
		return compIds;
	}

	public static String loadExtRef(Connection connection, int compid, int clientid) {
		String extref = "";
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT clientextref FROM COMPANYCLIENTS WHERE compid = ? AND clientid = ?");
		){
			statement.setInt(1, compid);
			statement.setInt(2, clientid);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				extref = rs.getString("clientextref");
			}
		}catch (Exception e) {
			System.out.println("Error querying table COMPANYCLIENTS: " + e.toString());
		}
		return extref;
	}
	
	public static CompanyClient updateCompanyClientBasic(Connection connection, CompanyClient companyClient) {
		try(
				PreparedStatement statement = connection.prepareStatement("UPDATE COMPANYCLIENTS SET status = ?, statusdate = ?, statustime = ?, "
						+ "clientextref = ? WHERE compid = ? AND clientid = ?");
		){
			statement.setString(1, companyClient.getStatus());
			statement.setString(2, DateUtil.getCurrentCCYYMMDDStr());
			statement.setString(3, DateUtil.getCurrentHHMMSSStr());
			statement.setString(4, companyClient.getClientExtRef());
			statement.setInt(5, companyClient.getCompId());
			statement.setInt(6, companyClient.getClientId());
			if(statement.executeUpdate()<=0)
				companyClient = new CompanyClient();
		}catch (Exception e) {
			System.out.println("Error updating COMPANYCLIENTS: " + e.toString());
		}
		return companyClient;
	}
	public static int totalClientsInComp(Connection connection, int compid) {
		int total = 0;
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT COUNT(DISTINCT clientid) FROM COMPANYCLIENTS WHERE compid = ?");
		){
			statement.setInt(1, compid);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				total = rs.getInt("COUNT");
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying table COMPANYCLIENTS: " + e.toString());
		}
		return total;
	}
}
