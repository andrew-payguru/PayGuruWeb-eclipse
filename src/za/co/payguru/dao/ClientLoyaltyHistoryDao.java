package za.co.payguru.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import za.co.payguru.model.ClientLoyaltyHistory;

public class ClientLoyaltyHistoryDao {

    public static ClientLoyaltyHistory loadClientLoyaltyHistory(Connection connection, int tranno) {
        ClientLoyaltyHistory loyaltyHistory = null;
        try (
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENTLOYATLYHISTORY WHERE tranno = ?");
        ) {
            statement.setInt(1, tranno);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                loyaltyHistory = new ClientLoyaltyHistory();
                loyaltyHistory.setTranno(rs.getInt("tranno"));
                loyaltyHistory.setClientid(rs.getInt("clientid"));
                loyaltyHistory.setCompid(rs.getInt("compid"));
                loyaltyHistory.setLoyaltyid(rs.getInt("loyaltyid"));
                loyaltyHistory.setTranamt(rs.getDouble("tranamt"));
                loyaltyHistory.setTranpoints(rs.getDouble("tranpoints"));
                loyaltyHistory.setTrandate(rs.getDate("trandate"));
                loyaltyHistory.setTrantime(rs.getTime("trantime"));
                loyaltyHistory.setTrantype(rs.getInt("trantype"));
            }
        } catch (SQLException e) {
            System.out.println("Error loading clientloyaltyhistory: " + e.toString());
        }
        return loyaltyHistory;
    }

    public static ArrayList<ClientLoyaltyHistory> getClientLoyaltyHistorys(Connection connection, int clientid, int compid, int loyaltyid) {
    	ArrayList<ClientLoyaltyHistory> data = new ArrayList<ClientLoyaltyHistory>();
      try (
          PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENTLOYATLYHISTORY WHERE clientid = ? AND compid = ? AND loyaltyid = ?");
      ) {
          statement.setInt(1, clientid);
          statement.setInt(2, compid);
          statement.setInt(3, loyaltyid);
          
          ResultSet rs = statement.executeQuery();
          while(rs.next()) {
          		ClientLoyaltyHistory loyaltyHistory = new ClientLoyaltyHistory();
              loyaltyHistory.setTranno(rs.getInt("tranno"));
              loyaltyHistory.setClientid(rs.getInt("clientid"));
              loyaltyHistory.setCompid(rs.getInt("compid"));
              loyaltyHistory.setLoyaltyid(rs.getInt("loyaltyid"));
              loyaltyHistory.setTranamt(rs.getDouble("tranamt"));
              loyaltyHistory.setTranpoints(rs.getDouble("tranpoints"));
              loyaltyHistory.setTrandate(rs.getDate("trandate"));
              loyaltyHistory.setTrantime(rs.getTime("trantime"));
              loyaltyHistory.setTrantype(rs.getInt("trantype"));
              data.add(loyaltyHistory);
          }rs.close();
      } catch (SQLException e) {
          System.out.println("Error loading CLIENTLOYALTYHISTORY: " + e.toString());
      }
      return data;
  }


  public static JSONArray getClientLoyaltyHistoryJSON(ArrayList<ClientLoyaltyHistory> loyaltyHistory) {
  	JSONArray jsonData = new JSONArray();
  	for(int i=0;i<loyaltyHistory.size();i++) {
  		ClientLoyaltyHistory history = loyaltyHistory.get(i);
  		JSONObject json = history.toJSON();
  		jsonData.put(json);
  	}
  	return jsonData;
  }
}
