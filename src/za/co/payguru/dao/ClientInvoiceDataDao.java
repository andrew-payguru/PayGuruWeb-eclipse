package za.co.payguru.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import za.co.payguru.model.ClientInvoice;
import za.co.payguru.model.ClientInvoiceData;
import za.co.payguru.model.ClientPayment;
import za.co.payguru.model.ClientPaymentRef;
import za.co.payguru.util.DateUtil;
import za.co.payguru.util.JSONHelper;
import za.co.payguru.util.Util;

public class ClientInvoiceDataDao {


	public static ClientInvoiceData loadClientInvoiceData(Connection connection, int compId, String invNo){
		ClientInvoiceData clientInvoiceData = new ClientInvoiceData();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENTINVOICEDATA WHERE compid = ? AND invno = ?");
				){
			statement.setInt(1, compId);
			statement.setString(2, invNo);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				clientInvoiceData.setInvNo(rs.getString("invno"));
				clientInvoiceData.setCompId(rs.getInt("compid"));
				clientInvoiceData.setInvData(rs.getString("invdata").trim().replace('\n', ' ').replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				clientInvoiceData.setInvDataType(rs.getString("invdatatype").trim().replace('\n', ' ').replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				clientInvoiceData.setInvDataRef1(rs.getString("invdataref1").trim().replace('\n', ' ').replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				clientInvoiceData.setInvDataRef2(rs.getString("invdataref2").trim().replace('\n', ' ').replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				clientInvoiceData.setInvDataRef3(rs.getString("invdataref3").trim().replace('\n', ' ').replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				clientInvoiceData.setInvDataRef4(rs.getString("invdataref4").trim().replace('\n', ' ').replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				clientInvoiceData.setInvDataRef5(rs.getString("invdataref5").trim().replace('\n', ' ').replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				clientInvoiceData.setInvDataRef6(rs.getString("invdataref6").trim().replace('\n', ' ').replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				clientInvoiceData.setInvDataRef7(rs.getString("invdataref7").trim().replace('\n', ' ').replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				clientInvoiceData.setInvDataRef8(rs.getString("invdataref8").trim().replace('\n', ' ').replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				clientInvoiceData.setInvDataAmt1(rs.getDouble("invdataamt1"));
				clientInvoiceData.setInvDataAmt2(rs.getDouble("invdataamt2"));
				clientInvoiceData.setInvDataAmt3(rs.getDouble("invdataamt3"));
				clientInvoiceData.setInvDataAmt4(rs.getDouble("invdataamt4"));
				clientInvoiceData.setInvDataAmt5(rs.getDouble("invdataamt5"));
				clientInvoiceData.setInvLink(rs.getString("invlink").trim());
				clientInvoiceData.setClientId(rs.getInt("clientid"));
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying table: " + e.toString());
		}
		return clientInvoiceData;
	}
	
	public static ArrayList<ClientInvoiceData> searchClientInvoiceData(Connection connection, int compid, String invno){
		ArrayList<ClientInvoiceData> clientInvoiceData = new ArrayList<ClientInvoiceData>();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENTINVOICEDATA WHERE compid = ? AND invno LIKE ?");
		){
			statement.setInt(1, compid);
			statement.setString(2, "%"+invno+"%");
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				ClientInvoiceData data = new ClientInvoiceData();
				data.setInvNo(rs.getString("invno"));
				data.setCompId(rs.getInt("compid"));
				data.setInvData(rs.getString("invdata").trim().replace('\n', ' ').replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				data.setInvDataType(rs.getString("invdatatype").trim().replace('\n', ' ').replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				data.setInvDataRef1(rs.getString("invdataref1").trim().replace('\n', ' ').replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				data.setInvDataRef2(rs.getString("invdataref2").trim().replace('\n', ' ').replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				data.setInvDataRef3(rs.getString("invdataref3").trim().replace('\n', ' ').replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				data.setInvDataRef4(rs.getString("invdataref4").trim().replace('\n', ' ').replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				data.setInvDataRef5(rs.getString("invdataref5").trim().replace('\n', ' ').replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				data.setInvDataRef6(rs.getString("invdataref6").trim().replace('\n', ' ').replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				data.setInvDataRef7(rs.getString("invdataref7").trim().replace('\n', ' ').replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				data.setInvDataRef8(rs.getString("invdataref8").trim().replace('\n', ' ').replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				data.setInvDataAmt1(rs.getDouble("invdataamt1"));
				data.setInvDataAmt2(rs.getDouble("invdataamt2"));
				data.setInvDataAmt3(rs.getDouble("invdataamt3"));
				data.setInvDataAmt4(rs.getDouble("invdataamt4"));
				data.setInvDataAmt5(rs.getDouble("invdataamt5"));
				data.setInvLink(rs.getString("invlink").trim());
				data.setClientId(rs.getInt("clientid"));
				clientInvoiceData.add(data);
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying CLIENTINVOICEDATA: " + e.toString());
		}
		return clientInvoiceData;
	}
	public static ArrayList<ClientInvoiceData> loadClientClientInvoiceData(Connection connection, int compid, int clientid, boolean period, String from, String to, boolean ignoreStatus){
		ArrayList<ClientInvoiceData> clientInvoiceData = new ArrayList<ClientInvoiceData>();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENTINVOICEDATA WHERE invno IN(SELECT invno FROM CLIENTINVOICES WHERE compid = ? AND clientid = ? AND active = ?" + (ignoreStatus ? "" : " AND status = '"+ClientInvoice.INVOICE_PAID+"' ") + (period?" AND paydate >= '"+from.replace('-', '/')+"' AND paydate <= '"+to.replace('-', '/')+"' " : " ")+"ORDER BY paydate DESC, invno DESC)");
		){
			statement.setInt(1, compid);
			statement.setInt(2, clientid);
			statement.setString(3, ClientInvoice.INVOICE_ACTIVE);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				ClientInvoiceData invData = new ClientInvoiceData();
				invData.setInvNo(rs.getString("invno"));
				invData.setCompId(rs.getInt("compid"));
				invData.setInvData(rs.getString("invdata").trim().replace('\n', ' ').replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				invData.setInvDataType(rs.getString("invdatatype").trim().replace('\n', ' ').replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				invData.setInvDataRef1(rs.getString("invdataref1").trim().replace('\n', ' ').replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				invData.setInvDataRef2(rs.getString("invdataref2").trim().replace('\n', ' ').replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				invData.setInvDataRef3(rs.getString("invdataref3").trim().replace('\n', ' ').replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				invData.setInvDataRef4(rs.getString("invdataref4").trim().replace('\n', ' ').replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				invData.setInvDataRef5(rs.getString("invdataref5").trim().replace('\n', ' ').replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				invData.setInvDataRef6(rs.getString("invdataref6").trim().replace('\n', ' ').replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				invData.setInvDataRef7(rs.getString("invdataref7").trim().replace('\n', ' ').replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				invData.setInvDataRef8(rs.getString("invdataref8").trim().replace('\n', ' ').replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				invData.setInvDataAmt1(rs.getDouble("invdataamt1"));
				invData.setInvDataAmt2(rs.getDouble("invdataamt2"));
				invData.setInvDataAmt3(rs.getDouble("invdataamt3"));
				invData.setInvDataAmt4(rs.getDouble("invdataamt4"));
				invData.setInvDataAmt5(rs.getDouble("invdataamt5"));
				invData.setInvLink(rs.getString("invlink").trim());
				invData.setClientId(rs.getInt("clientid"));
				clientInvoiceData.add(invData);
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying table CLIENTINVOICEDATA: " + e.toString());
		}
		return clientInvoiceData;
	}
	public static ArrayList<ClientInvoiceData> loadClientClientInvoiceData(Connection connection, int compid, int clientid){
		return loadClientClientInvoiceData(connection, compid, clientid, false, DateUtil.getCurrentCCYYMMDDStr(), DateUtil.getCurrentCCYYMMDDStr(),false);
	}
	public static ArrayList<ClientInvoiceData> getInactiveSmsInvoiceData(Connection connection, int compid, int prodid, Date fromdate, Date todate){
		return getInactiveSmsInvoiceData(connection, compid, prodid, fromdate, todate, null, null);
	}
	public static ArrayList<ClientInvoiceData> getInactiveSmsInvoiceData(Connection connection, int compid, int prodid, Date fromdate, Date todate, String invNo, String regNo){
		String smsChannelStr = CompanyParamDao.getCompParamValue(connection, compid, "manualactivatesmschannels");
		if(smsChannelStr==null||smsChannelStr.isEmpty())
			smsChannelStr = "SMS";
		StringBuilder channelSql = new StringBuilder();
		String [] channels = smsChannelStr.split(",");
		for(int i=0;i<channels.length;i++) {
			channelSql.append((i==0 ? "" : " OR ") + ("salechannel = '" + channels[i] + "'"));
		}
		ArrayList<ClientInvoiceData> clientInvoiceDatas = new ArrayList<ClientInvoiceData>();
		boolean invNoFilter = (invNo!=null&&invNo.length()>0);
		boolean regNoFilter = (regNo!=null&&regNo.length()>0);
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENTINVOICEDATA WHERE invno IN (SELECT invno FROM CLIENTINVOICES WHERE compid = ?"+(invNoFilter ? " AND invno LIKE '%"+invNo+"%' " : " ")+"AND paydate >= ? AND paydate <= ?"+(regNoFilter ? " AND payref ILIKE '%"+regNo+"%' " : " ")+"AND prodid IN (SELECT prodid FROM COMPANYPRODUCTS WHERE compid = ?" + (prodid>0 ? " AND prodid = " + prodid : "") + " AND status = ? AND (" + channelSql  + ")) AND invno in (SELECT invno FROM CLIENTPAYMENTREFS WHERE compid = ? AND createdate >= ? AND createdate <= ? AND status = ?) ORDER BY paydate, invno)");
				){
			statement.setInt(1, compid);
			statement.setString(2, String.valueOf(fromdate).replace('-', '/'));
			statement.setString(3, String.valueOf(todate).replace('-', '/'));
			statement.setInt(4, compid);
			statement.setString(5, ClientInvoice.INVOICE_INACTIVE);
			statement.setInt(6, compid);
			statement.setString(7, String.valueOf(fromdate).replace('-', '/'));
			statement.setString(8, String.valueOf(todate).replace('-', '/'));
			statement.setInt(9, Util.parseInt(ClientPaymentRef.ACTIVE,1));
			System.out.println(statement.toString());
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				ClientInvoiceData clientInvoiceData = new ClientInvoiceData();
				clientInvoiceData.setInvNo(rs.getString("invno"));
				clientInvoiceData.setCompId(rs.getInt("compid"));
				clientInvoiceData.setInvData(rs.getString("invdata").trim().replace('\n', ' ').replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				clientInvoiceData.setInvDataType(rs.getString("invdatatype").trim().replace('\n', ' ').replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				clientInvoiceData.setInvDataRef1(rs.getString("invdataref1").trim().replace('\n', ' ').replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				clientInvoiceData.setInvDataRef2(rs.getString("invdataref2").trim().replace('\n', ' ').replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				clientInvoiceData.setInvDataRef3(rs.getString("invdataref3").trim().replace('\n', ' ').replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				clientInvoiceData.setInvDataRef4(rs.getString("invdataref4").trim().replace('\n', ' ').replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				clientInvoiceData.setInvDataRef5(rs.getString("invdataref5").trim().replace('\n', ' ').replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				clientInvoiceData.setInvDataRef6(rs.getString("invdataref6").trim().replace('\n', ' ').replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				clientInvoiceData.setInvDataRef7(rs.getString("invdataref7").trim().replace('\n', ' ').replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				clientInvoiceData.setInvDataRef8(rs.getString("invdataref8").trim().replace('\n', ' ').replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				clientInvoiceData.setInvDataAmt1(rs.getDouble("invdataamt1"));
				clientInvoiceData.setInvDataAmt2(rs.getDouble("invdataamt2"));
				clientInvoiceData.setInvDataAmt3(rs.getDouble("invdataamt3"));
				clientInvoiceData.setInvDataAmt4(rs.getDouble("invdataamt4"));
				clientInvoiceData.setInvDataAmt5(rs.getDouble("invdataamt5"));
				clientInvoiceData.setInvLink(rs.getString("invlink").trim());
				clientInvoiceData.setClientId(rs.getInt("clientid"));
				clientInvoiceDatas.add(clientInvoiceData);
			}rs.close();
		}catch (Exception e) {
			System.out.println();
		}
		return clientInvoiceDatas;
	}

	//paydate not startdate
	public static ArrayList<ClientInvoiceData> getChannelClientInvoiceDataSales(Connection connection, int compId, int compinternalid, int prodid, String channel, String startDate, String endDate){
		ArrayList<ClientInvoiceData> clientInvoiceDatas = new ArrayList<ClientInvoiceData>();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENTINVOICEDATA WHERE invno IN (SELECT invno FROM CLIENTINVOICES WHERE compid = ? AND paydate >= ? AND paydate <= ? " + (channel.equalsIgnoreCase("ALL") ? "" : "AND salechannel = '"+channel.toUpperCase()+"'") + " AND (status = ? OR status = ?) AND prodid IN (SELECT prodid FROM COMPANYPRODUCTS WHERE compid = ?" + (prodid>0 ? " AND prodid = " + prodid : "") + (compinternalid>0 ? (" AND compinternalid = "+ compinternalid) : "") + ") ORDER BY paydate, invno)");
				){
			statement.setInt(1, compId);
			statement.setString(2, startDate.replace('-', '/'));
			statement.setString(3, endDate.replace('-', '/'));
			statement.setString(4, ClientInvoice.INVOICE_PAID);
			statement.setString(5, ClientInvoice.INVOICE_ACTIVE);
			statement.setInt(6, compId);
			System.out.println("\n\nSQL1: " + statement.toString());
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				ClientInvoiceData clientInvoiceData = new ClientInvoiceData();
				clientInvoiceData.setInvNo(rs.getString("invno"));
				clientInvoiceData.setCompId(rs.getInt("compid"));
				clientInvoiceData.setInvData(rs.getString("invdata").trim().replace('\n', ' ').replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				clientInvoiceData.setInvDataType(rs.getString("invdatatype").trim().replace('\n', ' ').replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				clientInvoiceData.setInvDataRef1(rs.getString("invdataref1").trim().replace('\n', ' ').replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				clientInvoiceData.setInvDataRef2(rs.getString("invdataref2").trim().replace('\n', ' ').replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				clientInvoiceData.setInvDataRef3(rs.getString("invdataref3").trim().replace('\n', ' ').replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				clientInvoiceData.setInvDataRef4(rs.getString("invdataref4").trim().replace('\n', ' ').replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				clientInvoiceData.setInvDataRef5(rs.getString("invdataref5").trim().replace('\n', ' ').replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				clientInvoiceData.setInvDataRef6(rs.getString("invdataref6").trim().replace('\n', ' ').replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				clientInvoiceData.setInvDataRef7(rs.getString("invdataref7").trim().replace('\n', ' ').replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				clientInvoiceData.setInvDataRef8(rs.getString("invdataref8").trim().replace('\n', ' ').replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				clientInvoiceData.setInvDataAmt1(rs.getDouble("invdataamt1"));
				clientInvoiceData.setInvDataAmt2(rs.getDouble("invdataamt2"));
				clientInvoiceData.setInvDataAmt3(rs.getDouble("invdataamt3"));
				clientInvoiceData.setInvDataAmt4(rs.getDouble("invdataamt4"));
				clientInvoiceData.setInvDataAmt5(rs.getDouble("invdataamt5"));
				clientInvoiceData.setInvLink(rs.getString("invlink"));
				clientInvoiceData.setClientId(rs.getInt("clientid"));
				clientInvoiceDatas.add(clientInvoiceData);
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying table: " + e.toString());
		}
		return clientInvoiceDatas;
	}


	public static ArrayList<ClientInvoiceData> getChannelClientInvoiceData(Connection connection, int compId, int compinternalid, int prodid, String channel, String startDate, String endDate){
		ArrayList<ClientInvoiceData> clientInvoiceDatas = new ArrayList<ClientInvoiceData>();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENTINVOICEDATA WHERE invno IN (SELECT invno FROM CLIENTINVOICES WHERE compid = ? AND paydate >= ? AND paydate <= ? " + (channel.equalsIgnoreCase("ALL") ? "" : "AND salechannel = '"+channel.toUpperCase()+"'") + " AND (status = ? OR status = ?) AND prodid IN (SELECT prodid FROM COMPANYPRODUCTS WHERE compid = ?" + (prodid>0 ? " AND prodid = " + prodid : "") + (compinternalid>0 ? (" AND compinternalid = "+ compinternalid) : "") + ") AND invdataref8 >= ? AND invdataref8 <= ? ORDER BY paydate, invno)");
				){
			statement.setInt(1, compId);
			statement.setString(2, String.valueOf(DateUtil.getPrevDate(DateUtil.getDateValue(startDate))).replace('-', '/'));
			statement.setString(3, endDate.replace('-', '/'));
			statement.setString(4, ClientInvoice.INVOICE_PAID);
			statement.setString(5, ClientInvoice.INVOICE_ACTIVE);
			statement.setInt(6, compId);
			statement.setString(7, startDate.replace('-', '/'));
			statement.setString(8, endDate.replace('-', '/'));
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				ClientInvoiceData clientInvoiceData = new ClientInvoiceData();
				clientInvoiceData.setInvNo(rs.getString("invno"));
				clientInvoiceData.setCompId(rs.getInt("compid"));
				clientInvoiceData.setInvData(rs.getString("invdata").trim().replace('\n', ' ').replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				clientInvoiceData.setInvDataType(rs.getString("invdatatype").trim().replace('\n', ' ').replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				clientInvoiceData.setInvDataRef1(rs.getString("invdataref1").trim().replace('\n', ' ').replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				clientInvoiceData.setInvDataRef2(rs.getString("invdataref2").trim().replace('\n', ' ').replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				clientInvoiceData.setInvDataRef3(rs.getString("invdataref3").trim().replace('\n', ' ').replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				clientInvoiceData.setInvDataRef4(rs.getString("invdataref4").trim().replace('\n', ' ').replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				clientInvoiceData.setInvDataRef5(rs.getString("invdataref5").trim().replace('\n', ' ').replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				clientInvoiceData.setInvDataRef6(rs.getString("invdataref6").trim().replace('\n', ' ').replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				clientInvoiceData.setInvDataRef7(rs.getString("invdataref7").trim().replace('\n', ' ').replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				clientInvoiceData.setInvDataRef8(rs.getString("invdataref8").trim().replace('\n', ' ').replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				clientInvoiceData.setInvDataAmt1(rs.getDouble("invdataamt1"));
				clientInvoiceData.setInvDataAmt2(rs.getDouble("invdataamt2"));
				clientInvoiceData.setInvDataAmt3(rs.getDouble("invdataamt3"));
				clientInvoiceData.setInvDataAmt4(rs.getDouble("invdataamt4"));
				clientInvoiceData.setInvDataAmt5(rs.getDouble("invdataamt5"));
				clientInvoiceData.setInvLink(rs.getString("invlink"));
				clientInvoiceData.setClientId(rs.getInt("clientid"));
				clientInvoiceDatas.add(clientInvoiceData);
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying table: " + e.toString());
		}
		return clientInvoiceDatas;
	}


		
	public static boolean updateInvoiceData(Connection connection, ClientInvoiceData invData) {
		boolean updated = false;
		try(
				PreparedStatement statement = connection.prepareStatement("UPDATE CLIENTINVOICEDATA SET invdata = ?,invdatatype = ?,"
						+ "invdataref1 = ?,invdataref2 = ?,invdataref3 = ?,invdataref4 = ?,invdataref5 = ?,invdataref6 = ?,invdataref7 = ?,invdataref8 = ?,"
						+ "invdataamt1 = ?,invdataamt2 = ?,invdataamt3 = ?,invdataamt4 = ?,invdataamt5 = ?,invlink = ?,clientid = ? WHERE compid = ? AND invno = ?");
		){
			statement.setString(1, invData.getInvData());
			statement.setString(2, invData.getInvDataType());
			statement.setString(3, invData.getInvDataRef1());
			statement.setString(4, invData.getInvDataRef2());
			statement.setString(5, invData.getInvDataRef3());
			statement.setString(6, invData.getInvDataRef4());
			statement.setString(7, invData.getInvDataRef5());
			statement.setString(8, invData.getInvDataRef6());
			statement.setString(9, invData.getInvDataRef7());
			statement.setString(10, invData.getInvDataRef8());
			statement.setDouble(11, invData.getInvDataAmt1());
			statement.setDouble(12, invData.getInvDataAmt2());
			statement.setDouble(13, invData.getInvDataAmt3());
			statement.setDouble(14, invData.getInvDataAmt4());
			statement.setDouble(15, invData.getInvDataAmt5());
			statement.setString(16, invData.getInvLink());
			statement.setInt(17, invData.getClientId());
			statement.setInt(18, invData.getCompId());
			statement.setString(19, invData.getInvNo());
			if(statement.executeUpdate()>0)
				updated = true;
		}catch (Exception e) {
			System.out.println("Error updating CLIENTINVOICEDATA: " + e.toString());
		}
		return updated;
	}
	
	//JSON HELPERS
	public static StringBuilder getClientInvoiceDatasJSON(ArrayList<ClientInvoiceData> invData) {
		StringBuilder sb = new StringBuilder();
		sb.append("[\n");
		for(int i=0;i<invData.size();i++) {
			ClientInvoiceData data = invData.get(i);
			sb.append((i==0 ? "" : ",") + data.toJsonString());
		}
		sb.append("]");
		return sb;
	}
	public static StringBuilder getClientInvoiceDatasJSONCamelCase(ArrayList<ClientInvoiceData> invData) {
		StringBuilder sb = new StringBuilder();
		sb.append("[\n");
		for(int i=0;i<invData.size();i++) {
			ClientInvoiceData data = invData.get(i);
			sb.append((i==0 ? "" : ",") + data.toJsonStringCamelCase());
		}
		sb.append("]");
		return sb;
	}
}
