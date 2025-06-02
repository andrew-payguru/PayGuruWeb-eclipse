package za.co.payguru.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;

import za.co.payguru.model.BlockedIP;
import za.co.payguru.util.DBUtil;
import za.co.payguru.util.DateUtil;

public class BlockedIPDao extends TemplateDao{

	public static boolean blockIpAddr(String ipAddr, String blockReason, Connection connection) {
		boolean ipBlocked = false;
		try(
				PreparedStatement statement = connection.prepareStatement("INSERT INTO BLOCKEDIPS VALUES (?,?,?,?,?)");
		){
			Date blockDate = DateUtil.getCurrentCCYYMMDDDate();
			Time blockTime = DateUtil.getCurrentHHMMSSTime();
			statement.setString(1, ipAddr);
			statement.setDate(2, blockDate);
			statement.setTime(3, blockTime);
			statement.setString(4, blockReason);
			statement.setInt(5, BlockedIP.BLOCK_ACTIVE);
			if(statement.executeUpdate()>0)
				ipBlocked = true;
		}catch (Exception e) {
			System.out.println("Error INSERTING BLOCKEDIPS: " + e.toString());
		}
		return ipBlocked;
	}
	public static boolean updateBlockedIpAddr(String ipAddr, boolean block, Connection connection) {
		boolean updated = false;
		try(
				PreparedStatement statement = connection.prepareStatement("UPDATE BLOCKEDIPS SET blockactive = ? WHERE ipaddr = ?");
		){
			statement.setInt(1, (block ? BlockedIP.BLOCK_ACTIVE : BlockedIP.BLOCK_INACTIVE));
			statement.setString(2, ipAddr);
			if(statement.executeUpdate()>0)
				updated= true;
		}catch (Exception e) {
			System.out.println("Error INSERTING BLOCKEDIPS: " + e.toString());
		}
		return updated;
	}
	public static boolean getBlockedIp(String ipAddr, boolean active, Connection connection) {
		boolean isBlocked = false;
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM BLOCKEDIPS WHERE ipaddr = ? AND blockactive = ?");
		){
			statement.setString(1, ipAddr);
			statement.setInt(2, (active ? BlockedIP.BLOCK_ACTIVE : BlockedIP.BLOCK_INACTIVE));
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				isBlocked = true;
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error QUERYING BLOCKEDIPS: " + e.toString());
		}
		return isBlocked;
	}
	
}
