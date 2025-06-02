package za.co.payguru.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import za.co.payguru.model.Client;
import za.co.payguru.model.ClientProduct;
import za.co.payguru.model.Company;
import za.co.payguru.util.DateUtil;

public class ClientProductDao {

	public static ClientProduct loadClientProd(Connection connection, int clientid, int compid, int prodid, String prodref) {
		ClientProduct clientProduct = new ClientProduct();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENTPRODUCTS WHERE clientid = ? AND compid = ? AND prodid = ? AND prodref = ?");
				){
			statement.setInt(1, clientid);
			statement.setInt(2, compid);
			statement.setInt(3, prodid);
			statement.setString(4, prodref);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				clientProduct.setClientId(rs.getInt("clientid"));
				clientProduct.setCompId(rs.getInt("compid"));
				clientProduct.setProdId(rs.getInt("prodid"));
				clientProduct.setCreateDate(rs.getString("createdate"));
				clientProduct.setCreateTime(rs.getString("createtime"));
				clientProduct.setStatus(rs.getInt("status"));
				clientProduct.setStatusDate(rs.getString("statusdate"));
				clientProduct.setStatusTime(rs.getString("statustime"));
				clientProduct.setProdDisc(rs.getDouble("proddisc"));
				clientProduct.setProdRsp(rs.getDouble("prodrsp"));
				clientProduct.setProdCycle(rs.getDouble("prodcycle"));
				clientProduct.setProdRef(rs.getString("prodref"));
				clientProduct.setProdData(rs.getString("proddata"));
				clientProduct.setDonorid(rs.getInt("donorid"));
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying table CLIENTPRODUCTS: " + e.toString());
		}
		return clientProduct;
	}
	public static ClientProduct loadMasterClientWallet(Connection connection, int clientid) {
		ClientProduct clientProduct = new ClientProduct();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENTPRODUCTS WHERE clientid = ? AND compid = ? AND prodid = ? AND prodref = ? AND status = ?");
				){
			statement.setInt(1, clientid);
			statement.setInt(2, -1);
			statement.setInt(3, -1);
			statement.setString(4, "-1");
			statement.setInt(5, ClientProduct.ACTIVE);

			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				clientProduct.setClientId(rs.getInt("clientid"));
				clientProduct.setCompId(rs.getInt("compid"));
				clientProduct.setProdId(rs.getInt("prodid"));
				clientProduct.setCreateDate(rs.getString("createdate"));
				clientProduct.setCreateTime(rs.getString("createtime"));
				clientProduct.setStatus(rs.getInt("status"));
				clientProduct.setStatusDate(rs.getString("statusdate"));
				clientProduct.setStatusTime(rs.getString("statustime"));
				clientProduct.setProdDisc(rs.getDouble("proddisc"));
				clientProduct.setProdRsp(rs.getDouble("prodrsp"));
				clientProduct.setProdCycle(rs.getDouble("prodcycle"));
				clientProduct.setProdRef(rs.getString("prodref"));
				clientProduct.setProdData(rs.getString("proddata"));
				clientProduct.setDonorid(rs.getInt("donorid"));
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying table CLIENTPRODUCTS: " + e.toString());
		}
		return clientProduct;
	}
	public static ArrayList<ClientProduct> getClientProducts(Connection connection, int compid, int clientid){
		ArrayList<ClientProduct> clientproducts = new ArrayList<ClientProduct>();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENTPRODUCTS WHERE clientid = ? AND compid = ? AND status = ?");
				){
			statement.setInt(1, clientid);
			statement.setInt(2, compid);
			statement.setInt(3, ClientProduct.ACTIVE);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				ClientProduct clientProduct = new ClientProduct();
				clientProduct.setClientId(rs.getInt("clientid"));
				clientProduct.setCompId(rs.getInt("compid"));
				clientProduct.setProdId(rs.getInt("prodid"));
				clientProduct.setCreateDate(rs.getString("createdate"));
				clientProduct.setCreateTime(rs.getString("createtime"));
				clientProduct.setStatus(rs.getInt("status"));
				clientProduct.setStatusDate(rs.getString("statusdate"));
				clientProduct.setStatusTime(rs.getString("statustime"));
				clientProduct.setProdDisc(rs.getDouble("proddisc"));
				clientProduct.setProdRsp(rs.getDouble("prodrsp"));
				clientProduct.setProdCycle(rs.getDouble("prodcycle"));
				clientProduct.setProdRef(rs.getString("prodref"));
				clientProduct.setProdData(rs.getString("proddata"));
				clientProduct.setDonorid(rs.getInt("donorid"));
				clientproducts.add(clientProduct);
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying table CLIENTPRODUCTS: " + e.toString());
		}
		return clientproducts;
	}
	//PAYGURU WALLET SPECIFIC
	public static ArrayList<ClientProduct> getClientWallets(Connection connection,int clientid){
		ArrayList<ClientProduct> clientproducts = new ArrayList<ClientProduct>();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENTPRODUCTS WHERE clientid = ? AND compid = ? AND status = ? ORDER BY prodref ASC");
				){
			statement.setInt(1, clientid);
			statement.setInt(2, ClientProduct.PAYGURU_WALLET);
			statement.setInt(3, ClientProduct.ACTIVE);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				ClientProduct clientProduct = new ClientProduct();
				clientProduct.setClientId(rs.getInt("clientid"));
				clientProduct.setCompId(rs.getInt("compid"));
				clientProduct.setProdId(rs.getInt("prodid"));
				clientProduct.setCreateDate(rs.getString("createdate"));
				clientProduct.setCreateTime(rs.getString("createtime"));
				clientProduct.setStatus(rs.getInt("status"));
				clientProduct.setStatusDate(rs.getString("statusdate"));
				clientProduct.setStatusTime(rs.getString("statustime"));
				clientProduct.setProdDisc(rs.getDouble("proddisc"));
				clientProduct.setProdRsp(rs.getDouble("prodrsp"));
				clientProduct.setProdCycle(rs.getDouble("prodcycle"));
				clientProduct.setProdRef(rs.getString("prodref"));
				clientProduct.setProdData(rs.getString("proddata"));
				clientProduct.setDonorid(rs.getInt("donorid"));
				clientproducts.add(clientProduct);
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying table CLIENTPRODUCTS: " + e.toString());
		}
		return clientproducts;
	}
	public static ArrayList<ClientProduct> getClientDonorWallets(Connection connection,int clientid, int donorid){
		ArrayList<ClientProduct> clientproducts = new ArrayList<ClientProduct>();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENTPRODUCTS WHERE clientid = ? AND compid = ? AND prodid = ? AND status = ? AND donorid = ? ORDER BY prodref ASC");
				){
			statement.setInt(1, clientid);
			statement.setInt(2, Company.PAYGURU_COMPID);
			statement.setInt(3, ClientProduct.PAYGURU_WALLET);
			statement.setInt(4, ClientProduct.ACTIVE);
			statement.setInt(5, donorid);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				ClientProduct clientProduct = new ClientProduct();
				clientProduct.setClientId(rs.getInt("clientid"));
				clientProduct.setCompId(rs.getInt("compid"));
				clientProduct.setProdId(rs.getInt("prodid"));
				clientProduct.setCreateDate(rs.getString("createdate"));
				clientProduct.setCreateTime(rs.getString("createtime"));
				clientProduct.setStatus(rs.getInt("status"));
				clientProduct.setStatusDate(rs.getString("statusdate"));
				clientProduct.setStatusTime(rs.getString("statustime"));
				clientProduct.setProdDisc(rs.getDouble("proddisc"));
				clientProduct.setProdRsp(rs.getDouble("prodrsp"));
				clientProduct.setProdCycle(rs.getDouble("prodcycle"));
				clientProduct.setProdRef(rs.getString("prodref"));
				clientProduct.setProdData(rs.getString("proddata"));
				clientProduct.setDonorid(rs.getInt("donorid"));
				clientproducts.add(clientProduct);
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying table CLIENTPRODUCTS: " + e.toString());
		}
		return clientproducts;
	}
	public static ArrayList<ClientProduct> getAllCompanyClientWallets(Connection connection, int clientid){
		ArrayList<ClientProduct> clientproducts = new ArrayList<ClientProduct>();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENTPRODUCTS WHERE clientid = ? AND compid != ? AND status = ? ORDER BY compid,prodref ASC");
				){
			statement.setInt(1, clientid);
			statement.setInt(2, ClientProduct.PAYGURU_WALLET);
			statement.setInt(3, ClientProduct.ACTIVE);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				ClientProduct clientProduct = new ClientProduct();
				clientProduct.setClientId(rs.getInt("clientid"));
				clientProduct.setCompId(rs.getInt("compid"));
				clientProduct.setProdId(rs.getInt("prodid"));
				clientProduct.setCreateDate(rs.getString("createdate"));
				clientProduct.setCreateTime(rs.getString("createtime"));
				clientProduct.setStatus(rs.getInt("status"));
				clientProduct.setStatusDate(rs.getString("statusdate"));
				clientProduct.setStatusTime(rs.getString("statustime"));
				clientProduct.setProdDisc(rs.getDouble("proddisc"));
				clientProduct.setProdRsp(rs.getDouble("prodrsp"));
				clientProduct.setProdCycle(rs.getDouble("prodcycle"));
				clientProduct.setProdRef(rs.getString("prodref"));
				clientProduct.setProdData(rs.getString("proddata"));
				clientProduct.setDonorid(rs.getInt("donorid"));
				clientproducts.add(clientProduct);
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying table CLIENTPRODUCTS: " + e.toString());
		}
		return clientproducts;
	}
	public static ArrayList<ClientProduct> getWallets(Connection connection, int clientid){
		ArrayList<ClientProduct> clientproducts = new ArrayList<ClientProduct>();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENTPRODUCTS WHERE clientid = ? AND status = ? ORDER BY compid,prodref ASC");
				){
			statement.setInt(1, clientid);
			statement.setInt(2, ClientProduct.ACTIVE);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				ClientProduct clientProduct = new ClientProduct();
				clientProduct.setClientId(rs.getInt("clientid"));
				clientProduct.setCompId(rs.getInt("compid"));
				clientProduct.setProdId(rs.getInt("prodid"));
				clientProduct.setCreateDate(rs.getString("createdate"));
				clientProduct.setCreateTime(rs.getString("createtime"));
				clientProduct.setStatus(rs.getInt("status"));
				clientProduct.setStatusDate(rs.getString("statusdate"));
				clientProduct.setStatusTime(rs.getString("statustime"));
				clientProduct.setProdDisc(rs.getDouble("proddisc"));
				clientProduct.setProdRsp(rs.getDouble("prodrsp"));
				clientProduct.setProdCycle(rs.getDouble("prodcycle"));
				clientProduct.setProdRef(rs.getString("prodref"));
				clientProduct.setProdData(rs.getString("proddata"));
				clientProduct.setDonorid(rs.getInt("donorid"));
				clientproducts.add(clientProduct);
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying table CLIENTPRODUCTS: " + e.toString());
		}
		return clientproducts;
	}
	public static ArrayList<ClientProduct> getMasterWallets(Connection connection, int clientid){
		ArrayList<ClientProduct> clientproducts = new ArrayList<ClientProduct>();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENTPRODUCTS WHERE clientid = ? AND prodref = ? AND status = ? ORDER BY compid,prodref ASC");
				){
			statement.setInt(1, clientid);
			statement.setString(2, "-1");
			statement.setInt(3, ClientProduct.ACTIVE);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				ClientProduct clientProduct = new ClientProduct();
				clientProduct.setClientId(rs.getInt("clientid"));
				clientProduct.setCompId(rs.getInt("compid"));
				clientProduct.setProdId(rs.getInt("prodid"));
				clientProduct.setCreateDate(rs.getString("createdate"));
				clientProduct.setCreateTime(rs.getString("createtime"));
				clientProduct.setStatus(rs.getInt("status"));
				clientProduct.setStatusDate(rs.getString("statusdate"));
				clientProduct.setStatusTime(rs.getString("statustime"));
				clientProduct.setProdDisc(rs.getDouble("proddisc"));
				clientProduct.setProdRsp(rs.getDouble("prodrsp"));
				clientProduct.setProdCycle(rs.getDouble("prodcycle"));
				clientProduct.setProdRef(rs.getString("prodref"));
				clientProduct.setProdData(rs.getString("proddata"));
				clientProduct.setDonorid(rs.getInt("donorid"));
				clientproducts.add(clientProduct);
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying table CLIENTPRODUCTS: " + e.toString());
		}
		return clientproducts;
	}
	public static ArrayList<ClientProduct> getCompanyClientWallets(Connection connection, int clientid, int compid){
		ArrayList<ClientProduct> clientproducts = new ArrayList<ClientProduct>();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENTPRODUCTS WHERE clientid = ? AND compid = ? AND status = ? ORDER BY compid,prodref ASC");
				){
			statement.setInt(1, clientid);
			statement.setInt(2, compid);
			statement.setInt(3, ClientProduct.ACTIVE);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				ClientProduct clientProduct = new ClientProduct();
				clientProduct.setClientId(rs.getInt("clientid"));
				clientProduct.setCompId(rs.getInt("compid"));
				clientProduct.setProdId(rs.getInt("prodid"));
				clientProduct.setCreateDate(rs.getString("createdate"));
				clientProduct.setCreateTime(rs.getString("createtime"));
				clientProduct.setStatus(rs.getInt("status"));
				clientProduct.setStatusDate(rs.getString("statusdate"));
				clientProduct.setStatusTime(rs.getString("statustime"));
				clientProduct.setProdDisc(rs.getDouble("proddisc"));
				clientProduct.setProdRsp(rs.getDouble("prodrsp"));
				clientProduct.setProdCycle(rs.getDouble("prodcycle"));
				clientProduct.setProdRef(rs.getString("prodref"));
				clientProduct.setProdData(rs.getString("proddata"));
				clientProduct.setDonorid(rs.getInt("donorid"));
				clientproducts.add(clientProduct);
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying table CLIENTPRODUCTS: " + e.toString());
		}
		return clientproducts;
	}
	
	/**
	 * 
	 * @param connection
	 * @param compid
	 * @param ref1
	 * @param ref2
	 * @apiNote Search For ClientProducts that have proddata like ref1 and ref2
	 * @return
	 */
	public static ArrayList<ClientProduct> getClientProductsLikeProddata(Connection connection, int compid, String ref1, String ref2){
		ArrayList<ClientProduct> clientproducts = new ArrayList<ClientProduct>();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENTPRODUCTS WHERE compid = ? AND proddata ILIKE ? AND proddata ILIKE ? AND status = ? ORDER BY clientid");
				){
			statement.setInt(1, compid);
			statement.setString(2, "%"+ref1+"%");
			statement.setString(3, "%"+ref2+"%");
			statement.setInt(4, ClientProduct.ACTIVE);
			System.out.println(statement.toString());
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				ClientProduct clientProduct = new ClientProduct();
				clientProduct.setClientId(rs.getInt("clientid"));
				clientProduct.setCompId(rs.getInt("compid"));
				clientProduct.setProdId(rs.getInt("prodid"));
				clientProduct.setCreateDate(rs.getString("createdate"));
				clientProduct.setCreateTime(rs.getString("createtime"));
				clientProduct.setStatus(rs.getInt("status"));
				clientProduct.setStatusDate(rs.getString("statusdate"));
				clientProduct.setStatusTime(rs.getString("statustime"));
				clientProduct.setProdDisc(rs.getDouble("proddisc"));
				clientProduct.setProdRsp(rs.getDouble("prodrsp"));
				clientProduct.setProdCycle(rs.getDouble("prodcycle"));
				clientProduct.setProdRef(rs.getString("prodref"));
				clientProduct.setProdData(rs.getString("proddata"));
				clientProduct.setDonorid(rs.getInt("donorid"));
				clientproducts.add(clientProduct);
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying table CLIENTPRODUCTS: " + e.toString());
		}
		return clientproducts;
	}
	
	public static ClientProduct createClientProduct(Connection connection, ClientProduct clientProduct) {
		clientProduct.setCreateDate(DateUtil.getCurrentCCYYMMDDStr());
		clientProduct.setCreateTime(DateUtil.getCurrentHHMMSSStr());
		clientProduct.setStatus(ClientProduct.ACTIVE);
		clientProduct.setStatusDate(DateUtil.getCurrentCCYYMMDDStr());
		clientProduct.setStatusTime(DateUtil.getCurrentHHMMSSStr());
		try(
				PreparedStatement statement = connection.prepareStatement("INSERT INTO CLIENTPRODUCTS VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				){
			statement.setInt(1, clientProduct.getClientId());
			statement.setInt(2, clientProduct.getCompId());
			statement.setInt(3, clientProduct.getProdId());
			statement.setString(4, clientProduct.getCreateDate());
			statement.setString(5, clientProduct.getCreateTime());
			statement.setInt(6, clientProduct.getStatus());
			statement.setString(7, clientProduct.getStatusDate());
			statement.setString(8, clientProduct.getStatusTime());
			statement.setDouble(9, clientProduct.getProdDisc());
			statement.setDouble(10, clientProduct.getProdRsp());
			statement.setDouble(11, clientProduct.getProdCycle());
			statement.setString(12, clientProduct.getProdRef());
			statement.setString(13, clientProduct.getProdData());
			statement.setInt(13, clientProduct.getDonorid());
			if(statement.executeUpdate()<=0)
				clientProduct = new ClientProduct();
		}catch (Exception e) {
			System.out.println("Error querying UPDATING CLIENTPRODUCTS: " +e.toString());
		}
		return clientProduct;
	}
	
	public static boolean deactiveClientProduct(Connection connection, ClientProduct clientProduct) {
		boolean deactivated = false;
		try(
			PreparedStatement statement = connection.prepareStatement("UPDATE CLIENTPRODUCTS SET status = ?, statusdate = ?, statustime = ? WHERE clientid = ? AND compid = ? AND prodid = ? AND prodref = ?");
		){
			statement.setInt(1, ClientProduct.IN_ACTIVE);
			statement.setString(2, DateUtil.getCurrentCCYYMMDDStr());
			statement.setString(3, DateUtil.getCurrentHHMMSSStr());
			statement.setInt(4, clientProduct.getClientId());
			statement.setInt(5, clientProduct.getCompId());
			statement.setInt(6, clientProduct.getProdId());
			statement.setString(7, clientProduct.getProdRef());
			
			if(statement.executeUpdate()>0)
				deactivated = true;
		}catch (Exception e) {
			System.out.println("Error updating CLIENTPRODUCTS: " + e.toString());
		}
		return deactivated;
	}
	
	public static boolean updateClientProduct(Connection connection, ClientProduct clientProduct) {
		boolean updated = false;
		try(
			PreparedStatement statement = connection.prepareStatement("UPDATE CLIENTPRODUCTS SET status = ?, statusdate = ?, statustime = ?, proddisc = ?, prodrsp = ?, prodcycle = ?, proddata = ? WHERE clientid = ? AND compid = ? AND prodid = ? AND prodref = ? AND donorid = ?");
		){
			statement.setInt(1, clientProduct.getStatus());
			statement.setString(2, DateUtil.getCurrentCCYYMMDDStr());
			statement.setString(3, DateUtil.getCurrentHHMMSSStr());
			statement.setDouble(4, clientProduct.getProdDisc());
			statement.setDouble(5, clientProduct.getProdRsp());
			statement.setDouble(6, clientProduct.getProdCycle());
			statement.setString(7, clientProduct.getProdData());
			statement.setInt(8, clientProduct.getClientId());
			statement.setInt(9, clientProduct.getCompId());
			statement.setInt(10, clientProduct.getProdId());
			statement.setString(11, clientProduct.getProdRef());
			statement.setInt(11, clientProduct.getDonorid());

			if(statement.executeUpdate()>0)
				updated = true;
		}catch (Exception e) {
			System.out.println("Error updating CLIENTPRODUCTS: " + e.toString());
		}
		return updated;
	}


	public static JSONArray toJSONArray(ArrayList<ClientProduct> products) {
		JSONArray json = new JSONArray();
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(int i=0;i<products.size();i++) {
			ClientProduct prod = products.get(i);
			sb.append((i==0 ? "" : ",") + prod.toJSON().toString());
		}
		sb.append("]");
		try {
			json = new JSONArray(sb.toString());
		} catch (Exception e) {
			System.out.println("Error creating json array: " + e.toString());
		}
		return json;
	}

}
