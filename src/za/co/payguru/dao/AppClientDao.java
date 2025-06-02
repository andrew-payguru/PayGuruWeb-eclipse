package za.co.payguru.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import za.co.payguru.model.AppClient;
import za.co.payguru.model.Client;
import za.co.payguru.util.DateUtil;

public class AppClientDao {

	public static AppClient loadAppClient(Connection connection, int clientId) {
		AppClient appClient = new AppClient(); 
		try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM APPCLIENT WHERE clientid = ?")){
			 statement.setInt(1,  clientId);
			 ResultSet rs = statement.executeQuery();
			 if(rs.next()) {
				 appClient.setClientId(rs.getInt("clientid"));
				 appClient.setClientCellNo(rs.getString("clientcellno"));
				 appClient.setClientEmail(rs.getString("clientemail"));
				 appClient.setClientPassword(rs.getString("clientpassword"));
				 appClient.setClientLastLogTime(rs.getTime("clientlastlogtime"));
				 appClient.setClientLastLogDate(rs.getDate("clientlastlogdate"));
				 appClient.setClientStatus(rs.getString("clientstatus"));
				 appClient.setClientStatusTime(rs.getTime("clientstatustime"));
				 appClient.setClientStatusDate(rs.getDate("clientstatusdate"));
				 appClient.setClientActive(rs.getString("clientactive"));
				 appClient.setAppRef1(rs.getString("appref1"));
				 appClient.setAppRef2(rs.getString("appref2"));
				 appClient.setAppRef3(rs.getString("appref3"));
				 appClient.setCreatedTime(rs.getTime("createdtime"));
				 appClient.setCreatedDate(rs.getDate("createddate"));
			 }rs.close();
		 }catch (Exception e) {
			System.out.println("Error querying table: " + e.toString());
		}
		return appClient;
	}
	public static AppClient loadAppClientByCell(Connection connection, String clientCellNo) {
		AppClient appClient = new AppClient(); 
		String [] prefixs = ParamDao.getParam("clientcellnumberprefix", connection).split(",");
		StringBuilder query = new StringBuilder();
		for(int i=0;i<prefixs.length;i++) {
			query.append(" OR clientcellno = '"+prefixs[i]+clientCellNo+"'");
			query.append(" OR clientcellno = '"+clientCellNo.replace(prefixs[i], "")+"'");
		}		
		try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM APPCLIENT WHERE clientcellno = ?" + query)){
			 statement.setString(1,  clientCellNo);
			 ResultSet rs = statement.executeQuery();
			 if(rs.next()) {
				 appClient.setClientId(rs.getInt("clientid"));
				 appClient.setClientCellNo(rs.getString("clientcellno"));
				 appClient.setClientEmail(rs.getString("clientemail"));
				 appClient.setClientPassword(rs.getString("clientpassword"));
				 appClient.setClientLastLogTime(rs.getTime("clientlastlogtime"));
				 appClient.setClientLastLogDate(rs.getDate("clientlastlogdate"));
				 appClient.setClientStatus(rs.getString("clientstatus"));
				 appClient.setClientStatusTime(rs.getTime("clientstatustime"));
				 appClient.setClientStatusDate(rs.getDate("clientstatusdate"));
				 appClient.setClientActive(rs.getString("clientactive"));
				 appClient.setAppRef1(rs.getString("appref1"));
				 appClient.setAppRef2(rs.getString("appref2"));
				 appClient.setAppRef3(rs.getString("appref3"));
				 appClient.setCreatedTime(rs.getTime("createdtime"));
				 appClient.setCreatedDate(rs.getDate("createddate"));
			 }rs.close();
		 }catch (Exception e) {
			System.out.println("Error querying table: " + e.toString());
		}
		return appClient;
	}
	public static AppClient loadAppClientByEmail(Connection connection, String clientEmail) {
		AppClient appClient = new AppClient(); 
		try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM APPCLIENT WHERE clientemail = ?")){
			 statement.setString(1,  clientEmail);
			 ResultSet rs = statement.executeQuery();
			 if(rs.next()) {
				 appClient.setClientId(rs.getInt("clientid"));
				 appClient.setClientCellNo(rs.getString("clientcellno"));
				 appClient.setClientEmail(rs.getString("clientemail"));
				 appClient.setClientPassword(rs.getString("clientpassword"));
				 appClient.setClientLastLogTime(rs.getTime("clientlastlogtime"));
				 appClient.setClientLastLogDate(rs.getDate("clientlastlogdate"));
				 appClient.setClientStatus(rs.getString("clientstatus"));
				 appClient.setClientStatusTime(rs.getTime("clientstatustime"));
				 appClient.setClientStatusDate(rs.getDate("clientstatusdate"));
				 appClient.setClientActive(rs.getString("clientactive"));
				 appClient.setAppRef1(rs.getString("appref1"));
				 appClient.setAppRef2(rs.getString("appref2"));
				 appClient.setAppRef3(rs.getString("appref3"));
				 appClient.setCreatedTime(rs.getTime("createdtime"));
				 appClient.setCreatedDate(rs.getDate("createddate"));
			 }rs.close();
		 }catch (Exception e) {
			System.out.println("Error querying table: " + e.toString());
		}
		return appClient;
	}
	public static AppClient addAppClient(Connection connection, AppClient appClient) {
		try(PreparedStatement statement = connection.prepareStatement("INSERT INTO APPCLIENT VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)")){
			appClient.setClientLastLogTime(DateUtil.getCurrentHHMMSSTime());
			appClient.setClientLastLogDate(DateUtil.getCurrentCCYYMMDDDate());
			appClient.setClientStatus(AppClient.STATUS_ACTIVE);
			appClient.setClientStatusTime(DateUtil.getCurrentHHMMSSTime());
			appClient.setClientStatusDate(DateUtil.getCurrentCCYYMMDDDate());
			appClient.setClientActive(AppClient.STATUS_ACTIVE);
			appClient.setCreatedTime(DateUtil.getCurrentHHMMSSTime());
			appClient.setCreatedDate(DateUtil.getCurrentCCYYMMDDDate());
			statement.setInt(1, appClient.getClientId());
			statement.setString(2, appClient.getClientCellNo());
			statement.setString(3, appClient.getClientEmail());
			statement.setString(4, appClient.getClientPassword());
			statement.setTime(5, appClient.getClientLastLogTime());
			statement.setDate(6, appClient.getClientLastLogDate());
			statement.setString(7, appClient.getClientStatus());
			statement.setTime(8, appClient.getClientStatusTime());
			statement.setDate(9, appClient.getClientStatusDate());
			statement.setString(10, appClient.getClientActive());
			statement.setString(11, appClient.getAppRef1());
			statement.setString(12, appClient.getAppRef2());
			statement.setString(13, appClient.getAppRef3());
			statement.setTime(14, appClient.getCreatedTime());
			statement.setDate(15, appClient.getCreatedDate());
			if(statement.executeUpdate()<=0)
				appClient = new AppClient();
		}catch (Exception e) {
			System.out.println("Error updating APPCLIENT: " + e.toString());
			appClient = new AppClient();
		}
		return appClient;
	}
	
	public static AppClient updateAppClient(Connection connection, AppClient appClient) {
		try(PreparedStatement statement = connection.prepareStatement("UPDATE APPCLIENT SET clientcellno = ?, clientemail = ?, "
				+ "clientpassword = ?, clientlastlogtime = ?, clientlastlogdate = ?, clientstatus = ?, clientstatustime = ?, clientstatusdate = ?, "
				+ "clientactive = ?, appref1 = ?, appref2 = ?, appref3 = ?, createdtime = ?, createddate = ? WHERE clientid = ?")){
			statement.setString(1, appClient.getClientCellNo());
			statement.setString(2, appClient.getClientEmail());
			statement.setString(3, appClient.getClientPassword());
			statement.setTime(4, appClient.getClientLastLogTime());
			statement.setDate(5, appClient.getClientLastLogDate());
			statement.setString(6, appClient.getClientStatus());
			statement.setTime(7, appClient.getClientStatusTime());
			statement.setDate(8, appClient.getClientStatusDate());
			statement.setString(9, appClient.getClientActive());
			statement.setString(10, appClient.getAppRef1());
			statement.setString(11, appClient.getAppRef2());
			statement.setString(12, appClient.getAppRef3());
			statement.setTime(13, appClient.getCreatedTime());
			statement.setDate(14, appClient.getCreatedDate());
			statement.setInt(15, appClient.getClientId());

			if(statement.executeUpdate()<=0)
				appClient = new AppClient();
		}catch (Exception e) {
			System.out.println("Error updating APPCLIENT: " + e.toString());
			appClient = new AppClient();
		}
		return appClient;
	}
	
public static StringBuilder getAppClientsJSON(ArrayList<AppClient> appClients) {
		
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(int i=0;i<appClients.size();i++) {
			AppClient appClient = appClients.get(i);
			sb.append((i==0 ? "" : ",") + appClient.toJsonString());
		}
		sb.append("]");
		return sb;
	}
}
