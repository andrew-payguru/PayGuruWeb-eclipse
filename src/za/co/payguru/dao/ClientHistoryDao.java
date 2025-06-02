
package za.co.payguru.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import za.co.payguru.model.ClientHistory;

public class ClientHistoryDao {
	
	public static ArrayList<ClientHistory> getClientHistorys(Connection connection, int clientid){
		ArrayList<ClientHistory> clientHisory = new ArrayList<ClientHistory>();
		try(
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENTHISTORY WHERE clientid = ? ORDER BY trandate DESC, trantime DESC");
		){
			statement.setString(1, ""+clientid);
			ResultSet rs = statement.executeQuery();
			while(rs.next()){
				ClientHistory clientHist = new ClientHistory();
				clientHist.setTranno(rs.getLong("tranno"));
				clientHist.setTrantype(rs.getString("trantype"));
				clientHist.setClientid(rs.getString("clientid"));
				clientHist.setTrandate(rs.getString("trandate"));
				clientHist.setTrantime(rs.getString("trantime"));
				clientHist.setTranamt(rs.getDouble("tranamt"));
				clientHist.setTranref1(rs.getString("tranref1"));
				clientHist.setTranref2(rs.getString("tranref2"));
				clientHisory.add(clientHist);
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying table CLIENTHISTORY: " + e.toString());
		}
		return clientHisory;
	}

	public static ArrayList<ClientHistory> getClientWalletTransfers(Connection connection, int clientid){
		ArrayList<ClientHistory> clientHisory = new ArrayList<ClientHistory>();
		try(
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENTHISTORY WHERE clientid = ? AND trantype = ? ORDER BY trandate DESC, trantime DESC");
		){
			statement.setString(1, ""+clientid);
			statement.setString(2, ClientHistory.TYPE_DEBIT_WALLET_BALANCE);
			ResultSet rs = statement.executeQuery();
			while(rs.next()){
				ClientHistory clientHist = new ClientHistory();
				clientHist.setTranno(rs.getLong("tranno"));
				clientHist.setTrantype(rs.getString("trantype"));
				clientHist.setClientid(rs.getString("clientid"));
				clientHist.setTrandate(rs.getString("trandate"));
				clientHist.setTrantime(rs.getString("trantime"));
				clientHist.setTranamt(rs.getDouble("tranamt"));
				clientHist.setTranref1(rs.getString("tranref1"));
				clientHist.setTranref2(rs.getString("tranref2"));
				clientHisory.add(clientHist);
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying table CLIENTHISTORY: " + e.toString());
		}
		return clientHisory;
	}
}
