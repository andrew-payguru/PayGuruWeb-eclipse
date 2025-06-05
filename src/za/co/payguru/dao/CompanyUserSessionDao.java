package za.co.payguru.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import za.co.payguru.model.CompanyUserSession;
import za.co.payguru.util.DateUtil;
import za.co.payguru.util.Util;

public class CompanyUserSessionDao {

    public static long getNextSessionId(Connection connection) {
        long id = 0;
        try (
            PreparedStatement statement = connection.prepareStatement("SELECT NEXTVAL('companyusersessions_sessionid_seq') AS id");
        ) {
            ResultSet rs = statement.executeQuery();
            if (rs.next())
                id = rs.getLong("id");
            rs.close();
        } catch (Exception e) {
            System.out.println("Error getting next sessionid: " + e.toString());
        }
        return id;
    }
	
    
    public static CompanyUserSession addCompanyUserSession(Connection connection, CompanyUserSession compUserSession) {
    	compUserSession.setSessionid(getNextSessionId(connection));
    	compUserSession.setToken(Util.generateUUID());
    	try(
    		PreparedStatement statement = connection.prepareStatement("INSERT INTO COMPANYUSERSESSIONS VALUES (?,?,?,?,?,?,?,?,?,?,?)");
    	){
    		statement.setInt(1, compUserSession.getCompid());
    		statement.setLong(2, compUserSession.getSessionid());
    		statement.setInt(3, compUserSession.getUserid());
    		statement.setString(4, compUserSession.getToken());
    		statement.setDate(5, compUserSession.getCreatedate());
    		statement.setTime(6, compUserSession.getCreatetime());
    		statement.setInt(7, compUserSession.getStatus());
    		statement.setDate(8, compUserSession.getStatusdate());
    		statement.setTime(9, compUserSession.getStatustime());
    		statement.setString(10, compUserSession.getIpaddress());
    		statement.setLong(11, compUserSession.getExpiretimemillis());
    		
    		int res = statement.executeUpdate();
    		if(res<=0)
    			compUserSession = new CompanyUserSession();
    	}catch (Exception e) {
    		System.out.println("Error creating COMPANYUSERSESSION: " + e.toString());
			compUserSession = new CompanyUserSession();
		}
    	
    	return compUserSession;
    }
    
    
    public static CompanyUserSession loadUserSessionByToken(Connection connection, int compid, String token) {
    	CompanyUserSession userSession = new CompanyUserSession();
    	try(
    		PreparedStatement statement = connection.prepareStatement("SELECT * FROM COMPANYUSERSESSIONS WHERE compid = ? AND token = ? and status = ?");
    	){
    		statement.setInt(1, compid);
    		statement.setString(2, token);
    		statement.setInt(3, CompanyUserSession.STATUS_ACTIVE);
    		ResultSet rs = statement.executeQuery();
    		if(rs.next()) {
    			userSession.setCompid(rs.getInt("compid"));
    			userSession.setSessionid(rs.getLong("sessionid"));
    			userSession.setUserid(rs.getInt("userid"));
    			userSession.setToken(rs.getString("token"));
    			userSession.setCreatedate(rs.getDate("createdate"));
    			userSession.setCreatetime(rs.getTime("createtime"));
    			userSession.setStatus(rs.getInt("status"));
    			userSession.setStatusdate(rs.getDate("statusdate"));
    			userSession.setStatustime(rs.getTime("statustime"));
    			userSession.setIpaddress(rs.getString("ipaddress"));
    			userSession.setExpiretimemillis(rs.getLong("expiretimemillis"));
    		}rs.close();
    	}catch (Exception e) {
    		System.out.println("Error querying table COMPANYUSERSESSIONS: " + e.toString());
		}
    	return userSession;
    }
    
    public static boolean deactivateUserSessions(Connection connection, int compid, int userid) {
    	boolean updated = false;
    	try(
    		PreparedStatement statement = connection.prepareStatement("UPDATE COMPANYUSERSESSIONS SET status = ?, statusdate = ?, statustime = ? WHERE compid = ? AND userid = ? AND status = ?");
    	){
    		statement.setInt(1,	CompanyUserSession.STATUS_INACTIVE);
    		statement.setDate(2, DateUtil.getCurrentCCYYMMDDDate());
    		statement.setTime(3, DateUtil.getCurrentHHMMSSTime());
    		statement.setInt(4, compid);
    		statement.setInt(5, userid);
    		statement.setInt(6, CompanyUserSession.STATUS_ACTIVE);
    		
    		updated = (statement.executeUpdate() > 0);
    	}catch (Exception e) {
    		System.out.println("Error updating table COMPANYUSERSESSIONS: " + e.toString());
		}
    	return updated;
    }
    
    public static boolean deactivateUserSessionsToken(Connection connection, int compid, String token) {
    	boolean updated = false;
    	try(
    		PreparedStatement statement = connection.prepareStatement("UPDATE COMPANYUSERSESSIONS SET status = ?, statusdate = ?, statustime = ? WHERE compid = ? AND token = ? AND status = ?");
    	){
    		statement.setInt(1,	CompanyUserSession.STATUS_INACTIVE);
    		statement.setDate(2, DateUtil.getCurrentCCYYMMDDDate());
    		statement.setTime(3, DateUtil.getCurrentHHMMSSTime());
    		statement.setInt(4, compid);
    		statement.setString(5, token);
    		statement.setInt(6, CompanyUserSession.STATUS_ACTIVE);
    		
    		updated = (statement.executeUpdate() > 0);
    	}catch (Exception e) {
    		System.out.println("Error updating table COMPANYUSERSESSIONS: " + e.toString());
		}
    	return updated;
    }
}
