package za.co.payguru.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.json.JSONArray;

import za.co.payguru.model.CompanyUser;


public class CompanyUserDao{
	public static int getNextUserId(Connection connection) {
		int id = 0;
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT NEXTVAL('companyusers_userid_seq') AS userid");
		){
			ResultSet rs = statement.executeQuery();
			if(rs.next())
				id = rs.getInt("userid");
			rs.close();
		}catch (Exception e) {
			System.out.println("Erorr getting next sequence: " + e.toString());
		}
		return id;
	}
	
	public static CompanyUser addCompanyUser(Connection connection, CompanyUser compUser) {
		try(
				PreparedStatement statement = connection.prepareStatement("INSERT INTO COMPANYUSERS VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
		){
			compUser.setUserId(getNextUserId(connection));
			compUser.setUserActive(CompanyUser.ACTIVE);
			statement.setInt(1, compUser.getCompId());
			statement.setInt(2, compUser.getUserId());
			statement.setString(3, compUser.getUserName());
			statement.setString(4, compUser.getUserSurname());
			statement.setString(5, compUser.getUserCellNo());
			statement.setString(6, compUser.getUserTelNo());
			statement.setString(7, compUser.getUserEmail());
			statement.setString(8, compUser.getUserPassword());
			statement.setString(9, compUser.getUserLoginDateTime());
			statement.setString(10, compUser.getUserActive());
			statement.setInt(11, compUser.getUserType());
			statement.setInt(12, compUser.getProfileId());
			statement.setString(13, compUser.getUserPermissions());
			if(statement.executeUpdate()<=0)
				compUser = new CompanyUser();
		}catch (Exception e) {
			System.out.println("Error creating COMPANYUSERS: " + e.toString());
			compUser = new CompanyUser();
		}
		return compUser;
	}
	
	public static boolean updateCompanyUser(Connection connection, CompanyUser compUser) {
		boolean updated = false;
		try(
				PreparedStatement statement = connection.prepareStatement("UPDATE COMPANYUSERS SET username = ?, usersurname = ?, usercellno = ?, usertelno = ?, useremail = ?, userlogindatetime = ?, useractive = ?, userpermissions = ? WHERE compid = ? AND userid = ?");
		){

			
			statement.setString(1, compUser.getUserName());
			statement.setString(2, compUser.getUserSurname());
			statement.setString(3, compUser.getUserCellNo());
			statement.setString(4, compUser.getUserTelNo());
			statement.setString(5, compUser.getUserEmail());
			statement.setString(6, compUser.getUserLoginDateTime());
			statement.setString(7, compUser.getUserActive());
			statement.setString(8, compUser.getUserPermissions());
			statement.setInt(9, compUser.getCompId());
			statement.setInt(10, compUser.getUserId());
			if(statement.executeUpdate()>0)
				updated = true;
		}catch (Exception e) {
			System.out.println("Error updating COMPANYUSERS: " + e.toString());
		}
		return updated;
	}

	public static CompanyUser getCompanyUserEmail(int compId, String userEmail,Connection connection) {
		CompanyUser companyUser = new CompanyUser();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM COMPANYUSERS WHERE compid = ? AND useremail = ? and useractive = ?");
		){
			statement.setInt(1, compId);
			statement.setString(2, userEmail);
			statement.setString(3, CompanyUser.ACTIVE);

			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				companyUser.setCompId(rs.getInt("compid"));
				companyUser.setUserId(rs.getInt("userid"));
				companyUser.setUserName(rs.getString("username"));
				companyUser.setUserSurname(rs.getString("userSurname"));
				companyUser.setUserCellNo(rs.getString("usercellno"));
				companyUser.setUserTelNo(rs.getString("usertelno"));
				companyUser.setUserEmail(rs.getString("useremail"));
				companyUser.setUserPassword(rs.getString("userpassword"));
				companyUser.setUserLoginDateTime(rs.getString("userlogindatetime"));
				companyUser.setUserActive(rs.getString("useractive"));
				companyUser.setUserType(rs.getInt("usertype"));
				companyUser.setProfileId(rs.getInt("profileid"));
				companyUser.setUserPermissions(rs.getString("userpermissions"));
			}rs.close();
		}catch(Exception e) {
			System.out.println("Error querying table COMPANYUSERS: " + e.toString());
		}
		return companyUser;
	}
	
	public static CompanyUser loadCompanyUser(int compId, int userId,Connection connection) {
		CompanyUser companyUser = new CompanyUser();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM COMPANYUSERS WHERE compid = ? AND userid = ? AND useractive = ?");
		){
			statement.setInt(1, compId);
			statement.setInt(2, userId);
			statement.setString(3, CompanyUser.ACTIVE);

			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				companyUser.setCompId(rs.getInt("compid"));
				companyUser.setUserId(rs.getInt("userid"));
				companyUser.setUserName(rs.getString("username"));
				companyUser.setUserSurname(rs.getString("userSurname"));
				companyUser.setUserCellNo(rs.getString("usercellno"));
				companyUser.setUserTelNo(rs.getString("usertelno"));
				companyUser.setUserEmail(rs.getString("useremail"));
				companyUser.setUserPassword(rs.getString("userpassword"));
				companyUser.setUserLoginDateTime(rs.getString("userlogindatetime"));
				companyUser.setUserActive(rs.getString("useractive"));
				companyUser.setUserType(rs.getInt("usertype"));
				companyUser.setProfileId(rs.getInt("profileid"));
				companyUser.setUserPermissions(rs.getString("userpermissions"));
			}rs.close();
		}catch(Exception e) {
			System.out.println("Error querying table COMPANYUSERS: " + e.toString());
		}
		return companyUser;
	}
	
	public static ArrayList<CompanyUser> getCompanyUsers(int compId, Connection connection) {
		ArrayList<CompanyUser> companyUsers = new ArrayList<CompanyUser>();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM COMPANYUSERS WHERE compid = ? AND useractive = ? ORDER BY usersurname, username");
		){
			statement.setInt(1, compId);
			statement.setString(2, CompanyUser.ACTIVE);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				CompanyUser companyUser = new CompanyUser();
				companyUser.setCompId(rs.getInt("compid"));
				companyUser.setUserId(rs.getInt("userid"));
				companyUser.setUserName(rs.getString("username"));
				companyUser.setUserSurname(rs.getString("userSurname"));
				companyUser.setUserCellNo(rs.getString("usercellno"));
				companyUser.setUserTelNo(rs.getString("usertelno"));
				companyUser.setUserEmail(rs.getString("useremail"));
				companyUser.setUserPassword(rs.getString("userpassword"));
				companyUser.setUserLoginDateTime(rs.getString("userlogindatetime"));
				companyUser.setUserActive(rs.getString("useractive"));
				companyUser.setUserType(rs.getInt("usertype"));
				companyUser.setProfileId(rs.getInt("profileid"));
				companyUser.setUserPermissions(rs.getString("userpermissions"));
				companyUsers.add(companyUser);
			}rs.close();
		}catch(Exception e) {
			System.out.println("Error querying table COMPANYUSERS: " + e.toString());
		}
		return companyUsers;
	}
	
	//JSON HELPER METHODS
	public static StringBuilder getJsonCompanyUser(CompanyUser companyUser) {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"compid\" : "+companyUser.getCompId()+",\n");
		sb.append("\"userid\" : "+companyUser.getUserId()+",\n");
		sb.append("\"username\" : \""+companyUser.getUserName()+"\",\n");
		sb.append("\"usersurname\" : \""+companyUser.getUserSurname()+"\",\n");
		sb.append("\"usercellno\" : \""+companyUser.getUserCellNo()+"\",\n");
		sb.append("\"usertelno\" : \""+companyUser.getUserTelNo()+"\",\n");
		sb.append("\"useremail\" : \""+companyUser.getUserEmail()+"\",\n");
		sb.append("\"userpassword\" : \""+companyUser.getUserPassword()+"\",\n");
		sb.append("\"userlogindatetime\" : \""+companyUser.getUserLoginDateTime()+"\",\n");
		sb.append("\"useractive\" : \""+companyUser.getUserActive()+"\",\n");
		sb.append("\"usertype\" : "+companyUser.getUserType()+",\n");
		sb.append("\"profileid\" : "+companyUser.getProfileId()+",\n");
		sb.append("\"userpermissions\" : \""+companyUser.getUserPermissions()+"\"\n");
		sb.append("}");
		return sb;
	}
	
	public static JSONArray getCompanyUsersJSON(ArrayList<CompanyUser> companyUsers) {
		JSONArray jsonAr = new JSONArray();
		try {
			for(int i=0;i<companyUsers.size();i++) {
				CompanyUser user = companyUsers.get(i);
				jsonAr.put(user.toJSON());
			}
		}catch (Exception e) {
			System.out.println("Error creating json: " + e.toString());
		}
		return jsonAr;
	}
}
