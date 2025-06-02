package za.co.payguru.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.json.JSONArray;

import za.co.payguru.model.ClientBalance;

public class ClientBalanceDao {

	public static ArrayList<ClientBalance> getClientBalances(Connection connection, int clientid){
		ArrayList<ClientBalance> clientBalances = new ArrayList<ClientBalance>();
		try(
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENTBALANCES WHERE clientid = ?");
		){
			statement.setInt(1, clientid);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				ClientBalance clientBalance = new ClientBalance();
				clientBalance.setClientId(rs.getInt("clientid"));
				clientBalance.setClientBal(rs.getDouble("clientbal"));
				clientBalance.setSubacc(rs.getInt("subacc"));
				clientBalances.add(clientBalance);
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying CLIENTBALANCES: " + e.toString());
		}
		return clientBalances;
	}
	
	public static ArrayList<ClientBalance> getCompanyClientBalances(Connection connection, int compid, String cellno){
		ArrayList<ClientBalance> clientBalances = new ArrayList<ClientBalance>();
		try(
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENTBALANCES WHERE clientid IN (SELECT clientid FROM CLIENTS WHERE clientid IN (SELECT "
					+ "clientid FROM COMPANYCLIENTS WHERE compid = ?) AND clientcellno ilike ?)");
		){
			statement.setInt(1, compid);
			statement.setString(2, "%"+cellno+"%");
			System.out.println(statement.toString());
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				ClientBalance clientBalance = new ClientBalance();
				clientBalance.setClientId(rs.getInt("clientid"));
				clientBalance.setClientBal(rs.getDouble("clientbal"));
				clientBalances.add(clientBalance);
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying CLIENTBALANCES: " + e.toString());
		}
		return clientBalances;
	}
	
	public static JSONArray toJSONArray(ArrayList<ClientBalance> balances) {
		JSONArray jsonAr = new JSONArray();
		try {
			for(int i=0;i<balances.size();i++) {
				jsonAr.put(balances.get(i).toJSON());
			}
		}catch (Exception e) {
			System.out.println("Error making json: " + e.toString());
		}
		return jsonAr;
	}

	public static StringBuilder getClientBalancesJson(ArrayList<ClientBalance> balances) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(int i=0;i<balances.size();i++) {
			ClientBalance balance = balances.get(i);
			sb.append((i==0 ? "" : ",") + balance.toJsonString());
		}
		sb.append("]");
		return sb;
	}
}
