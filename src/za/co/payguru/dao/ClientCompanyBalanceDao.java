package za.co.payguru.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.json.JSONArray;

import za.co.payguru.model.ClientCompanyBalance;
import za.co.payguru.model.ClientProduct;
import za.co.payguru.model.Company;

public class ClientCompanyBalanceDao {
	
	public static ClientCompanyBalance loadClientCompanyBalance(Connection connection, int clientid, int compid, int subacc, int prodid, int donorid){
		ClientCompanyBalance balance = new ClientCompanyBalance();
		try(
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENTCOMPANYBALANCES WHERE clientid = ? AND compid = ? AND subacc = ? AND prodid = ? AND donorid = ?");	
		){
			statement.setInt(1, clientid);
			statement.setInt(2, compid);
			statement.setInt(3, subacc);
			statement.setInt(4, prodid);
			statement.setInt(5, donorid);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				balance.setClientid(rs.getInt("clientid"));
				balance.setCompid(rs.getInt("compid"));
				balance.setBalance(rs.getDouble("balance"));
				balance.setSubacc(rs.getInt("subacc"));
				balance.setProdid(rs.getInt("prodid"));
				balance.setDonorid(rs.getInt("donorid"));
			}
			rs.close();
		}catch (Exception e) {
			System.out.println("Error querying table CLIETNCOMPANYBALANCES: " + e.toString());
		}
		return balance;
	}
	
	public static ClientCompanyBalance loadClientCompanyBalance(Connection connection, int clientid, int compid, int subacc, int prodid){
		return loadClientCompanyBalance(connection,clientid,compid,subacc,prodid,-1);
	}

	public static ArrayList<ClientCompanyBalance> getClientCompanyBalances(Connection connection, int clientid){
		ArrayList<ClientCompanyBalance> cliCompBals = new ArrayList<ClientCompanyBalance>();
		try(
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENTCOMPANYBALANCES WHERE clientid = ? ORDER BY compid, subacc");	
		){
			statement.setInt(1, clientid);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				ClientCompanyBalance balance = new ClientCompanyBalance();
				balance.setClientid(rs.getInt("clientid"));
				balance.setCompid(rs.getInt("compid"));
				balance.setBalance(rs.getDouble("balance"));
				balance.setSubacc(rs.getInt("subacc"));
				balance.setProdid(rs.getInt("prodid"));
				balance.setDonorid(rs.getInt("donorid"));
				cliCompBals.add(balance);
			}
			rs.close();
		}catch (Exception e) {
			System.out.println("Error querying table CLIETNCOMPANYBALANCES: " + e.toString());
		}
		return cliCompBals;
	}
	
	public static ArrayList<ClientCompanyBalance> getClientCompanyBalances(Connection connection, int clientid, int compid){
		ArrayList<ClientCompanyBalance> cliCompBals = new ArrayList<ClientCompanyBalance>();
		try(
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENTCOMPANYBALANCES WHERE clientid = ? AND compid = ? ORDER BY compid, subacc");	
		){
			statement.setInt(1, clientid);
			statement.setInt(2, compid);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				ClientCompanyBalance balance = new ClientCompanyBalance();
				balance.setClientid(rs.getInt("clientid"));
				balance.setCompid(rs.getInt("compid"));
				balance.setBalance(rs.getDouble("balance"));
				balance.setSubacc(rs.getInt("subacc"));
				balance.setProdid(rs.getInt("prodid"));
				balance.setDonorid(rs.getInt("donorid"));
				cliCompBals.add(balance);
			}
			rs.close();
		}catch (Exception e) {
			System.out.println("Error querying table CLIETNCOMPANYBALANCES: " + e.toString());
		}
		return cliCompBals;
	}
	
	public static ArrayList<ClientCompanyBalance> getClientDonorWalletBalances(Connection connection, int clientid, int donorid){
		ArrayList<ClientCompanyBalance> cliCompBals = new ArrayList<ClientCompanyBalance>();
		try(
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENTCOMPANYBALANCES WHERE clientid = ? AND compid = ? AND prodid = ? AND donorid = ? ORDER BY subacc");	
		){
			statement.setInt(1, clientid);
			statement.setInt(2, Company.PAYGURU_COMPID);
			statement.setInt(3, ClientProduct.PAYGURU_WALLET);
			statement.setInt(4, donorid);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				ClientCompanyBalance balance = new ClientCompanyBalance();
				balance.setClientid(rs.getInt("clientid"));
				balance.setCompid(rs.getInt("compid"));
				balance.setBalance(rs.getDouble("balance"));
				balance.setSubacc(rs.getInt("subacc"));
				balance.setProdid(rs.getInt("prodid"));
				balance.setDonorid(rs.getInt("donorid"));
				cliCompBals.add(balance);
			}
			rs.close();
		}catch (Exception e) {
			System.out.println("Error querying table CLIETNCOMPANYBALANCES: " + e.toString());
		}
		return cliCompBals;
	}
	public static boolean updateClientCompanyBalance(Connection connection, ClientCompanyBalance balance) {
	    String query = "UPDATE CLIENTCOMPANYBALANCES SET balance = ? WHERE clientid = ? AND compid = ? AND  prodid = ? AND subacc = ?  AND donorid = ?  ";
	    try (PreparedStatement statement = connection.prepareStatement(query)) {
	        statement.setDouble(1, balance.getBalance());
	        statement.setInt(2, balance.getClientid());
	        statement.setInt(3, balance.getCompid());
	        statement.setInt(4, balance.getProdid());
	        statement.setInt(5, balance.getSubacc());
	        statement.setInt(6, balance.getDonorid());
	        
	        System.out.println(statement.toString());
	        int rowsUpdated = statement.executeUpdate();
	        return rowsUpdated > 0; // Returns true if at least one row was updated
	    } catch (Exception e) {
	        System.out.println("Error updating CLIENTCOMPANYBALANCES: " + e.toString());
	        return false;
	    }
	}


	public static JSONArray toJSONArray(ArrayList<ClientCompanyBalance> balances) {
		JSONArray jsonArray = new JSONArray();
		try {
			for(int i=0;i<balances.size();i++) {
				jsonArray.put(balances.get(i).toJSON());
			}
		}catch (Exception e) {
			System.out.println("Error creating json: " + e.toString()); 
		}
		return jsonArray;
	}

	public static double getTotalPositiveBalances(Connection connection, int compid, int prodid) {

	    double total = 0.0;

	    String query = "SELECT SUM(balance) AS total FROM CLIENTCOMPANYBALANCES WHERE compid = ? AND prodid = ? AND balance > 0";

	    try (

	        PreparedStatement statement = connection.prepareStatement(query);

	    ) {

	        statement.setInt(1, compid);

	        statement.setInt(2, prodid);

	        ResultSet rs = statement.executeQuery();

	        if (rs.next()) {

	            total = rs.getDouble("total");

	        }

	        rs.close();

	    } catch (Exception e) {

	        System.out.println("Error summing positive balances from CLIENTCOMPANYBALANCES: " + e.toString());

	    }

	    return total;

	}

	public static double getTotalNegativeBalances(Connection connection, int compid, int prodid) {

	    double total = 0.0;

	    String query = "SELECT SUM(balance) AS total FROM CLIENTCOMPANYBALANCES WHERE compid = ? AND prodid = ? AND balance < 0";

	    try (

	        PreparedStatement statement = connection.prepareStatement(query);

	    ) {

	        statement.setInt(1, compid);

	        statement.setInt(2, prodid);

	        ResultSet rs = statement.executeQuery();

	        if (rs.next()) {

	            total = Math.abs(rs.getDouble("total"));

	        }

	        rs.close();

	    } catch (Exception e) {

	        System.out.println("Error summing negative balances from CLIENTCOMPANYBALANCES: " + e.toString());

	    }

	    return total;

	}

 
 
}
