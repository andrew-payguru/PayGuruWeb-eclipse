package za.co.payguru.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import za.co.payguru.model.ClientInvoice;
import za.co.payguru.model.ProdRefBlacklist;

public class ProdRefBlacklistDao {

	public static	boolean uploadProdRefBlacklist(Connection connection, ProdRefBlacklist prodRefBlacklist) {
		boolean uploaded = false;
		try(
				PreparedStatement statement = connection.prepareStatement("INSERT INTO PRODREFBLACKLIST VALUES (?,?,?,?,?,?,?)");
		){
			statement.setInt(1, prodRefBlacklist.getCompid());
			statement.setString(2, prodRefBlacklist.getProdref());
			statement.setInt(3, prodRefBlacklist.getProdid());
			statement.setString(4, ProdRefBlacklist.ACTIVE);
			statement.setString(5, prodRefBlacklist.getReason());
			statement.setString(6, prodRefBlacklist.getBlacklistdate());
			statement.setString(7, prodRefBlacklist.getBlacklisttime());
			if(statement.executeUpdate()>0)
				uploaded = true;
		}catch (Exception e) {
			System.out.println("Error Updating table PRODREFBLACKLIST:" + e.toString());
		}
		return uploaded;
	}
	
	public static ArrayList<ProdRefBlacklist> loadProdRefBlacklists(Connection connection, int compid, String prodref) {
		ArrayList<ProdRefBlacklist> prodRefBlacklists = new ArrayList<ProdRefBlacklist>();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM PRODREFBLACKLIST WHERE compid = ? AND prodref ILIKE ? AND active = ?");
		){
			statement.setInt(1, compid);
			statement.setString(2, "%"+prodref+"%");
			statement.setString(3, ProdRefBlacklist.ACTIVE);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				ProdRefBlacklist blacklist = new ProdRefBlacklist();
				blacklist.setCompid(rs.getInt("compid"));
				blacklist.setProdref(rs.getString("prodref"));
				blacklist.setProdid(rs.getInt("prodid"));
				blacklist.setActive(rs.getString("active"));
				blacklist.setReason(rs.getString("reason"));
				blacklist.setBlacklistdate(rs.getString("blacklistdate"));
				blacklist.setBlacklisttime(rs.getString("blacklisttime"));
				prodRefBlacklists.add(blacklist);
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying table PRODREFBLACKLIST: " + e.toString());
		}
		return prodRefBlacklists;
	}
	
	public static	boolean deleteProdRefBlacklist(Connection connection, ProdRefBlacklist prodRefBlacklist) {
		boolean deleted = false;
		try(
				PreparedStatement statement = connection.prepareStatement("DELETE FROM PRODREFBLACKLIST WHERE compid = ? AND prodref = ? AND prodid = ?");
		){
			statement.setInt(1, prodRefBlacklist.getCompid());
			statement.setString(2, prodRefBlacklist.getProdref());
			statement.setInt(3, prodRefBlacklist.getProdid());
			if(statement.executeUpdate()>0)
				deleted = true;
		}catch (Exception e) {
			System.out.println("Error Deleting from table PRODREFBLACKLIST:" + e.toString());
		}
		return deleted;
	}
	
	public static StringBuilder getProdRefBlacklistJson(ArrayList<ProdRefBlacklist> prodRefBlacklist) {
		StringBuilder sb = new StringBuilder();
		sb.append("[\n");
		for(int i=0;i<prodRefBlacklist.size();i++) {
			ProdRefBlacklist prodRefBlacklistItem = prodRefBlacklist.get(i);
			sb.append(prodRefBlacklistItem.toJsonString() + (i==prodRefBlacklist.size()-1 ? "" : ","));
		}
		sb.append("]");
		return sb;
	}
}
