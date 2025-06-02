package za.co.payguru.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import za.co.payguru.model.ClientInvoiceQuery;
import za.co.payguru.util.DateUtil;

public class ClientInvoiceQueryDao {

	public static int getNextInvQueryId(Connection connection) {
		int invQueryId = 0;
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT NEXTVAL('clientinvoicequerys_invqueryid_seq') AS invqueryid");
		){
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				invQueryId = rs.getInt("invqueryid");
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error getting next invqueryid: " + e.toString());
		}
		return invQueryId;
	}
	public static ClientInvoiceQuery addClientInvoiceQuery(Connection connection, ClientInvoiceQuery clientInvoiceQuery) {
		clientInvoiceQuery.setInvQueryId(getNextInvQueryId(connection));
		clientInvoiceQuery.setInvQueryCreateDate(DateUtil.getCurrentCCYYMMDDDate());
		clientInvoiceQuery.setInvQueryCreateTime(DateUtil.getCurrentHHMMSSTime());
		try(
				PreparedStatement statement = connection.prepareStatement("INSERT INTO CLIENTINVOICEQUERYS VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		){
			statement.setInt(1, clientInvoiceQuery.getInvQueryId());
			statement.setString(2, clientInvoiceQuery.getInvNo());
			statement.setString(3, clientInvoiceQuery.getInvQueryNo());
			statement.setInt(4, clientInvoiceQuery.getInvQueryCompId());
			statement.setInt(5, clientInvoiceQuery.getInvQueryUserId());
			statement.setDate(6, clientInvoiceQuery.getInvQueryCreateDate());
			statement.setTime(7, clientInvoiceQuery.getInvQueryCreateTime());
			statement.setString(8, clientInvoiceQuery.getInvQueryRef1().trim().toUpperCase());
			statement.setString(9, clientInvoiceQuery.getInvQueryRef2().trim().toUpperCase());
			statement.setString(10, clientInvoiceQuery.getInvQueryRef3().trim().toUpperCase());
			statement.setString(11, clientInvoiceQuery.getInvQueryRef4().trim().toUpperCase());
			statement.setString(12, clientInvoiceQuery.getInvQueryComment().trim().toUpperCase());
			statement.setDouble(13, clientInvoiceQuery.getInvQueryAmt1());
			statement.setDouble(14, clientInvoiceQuery.getInvQueryAmt2());
			statement.setString(15, clientInvoiceQuery.getInvQueryType());
			statement.setDate(16, clientInvoiceQuery.getInvQueryDate1());
			int result = statement.executeUpdate();
			if(result<=0)
				clientInvoiceQuery = new ClientInvoiceQuery();
		}catch (Exception e) {
			System.out.println("Error adding to CLIENTINVOICEQUERYS: " +e.toString());
		}
		return clientInvoiceQuery;
	}
	
	public static boolean updateInvQuery(Connection connection, ClientInvoiceQuery query) {
		boolean updated = false;
		try(
				PreparedStatement statement = connection.prepareStatement("UPDATE CLIENTINVOICEQUERYS SET invqueryno = ?, invqueryuserid = ?, invqueryref1 = ?,"
						+ " invqueryref2 = ?, invqueryref3 = ?, invqueryref4 = ?, invquerycomment = ?, invqueryamt1 = ?, invqueryamt2 = ?, invquerytype = ?,"
						+ " invquerydate1 = ? WHERE invqueryid = ? AND invquerycompid = ?");
		){
			statement.setString(1, query.getInvQueryNo());
			statement.setInt(2, query.getInvQueryUserId());
			statement.setString(3, query.getInvQueryRef1().trim().toUpperCase());
			statement.setString(4, query.getInvQueryRef2().trim().toUpperCase());
			statement.setString(5, query.getInvQueryRef3().trim().toUpperCase());
			statement.setString(6, query.getInvQueryRef4().trim().toUpperCase());
			statement.setString(7, query.getInvQueryComment().trim().toUpperCase());
			statement.setDouble(8, query.getInvQueryAmt1());
			statement.setDouble(9, query.getInvQueryAmt2());
			statement.setString(10, query.getInvQueryType());
			statement.setDate(11, query.getInvQueryDate1());
			statement.setInt(12, query.getInvQueryId());
			statement.setInt(13, query.getInvQueryCompId());

			int result = statement.executeUpdate();
			if(result>0)
				updated = true;
		}catch (Exception e) {
			System.out.println("Error updating CLIENTINVOICEQUERYS: " +e.toString());
		}
		return updated;
	}
	
	public static boolean deleteInvQuery(Connection connection, ClientInvoiceQuery query) {
		boolean deleted = false;
		try(
				PreparedStatement statement = connection.prepareStatement("DELETE FROM CLIENTINVOICEQUERYS WHERE invqueryid = ? AND invquerycompid = ?");
		){

			statement.setInt(1, query.getInvQueryId());
			statement.setInt(2, query.getInvQueryCompId());

			int result = statement.executeUpdate();
			if(result>0)
				deleted = true;
		}catch (Exception e) {
			System.out.println("Error deleteing from CLIENTINVOICEQUERYS: " +e.toString());
		}
		return deleted;
	}
	public static ClientInvoiceQuery loadInvoiceQuery(Connection connection, int compid, int queryid){
		ClientInvoiceQuery query = new ClientInvoiceQuery();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENTINVOICEQUERYS WHERE invquerycompid = ? AND invqueryid = ?");
				){
			statement.setInt(1, compid);
			statement.setInt(2, queryid);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				query.setInvQueryId(rs.getInt("invqueryid"));
				query.setInvNo(rs.getString("invno"));
				query.setInvQueryNo(rs.getString("invqueryno"));
				query.setInvQueryCompId(rs.getInt("invquerycompid"));
				query.setInvQueryUserId(rs.getInt("invqueryuserid"));
				query.setInvQueryCreateDate(rs.getDate("invquerycreatedate"));
				query.setInvQueryCreateTime(rs.getTime("invquerycreatetime"));
				query.setInvQueryRef1(rs.getString("invqueryref1"));
				query.setInvQueryRef2(rs.getString("invqueryref2"));
				query.setInvQueryRef3(rs.getString("invqueryref3"));
				query.setInvQueryRef4(rs.getString("invqueryref4"));
				query.setInvQueryComment(rs.getString("invquerycomment"));
				query.setInvQueryAmt1(rs.getDouble("invqueryamt1"));
				query.setInvQueryAmt2(rs.getDouble("invqueryamt2"));
				query.setInvQueryType(rs.getString("invquerytype"));
				query.setInvQueryDate1(rs.getDate("invquerydate1"));
			}
			rs.close();
		}catch (Exception e) {
			System.out.println("Error querying CLIENTINVOICEQUERYS: " + e.toString());
		}
		return query;
	}
	public static ArrayList<ClientInvoiceQuery> getInvoiceQuerys(Connection connection, int compid, String invno){
		ArrayList<ClientInvoiceQuery> querys = new ArrayList<ClientInvoiceQuery>();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENTINVOICEQUERYS WHERE invquerycompid = ? AND invno = ?");
				){
			statement.setInt(1, compid);
			statement.setString(2, invno);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				ClientInvoiceQuery query = new ClientInvoiceQuery();
				query.setInvQueryId(rs.getInt("invqueryid"));
				query.setInvNo(rs.getString("invno"));
				query.setInvQueryNo(rs.getString("invqueryno"));
				query.setInvQueryCompId(rs.getInt("invquerycompid"));
				query.setInvQueryUserId(rs.getInt("invqueryuserid"));
				query.setInvQueryCreateDate(rs.getDate("invquerycreatedate"));
				query.setInvQueryCreateTime(rs.getTime("invquerycreatetime"));
				query.setInvQueryRef1(rs.getString("invqueryref1"));
				query.setInvQueryRef2(rs.getString("invqueryref2"));
				query.setInvQueryRef3(rs.getString("invqueryref3"));
				query.setInvQueryRef4(rs.getString("invqueryref4"));
				query.setInvQueryComment(rs.getString("invquerycomment"));
				query.setInvQueryAmt1(rs.getDouble("invqueryamt1"));
				query.setInvQueryAmt2(rs.getDouble("invqueryamt2"));
				query.setInvQueryType(rs.getString("invquerytype"));
				query.setInvQueryDate1(rs.getDate("invquerydate1"));
				querys.add(query);
			}
			rs.close();
		}catch (Exception e) {
			System.out.println("Error querying CLIENTINVOICEQUERYS: " + e.toString());
		}
		return querys;
	}

	public static StringBuilder searchCompanyPolicyClaims(Connection connection, int compid, Date from, Date to, int compinternalid){
		JSONArray querys = new JSONArray();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT CLIENTINVOICEQUERYS.invqueryid,CLIENTINVOICEQUERYS.invno,CLIENTINVOICEQUERYS.invqueryno,"
						+ "CLIENTINVOICEQUERYS.invquerycompid,CLIENTINVOICEQUERYS.invqueryuserid,CLIENTINVOICEQUERYS.invquerycreatedate,CLIENTINVOICEQUERYS.invquerycreatetime,"
						+ "CLIENTINVOICEQUERYS.invqueryref1,CLIENTINVOICEQUERYS.invqueryref2, CLIENTINVOICEQUERYS.invqueryref3, CLIENTINVOICEQUERYS.invqueryref4,"
						+ "CLIENTINVOICEQUERYS.invquerycomment,CLIENTINVOICEQUERYS.invqueryamt1,CLIENTINVOICEQUERYS.invqueryamt2,CLIENTINVOICEQUERYS.invquerytype,"
						+ "CLIENTINVOICEQUERYS.invquerydate1, CLIENTS.clientcellno, CLIENTINVOICES.salechannel, CLIENTINVOICES.salesubchannel FROM CLIENTINVOICEQUERYS JOIN CLIENTINVOICES ON "
						+ "(CLIENTINVOICEQUERYS.invquerycompid = CLIENTINVOICES.compid AND CLIENTINVOICEQUERYS.invno = CLIENTINVOICES.invno)"
						+ " JOIN CLIENTS ON (CLIENTINVOICES.clientid = CLIENTS.clientid) JOIN COMPANYPRODUCTS ON (CLIENTINVOICES.compid = COMPANYPRODUCTS.compid AND CLIENTINVOICES.prodid = COMPANYPRODUCTS.prodid)"
						+ "WHERE invquerycompid = ? AND invquerycreatedate >= ? AND invquerycreatedate <= ? "
						+ "AND invquerytype = ?"+( (compinternalid>0) ? (" AND COMPANYPRODUCTS.compinternalid = " + compinternalid + " ") : " " )+"ORDER BY invquerycreatedate DESC, invquerycreatetime DESC");
				){
			statement.setInt(1, compid);
			statement.setDate(2, from);
			statement.setDate(3, to);
			statement.setString(4, ClientInvoiceQuery.INV_QUERY_TYPE_CLAIM);
			
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				JSONObject query = new JSONObject();
				query.put("invqueryid", rs.getInt("invqueryid"));
				query.put("invno", rs.getString("invno"));
				query.put("invqueryno", rs.getString("invqueryno"));
				query.put("invquerycompid", rs.getInt("invquerycompid"));
				query.put("invqueryuserid", rs.getInt("invqueryuserid"));
				query.put("invquerycreatedate", rs.getDate("invquerycreatedate"));
				query.put("invquerycreatetime", rs.getTime("invquerycreatetime"));
				query.put("invqueryref1", rs.getString("invqueryref1"));
				query.put("invqueryref2", rs.getString("invqueryref2"));
				query.put("invqueryref3", rs.getString("invqueryref3"));
				query.put("invqueryref4", rs.getString("invqueryref4"));
				query.put("invquerycomment", rs.getString("invquerycomment"));
				query.put("invqueryamt1", rs.getDouble("invqueryamt1"));
				query.put("invqueryamt2", rs.getDouble("invqueryamt2"));
				query.put("invquerytype", rs.getString("invquerytype"));
				query.put("invquerydate1", rs.getDate("invquerydate1"));
				query.put("salechannel", rs.getString("salechannel"));
				query.put("salesubchannel", rs.getString("salesubchannel"));
				querys.put(query);
			}
			rs.close();
		}catch (Exception e) {
			System.out.println("Error querying CLIENTINVOICEQUERYS: " + e.toString());
		}
		return new StringBuilder(querys.toString());
	}

	public static StringBuilder getInvoiceQuerysJSON(ArrayList<ClientInvoiceQuery> invQuerys) {
		JSONArray jsonData = new JSONArray();
		for(int i=0;i<invQuerys.size();i++) {
			ClientInvoiceQuery query = invQuerys.get(i);
			JSONObject jsonQuery = new JSONObject();
			try {
				jsonQuery.put("invqueryid", query.getInvQueryId());
				jsonQuery.put("invno", query.getInvNo());
				jsonQuery.put("invqueryno", query.getInvQueryNo());
				jsonQuery.put("invquerycompid", query.getInvQueryCompId());
				jsonQuery.put("invqueryuserid", query.getInvQueryId());
				jsonQuery.put("invquerycreatedate", query.getInvQueryCreateDate());
				jsonQuery.put("invquerycreatetime", query.getInvQueryCreateTime());
				jsonQuery.put("invqueryref1", query.getInvQueryRef1());
				jsonQuery.put("invqueryref2", query.getInvQueryRef2());
				jsonQuery.put("invqueryref3", query.getInvQueryRef3());
				jsonQuery.put("invqueryref4", query.getInvQueryRef4());
				jsonQuery.put("invquerycomment", query.getInvQueryComment());
				jsonQuery.put("invqueryamt1", query.getInvQueryAmt1());
				jsonQuery.put("invqueryamt2", query.getInvQueryAmt2());
				jsonQuery.put("invquerytype", query.getInvQueryType());
				jsonQuery.put("invquerydate1", query.getInvQueryDate1());
			}catch (Exception e) {System.out.println("JSON Error:" + e.toString()); continue;}
			jsonData.put(jsonQuery);
		}
		return new StringBuilder(jsonData.toString());
	}
	
}
