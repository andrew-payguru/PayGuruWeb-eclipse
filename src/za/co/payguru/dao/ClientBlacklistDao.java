package za.co.payguru.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import za.co.payguru.model.Client;
import za.co.payguru.model.ClientBlacklist;

public class ClientBlacklistDao {
	public static ArrayList<ClientBlacklist> searchBlacklistedClientsLike (Connection connection, int compid, String cellNo){
		ArrayList<ClientBlacklist> blacklistClients = new ArrayList<ClientBlacklist>();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENTBLACKLIST WHERE compid = ? AND clientid IN "
						+ "(SELECT clientid FROM CLIENTS WHERE clientcellno like ?) AND active = ?");
				){
			statement.setInt(1, compid);
			statement.setString(2, "%"+cellNo+"%");
			statement.setString(3, ClientBlacklist.BLACKLIST_ACTIVE);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				ClientBlacklist clientBlacklist = new ClientBlacklist();
				clientBlacklist.setCompId(rs.getInt("compid"));
				clientBlacklist.setClientId(rs.getInt("clientid"));
				clientBlacklist.setProdId(rs.getInt("prodid"));
				clientBlacklist.setActive(rs.getString("active"));
				blacklistClients.add(clientBlacklist);
			}rs.close();
		}catch(Exception e) {
			System.out.println("Error querying table CLIENTBLACKLIST: " + e.toString());
		}
		return blacklistClients;
	}

	public static boolean addClientBlacklist(Connection connection, int compid, int clientid, int prodid) {
		boolean created = false;
		try(
				PreparedStatement statement = connection.prepareStatement("INSERT INTO CLIENTBLACKLIST VALUES (?,?,?,?)");
				){
			statement.setInt(1, compid);
			statement.setInt(2, clientid);
			statement.setInt(3, prodid);
			statement.setString(4, "1");
			if(statement.executeUpdate()>0)
				created = true;
		}catch (Exception e) {
			System.out.println("Error creating ClientBlacklist: " + e.toString());
		}
		return created;
	}
	public static boolean deleteClientBlacklist(Connection connection, int compid, int clientid, int prodid) {
		boolean deleted = false;
		try(
				PreparedStatement statement = connection.prepareStatement("DELETE FROM CLIENTBLACKLIST WHERE compid = ? AND clientid = ? AND prodid = ?");
				){
			statement.setInt(1, compid);
			statement.setInt(2, clientid);
			statement.setInt(3, prodid);
			if(statement.executeUpdate()>0)
				deleted = true;
		}catch (Exception e) {
			System.out.println("Error deleting ClientBlacklist: " + e.toString());
		}
		return deleted;
	}
	public static ArrayList<Integer> getClientBlacklistProdIds(Connection connection, int compId, int clientId){
		ArrayList<Integer> blacklistProdIds = new ArrayList<Integer>();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT prodid FROM CLIENTBLACKLIST WHERE compid = ? AND clientid = ?");
				){
			statement.setInt(1, compId);
			statement.setInt(2, clientId);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				blacklistProdIds.add(rs.getInt("prodid"));
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying CLIENTBLACKLIST: " + e.toString());
		}
		return blacklistProdIds;
	}

	public static ArrayList<Integer> updateClientBlacklist(Connection connection, int compId, int clientId, ArrayList<Integer> newBlacklistedProds){
		ArrayList<Integer> blacklistedProds = getClientBlacklistProdIds(connection, compId, clientId);
	for(int i=0;i<newBlacklistedProds.size();i++) {
			if(blacklistedProds.indexOf(newBlacklistedProds.get(i))>=0 || newBlacklistedProds.get(i)==0) 
				continue;
			addClientBlacklist(connection, compId, clientId, newBlacklistedProds.get(i));
		}
		for(int i=0;i<blacklistedProds.size();i++) {
			if(newBlacklistedProds.indexOf(blacklistedProds.get(i))>=0)
				continue;
			deleteClientBlacklist(connection, compId, clientId, blacklistedProds.get(i));
		}
		
		return newBlacklistedProds;
	}

	public static StringBuilder getBlacklistClientsJson(ArrayList<ClientBlacklist> blacklistedClients) {

		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(int i=0;i<blacklistedClients.size();i++) {
			ClientBlacklist blackClient = blacklistedClients.get(i);
			sb.append((i==0 ? "" : ",") + blackClient.toJsonString());
		}
		sb.append("]");
		return sb;
	}
}
