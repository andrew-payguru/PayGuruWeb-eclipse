package za.co.payguru.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import za.co.payguru.model.FinTran;
import za.co.payguru.util.Util;

public class FinTranDao {

    public static FinTran loadFinTrans(Connection connection, String fintranno) {
        FinTran finTran = new FinTran();
        try (
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM fintrans WHERE fintranno = ?");
        ) {
            statement.setString(1, fintranno);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
            	finTran.setFintranno(rs.getString("fintranno"));
            	finTran.setFintrancreateddate(rs.getDate("fintrancreateddate"));
            	finTran.setFintrancreatedtime(rs.getTime("fintrancreatedtime"));
            	finTran.setCompid(rs.getInt("compid"));
            	finTran.setFintranref1(rs.getString("fintranref1"));
            	finTran.setFintranref2(rs.getString("fintranref2"));
            	finTran.setFintranref3(rs.getString("fintranref3"));
            	finTran.setFintranextref1(rs.getString("fintranextref1"));
            	finTran.setFintranexttranno(rs.getString("fintranexttranno"));
            	finTran.setFintranamt(rs.getDouble("fintranamt"));
            	finTran.setFintranfee(rs.getDouble("fintranfee"));
            	finTran.setFintranstatus(rs.getString("fintranstatus"));
                finTran.setFintranstatustime(rs.getTime("fintranstatustime"));
                finTran.setFintranstatusdate(rs.getDate("fintranstatusdate"));
                finTran.setFintranstatusdesc(rs.getString("fintranstatusdesc"));
                finTran.setFintrantype(rs.getInt("fintrantype"));
                finTran.setFintranresponsetime(rs.getTime("fintranresponsetime"));
                finTran.setFintranresponsedate(rs.getDate("fintranresponsedate"));
                finTran.setFintranactiontranno(rs.getString("fintranactiontranno"));
                finTran.setFintranactiontrantime(rs.getTime("fintranactiontrantime"));
                finTran.setFintranactiontrandate(rs.getDate("fintranactiontrandate"));
                finTran.setFintranactive(rs.getString("fintranactive"));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error loading FINTRANS: " + e.toString());
        }
        return finTran;
    }

    public static ArrayList<FinTran> getClientFinTrans(Connection connection, int clientId){
    	ArrayList<FinTran> finTrans = new ArrayList<FinTran>();
    	try(
    		PreparedStatement statement = connection.prepareStatement("SELECT * FROM FINTRANS WHERE fintranref1 = ? AND fintranstatus = ? AND fintranactive = ? ORDER BY fintrancreateddate DESC, fintrancreatedtime DESC");
    	){
    		statement.setString(1, ""+clientId);
    		statement.setString(2, FinTran.DONE);
    		statement.setString(3, FinTran.ACTIVE);
    		ResultSet rs = statement.executeQuery();
    		while(rs.next()) {
    			FinTran finTran = new FinTran();
    			finTran.setFintranno(rs.getString("fintranno"));
            	finTran.setFintrancreateddate(rs.getDate("fintrancreateddate"));
            	finTran.setFintrancreatedtime(rs.getTime("fintrancreatedtime"));
            	finTran.setCompid(rs.getInt("compid"));
            	finTran.setFintranref1(rs.getString("fintranref1"));
            	finTran.setFintranref2(rs.getString("fintranref2"));
            	finTran.setFintranref3(rs.getString("fintranref3"));
            	finTran.setFintranextref1(rs.getString("fintranextref1"));
            	finTran.setFintranexttranno(rs.getString("fintranexttranno"));
            	finTran.setFintranamt(rs.getDouble("fintranamt"));
            	finTran.setFintranfee(rs.getDouble("fintranfee"));
            	finTran.setFintranstatus(rs.getString("fintranstatus"));
                finTran.setFintranstatustime(rs.getTime("fintranstatustime"));
                finTran.setFintranstatusdate(rs.getDate("fintranstatusdate"));
                finTran.setFintranstatusdesc(rs.getString("fintranstatusdesc"));
                finTran.setFintrantype(rs.getInt("fintrantype"));
                finTran.setFintranresponsetime(rs.getTime("fintranresponsetime"));
                finTran.setFintranresponsedate(rs.getDate("fintranresponsedate"));
                finTran.setFintranactiontranno(rs.getString("fintranactiontranno"));
                finTran.setFintranactiontrantime(rs.getTime("fintranactiontrantime"));
                finTran.setFintranactiontrandate(rs.getDate("fintranactiontrandate"));
                finTran.setFintranactive(rs.getString("fintranactive"));
                finTrans.add(finTran);
    		}rs.close();
    	}catch (Exception e) {
			System.out.println("Error querying FinTrans: " + e.toString());
		}
    	return finTrans;
    }
   

    public static boolean updateFinTrans(Connection connection, FinTran finTran) {
    	boolean updated = false;
        try (
            PreparedStatement statement = connection.prepareStatement("UPDATE fintrans SET fintrancreateddate = ?, fintrancreatedtime = ?, compid = ?, fintranref1 = ?, fintranref2 = ?, fintranref3 = ?, fintranextref1 = ?, fintranexttranno = ?, fintranamt = ?, fintranfee = ?, fintranstatus = ?, fintranstatustime = ?, fintranstatusdate = ?, fintranstatusdesc = ?, fintrantype = ?, fintranresponsetime = ?, fintranresponsedate = ?, fintranactiontranno = ?, fintranactiontrantime = ?, fintranactiontrandate = ?, fintranactive = ? WHERE fintranno = ?");
        ) {
            statement.setDate(1, finTran.getFintrancreateddate());
            statement.setTime(2, finTran.getFintrancreatedtime());
            statement.setInt(3, finTran.getCompid());
            statement.setString(4, finTran.getFintranref1());
            statement.setString(5, finTran.getFintranref2());
            statement.setString(6, finTran.getFintranref3());
            statement.setString(7, finTran.getFintranextref1());
            statement.setString(8, finTran.getFintranexttranno());
            statement.setDouble(9, finTran.getFintranamt());
            statement.setDouble(10, finTran.getFintranfee());
            statement.setString(11, finTran.getFintranstatus());
            statement.setTime(12, finTran.getFintranstatustime());
            statement.setDate(13, finTran.getFintranstatusdate());
            statement.setString(14, finTran.getFintranstatusdesc());
            statement.setInt(15, finTran.getFintrantype());
            statement.setTime(16, finTran.getFintranresponsetime());
            statement.setDate(17, finTran.getFintranresponsedate());
            statement.setString(18, finTran.getFintranactiontranno());
            statement.setTime(19, finTran.getFintranactiontrantime());
            statement.setDate(20, finTran.getFintranactiontrandate());
            statement.setString(21, finTran.getFintranactive());
            statement.setString(22, finTran.getFintranno());
            
            updated = (statement.executeUpdate() > 0);
        } catch (SQLException e) {
            System.out.println("Error updating FINTRANS: " + e.toString());
        }
        return updated;
    }
    
    public static JSONArray getFinTransJSON(ArrayList<FinTran> finTrans) {
    	JSONArray jsonAr = new JSONArray();
    	try {
    		for(FinTran finTran : finTrans) {
    			jsonAr.put(finTran.toJson());
    		}
    	}catch (Exception e) {
    		System.out.println("Error creating json array: " + e.toString());
		}
    	return jsonAr;  	
    }
}
