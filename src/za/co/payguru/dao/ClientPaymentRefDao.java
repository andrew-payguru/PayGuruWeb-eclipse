package za.co.payguru.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import za.co.payguru.model.ClientPaymentRef;
import za.co.payguru.util.DateUtil;
import za.co.payguru.util.Util;

public class ClientPaymentRefDao {
	public static ArrayList<ClientPaymentRef> loadActiveClientPaymentRefs(Connection connection, int compid, int clientid){
		ArrayList<ClientPaymentRef> payRefs = new ArrayList<ClientPaymentRef>();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENTPAYMENTREFS WHERE compid = ? AND clientid = ? AND status = ? ORDER BY createdate desc, invno desc");
		){
			statement.setInt(1, compid);
			statement.setInt(2, clientid);
			statement.setInt(3, Util.parseInt(ClientPaymentRef.ACTIVE,1));
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				ClientPaymentRef clientPaymentRef = new ClientPaymentRef();
				clientPaymentRef.setCompid(rs.getInt("compid"));
				clientPaymentRef.setPayref(rs.getString("payref").trim());
				clientPaymentRef.setClientid(rs.getInt("clientid"));
				clientPaymentRef.setProdid(rs.getInt("prodid"));
				clientPaymentRef.setProdref(rs.getString("prodref").trim());
				clientPaymentRef.setPayamt(rs.getDouble("payamt"));
				clientPaymentRef.setInvno(rs.getString("invno").trim());
				clientPaymentRef.setCreatedate(rs.getString("createdate").trim());
				clientPaymentRef.setCreatetime(rs.getString("createtime").trim());
				clientPaymentRef.setStatus(rs.getString("status").trim());
				clientPaymentRef.setStatusdate( rs.getString("statusdate").trim());
				clientPaymentRef.setStatustime(rs.getString("statustime").trim());
				payRefs.add(clientPaymentRef);
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying table CLIENTPAYMENTREFS: " + e.toString());
		}
		return payRefs;
	}
	public static ClientPaymentRef loadPaymentRef(Connection connection, int compid, String payref) {
		ClientPaymentRef clientPaymentRef = new ClientPaymentRef();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENTPAYMENTREFS WHERE compid = ? AND payref = ?");
		){
			statement.setInt(1, compid);
			statement.setString(2, payref);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				clientPaymentRef.setCompid(rs.getInt("compid"));
				clientPaymentRef.setPayref(rs.getString("payref").trim());
				clientPaymentRef.setClientid(rs.getInt("clientid"));
				clientPaymentRef.setProdid(rs.getInt("prodid"));
				clientPaymentRef.setProdref(rs.getString("prodref").trim());
				clientPaymentRef.setPayamt(rs.getDouble("payamt"));
				clientPaymentRef.setInvno(rs.getString("invno").trim());
				clientPaymentRef.setCreatedate(rs.getString("createdate").trim());
				clientPaymentRef.setCreatetime(rs.getString("createtime").trim());
				clientPaymentRef.setStatus(rs.getString("status").trim());
				clientPaymentRef.setStatusdate( rs.getString("statusdate").trim());
				clientPaymentRef.setStatustime(rs.getString("statustime").trim());
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying table CLIENTPAYMENTREFS: " + e.toString());
		}
		return clientPaymentRef;
	}
	public static ClientPaymentRef loadActivePaymentRef(Connection connection, int compid, String payref) {
		ClientPaymentRef clientPaymentRef = new ClientPaymentRef();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENTPAYMENTREFS WHERE compid = ? AND payref = ? AND status = '"+ClientPaymentRef.ACTIVE+"'");
		){
			statement.setInt(1, compid);
			statement.setString(2, payref);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				clientPaymentRef.setCompid(rs.getInt("compid"));
				clientPaymentRef.setPayref(rs.getString("payref").trim());
				clientPaymentRef.setClientid(rs.getInt("clientid"));
				clientPaymentRef.setProdid(rs.getInt("prodid"));
				clientPaymentRef.setProdref(rs.getString("prodref").trim());
				clientPaymentRef.setPayamt(rs.getDouble("payamt"));
				clientPaymentRef.setInvno(rs.getString("invno").trim());
				clientPaymentRef.setCreatedate(rs.getString("createdate").trim());
				clientPaymentRef.setCreatetime(rs.getString("createtime").trim());
				clientPaymentRef.setStatus(rs.getString("status").trim());
				clientPaymentRef.setStatusdate( rs.getString("statusdate").trim());
				clientPaymentRef.setStatustime(rs.getString("statustime").trim());
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying table CLIENTPAYMENTREFS: " + e.toString());
		}
		return clientPaymentRef;
	}
	public static ClientPaymentRef loadPaymentRefByInvoiceNo(Connection connection, int compid, int clientid, String invno) {
		ClientPaymentRef clientPaymentRef = new ClientPaymentRef();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENTPAYMENTREFS WHERE compid = ? AND clientid = ? AND invno = ? and status = ? order by createdate desc, createtime desc limit 1");
		){
			statement.setInt(1, compid);
			statement.setInt(2, clientid);
			statement.setString(3, invno);
			statement.setInt(4, Util.parseInt(ClientPaymentRef.ACTIVE,0));
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				clientPaymentRef.setCompid(rs.getInt("compid"));
				clientPaymentRef.setPayref(rs.getString("payref").trim());
				clientPaymentRef.setClientid(rs.getInt("clientid"));
				clientPaymentRef.setProdid(rs.getInt("prodid"));
				clientPaymentRef.setProdref(rs.getString("prodref").trim());
				clientPaymentRef.setPayamt(rs.getDouble("payamt"));
				clientPaymentRef.setInvno(rs.getString("invno").trim());
				clientPaymentRef.setCreatedate(rs.getString("createdate").trim());
				clientPaymentRef.setCreatetime(rs.getString("createtime").trim());
				clientPaymentRef.setStatus(rs.getString("status").trim());
				clientPaymentRef.setStatusdate( rs.getString("statusdate").trim());
				clientPaymentRef.setStatustime(rs.getString("statustime").trim());
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying table CLIENTPAYMENTREFS: " + e.toString());
		}
		return clientPaymentRef;
	} 
	public static boolean updatePaymentRefStatus(Connection connection, ClientPaymentRef clientPaymentRef) {
		boolean updated = false;
		try(
				PreparedStatement statement = connection.prepareStatement("UPDATE CLIENTPAYMENTREFS SET status = ?, statusdate = ?, statustime = ? WHERE compid = ? AND payref = ?");
		){
			statement.setInt(1, Util.parseInt(clientPaymentRef.getStatus(),0));
			statement.setString(2, DateUtil.getCurrentCCYYMMDDStr());
			statement.setString(3, DateUtil.getCurrentHHMMSSStr());
			statement.setInt(4, clientPaymentRef.getCompid());
			statement.setString(5, clientPaymentRef.getPayref());
			if(statement.executeUpdate()>0)
				updated = true;
		}catch (Exception e) {
			System.out.println("Error updating table CLIENTPAYMENTREFS: " + e.toString());
		}
		return updated;
	}
	
	public static StringBuilder getPaymentRefsJson(ArrayList<ClientPaymentRef> payrefs) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(int i=0;i<payrefs.size();i++) {
			ClientPaymentRef payref = payrefs.get(i);
			sb.append((i==0 ? "" : ",") + payref.toJsonString());
		}
		sb.append("]");
		return sb;
	}
}
