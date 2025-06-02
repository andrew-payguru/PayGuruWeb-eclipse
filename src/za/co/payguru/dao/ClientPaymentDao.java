package za.co.payguru.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import za.co.payguru.model.ClientPayment;

public class ClientPaymentDao {

	public static ArrayList<String> getCompBankClientPayRefs(Connection connection, int compid, int bankid, String startDate, String endDate){
		ArrayList<String> clientPayments = new ArrayList<String>();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT payref FROM CLIENTPAYMENTS WHERE compid = ? AND bankid = ? AND paydate >= ? AND paydate <= ?");
		){
			statement.setInt(1, compid);
			statement.setInt(2, bankid);
			statement.setString(3, startDate.replace('-', '/'));
			statement.setString(4, endDate.replace('-', '/'));
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				String payref = rs.getString("payref");
				clientPayments.add(payref);
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying CLIENTPAYMENTS: " + e.toString());
		}
		return clientPayments;
	}
	
	public static ArrayList<ClientPayment> getCompBankClientPayments(Connection connection, int compid, int bankid, String startDate, String endDate){
		ArrayList<ClientPayment> clientPayments = new ArrayList<ClientPayment>();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENTPAYMENTS WHERE compid = ? AND bankid = ? AND paydate >= ? AND paydate <= ? AND paytransferstatus != ? AND payreversedate = ? order by paydate desc, payid desc");
		){
			statement.setInt(1, compid);
			statement.setInt(2, bankid);
			statement.setString(3, startDate.replace('-', '/'));
			statement.setString(4, endDate.replace('-', '/'));
			statement.setInt(5, ClientPayment.TRANSFER_STATUS_DONE);
			statement.setString(6, ClientPayment.DEFAULT_REVERSE_DATE);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				ClientPayment clientPayment = new ClientPayment();
				clientPayment.setPayId(rs.getLong("payid"));
				clientPayment.setClientId(rs.getInt("clientid"));
				clientPayment.setCompId(rs.getInt("compid"));
				clientPayment.setProdId(rs.getInt("prodid"));
				clientPayment.setBankId(rs.getInt("bankid"));
				clientPayment.setCreateDate(rs.getString("createdate"));
				clientPayment.setCreateTime(rs.getString("createtime"));
				clientPayment.setStatus(rs.getInt("status"));
				clientPayment.setStatusDate(rs.getString("statusdate"));
				clientPayment.setStatusTime(rs.getString("statustime"));
				clientPayment.setPayDate(rs.getString("paydate"));
				clientPayment.setPayAmt(rs.getDouble("payamt"));
				clientPayment.setPayRef(rs.getString("payref"));
				clientPayment.setPayTran(rs.getDouble("paytran"));
				clientPayment.setPayTransferStatus(rs.getInt("paytransferstatus"));
				clientPayment.setPayTransferStatusDate(rs.getString("paytransferstatusdate"));
				clientPayment.setPayTransferStatusTime(rs.getString("paytransferstatustime"));
				clientPayment.setPayTransferStatusUserId(rs.getString("paytransferstatususerid"));
				clientPayment.setPayTransferRef(rs.getString("paytransferref"));
				clientPayment.setPayTransferClientId(rs.getDouble("paytransferclientid"));
				clientPayment.setPayTransferPayId(rs.getDouble("paytransferpayid"));
				clientPayments.add(clientPayment);
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying CLIENTPAYMENTS: " + e.toString());
		}
		return clientPayments;
	}
	
	public static JSONArray getCompBankClientPaymentsWithClientDetails(Connection connection, int compid, int bankid, String startDate, String endDate){
		JSONArray clientPayments = new JSONArray();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT cp.payid,cp.clientid,cp.compid,cp.prodid,cp.bankid,cp.createdate,cp.createtime,cp.status,cp.statusdate,cp.statustime,cp.paydate,cp.payamt,cp.payref,cp.paytran,cp.paytransferstatus,cp.paytransferstatusdate,cp.paytransferstatustime,cp.paytransferstatususerid,cp.paytransferref,cp.paytransferclientid,cp.paytransferpayid,cp.payreversedate,cp.payreversetime,cp.payreversedesc,cp.payreverseuserid,c.clientcellno,c.clientname,c.clientsurname,c.clientemail,c2.clientname as tranclientname,c2.clientsurname as tranclientsurname FROM CLIENTPAYMENTS cp JOIN CLIENTS c ON (cp.clientid = c.clientid) LEFT JOIN CLIENTS c2 ON (cp.paytransferclientid = c2.clientid) WHERE cp.compid = ? AND cp.bankid = ? AND cp.paydate >= ? AND cp.paydate <= ? AND cp.payreversedate = ? order by cp.paydate desc, cp.payid desc");
		){
			statement.setInt(1, compid);
			statement.setInt(2, bankid);
			statement.setString(3, startDate.replace('-', '/'));
			statement.setString(4, endDate.replace('-', '/'));
			statement.setString(5, ClientPayment.DEFAULT_REVERSE_DATE);
			System.out.println(statement.toString());
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				JSONObject clientPayment = new JSONObject();
				clientPayment.put("payid" , rs.getLong("payid"));
				clientPayment.put("clientid", rs.getInt("clientid"));
				clientPayment.put("compid", rs.getInt("compid"));
				clientPayment.put("prodid", rs.getInt("prodid"));
				clientPayment.put("bankid", rs.getInt("bankid"));
				clientPayment.put("createdate", rs.getString("createdate"));
				clientPayment.put("createtime", rs.getString("createtime"));
				clientPayment.put("status", rs.getInt("status"));
				clientPayment.put("statusdate", rs.getString("statusdate"));
				clientPayment.put("statustime", rs.getString("statustime"));
				clientPayment.put("paydate", rs.getString("paydate"));
				clientPayment.put("payamt", rs.getDouble("payamt"));
				clientPayment.put("payref", rs.getString("payref"));
				clientPayment.put("paytran", rs.getDouble("paytran"));
				clientPayment.put("paytransferstatus", rs.getInt("paytransferstatus"));
				clientPayment.put("paytransferstatusdate", rs.getString("paytransferstatusdate"));
				clientPayment.put("paytransferstatustime", rs.getString("paytransferstatustime"));
				clientPayment.put("paytransferstatususerid", rs.getString("paytransferstatususerid"));
				clientPayment.put("paytransferref", rs.getString("paytransferref"));
				clientPayment.put("paytransferclientid", rs.getDouble("paytransferclientid"));
				clientPayment.put("paytransferpayid", rs.getDouble("paytransferpayid"));
				clientPayment.put("clientname", rs.getString("clientname"));
				clientPayment.put("clientsurname", rs.getString("clientsurname"));
				clientPayment.put("clientemail", rs.getString("clientemail"));
				clientPayment.put("clientcellno", rs.getString("clientcellno"));
				String tranclientname = rs.getString("tranclientname");
				String tranclientsurname = rs.getString("tranclientsurname");
				clientPayment.put("tranclientname", (tranclientname==null?"":tranclientname));
				clientPayment.put("tranclientsurname", (tranclientsurname==null)?"":tranclientsurname);
				clientPayments.put(clientPayment);
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying CLIENTPAYMENTS: " + e.toString());
		}
		return clientPayments;
	}
	
	public static ArrayList<ClientPayment> getCompClientPaymentsLast5Days(int compId, Connection connection){
		ArrayList<ClientPayment> clientPayments = new ArrayList<ClientPayment>();
		try(
				PreparedStatement statement = connection.prepareStatement("");
		){
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				ClientPayment clientPayment = new ClientPayment();
				clientPayment.setPayId(rs.getLong("payid"));
				clientPayment.setClientId(rs.getInt("clientid"));
				clientPayment.setCompId(rs.getInt("compid"));
				clientPayment.setProdId(rs.getInt("prodid"));
				clientPayment.setBankId(rs.getInt("bankid"));
				clientPayment.setCreateDate(rs.getString("createdate"));
				clientPayment.setCreateTime(rs.getString("createtime"));
				clientPayment.setStatus(rs.getInt("status"));
				clientPayment.setStatusDate(rs.getString("statusdate"));
				clientPayment.setStatusTime(rs.getString("statustime"));
				clientPayment.setPayDate(rs.getString("paydate"));
				clientPayment.setPayAmt(rs.getDouble("payamt"));
				clientPayment.setPayRef(rs.getString("payref"));
				clientPayment.setPayTran(rs.getDouble("paytran"));
				clientPayment.setPayTransferStatus(rs.getInt("paytransferstatus"));
				clientPayment.setPayTransferStatusDate(rs.getString("paytransferstatusdate"));
				clientPayment.setPayTransferStatusTime(rs.getString("paytransferstatustime"));
				clientPayment.setPayTransferStatusUserId(rs.getString("paytransferstatususerid"));
				clientPayment.setPayTransferRef(rs.getString("paytransferref"));
				clientPayment.setPayTransferClientId(rs.getDouble("paytransferclientid"));
				clientPayment.setPayTransferPayId(rs.getDouble("paytransferpayid"));
				clientPayments.add(clientPayment);
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying CLIENTPAYMENTS: " + e.toString());
		}
		return clientPayments;
	}
	
	public static StringBuilder getClientPaymentsJson(ArrayList<ClientPayment> payments) {
		StringBuilder sb = new StringBuilder();
		sb.append("[\n");
		for(int i=0;i<payments.size();i++) {
			ClientPayment payment = payments.get(i);
			sb.append("{\n");
			sb.append("\"payid\" : " + payment.getPayId()+",\n");
			sb.append("\"clientid\" : " + payment.getClientId()+",\n");
			sb.append("\"compid\" : " + payment.getCompId()+",\n");
			sb.append("\"prodid\" : " + payment.getProdId()+",\n");
			sb.append("\"bankid\" : " + payment.getBankId()+",\n");
			sb.append("\"createdate\" : \"" + payment.getCreateDate()+"\",\n");
			sb.append("\"createtime\" : \"" + payment.getCreateTime()+"\",\n");
			sb.append("\"status\" : \"" + payment.getStatus()+"\",\n");
			sb.append("\"statusdate\" : \"" + payment.getStatusDate()+"\",\n");
			sb.append("\"statustime\" : \"" + payment.getStatusTime()+"\",\n");
			sb.append("\"paydate\" : \"" + payment.getPayDate()+"\",\n");
			sb.append("\"payamt\" : " + payment.getPayAmt()+",\n");
			sb.append("\"payref\" : \"" + payment.getPayRef()+"\",\n");
			sb.append("\"paytran\" : " + payment.getPayTran()+",\n");
			sb.append("\"paytransferstatus\" : \"" + payment.getPayTransferStatus()+"\",\n");
			sb.append("\"paytransferstatusdate\" : \"" + payment.getPayTransferStatusDate()+"\",\n");
			sb.append("\"paytransferstatustime\" : \"" + payment.getPayTransferStatusTime()+"\",\n");
			sb.append("\"paytransferstatususerid\" : \"" + payment.getPayTransferStatusUserId()+"\",\n");
			sb.append("\"paytransferref\" : \"" + payment.getPayTransferRef()+"\",\n");
			sb.append("\"paytransferclientid\" : " + payment.getPayTransferClientId()+",\n");
			sb.append("\"paytransferpayid\" : " + payment.getPayTransferPayId()+",\n");
			sb.append("\"payreversedate\" : \"" + payment.getPayReverseDate()+"\",\n");
			sb.append("\"payreversetime\" : \"" + payment.getPayReverseTime()+"\",\n");
			sb.append("\"payreversedesc\" : \"" + payment.getPayReverseDesc()+"\",\n");
			sb.append("\"paytransferuserid\" : " + payment.getPayReverseUserId()+"\n");
			sb.append("}"+(i==payments.size()-1 ? "" : ",")+"\n"); 
		}
		sb.append("]");
		return sb;
	}
}
