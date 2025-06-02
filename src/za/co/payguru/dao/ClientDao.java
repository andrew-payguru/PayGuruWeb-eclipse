package za.co.payguru.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.json.JSONArray;

import za.co.payguru.model.Client;
import za.co.payguru.model.ClientInvoice;
import za.co.payguru.model.ClientPayment;
import za.co.payguru.model.ClientProduct;
import za.co.payguru.model.CompanyClient;
import za.co.payguru.util.Util;

public class ClientDao {

	public static int getNextClientId(Connection connection) {
		int id = 0;
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT NEXTVAL('clients_clientid_seq') AS clientid");
				){
			ResultSet rs = statement.executeQuery();
			if(rs.next())
				id = rs.getInt("clientid");
			rs.close();
		}catch (Exception e) {
			System.out.println("Error querying sequence: " + e.toString());
		}
		return id;
	}

	public static Client loadClient(Connection connection, int clientid) {
		Client client = new Client();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENTS WHERE clientid = ?");
				){
			statement.setInt(1, clientid);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				client.setClientid(rs.getInt("clientid"));
				client.setClientname(rs.getString("clientname"));
				client.setClientsurname(rs.getString("clientsurname"));
				client.setClientidno(rs.getString("clientidno"));
				client.setClienttelno(rs.getString("clienttelno"));
				client.setClientcellno(rs.getString("clientcellno"));
				client.setClientvatno(rs.getString("clientvatno"));
				client.setClientregno(rs.getString("clientregno"));
				client.setClientemail(rs.getString("clientemail"));
				client.setClientwebsite(rs.getString("clientwebsite"));
				client.setClientphysaddr1(rs.getString("clientphysaddr1"));
				client.setClientphysaddr2(rs.getString("clientphysaddr2"));
				client.setClientphysaddr3(rs.getString("clientphysaddr3"));
				client.setClientphystown(rs.getString("clientphystown"));
				client.setClientphysprov(rs.getString("clientphysprov"));
				client.setClientphyspostcode(rs.getString("clientphyspostcode"));
				client.setClientpostaddr1(rs.getString("clientpostaddr1"));
				client.setClientpostaddr2(rs.getString("clientpostaddr2"));
				client.setClientpostaddr3(rs.getString("clientpostaddr3"));
				client.setClientposttown(rs.getString("clientposttown"));
				client.setClientpostprov(rs.getString("clientpostprov"));
				client.setClientpostcode(rs.getString("clientpostcode"));
				client.setContname(rs.getString("contname"));
				client.setContsurname(rs.getString("contsurname"));
				client.setContemail(rs.getString("contemail"));
				client.setContcellno(rs.getString("contcellno"));
				client.setConttelno(rs.getString("conttelno"));
				client.setClientpayref(rs.getString("clientpayref"));
				client.setClientdeviceno(rs.getString("clientdeviceno"));
				client.setClientinitdate(rs.getString("clientinitdate"));
				client.setClientref(rs.getString("clientref"));
				client.setClientpassword(rs.getString("clientpassword"));
				client.setClientpasswordmillis(rs.getLong("clientpasswordmillis"));
				client.setClientuniqueid(rs.getString("clientuniqueid"));
				client.setClientuniquemillis(rs.getLong("clientuniquemillis"));
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying CLIENTS: " + e.toString());
		}
		return client;
	}
	public static Client loadClientByCellNo(Connection connection, String cellno) {
		Client client = new Client();
		String [] prefixs = ParamDao.getParam("clientcellnumberprefix", connection).split(",");
		StringBuilder query = new StringBuilder();
		for(int i=0;i<prefixs.length;i++) {
			query.append(" OR clientcellno = '"+prefixs[i]+cellno+"'");
			query.append(" OR clientcellno = '"+cellno.replace(prefixs[i], "")+"'");
		}		
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENTS WHERE clientcellno = ?" + query);
				){
			statement.setString(1, cellno);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				client.setClientid(rs.getInt("clientid"));
				client.setClientname(rs.getString("clientname"));
				client.setClientsurname(rs.getString("clientsurname"));
				client.setClientidno(rs.getString("clientidno"));
				client.setClienttelno(rs.getString("clienttelno"));
				client.setClientcellno(rs.getString("clientcellno"));
				client.setClientvatno(rs.getString("clientvatno"));
				client.setClientregno(rs.getString("clientregno"));
				client.setClientemail(rs.getString("clientemail"));
				client.setClientwebsite(rs.getString("clientwebsite"));
				client.setClientphysaddr1(rs.getString("clientphysaddr1"));
				client.setClientphysaddr2(rs.getString("clientphysaddr2"));
				client.setClientphysaddr3(rs.getString("clientphysaddr3"));
				client.setClientphystown(rs.getString("clientphystown"));
				client.setClientphysprov(rs.getString("clientphysprov"));
				client.setClientphyspostcode(rs.getString("clientphyspostcode"));
				client.setClientpostaddr1(rs.getString("clientpostaddr1"));
				client.setClientpostaddr2(rs.getString("clientpostaddr2"));
				client.setClientpostaddr3(rs.getString("clientpostaddr3"));
				client.setClientposttown(rs.getString("clientposttown"));
				client.setClientpostprov(rs.getString("clientpostprov"));
				client.setClientpostcode(rs.getString("clientpostcode"));
				client.setContname(rs.getString("contname"));
				client.setContsurname(rs.getString("contsurname"));
				client.setContemail(rs.getString("contemail"));
				client.setContcellno(rs.getString("contcellno"));
				client.setConttelno(rs.getString("conttelno"));
				client.setClientpayref(rs.getString("clientpayref"));
				client.setClientdeviceno(rs.getString("clientdeviceno"));
				client.setClientinitdate(rs.getString("clientinitdate"));
				client.setClientref(rs.getString("clientref"));
				client.setClientpassword(rs.getString("clientpassword"));
				client.setClientpasswordmillis(rs.getLong("clientpasswordmillis"));
				client.setClientuniqueid(rs.getString("clientuniqueid"));
				client.setClientuniquemillis(rs.getLong("clientuniquemillis"));
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying CLIENTS: " + e.toString());
		}
		return client;
	}
	public static Client loadCompClientByCellNo(Connection connection, int compid, String cellno) {
		Client client = new Client();
		String [] prefixs = ParamDao.getParam("clientcellnumberprefix", connection).split(",");
		StringBuilder query = new StringBuilder();
		for(int i=0;i<prefixs.length;i++) {
			query.append(" OR clientcellno = '"+prefixs[i]+cellno+"'");
			query.append(" OR clientcellno = '"+cellno.replace(prefixs[i], "")+"'");
		}		
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENTS WHERE (clientcellno = ?" + query + ") AND clientid IN (SELECT clientid FROM COMPANYCLIENTS WHERE compid = ? AND status = ?");
				){
			statement.setString(1, cellno);
			statement.setInt(2, compid);
			statement.setString(3, Client.ACTIVE);
			System.out.println(statement);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				client.setClientid(rs.getInt("clientid"));
				client.setClientname(rs.getString("clientname"));
				client.setClientsurname(rs.getString("clientsurname"));
				client.setClientidno(rs.getString("clientidno"));
				client.setClienttelno(rs.getString("clienttelno"));
				client.setClientcellno(rs.getString("clientcellno"));
				client.setClientvatno(rs.getString("clientvatno"));
				client.setClientregno(rs.getString("clientregno"));
				client.setClientemail(rs.getString("clientemail"));
				client.setClientwebsite(rs.getString("clientwebsite"));
				client.setClientphysaddr1(rs.getString("clientphysaddr1"));
				client.setClientphysaddr2(rs.getString("clientphysaddr2"));
				client.setClientphysaddr3(rs.getString("clientphysaddr3"));
				client.setClientphystown(rs.getString("clientphystown"));
				client.setClientphysprov(rs.getString("clientphysprov"));
				client.setClientphyspostcode(rs.getString("clientphyspostcode"));
				client.setClientpostaddr1(rs.getString("clientpostaddr1"));
				client.setClientpostaddr2(rs.getString("clientpostaddr2"));
				client.setClientpostaddr3(rs.getString("clientpostaddr3"));
				client.setClientposttown(rs.getString("clientposttown"));
				client.setClientpostprov(rs.getString("clientpostprov"));
				client.setClientpostcode(rs.getString("clientpostcode"));
				client.setContname(rs.getString("contname"));
				client.setContsurname(rs.getString("contsurname"));
				client.setContemail(rs.getString("contemail"));
				client.setContcellno(rs.getString("contcellno"));
				client.setConttelno(rs.getString("conttelno"));
				client.setClientpayref(rs.getString("clientpayref"));
				client.setClientdeviceno(rs.getString("clientdeviceno"));
				client.setClientinitdate(rs.getString("clientinitdate"));
				client.setClientref(rs.getString("clientref"));
				client.setClientpassword(rs.getString("clientpassword"));
				client.setClientpasswordmillis(rs.getLong("clientpasswordmillis"));
				client.setClientuniqueid(rs.getString("clientuniqueid"));
				client.setClientuniquemillis(rs.getLong("clientuniquemillis"));
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying CLIENTS: " + e.toString());
		}
		return client;
	}
	public static ArrayList<Client> getCompanyClientsLikeCell(Connection connection, int compid, String cellno) {
		ArrayList<Client> clients = new ArrayList<Client>();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENTS WHERE clientid IN (SELECT "
						+ "clientid FROM COMPANYCLIENTS WHERE compid = ?) AND clientcellno ilike ?");
				){
			statement.setInt(1, compid);
			statement.setString(2, "%"+cellno+"%");
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				Client client = new Client();
				client.setClientid(rs.getInt("clientid"));
				client.setClientname(rs.getString("clientname"));
				client.setClientsurname(rs.getString("clientsurname"));
				client.setClientidno(rs.getString("clientidno"));
				client.setClienttelno(rs.getString("clienttelno"));
				client.setClientcellno(rs.getString("clientcellno"));
				client.setClientvatno(rs.getString("clientvatno"));
				client.setClientregno(rs.getString("clientregno"));
				client.setClientemail(rs.getString("clientemail"));
				client.setClientwebsite(rs.getString("clientwebsite"));
				client.setClientphysaddr1(rs.getString("clientphysaddr1"));
				client.setClientphysaddr2(rs.getString("clientphysaddr2"));
				client.setClientphysaddr3(rs.getString("clientphysaddr3"));
				client.setClientphystown(rs.getString("clientphystown"));
				client.setClientphysprov(rs.getString("clientphysprov"));
				client.setClientphyspostcode(rs.getString("clientphyspostcode"));
				client.setClientpostaddr1(rs.getString("clientpostaddr1"));
				client.setClientpostaddr2(rs.getString("clientpostaddr2"));
				client.setClientpostaddr3(rs.getString("clientpostaddr3"));
				client.setClientposttown(rs.getString("clientposttown"));
				client.setClientpostprov(rs.getString("clientpostprov"));
				client.setClientpostcode(rs.getString("clientpostcode"));
				client.setContname(rs.getString("contname"));
				client.setContsurname(rs.getString("contsurname"));
				client.setContemail(rs.getString("contemail"));
				client.setContcellno(rs.getString("contcellno"));
				client.setConttelno(rs.getString("conttelno"));
				client.setClientpayref(rs.getString("clientpayref"));
				client.setClientdeviceno(rs.getString("clientdeviceno"));
				client.setClientinitdate(rs.getString("clientinitdate"));
				client.setClientref(rs.getString("clientref"));
				client.setClientpassword(rs.getString("clientpassword"));
				client.setClientpasswordmillis(rs.getLong("clientpasswordmillis"));
				client.setClientuniqueid(rs.getString("clientuniqueid"));
				client.setClientuniquemillis(rs.getLong("clientuniquemillis"));
				clients.add(client);
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying CLIENTS: " + e.toString());
		}
		return clients;
	}

	public static ArrayList<Client> getPaymentClients(Connection connection, int compid, int bankid, String startDate, String endDate){
		ArrayList<Client> clients = new ArrayList<Client>();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENTS WHERE clientid IN (SELECT clientid FROM CLIENTPAYMENTS WHERE compid = ? AND bankid = ? AND paydate >= ? AND paydate <= ? AND paytransferstatus != ? AND payreversedate = ? order by paydate desc)");
				){
			statement.setInt(1, compid);
			statement.setInt(2, bankid);
			statement.setString(3, startDate);
			statement.setString(4, endDate);
			statement.setInt(5, ClientPayment.TRANSFER_STATUS_DONE);
			statement.setString(6, ClientPayment.DEFAULT_REVERSE_DATE);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				Client client = new Client();
				client.setClientid(rs.getInt("clientid"));
				client.setClientname(rs.getString("clientname"));
				client.setClientsurname(rs.getString("clientsurname"));
				client.setClientidno(rs.getString("clientidno"));
				client.setClienttelno(rs.getString("clienttelno"));
				client.setClientcellno(rs.getString("clientcellno"));
				client.setClientvatno(rs.getString("clientvatno"));
				client.setClientregno(rs.getString("clientregno"));
				client.setClientemail(rs.getString("clientemail"));
				client.setClientwebsite(rs.getString("clientwebsite"));
				client.setClientphysaddr1(rs.getString("clientphysaddr1"));
				client.setClientphysaddr2(rs.getString("clientphysaddr2"));
				client.setClientphysaddr3(rs.getString("clientphysaddr3"));
				client.setClientphystown(rs.getString("clientphystown"));
				client.setClientphysprov(rs.getString("clientphysprov"));
				client.setClientphyspostcode(rs.getString("clientphyspostcode"));
				client.setClientpostaddr1(rs.getString("clientpostaddr1"));
				client.setClientpostaddr2(rs.getString("clientpostaddr2"));
				client.setClientpostaddr3(rs.getString("clientpostaddr3"));
				client.setClientposttown(rs.getString("clientposttown"));
				client.setClientpostprov(rs.getString("clientpostprov"));
				client.setClientpostcode(rs.getString("clientpostcode"));
				client.setContname(rs.getString("contname"));
				client.setContsurname(rs.getString("contsurname"));
				client.setContemail(rs.getString("contemail"));
				client.setContcellno(rs.getString("contcellno"));
				client.setConttelno(rs.getString("conttelno"));
				client.setClientpayref(rs.getString("clientpayref"));
				client.setClientdeviceno(rs.getString("clientdeviceno"));
				client.setClientinitdate(rs.getString("clientinitdate"));
				client.setClientref(rs.getString("clientref"));
				client.setClientpassword(rs.getString("clientpassword"));
				client.setClientpasswordmillis(rs.getLong("clientpasswordmillis"));
				client.setClientuniqueid(rs.getString("clientuniqueid"));
				client.setClientuniquemillis(rs.getLong("clientuniquemillis"));
				clients.add(client);
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying table CLIENTS: " + e.toString());
		}
		return clients;
	}

	public static ArrayList<Client> getClientsByInvoicesProd(int compId, int prodId, String fromDate, String toDate, Connection connection) {
		ArrayList<Client> clients = new ArrayList<Client>();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENTS WHERE clientid IN (SELECT clientid FROM CLIENTINVOICES WHERE compid = ? AND prodid = ? AND paydate >= ? AND paydate <= ?  AND status != ? ORDER BY paydate DESC, invno DESC)");
				){
			fromDate = fromDate.replace("-", "/");
			toDate = toDate.replace("-", "/");
			statement.setInt(1, compId);
			statement.setInt(2, prodId);
			statement.setString(3, fromDate);
			statement.setString(4, toDate);
			statement.setString(5, ClientInvoice.INVOICE_SAVINGS_BALANCE);
			System.out.println(statement.toString());
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				Client client = new Client();
				client.setClientid(rs.getInt("clientid"));
				client.setClientname(rs.getString("clientname"));
				client.setClientsurname(rs.getString("clientsurname"));
				client.setClientidno(rs.getString("clientidno"));
				client.setClienttelno(rs.getString("clienttelno"));
				client.setClientcellno(rs.getString("clientcellno"));
				client.setClientvatno(rs.getString("clientvatno"));
				client.setClientregno(rs.getString("clientregno"));
				client.setClientemail(rs.getString("clientemail"));
				client.setClientwebsite(rs.getString("clientwebsite"));
				client.setClientphysaddr1(rs.getString("clientphysaddr1"));
				client.setClientphysaddr2(rs.getString("clientphysaddr2"));
				client.setClientphysaddr3(rs.getString("clientphysaddr3"));
				client.setClientphystown(rs.getString("clientphystown"));
				client.setClientphysprov(rs.getString("clientphysprov"));
				client.setClientphyspostcode(rs.getString("clientphyspostcode"));
				client.setClientpostaddr1(rs.getString("clientpostaddr1"));
				client.setClientpostaddr2(rs.getString("clientpostaddr2"));
				client.setClientpostaddr3(rs.getString("clientpostaddr3"));
				client.setClientposttown(rs.getString("clientposttown"));
				client.setClientpostprov(rs.getString("clientpostprov"));
				client.setClientpostcode(rs.getString("clientpostcode"));
				client.setContname(rs.getString("contname"));
				client.setContsurname(rs.getString("contsurname"));
				client.setContemail(rs.getString("contemail"));
				client.setContcellno(rs.getString("contcellno"));
				client.setConttelno(rs.getString("conttelno"));
				client.setClientpayref(rs.getString("clientpayref"));
				client.setClientdeviceno(rs.getString("clientdeviceno"));
				client.setClientinitdate(rs.getString("clientinitdate"));
				client.setClientref(rs.getString("clientref"));
				client.setClientpassword(rs.getString("clientpassword"));
				client.setClientpasswordmillis(rs.getLong("clientpasswordmillis"));
				client.setClientuniqueid(rs.getString("clientuniqueid"));
				client.setClientuniquemillis(rs.getLong("clientuniquemillis"));
				clients.add(client);
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying table CLIENTS: " + e.toString());
		}
		return clients;
	}
	
	

	public static ArrayList<String> getClientCellsByProd(int compId, long prodId, Connection connection) {
		ArrayList<String> clientCells = new ArrayList<String>();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT CLIENTS.clientcellno FROM CLIENTS JOIN COMPANYCLIENTS ON (CLIENTS.clientid = COMPANYCLIENTS.clientid) JOIN CLIENTPRODUCTS ON (CLIENTS.clientid = CLIENTPRODUCTS.clientid) WHERE COMPANYCLIENTS.compid = ? AND CLIENTPRODUCTS.prodid = ? AND CLIENTPRODUCTS.status = ? AND COMPANYCLIENTS.status = ?");
				){
			statement.setInt(1, compId);
			statement.setLong(2, prodId);
			statement.setInt(3, ClientProduct.ACTIVE);
			statement.setString(4, CompanyClient.COMP_CLIENT_ACTIVE);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				clientCells.add(rs.getString("clientcellno"));
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying table CLIENTS: " + e.toString());
		}
		return clientCells;
	}
	
	
	public static ArrayList<Client> getClientProductsLikeProddataClients(Connection connection, int compid, String ref1, String ref2) {
		ArrayList<Client> clients = new ArrayList<Client>();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENTS WHERE clientid IN (SELECT clientid FROM CLIENTPRODUCTS WHERE compid = ? AND proddata ILIKE ? AND proddata ILIKE ? AND status = ? ORDER BY clientid)");
		){
			statement.setInt(1, compid);
			statement.setString(2, "%"+ref1+"%");
			statement.setString(3, "%"+ref2+"%");
			statement.setInt(4, ClientProduct.ACTIVE);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				Client client = new Client();
				client.setClientid(rs.getInt("clientid"));
				client.setClientname(rs.getString("clientname"));
				client.setClientsurname(rs.getString("clientsurname"));
				client.setClientidno(rs.getString("clientidno"));
				client.setClienttelno(rs.getString("clienttelno"));
				client.setClientcellno(rs.getString("clientcellno"));
				client.setClientvatno(rs.getString("clientvatno"));
				client.setClientregno(rs.getString("clientregno"));
				client.setClientemail(rs.getString("clientemail"));
				client.setClientwebsite(rs.getString("clientwebsite"));
				client.setClientphysaddr1(rs.getString("clientphysaddr1"));
				client.setClientphysaddr2(rs.getString("clientphysaddr2"));
				client.setClientphysaddr3(rs.getString("clientphysaddr3"));
				client.setClientphystown(rs.getString("clientphystown"));
				client.setClientphysprov(rs.getString("clientphysprov"));
				client.setClientphyspostcode(rs.getString("clientphyspostcode"));
				client.setClientpostaddr1(rs.getString("clientpostaddr1"));
				client.setClientpostaddr2(rs.getString("clientpostaddr2"));
				client.setClientpostaddr3(rs.getString("clientpostaddr3"));
				client.setClientposttown(rs.getString("clientposttown"));
				client.setClientpostprov(rs.getString("clientpostprov"));
				client.setClientpostcode(rs.getString("clientpostcode"));
				client.setContname(rs.getString("contname"));
				client.setContsurname(rs.getString("contsurname"));
				client.setContemail(rs.getString("contemail"));
				client.setContcellno(rs.getString("contcellno"));
				client.setConttelno(rs.getString("conttelno"));
				client.setClientpayref(rs.getString("clientpayref"));
				client.setClientdeviceno(rs.getString("clientdeviceno"));
				client.setClientinitdate(rs.getString("clientinitdate"));
				client.setClientref(rs.getString("clientref"));
				client.setClientpassword(rs.getString("clientpassword"));
				client.setClientpasswordmillis(rs.getLong("clientpasswordmillis"));
				client.setClientuniqueid(rs.getString("clientuniqueid"));
				client.setClientuniquemillis(rs.getLong("clientuniquemillis"));
				clients.add(client);
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying table CLIENTS: " + e.toString());
		}
		return clients;
	}

	public static Client createClient(Connection connection, Client client) {
		try(
				PreparedStatement statement = connection.prepareStatement("INSERT INTO CLIENTS VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				){
			client.setClientid(getNextClientId(connection));
			statement.setInt(1, client.getClientid());
			statement.setString(2, client.getClientname());
			statement.setString(3, client.getClientsurname());
			statement.setString(4, client.getClientidno());
			statement.setString(5, client.getClienttelno());
			statement.setString(6, client.getClientcellno());
			statement.setString(7, client.getClientvatno());
			statement.setString(8, client.getClientregno());
			statement.setString(9, client.getClientemail());
			statement.setString(10, client.getClientwebsite());
			statement.setString(11, client.getClientphysaddr1());
			statement.setString(12, client.getClientphysaddr2());
			statement.setString(13, client.getClientphysaddr3());
			statement.setString(14, client.getClientphystown());
			statement.setString(15, client.getClientphysprov());
			statement.setString(16, client.getClientphyspostcode());
			statement.setString(17, client.getClientpostaddr1());
			statement.setString(18, client.getClientpostaddr2());
			statement.setString(19, client.getClientpostaddr3());
			statement.setString(20, client.getClientposttown());
			statement.setString(21, client.getClientpostprov());
			statement.setString(22, client.getClientpostcode());
			statement.setString(23, client.getContname());
			statement.setString(24, client.getContsurname());
			statement.setString(25, client.getContemail());
			statement.setString(26, client.getContcellno());
			statement.setString(27, client.getConttelno());
			statement.setString(28, client.getClientpayref());
			statement.setString(29, client.getClientdeviceno());
			statement.setString(30, client.getClientinitdate());
			statement.setString(31, client.getClientref());
			statement.setString(32, client.getClientpassword());
			statement.setLong(33, client.getClientpasswordmillis());
			statement.setString(34, client.getClientuniqueid());
			statement.setLong(35, client.getClientuniquemillis());
			if(statement.executeUpdate()<=0)
				client = new Client();
		}catch (Exception e) {
			System.out.println("Error creating CLIENT: " + e.toString());
			client = new Client();
		}
		return client;
	}

	public static Client updateClientBasic(Connection connection, Client client) {
		try(
				PreparedStatement statement = connection.prepareStatement("UPDATE CLIENTS SET clientname = ?, clientsurname = ?, clientcellno = ?, "
						+ "clientemail = ?, clientidno = ?, clienttelno = ? WHERE clientid = ?");
				){
			statement.setString(1, client.getClientname());
			statement.setString(2, client.getClientsurname());
			statement.setString(3, client.getClientcellno());
			statement.setString(4, client.getClientemail());
			statement.setString(5, client.getClientidno());
			statement.setString(6, client.getClienttelno());
			statement.setInt(7, client.getClientid());
			if(statement.executeUpdate()<=0)
				client = new Client();
		}catch (Exception e) {
			System.out.println("Error updating CLIENTS: " + e.toString());
		}
		return client;
	}

	public static StringBuilder getClientsJSON(ArrayList<Client> clients) {

		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(int i=0;i<clients.size();i++) {
			Client client = clients.get(i);
			sb.append((i==0 ? "" : ",") + client.toJsonString());
		}
		sb.append("]");
		return sb;
	}


	public static JSONArray toJSONArray(ArrayList<Client> clients) {
		JSONArray jsonAr = new JSONArray();
		try {
			for(int i=0;i<clients.size();i++) {
				Client client = clients.get(i);
				jsonAr.put(client.toJSON());
			}
		}catch (Exception e) {
			System.out.println("Error creating json array: " + e.toString());
		}
		return jsonAr;
	}

}
